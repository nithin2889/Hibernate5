package com.javabotanist.interceptors;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.javabotanist.entities.Person;

public class LoggerInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(LoggerInterceptor.class);

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("onSave method is called...");
		if (entity instanceof Person) {
			Person person = (Person) entity;
			logger.info(person);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		logger.info("onLoad method is called...");
		if (entity instanceof Person) {
			Person person = (Person) entity;
			logger.info(person);
		}
		return super.onLoad(entity, id, state, propertyNames, types);
	}

}
