package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

/**
 * We can overcome the problem of unnecessary query by enabling second-level cache.
 * If we enable second-level cache all session will point to a single cache.
 * We need to integrate JCache whose implementation is known as EHCache. 
 * Here, JCache API is a Java specification for temporary in-memory caching of Java Objects
 * including Object Creations, Shared Access, Spooling, invalidation consisting across JVMs.
 * After adding the required binaries and enabling the second-level cache in pom.xml,
 * we need to annotate the entity on which we need caching to happen using @Cache annotation.
 */

public class HibernateSecondLevelSessionCacheClientTest {

	public static void main(String[] args) {
		Session session = null;
		// Session-1
		try {
			Long employeeId = 1L;
			session = HibernateUtil.getSessionFactory().openSession();
			Employee employee = session.get(Employee.class, employeeId);
			System.out.println(employee);
		} catch(Exception e) {
			throw e;
		} finally {
			if(session != null) {
				session.close();				
			}
		}
		
		// Session-2
		try {
			Long employeeId = 1L;
			session = HibernateUtil.getSessionFactory().openSession();
			Employee employee = session.get(Employee.class, employeeId);
			System.out.println(employee);
		} catch(Exception e) {
			throw e;
		} finally {
			if(session != null) {
				session.close();				
			}
		}
	}
	
}