package com.javabotanist.events;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.RefreshEvent;
import org.hibernate.event.spi.RefreshEventListener;

import com.javabotanist.entities.Person;

public class HibernateRefreshEventListenerImpl implements RefreshEventListener {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(HibernateRefreshEventListenerImpl.class);

	@Override
	public void onRefresh(RefreshEvent event) throws HibernateException {
		logger.info("onRefresh is called.");
		Object obj = event.getObject();
		if (obj instanceof Person) {
			Person person = (Person) obj;
			logger.info(person);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onRefresh(RefreshEvent event, Map refreshedAlready) throws HibernateException {
		logger.info("onRefresh is called.");
	}

}
