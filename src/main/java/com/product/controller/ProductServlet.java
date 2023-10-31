package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.entity.ProductVO;
import com.product.service.ProductService;
import com.webempadmin.model.WebempadminService;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class ProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		

		String action = req.getParameter("action");


		if ("getOne_For_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("productTypeID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("productTypeID", "請輸入商品類型");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/NewFilePTest.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer productTypeID = null;
			try {
				productTypeID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("productTypeID", "編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/NewFilePTest.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO product = productSvc.getOneProduct(productTypeID);
			if (product == null) {
				errorMsgs.put("productID", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/NewFilePTest.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			session.setAttribute("product", product); // 資料庫取出的empVO物件,存入req
			String url = "/dinerbackground/pages/Team/p_list/NewFilePTest2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getByType_Display".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("productTypeID");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("productTypeID", "請輸入商品類型");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/NewFilePTest.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer productTypeID = null;
			try {
				productTypeID = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("productTypeID", "編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/NewFilePTest.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO product = productSvc.getOneProduct(productTypeID);
			if (product == null) {
				errorMsgs.put("productID", "查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/NewFilePTest2.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("product", product); // 資料庫取出的empVO物件,存入req
			String url = "/dinerbackground/pages/Team/p_list/NewFilePTest2.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自p_list.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer productID = Integer.valueOf(req.getParameter("productID"));

			System.out.println(productID);

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService pSvc = new ProductService();
			ProductVO product = pSvc.getOneProduct(productID);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String param = "?&productID=" + product.getProductID() + "&dinerID=" + product.getDinerID()
					+ "&productName=" + product.getProductName() + "&productPrice=" + product.getProductPrice()
					+ "&productTypeID=" + product.getProductTypeID() + "&productDailyStock="
					+ product.getProductDailyStock() + "&productRemark=" + product.getProductRemark() + "&productBlob1="
					+ product.getProductBlob1() + "&productBlob2=" + product.getProductBlob2() + "&productBlob3="
					+ product.getProductBlob3() + "&productStatus=" + product.getProductStatus();

			System.out.println(param);

			String url = "/dinerbackground/pages/Team/p_list/update_product.jsp" + param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_product.jsp
			successView.forward(req, res);
		}
		
		

		if ("update".equals(action)) { // 來自update_product.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer productID = Integer.valueOf(req.getParameter("productID").trim());
			
			Integer dinerID = Integer.valueOf(req.getParameter("dinerID").trim());

			String productName = req.getParameter("productName");
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("productName", "商品名稱請勿空白");
			} else if (!productName.trim().matches(productNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("productName", "商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Integer productPrice = null;
			try {
				productPrice = Integer.valueOf(req.getParameter("productPrice").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productPrice", "請輸入商品價格");
			}

			Integer productTypeID = null;
			try {
				productTypeID = Integer.valueOf(req.getParameter("productTypeID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productTypeID", "請輸入商品類型");
			}

			Integer productDailyStock = Integer.valueOf(req.getParameter("productDailyStock").trim());
			try {
				productDailyStock = Integer.valueOf(req.getParameter("productDailyStock").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productDailyStock", "請輸入商品庫存");
			}

			String productRemark = req.getParameter("productRemark").trim();
			if (productRemark == null || productRemark.trim().length() == 0) {
				errorMsgs.put("productRemark", "請輸入商品介紹");
			}

			InputStream in = req.getPart("productBlob1").getInputStream();
			byte[] productBlob1 = null;
			if (in.available() != 0) {
				productBlob1 = new byte[in.available()];
				in.read(productBlob1);
				in.close();
			} else {
				ProductService PSvc = new ProductService();
				productBlob1 = PSvc.getOneProduct(productID).getProductBlob1();
			}

			InputStream in2 = req.getPart("productBlob2").getInputStream();
			byte[] productBlob2 = null;
			if (in2.available() != 0) {
				productBlob2 = new byte[in2.available()];
				in2.read(productBlob2);
				in2.close();
			} else {
				ProductService PSvc = new ProductService();
				productBlob2 = PSvc.getOneProduct(productID).getProductBlob2();
			}

			InputStream in3 = req.getPart("productBlob3").getInputStream();
			byte[] productBlob3 = null;
			if (in3.available() != 0) {
				productBlob3 = new byte[in3.available()];
				in3.read(productBlob3);
				in3.close();
			} else {
				ProductService PSvc = new ProductService();
				productBlob3 = PSvc.getOneProduct(productID).getProductBlob3();
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/update_product.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO product = productSvc.updateProduct(productID, dinerID, productName, productPrice, productTypeID,
					productDailyStock, productRemark, productBlob1, productBlob2, productBlob3);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			session.setAttribute("product", product); // 資料庫update成功後,正確的的VO物件,存入req
			String url = "/dinerbackground/pages/Team/shelve/update_shelve_PV.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交p_list.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer dinerID = Integer.valueOf(req.getParameter("dinerID"));

			String productName = req.getParameter("productName").trim();
			String productNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (productName == null || productName.trim().length() == 0) {
				errorMsgs.put("productName", "*請輸入商品名稱");
			} else if (!productName.trim().matches(productNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("productName", "商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			Integer productPrice = null;
			try {
				productPrice = Integer.valueOf(req.getParameter("productPrice").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.put("productPrice", "*請輸入商品價格");
			}

			Integer productTypeID = null;
			try {
				productTypeID = Integer.valueOf(req.getParameter("productTypeID").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productTypeID", "*請輸入商品類型");
			}

			Integer productDailyStock = null;
			try {
				productDailyStock = Integer.valueOf(req.getParameter("productDailyStock").trim());
			} catch (NumberFormatException e) {
				errorMsgs.put("productDailyStock", "*請輸入每日庫存");
			}

			String productRemark = req.getParameter("productRemark").trim();
			if (productRemark == null || productRemark.trim().length() == 0) {
				errorMsgs.put("productRemark", "*請輸入商品介紹");
			}

			// 照片
			InputStream in = req.getPart("productBlob1").getInputStream(); // 從javax.servlet.http.Part物件取得上傳檔案的InputStream

			byte[] productBlob1 = new byte[in.available()];
			in.read(productBlob1);
			in.close();

			InputStream in2 = req.getPart("productBlob2").getInputStream(); // 從javax.servlet.http.Part物件取得上傳檔案的InputStream

			byte[] productBlob2 = new byte[in2.available()];
			in2.read(productBlob2);
			in2.close();

			InputStream in3 = req.getPart("productBlob3").getInputStream(); // 從javax.servlet.http.Part物件取得上傳檔案的InputStream

			byte[] productBlob3 = new byte[in3.available()];
			in3.read(productBlob3);
			in3.close();

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/shelve/shelve.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService productSvc = new ProductService();
			productSvc.addProduct(dinerID, productName, productPrice, productTypeID, productDailyStock, productRemark,
					productBlob1, productBlob2, productBlob3);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/dinerbackground/pages/Team/shelve/shelve_PV.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自p_list.jsp

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer productID = Integer.valueOf(req.getParameter("productID"));

			/*************************** 2.開始刪除資料 ***************************************/
			ProductService PSvc = new ProductService();
			PSvc.deleteProduct(productID);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/dinerbackground/pages/Team/p_list/p_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("off_shelve".equals(action)) { // 來自update_product.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer productID = Integer.valueOf(req.getParameter("productID").trim());

			String productStatus = req.getParameter("productStatus").trim();

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/dinerbackground/pages/Team/p_list/p_list.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService productSvc = new ProductService();
			ProductVO product = productSvc.offShelveProduct(productID, productStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("product", product); // 資料庫update成功後,正確的的VO物件,存入req
			String url = "/dinerbackground/pages/Team/p_list/p_list.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交p_list.jsp
			successView.forward(req, res);
		}

	}
}
