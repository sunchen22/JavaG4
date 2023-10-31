// $(function () {
//     $("#creat_group_order_btn").click(function() {
//         // Get the openTime and closeTime values
//         let openTime = $("#open_time").html();
//         let closeTime = $("#close_time").html();
        
//         // Assuming you want to add options with 30-minute intervals between openTime and closeTime
//         let currentTime = new Date("2023-10-30 " + openTime);
//         currentTime.setHours(currentTime.getHours() + 8); // Adjust for GMT+8 timezone
//         const select = $("#group_order_submit_time");

//         // Clear existing options (if needed)
//         select.empty();

//         let end = new Date("2023-10-30 " + closeTime);
//         end.setHours(currentTime.getHours() + 8);  // Adjust for GMT+8 timezone

//         while (currentTime <= end) {
//             let optionValue = currentTime.toISOString().slice(0, 19).replace("T", " ");
//             let optionText = optionValue;
//             select.append($("<option>").attr('value', optionValue).text(optionText));

//             // Increment time by 30 minutes
//             currentTime.setMinutes(currentTime.getMinutes() + 30);
//         }
//     });
// });

$(function () {
    $("#creat_group_order_btn").click(function() {
        // How to define a Date by a string argument
		// let newATime = new Date("2023-10-30 " + "22:22");
		// console.log("let newATime = new Date(\"2023-10-30 \" + \"22:22\");\n  newATime: ", newATime);
		
		// Get the openTime and closeTime values from HTML
		let openTime = $("#open_time").html();
        let closeTime = $("#close_time").html();
        
        // The <select> tag to add <option> tags
        let select = $("#group_order_submit_time");

		// Get current time
		let rightNow = new Date();
		
		// Convert open time to Date
		let todayYear = rightNow.getFullYear();
		let todayMonth = rightNow.getMonth() + 1;
		let todayDay = rightNow.getDate();
		if (todayDay < 10) {
			todayDay = "0" + todayDay;
		}
		let formattedTodayDate = `${todayYear}-${todayMonth}-${todayDay} `;
		let open = new Date(formattedTodayDate + openTime);
		
		console.log("current time: ", rightNow);
		let start;
		// If current time is later than open time, use current time +30min as start of time slot
		if (rightNow >= open) {
			if (rightNow.getMinutes() >= 30) {
	            rightNow.setHours(rightNow.getHours() + 1, 30, 0, 0); // 9:43 --> 10:30
	        } else {
	            rightNow.setHours(rightNow.getHours() + 1, 0, 0, 0); //  9:24 --> 10:00 
	        }	
			start = rightNow;
		// If current time is earlier than open time, use open time +30min as start of time slot
		} else {
			if (open.getMinutes() >= 30) {
	            open.setHours(open.getHours() + 1, 30, 0, 0); // 9:43 --> 10:30
	        } else {
	            open.setHours(open.getHours() + 1, 0, 0, 0); //  9:24 --> 10:00 
	        }	
			start = open;
		}
		
		console.log("start: ", start);
		
        // Clear existing options
        select.empty();
		
		// End of time slot is close time -1hour
        let end = new Date(formattedTodayDate + closeTime);
        end.setHours(end.getHours() - 1, 0, 0, 0);
        console.log("end: ", end);
        

        while (start <= end) {
            // Format the time slot manually
            const hour = String(start.getHours()).padStart(2, '0');
            const minute = String(start.getMinutes()).padStart(2, '0');
            const second = '00'; // Set seconds to '00'
            const formattedSlot = `${formattedTodayDate}${hour}:${minute}:${second}`;
            
            select.append($("<option>").attr('value', formattedSlot).text(formattedSlot));

            // Increment time by 30 minutes
            start.setMinutes(start.getMinutes() + 30);
        }
    });
});