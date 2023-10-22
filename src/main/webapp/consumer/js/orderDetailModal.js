/**
 * 
 */

function openOrderDetailModal(productID) {
	$.ajax({
		url: contextPath + "/GroupOrder.do?action=openOrderDetailModal&productID=" + productID,  // 資料請求的網址
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		// data: ,   // 將物件資料(不用雙引號) 傳送到指定的 url
		contentType: "application/json",
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		beforeSend: function () {     // 在 request 發送之前執行
			// 阻止使用者重複發出請求
		},
		success: function (data) {    // request 成功取得回應後執行
			// console.log(data);
			
			if (data.length == 0) {

			} else {
				$("#modal_product_name").html(data.productName);
				$("#modal_product_remark").html(data.productRemark);
				$("#modal_product_price").html(data.productPrice);
				$("#modal_product_img_1 img").attr("src", `${contextPath}/GroupOrderDinerImage?entity=Product&id=${productID}`);
				$("#modal_subtotal").html(data.productPrice);
				$("#modal_productID").attr("data-productid", productID);
				// console.log(data.productID);

				let list_html = "";
				let x = 1;
				$.each(data.varyTypes, function (index, item) {
					
					list_html += `<h5 class="modal_vary_type">${index}</h5>`;
					console.log("index " + index);
					console.log("item " + item);
					list_html += `<div class="form-check">
									<input class="form-check-input" type="radio" name="${index}" id="productVaryIDNone${x}" value="0" checked required>
									<label class="form-check-label" for="productVaryIDNone${x}">無</label>
				  				</div>`
					x += 1;
					$.each(item, function (i, it) {
						console.log(i);
						console.log(it);
						list_html += `<div class="form-check">
										<input class="form-check-input" type="radio" name="${index}" id="productVaryID${it.productVaryID}" value="${it.productVaryID}">
										<label class="form-check-label" for="productVaryID${it.productVaryID}">${it.productVaryDes} +${it.productVaryPrice}元</label>
									  </div>`;
					});
	
				});

				$("#modal_vary_types").html(list_html);
			}
		},
		error: function (xhr) {  // request 發生錯誤的話執行
			console.log(xhr);
		},
		complete: function (xhr) {  // request 完成之後執行(在 success / error 事件之後執行)
			console.log(xhr);
		}
	});
}

function calculateSubtotal(data) {
	$.ajax({
		url: contextPath + "/GroupOrder.do?action=calculateSubtotal",  // 資料請求的網址
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		data: data,  // 將物件資料(不用雙引號) 傳送到指定的 url
		contentType: "application/json",
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		beforeSend: function () {     // 在 request 發送之前執行
			// 阻止使用者重複發出請求
			$("#modal_product_quantity").attr("disabled", true);
			$('#modal_vary_types input[type="radio"]').attr("disabled", true);
		},
		success: function (subtotal) {    // request 成功取得回應後執行
			$("#modal_subtotal").html(subtotal);
		},
		error: function (xhr) {  // request 發生錯誤的話執行
			console.log(xhr);
		},
		complete: function (xhr) {  // request 完成之後執行(在 success / error 事件之後執行)
			console.log(xhr);
			$("#modal_product_quantity").attr("disabled", false);
			$('#modal_vary_types input[type="radio"]').attr("disabled", false);
		}
	});
}

function getCheckedVariesAndQuantity() {
	// Find all checked radio buttons
	let checkedRadios = $('input[type="radio"]:checked');
    
	// Log the values of all checked radios
	let checkedValues = checkedRadios.map(function() {
		return parseInt($(this).val());
	}).get();
	
	// Get product ID
	let productID = $("#modal_productID").attr("data-productid");

	// Get quantity value
	let quantity = parseInt($('#modal_product_quantity').val());

	// Data to send with AJAX request
	let obj = {"productID": productID, "quantity": quantity, "productVaryIDList": checkedValues};
	return JSON.stringify(obj);
}

$(function () {
	
	$("div.product").on("click", function() {
		console.log($(this));
		$("#modal_form")[0].reset();
		// $("#modal_product_quantity").val("1");

		let productID = $(this).find("a.stretched-link").attr("data-productid");
		console.log(productID);

		openOrderDetailModal(productID);

		// $("#modal_vary_types").on("change", 'input[type="radio"]', function() {
		// 	// if ($("#order_detail_modal").hasClass("show")) {
		// 		let data = getCheckedVariesAndQuantity(productID);
		// 		calculateSubtotal(data);
		// 	// }
		// });


	
		// $('#modal_product_quantity').on("change", function() {
		// 	// if ($("#order_detail_modal").hasClass("show")) {
		// 		let data = getCheckedVariesAndQuantity(productID);
		// 		calculateSubtotal(data);
		// 	// }
		// });

	});

	$(document).on("change", $("#modal_form"),function() {
		if ($("#order_detail_modal").hasClass("show")) {
			let data = getCheckedVariesAndQuantity();
			console.log(data);
			calculateSubtotal(data);
		}
	});

	$('#modal_product_quantity').on('keydown', function(event) {
		if (event.which === 13) {
		  event.preventDefault(); // Prevent form submission on Enter key press
		}
	  });

});