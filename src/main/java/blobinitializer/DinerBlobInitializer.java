package blobinitializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.advertisement.entity.Advertisement;
import com.dinerinfo.entity.DinerInfo;
import com.userinfo.entity.UserInfo;

import util.HibernateUtil;


public class DinerBlobInitializer {

	public static void main(String[] args) throws IOException {
		File dir = new File("src/main/resources/img/diner/");
		String[] fileNames = dir.list();
		Arrays.sort(fileNames);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();

			for (int i = 0; i < 5; i++) {
				FileInputStream fis = new FileInputStream(new File(dir, fileNames[i]));
				byte[] picture = fis.readAllBytes();

				DinerInfo diner = session.get(DinerInfo.class, i + 1);
				diner.setDinerBlob(picture);
				
				fis.close();
			}

			session.getTransaction().commit();

		} catch (Exception e) {
			factory.getCurrentSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			HibernateUtil.shutdown();
		}
	}
}
