package com.mazdausa.mmg.batch.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class QuartzServletContextListener implements ServletContextListener {
	final Logger logger = LoggerFactory.getLogger(QuartzServletContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		logger.info("Do Nothing");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		final WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce
				.getServletContext());
		try {
			SchedulerFactoryBean factory = (SchedulerFactoryBean) context.getBean(SchedulerFactoryBean.class);
			StdSchedulerFactory.getDefaultScheduler().shutdown(true);
			factory.getScheduler().shutdown();
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			logger.error("Quartz failed to shutdn", ex);
		} catch (SchedulerException ex) {
			logger.error("Quartz failed to shut//wn", ex);
		}
	}
}
