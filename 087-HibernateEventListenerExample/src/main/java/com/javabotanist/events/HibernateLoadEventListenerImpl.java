package com.javabotanist.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.LoadEvent;
import org.hibernate.event.spi.LoadEventListener;

import com.javabotanist.entities.Person;

public class HibernateLoadEventListenerImpl implements LoadEventListener {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(HibernateLoadEventListenerImpl.class);

	@Override
	public void onLoad(LoadEvent event, LoadType loadType) throws HibernateException {
		logger.info("onLoad is called.");
		Object obj = event.getResult();
		if (obj instanceof Person) {
			Person person = (Person) obj;
			logger.info(person);
		}
	}

}
