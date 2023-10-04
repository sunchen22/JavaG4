/**
 * 
 */

//function search(query){
//	$.ajax({
//        url: "https://notes.webmix.cc/ajax/teach/api/list.php",  // 資料請求的網址
//        type: "GET",                  // GET | POST | PUT | DELETE | PATCH
//        data: query,   // 將物件資料(不用雙引號) 傳送到指定的 url
//        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
//        beforeSend: function () {     // 在 request 發送之前執行
//            $("ul.task_list").html('<li style="text-align: center;"><i class="fas fa-spinner fa-spin fa-3x"></i></li>');
//        },
//        success: function (data) {    // request 成功取得回應後執行
//            // console.log(data);
//            // console.log(data.length);
//            if (data.length == 0) {
//                $("ul.task_list").html("")
//            } else {
//                let list_html = "";
//                $.each(data, function (index, item) {
//                    // console.log(index + " " + item);
//                    let star_on = ["", "", "", "", ""]
//                    for (let i = 1; i <= item.star; i++) {
//                        star_on[i - 1] = "-on";
//                    }
//                    list_html += `
//                            <li data-id="${item.item_id}" data-sort="${item.sort}" data-star="${item.star}">
//                                <div class="item_flex">
//                                    <div class="left_block">
//                                        <div class="btn_flex">
//                                            <button type="button" class="btn_up">往上</button>
//                                            <button type="button" class="btn_down">往下</button>
//                                        </div>
//                                    </div>
//                                    <div class="middle_block">
//                                    <div class="star_block">
//                                        <span class="star ${star_on[0]}" data-star="1"><i class="fas fa-star"></i></span>
//                                        <span class="star ${star_on[1]}" data-star="2"><i class="fas fa-star"></i></span>
//                                        <span class="star ${star_on[2]}" data-star="3"><i class="fas fa-star"></i></span>
//                                        <span class="star ${star_on[3]}" data-star="4"><i class="fas fa-star"></i></span>
//                                        <span class="star ${star_on[4]}" data-star="5"><i class="fas fa-star"></i></span>
//                                    </div>
//                                        <p class="para">${item.name}</p>
//                                        <input type="text" class="task_name_update -none" placeholder="更新待辦事項…" value="${item.name}">
//                                    </div>
//                                    <div class="right_block">
//                                        <div class="btn_flex">
//                                            <button type="button" class="btn_update">更新</button>
//                                            <button type="button" class="btn_delete">移除</button>
//                                        </div>
//                                    </div>
//                                </div>
//                            </li>
//                        `;
//
//                    $("ul.task_list").html(list_html);
//                });
//            }
//        },
//        error: function (xhr) {  // request 發生錯誤的話執行
//            console.log(xhr);
//        },
//        complete: function (xhr) {  // request 完成之後執行(在 success / error 事件之後執行)
//            console.log(xhr);
//        }
//    });
//}


function init() {
	
	// Filter synchronization between tabs.
	$("ul.filter_by input").on("change", function() {
		console.log($(this));
		let className = $(this).attr("class").split(' ')[1];
		console.log($(this).attr("class"));
		console.log($(className));
		$("." + className).prop("checked", $(this).prop("checked"));
	});
	
	// Sorter synchronization between tabs.
	$("div.order_by select.form-select").change(function() {
		// Get the selected option value
		let selectedValue = $(this).val();
		console.log(selectedValue);
		// Find other select elements in divs with class "order_by"
		if (selectedValue == "progress" || selectedValue == "deadline") {
			$(".order_by select.form-select").not(this).val("distance");
		} else {
			$(".order_by select.form-select").not(this).val(selectedValue);
		}
	});

}




$(function() {
	init();

	// After the page is loaded, before user enters any query conditions.
	// search();

	$("#btn_search").on("click", function() {
		console.log("keyword: ", $("div.search_bar input.input_keyword").val());
		console.log("address: ", $("div.search_bar input.input_address").val());
		console.log("tab on: ", $("#nav_searchgrouporder_tab").hasClass("active") ? $("#nav_searchgrouporder_tab").text() : $("#nav-searchdiner-tab").text());
		
		console.log("my building only checked:", $("#g_my_building_only").prop("checked"));
		console.log("my building only checked:", $("#d_my_building_only").prop("checked"));
		console.log("achived only checked:", $("input.achived_only").prop("checked"));
		console.log("has group only checked:", $("input.has_group_only").prop("checked"));
		console.log("now open only checked:", $("input.now_open_only").prop("checked"));

		console.log($("#d_type_food").prop("checked"));
		console.log($("#g_type_food").prop("checked"));
		$("li.filter_by input")


	});


});