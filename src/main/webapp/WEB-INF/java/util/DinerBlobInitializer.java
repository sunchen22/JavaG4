package util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

//只需引入一張照片
//施工中
public class DinerBlobInitializer {
	public static void main(String[] args) throws IOException{
		File dir = new File("src/main/resources/img/");
		String[] fileNames = dir.list();
		Arrays.sort(fileNames);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();
			
			
			
		} catch (Exception e) {
			
		} finally {
			
		}
		
	}

}
