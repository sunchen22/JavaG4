package listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import util.HibernateUtil;
import util.JedisUtil;

@WebListener
public class InitializerListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("context started");
		HibernateUtil.getSessionFactory();
		JedisUtil.getJedisPool();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context ended");
		HibernateUtil.shutdown();
		JedisUtil.shutdownJedisPool();
	}

}
