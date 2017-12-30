package com.javabotanist.events;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import com.javabotanist.entities.Person;

public class HibernateSaveOrUpdateEventListenerImpl implements SaveOrUpdateEventListener {

	private static Logger logger = LogManager.getLogger(HibernateSaveOrUpdateEventListenerImpl.class);
	private static final long serialVersionUID = 1L;

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent event) throws HibernateException {
		logger.info("onSaveOrUpdate is called.");
		Object obj = event.getEntity();
		if(obj instanceof Person) {
			Person person = (Person) obj;
			logger.info(person);
		}
	}

}
