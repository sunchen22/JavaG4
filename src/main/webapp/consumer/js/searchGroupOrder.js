/**
 * 
 */

function search(requestData) {
	$.ajax({
		url: contextPath + "/GroupOrder.do?action=getAll",  // 資料請求的網址
		type: "POST",                  // GET | POST | PUT | DELETE | PATCH
		data: JSON.stringify(requestData),   // 將物件資料(不用雙引號) 傳送到指定的 url
		contentType: "application/json",
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		beforeSend: function () {     // 在 request 發送之前執行
			// 阻止使用者重複發出請求
		},
		success: function (data) {    // request 成功取得回應後執行
			// console.log(data);
			// console.log(data.length);
			if (data.length == 0) {

			} else {
				let list_html = "";
				$.each(data, function (index, item) {
					console.log(index + " " + item);
					list_html += `<div class="card">
					<div class="row g-0 align-items-center">
						<div class="col-4 ">
							<img src="${contextPath}/GroupOrderDinerImage?entity=GroupOrder&id=${item.groupOrderID}" class="card-img" alt="...">
						</div>
						<div class="col-8">
							<div class="card-body">
								<h5 class="card-title">${item.buildingName}</h5>
								<ul class="list-unstyled card-text">
									<li>大樓地址：${item.buildingAddress}</li>
									<li>${item.dinerName}</li>
									<li class="list-inline-item"><span class="badge fs-6 rounded-pill bg-secondary">

									${item.dinerType == 'M' ? '<i class="fa-solid fa-utensils"></i>' : (item.dinerType=='D' ? '<i class="fa-solid fa-mug-saucer"></i>' : '<i class="fa-solid fa-utensils"></i><i class="fa-solid fa-mug-saucer"></i>')}
									</span>
									</li>
									<li class="list-inline-item"><span class="badge fs-6 rounded-pill bg-secondary"><i class="fa-solid fa-star"></i>${item.dinerRating.toFixed(1)}</span>
									</li><li>主揪：${item.userNickName}</li>
									<li class="list-inline-item">成團條件：${item.dinerOrderThreshold}元 </li>
									<li class="list-inline-item">成團狀態：${item.orderStatus=='1'? '未達成團條件' : '已達成團條件'}</li>
									<li>付款截止時間：${item.groupOrderSubmitTime}</li>
									<div class="d-flex justify-content-end"><a class="btn btn-dark" href=${contextPath}/GroupOrder.do?action=getOne&groupOrderID=${item.groupOrderID}>查看大樓揪團詳情</a>
									</div>
								</ul>
							</div>
						</div>
					</div>
				</div>`
				});
				$("#group_order_results").html(list_html);
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


function inputSync() {
	// Filter synchronization between tabs.
	$("ul.filter_by input").on("change", function () {
		// Get the selected checkbox
		let className = $(this).attr("class").split(' ')[1];
		// console.log($(className));
		// Find the other checkbox welements ith the same class.
		$("." + className).prop("checked", $(this).prop("checked"));
	});

	// Sorter synchronization between tabs.
	$("div.order_by select.form-select").change(function () {
		// Get the selected option value
		let selectedValue = $(this).val();
		console.log(selectedValue);
		// Find other select elements in divs with class "order_by."
		if (selectedValue == "progress" || selectedValue == "deadline") {
			$(".order_by select.form-select").not(this).val("distance");
		} else {
			$(".order_by select.form-select").not(this).val(selectedValue);
		}
	});

}

function getRequestData() {
	// console.log("keyword: ", $("div.search_bar input.input_keyword").val());
	// console.log("address: ", $("div.search_bar input.input_address").val());
	// console.log("tab on: ", $("#nav_searchgrouporder_tab").hasClass("active") ? $("#nav_searchgrouporder_tab").attr("id") : $("#nav_searchdiner_tab").attr("id"));

	// console.log("my building only checked:", $("#g_my_building_only").prop("checked"));
	// console.log("my building only checked:", $("#d_my_building_only").prop("checked"));
	// console.log("achived only checked:", $("input.achived_only").prop("checked"));
	// console.log("has group only checked:", $("input.has_group_only").prop("checked"));
	// console.log("now open only checked:", $("input.now_open_only").prop("checked"));

	// console.log($("input.type_food").prop("checked"));
	// console.log($("input.type_drinks").prop("checked"));
	// console.log($("input.type_mixed").prop("checked"));

	// console.log($("div.order_by select.form-select").val());

	let requestData = {
		keyword: $("div.search_bar input.input_keyword").val(),
		address: $("div.search_bar input.input_address").val(),
		target: $("#nav_searchgrouporder_tab").hasClass("active") ? $("#nav_searchgrouporder_tab").attr("id") : $("#nav_searchdiner_tab").attr("id"),
		myBuildingOnly: $("#g_my_building_only").prop("checked"),
		achivedOnly: $("input.achived_only").prop("checked"),
		hasGroupOnly: $("input.has_group_only").prop("checked"),
		nowOpenOnly: $("input.now_open_only").prop("checked"),
		typeFood: $("input.type_food").prop("checked"),
		typeDrinks: $("input.type_drinks").prop("checked"),
		typeMixed: $("input.type_mixed").prop("checked"),
		orderBy: $("div.order_by select.form-select").val()
	};
	// console.log(requestData);
	return requestData;
}


$(function () {
	inputSync();

	// After the page is loaded, before user enters any query conditions.
	// search(getRequestData());

	$("#btn_search").on("click", function () {

		search(getRequestData());

	});


});