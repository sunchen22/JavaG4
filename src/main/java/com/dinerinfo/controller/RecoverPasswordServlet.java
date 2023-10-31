package com.dinerinfo.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dinerinfo.entity.DinerInfo;
import com.dinerinfo.service.DinerInfoServiceImpl;
import com.dinerinfo.service.ResetPasswordMailService;


@WebServlet("/RecoverPasswordServlet")
public class RecoverPasswordServlet extends HttpServlet {

	private DinerInfoServiceImpl dinerInfoServiceImpl;

	@Override
	public void init() throws ServletException {
		dinerInfoServiceImpl = new DinerInfoServiceImpl(); // 實做一個方法物件
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//			=========================== 忘記密碼 ======================================================	

		if ("resetPassword".equals(action)) { // 如果接收到的是insert，代表來自 register-form.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			DinerInfo dinerInfoReset = new DinerInfo(); // 創建一個 dinerInfo 對象，來儲存註冊者輸入的資料

			String dinerTaxID = req.getParameter("dinerTaxID");
			String dinerTaxIDReg = "^[(0-9)]{8}$";

			if (dinerTaxID == null || dinerTaxID.trim().length() == 0) {
				errorMsgs.add("商家帳號 : 請勿空白");
			} else if (!dinerTaxID.trim().matches(dinerTaxIDReg)) {
				errorMsgs.add("商家帳號 : 只能是8個數字");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				DinerInfo oldDiner = dinerInfoServiceImpl.getDinerInfoByDinerTaxID(dinerTaxID);
				if (oldDiner == null) {
					errorMsgs.add("商家統編 : 查無此帳號");
				}
			}

			dinerInfoReset.setDinerTaxID(dinerTaxID); // 存下註冊者輸入的資訊

			String dinerEmail = req.getParameter("dinerEmail");
			String dinerEmailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
			if (dinerEmail == null || dinerEmail.trim().length() == 0) {
				errorMsgs.add("商家Email : 請勿空白");
			} else if (!dinerEmail.trim().matches(dinerEmailReg)) {
				errorMsgs.add("商家Email : 輸入的不是有效的Email地址 ");
			} else {
				// 比對資料庫第一次 : 記錄錯誤選項
				DinerInfo oldDiner = dinerInfoServiceImpl.getDinerInfoByDinerTaxID(dinerTaxID); // 容器引入使用者在資料庫裡的舊資料
				String oldEmail = oldDiner.getDinerEmail();
				if (!oldEmail.equals(dinerEmail)) {
					errorMsgs.add("商家Email : Email填寫錯誤");
				}
			}
			dinerInfoReset.setDinerEmail(dinerEmail); // 存下註冊者輸入的資訊

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("dinerInfo", dinerInfoReset); // 含有輸入格式錯誤的dinerInfo物件,也存入req
																// 這樣重新登錄的時候，填過的資料就不會消失
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/login/forget-password.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.確認資料無誤後，開始重設密碼 *****************************************/
			
			DinerInfo oldDiner = dinerInfoServiceImpl.getDinerInfoByDinerTaxID(dinerTaxID);  // 找出舊資料

			HttpSession session = req.getSession();
			session.setAttribute("dinerInfo", oldDiner);
			
			String tempPassword = UUID.randomUUID().toString().substring(0, 8); // 生成8位隨機密碼
			// 使用UUID : 通用唯一識別碼
			
			oldDiner.setDinerPassword(tempPassword);
			//其實可以設置密碼過期時間，目前先不做
			
//			oldDiner.setDinerStatus("reset");
////			這裡可以改會員狀態，讓他在拿到密碼後第一次登入，一定要去更改資訊
			String dinerName = oldDiner.getDinerName();
			
			String subject = "樓頂揪樓咖-商家後臺密碼重設";
			String messageText = "親愛的商業夥伴 " + dinerName + "，您申請的重設密碼已經通過囉~為您送上您的新密碼  "+ tempPassword +"  ，請盡速重新登入，並修改您的密碼";
			
			ResetPasswordMailService resetPasswordMailService = new ResetPasswordMailService();
			resetPasswordMailService.sendMail(dinerEmail, subject, messageText);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/login/resetPasswordSuccess.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交 registerSuccess.html
			successView.forward(req, res);


		}
		
		

	}
}
