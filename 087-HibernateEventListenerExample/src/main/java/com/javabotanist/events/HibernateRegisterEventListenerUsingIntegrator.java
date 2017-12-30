package com.javabotanist.events;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class HibernateRegisterEventListenerUsingIntegrator implements Integrator {

	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {

		EventListenerRegistry eventListenerRegistry = 
			serviceRegistry.getService(EventListenerRegistry.class);
		
		eventListenerRegistry.getEventListenerGroup(EventType.SAVE_UPDATE)
			.appendListener(new HibernateSaveOrUpdateEventListenerImpl());
		
		eventListenerRegistry.getEventListenerGroup(EventType.LOAD)
			.appendListener(new HibernateLoadEventListenerImpl());
		
		eventListenerRegistry.getEventListenerGroup(EventType.REFRESH)
			.appendListener(new HibernateRefreshEventListenerImpl());

	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		
		
		
	}

}
