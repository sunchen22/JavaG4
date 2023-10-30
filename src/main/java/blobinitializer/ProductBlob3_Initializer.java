package blobinitializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.product.entity.Product;

import util.HibernateUtil;


public class ProductBlob3_Initializer {

	public static void main(String[] args) throws IOException {
		File dir = new File("src/main/resources/img/product3/");
		String[] fileNames = dir.list();
		Arrays.sort(fileNames);
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		
		try {
			Session session = factory.getCurrentSession();
			session.beginTransaction();

			for (int i = 0; i < 20; i++) {
				FileInputStream fis = new FileInputStream(new File(dir, fileNames[i]));
				byte[] picture = fis.readAllBytes();

				Product p = session.get(Product.class, i + 1);
				p.setProductBlob3(picture);
				
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
