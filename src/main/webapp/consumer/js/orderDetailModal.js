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
				$("#modal_product_img_1 img").attr("src", `${contextPath}/GroupOrderDinerImage?entity=Product&id=${productID}&no=1`);
				$("#modal_product_img_2 img").attr("src", `${contextPath}/GroupOrderDinerImage?entity=Product&id=${productID}&no=2`);
				$("#modal_product_img_3 img").attr("src", `${contextPath}/GroupOrderDinerImage?entity=Product&id=${productID}&no=3`);
				$("#modal_subtotal").html(data.productPrice);
				$("#modal_productID").val(productID);
				// console.log(data.productID);

				let list_html = "";

				let x = 1;
				$.each(data.varyTypes, function (index, item) {

					list_html += `<h5 class="modal_vary_type">${index}</h5>`;
					console.log("index " + index);
					console.log("item " + item);


					$.each(item, function (i, it) {
						let isFirstOption = true;
						console.log(i);
						console.log(it);
						list_html += `<div class="form-check">
										<input class="form-check-input" type="radio" name="varyTypeCount${x}" id="productVaryID${it.productVaryID}" value="${it.productVaryID}" required>
										<label class="form-check-label" for="productVaryID${it.productVaryID}">${it.productVaryDes} +${it.productVaryPrice}元</label>
									  </div>`;
						if (isFirstOption) {
							$(`#productVaryID${it.productVaryID}`).prop('checked', true);
							isFirstOption = false;
						}
					});
					x += 1;

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
	let checkedValues = checkedRadios.map(function () {
		return parseInt($(this).val());
	}).get();

	// Get product ID
	let productID = $("#modal_productID").val();

	// Get quantity value
	let quantity = parseInt($('#modal_product_quantity').val());

	// Data to send with AJAX request
	let obj = { "productID": productID, "quantity": quantity, "productVaryIDList": checkedValues };
	return JSON.stringify(obj);
}

function addToCart(form, groupOrderID, dinerID) {
	$.ajax({
		url: contextPath + "/GroupOrder.do?action=addToCart",  // 資料請求的網址
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		data: form.serialize(),  // 將物件資料(不用雙引號) 傳送到指定的 url
		// contentType: "application/json",
		// dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		beforeSend: function () {     // 在 request 發送之前執行
			// 阻止使用者重複發出請求
			$("#add_to_cart").attr("disabled", true);
		},
		success: function () {    // request 成功取得回應後執行
			$("#order_detail_modal").modal("hide");
			// $("#shopping_cart").offcanvas("show");
			
			openCart(groupOrderID, dinerID);
		},
		error: function (xhr) {  // request 發生錯誤的話執行
			console.log(xhr);
		},
		complete: function (xhr) {  // request 完成之後執行(在 success / error 事件之後執行)
			console.log(xhr);
			$("#add_to_cart").attr("disabled", false);
		}
	});
}

function openCart(groupOrderID, dinerID) {
	$.ajax({
		url: contextPath + "/GroupOrder.do?action=openCart&groupOrderID=" + groupOrderID + "&dinerID=" + dinerID,  // 資料請求的網址
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		// data:,  // 將物件資料(不用雙引號) 傳送到指定的 url
		contentType: "application/json",
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		beforeSend: function () {     // 在 request 發送之前執行
			// 阻止使用者重複發出請求
			$("#open_cart_btn").addClass("disabled");
		},
		success: function (data) {    // request 成功取得回應後執行
			// console.log(data);
			if (data.length > 0) {
				let list_html = "";
				let total = 0;
				$.each(data, function (index, item) {
					console.log("index " + index);
					console.log("item " + item);
					console.log("item.productName " + item.productName);
					
					list_html += `<li class="list-group-item px-1">
									<div class="row">
										<div class="col-5">
											<h6 class="mb-0">${item.productName}</h6>`;
					$.each(item.productVaryDess, function (innerIndex, innerItem) {
						console.log("innerIndex " + innerIndex);
						console.log("innerItem " + innerItem);
						list_html += `<p class="mb-0 ms-2 text-muted">${innerItem}</p>`;
					});

					list_html += `			
								</div>
								<div class="col-4">
									<div class="input-group">
										<input type="number" class="form-control form-control-sm disabled"
											min="1" value="${item.quantity}" disabled>
										
									</div>
								</div>
								<div class="col-3">
									<span class="fs-6">${item.productAndVaryPrice}元</span>
								</div>
							</div>
						</li>`;

					total += item.productAndVaryPrice;
				});

				$("#shopping_cart ul.list-group").html(list_html);
				$("#cart_total").html("總計" + total + "元");
				
				$("#shopping_cart").offcanvas("show");
			} else {
				alert("購物車是空的！");
			}

		},
		error: function (xhr) {  // request 發生錯誤的話執行
			console.log(xhr);
			
		},
		complete: function (xhr) {  // request 完成之後執行(在 success / error 事件之後執行)
			console.log(xhr);
			$("#open_cart_btn").removeClass("disabled");
		}
	});
}

$(function () {

	$("div.product").on("click", function () {
		console.log($(this));
		$("#modal_form")[0].reset();
		// $("#modal_product_quantity").val("1");

		let productID = $(this).find("a.stretched-link").attr("data-productid");
		console.log(productID);

		openOrderDetailModal(productID);

	});

	$(document).on("change", $("#modal_form"), function () {
		if ($("#order_detail_modal").hasClass("show")) {
			let data = getCheckedVariesAndQuantity();
			console.log(data);
			calculateSubtotal(data);
		}
	});

	$('#modal_product_quantity').on('keydown', function (event) {
		if ((!event.which >= 48) || !(event.which <= 57)) {  // If not numbers (0-9)
			event.preventDefault(); // Prevent the key presses
		}
	});


	$("#share_link_button").click(function () {
		// Prevent the default <a> click action
		event.preventDefault();

		// Get the current URL
		var currentURL = window.location.href;

		// Create an input element to temporarily hold the URL
		var $tempInput = $('<input>');

		// Append the input element to the body
		$('body').append($tempInput);

		// Set the value of the input element to the current URL
		$tempInput.val(currentURL);

		// Select the text in the input
		$tempInput.select();

		// Copy the selected text to the clipboard
		document.execCommand('copy');

		// Remove the temporary input element
		$tempInput.remove();

		// Alert the user
		alert("已複製連結：\n" + currentURL);
	});

	$("#add_to_cart").click(function () {
		let form = $("#modal_form");
		let valid = form[0].reportValidity();
		if (valid) {
			let groupOrderID = $("#open_cart_btn").attr("data-grouporderid");
			let dinerID = $("#open_cart_btn").attr("data-dinerid");
			addToCart(form, groupOrderID, dinerID);
		}

	});

	$("#open_cart_btn").click(function (event) {
		event.preventDefault();
		let groupOrderID = $(this).attr("data-grouporderid");
		let dinerID = $(this).attr("data-dinerid");
		openCart(groupOrderID, dinerID);
	});

});