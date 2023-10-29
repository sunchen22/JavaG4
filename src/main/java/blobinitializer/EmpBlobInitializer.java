package blobinitializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.webempadmin.entity.Webempadmin;

import util.HibernateUtil;


public class EmpBlobInitializer {

	public static void main(String[] args) throws IOException {
		File dir = new File("src/main/resources/img/emp/");
		String[] fileNames = dir.list();
		Arrays.sort(fileNames);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();

			for (int i = 0; i < 5; i++) {
				FileInputStream fis = new FileInputStream(new File(dir, fileNames[i]));
				byte[] picture = fis.readAllBytes();

				Webempadmin emp = session.get(Webempadmin.class, i + 1);
				emp.setEmpBlob(picture);
				
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
