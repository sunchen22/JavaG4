package com.grouporder.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dinerinfo.entity.DinerInfo;

import util.HibernateUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class TestImage {


	// ...

	public void updateDinerBlob(Integer dinerID) {
	    Session session = null;
	    Transaction transaction = null;
	    
	    try {
	        session = HibernateUtil.getSessionFactory().getCurrentSession();
	        transaction = session.beginTransaction();

	        // Load the DinerInfo entity by its ID
	        DinerInfo dinerInfo = session.get(DinerInfo.class, dinerID);

	        // Specify the file path of the image you want to update from
	        String imagePath = "C:\\Users\\Tibame_T14\\OneDrive\\桌面\\假圖片\\1.jpg";
	        
	        // Read the image from the file and convert it to bytes
	        File imageFile = new File(imagePath);
	        byte[] imageBytes = new byte[(int) imageFile.length()];
	        FileInputStream fis = new FileInputStream(imageFile);
	        fis.read(imageBytes);
	        fis.close();

	        // Update the dinerBlob column with the image data
	        dinerInfo.setDinerBlob(imageBytes);

	        session.update(dinerInfo);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	}

	// ...
	public static void main(String[] args) {
		TestImage t = new TestImage();
		// Call the updateDinerBlob method with the ID of the DinerInfo entity you want to update
		t.updateDinerBlob(1);
	}


}
