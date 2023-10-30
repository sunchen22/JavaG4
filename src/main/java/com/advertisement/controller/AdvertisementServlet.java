package com.advertisement.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.advertisement.entity.Advertisement;
import com.advertisement.service.AdvertisementServiceImpl;
import com.dinerinfo.entity.DinerInfo;
import com.dinerinfo.service.DinerInfoServiceImpl;



public class AdvertisementServlet extends HttpServlet {
	// 一個 servlet 實體對應一個 service 實體
	private AdvertisementServiceImpl advertisementServiceimpl;
//	private DinerInfoServiceImpl dinerInfoServiceImpl;

	@Override
	public void init() throws ServletException {
		advertisementServiceimpl = new AdvertisementServiceImpl();
//		dinerInfoServiceImpl = new DinerInfoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
	

//		=========================== 廣告上架申請 ======================================================	

		if ("requestAD".equals(action)) { // 如果接收到的是insert，代表來自 register-form.jsp 的請求
          System.out.println("這裡進入servlet========47=============");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Advertisement inputAd = new Advertisement(); // 創建一個 dinerInfo 對象，來儲存註冊者輸入的資料
			int dinerID = Integer.parseInt(req.getParameter("dinerID")); // 獲得傳入請求的使用者id
//			String dinerTaxID = req.getParameter("dinerTaxID");

			String advertisementName = req.getParameter("advertisementName");
			String advertisementNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9')]+(\\s[(\u4e00-\u9fa5)(a-zA-Z0-9')]{1,15}+)?$";
			if (advertisementName == null || advertisementName.trim().length() == 0) {
				errorMsgs.add("廣告名稱 : 請勿空白");
			} else if (!advertisementName.trim().matches(advertisementNameReg)) {
				errorMsgs.add("廣告名稱 : 只能是中、英文字母、數字和'符號,中間可加一個空格，字串長度必需在1到15之間");
			}

			String UpTime = (req.getParameter("UpTime") + " 00:00:00");
			// 時間轉換器
			Timestamp advertisementUpTime = Timestamp.valueOf(UpTime);

			String DownTime = (req.getParameter("DownTime") + " 23:59:59");
			// 時間轉換器
			Timestamp advertisementDownTime = Timestamp.valueOf(DownTime);
			
			int advertisementDuringTime = Integer.parseInt(req.getParameter("advertisementDuringTime"));

//			DinerInfo oldDiner = advertisementServiceimpl.getDinerInfoByDinerID(dinerID);
//			inputAd.setDinerid(oldDiner);
			inputAd.setAdvertisementName(advertisementName);
			inputAd.setAdvertisementUpTime(advertisementUpTime);
			inputAd.setAdvertisementDownTime(advertisementDownTime);
			inputAd.setAdvertisementDuringTime(advertisementDuringTime);

			Part advertisementBlobPart = req.getPart("advertisementBlob");
//
//			if (advertisementBlobPart != null && advertisementBlobPart.getSize() > 0) {
//				try {
//					InputStream in = advertisementBlobPart.getInputStream();
//					byte[] adInputBlob = new byte[(int) advertisementBlobPart.getSize()];
//					adInputBlob = in.readAllBytes();
//					inputAd.setAdvertisementBlob(adInputBlob);
//
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			
//			Part filePart = req.getPart("advertisementBlob"); // 取得上傳的文件
//			if (filePart != null && filePart.getSize() > 0) {
//				InputStream fileContent = filePart.getInputStream();
//				
//				byte[] fileData = new byte[(int) filePart.getSize()];
//				fileContent.read(fileData);

//			Part advertisementBlobPart  = req.getPart("advertisementBlob");
//			
//			byte[] adInputBlob = null;
//			try {
//				InputStream in = advertisementBlobPart.getInputStream();
//				adInputBlob = in.readAllBytes();
//				inputAd.setAdvertisementBlob(adInputBlob);
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("advertisement", inputAd);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/help/request-Ad-placement.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 *****************************************/
//			HttpSession session = req.getSession();
//			List<Advertisement> adList = advertisementServiceimpl.getAdvertisementsByDinerID(dinerID);

//			Advertisement ad = new Advertisement();
//			ad.setAdvertisementName(advertisementName);
//			ad.setAdvertisementUpTime(advertisementUpTime);
//			ad.setAdvertisementDownTime(advertisementDownTime);
//			ad.setAdvertisementDuringTime(advertisementDuringTime);
			
//			DinerInfo dinerInfo = advertisementServiceimpl.getDinerInfoByDinerID(dinerID);
//			inputAd.setDinerid(dinerInfo);
			
			System.out.println("是這個dinerID:"+dinerID);
			System.out.println("是這個inputAd:"+inputAd);
//			System.out.println("是這個dinerTaxID:"+dinerTaxID);
			
			byte[] adBlob = null;
			try {
				InputStream in = advertisementBlobPart.getInputStream();
				adBlob = in.readAllBytes();
				inputAd.setAdvertisementBlob(adBlob);

				advertisementServiceimpl.addAdvertisement(inputAd,dinerID);
//				adList.add(inputAd);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			DinerInfo oldDiner =advertisementServiceimpl.getDinerInfoByDinerID(dinerID);
			System.out.println("**************這裡有來過Servlet*********");

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
		
//			session.setAttribute("account", oldDiner);
			

			String url = "/dinerbackground/pages/Team/help/request-Ad-placement.jsp"; // 登入完成後跳轉的頁面
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 info-change.jsp
			successView.forward(req, res);

		}

	}

}
