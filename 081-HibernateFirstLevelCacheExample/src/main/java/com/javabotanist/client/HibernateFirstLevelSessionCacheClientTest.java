package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateFirstLevelSessionCacheClientTest {

	public static void main(String[] args) {
		// sessionCacheForInsertRecord();
		// sessionCacheReadByPrimaryKey();
		// sessionCacheReadByNaturalId();
		// sessionCacheForUpdateRecord();
		// sessionCacheForDeleteRecord();
	}

	private static void sessionCacheForDeleteRecord() {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long employeeId = 2L;
			// Hibernate will trigger a select query
			Employee employee1 = session.get(Employee.class, employeeId);
			System.out.println(employee1);
			if(employee1 != null) {
				tx = session.beginTransaction();
				// Hibernate will delete the object from session cache when it is deleted from the DB.
				session.delete(employee1);
				tx.commit();
			}
			System.out.println("--------------------------------");
			// Hibernate will trigger another select query as it is not found in DB or the session cache.
			Employee employee2 = session.get(Employee.class, employeeId);
			System.out.println(employee2);
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
		}
	}

	private static void sessionCacheForUpdateRecord() {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long employeeId = 3L;
			String password = "Pass@123";
			// Hibernate will trigger a select query
			Employee employee1 = session.get(Employee.class, employeeId);
			System.out.println(employee1);
			if(employee1 != null) {
				tx = session.beginTransaction();
				//Hibernate will update this object into the session cache.
				employee1.setPassword(password);
				tx.commit();
			}
			System.out.println("--------------------------------");
			// Hibernate will not trigger a select query.
			Employee employee2 = session.get(Employee.class, employeeId);
			System.out.println(employee2);
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

	private static void sessionCacheReadByNaturalId() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String username = "seanm";
			// Hibernate will trigger 2 SQL queries for the below line.
			// 1. Based on the username the primary key is selected.
			// 2. Using the primary key fetched it will select all records.
			Employee employee1 = session.bySimpleNaturalId(Employee.class).load(username);
			System.out.println(employee1);
			System.out.println("--------------------------");
			// Hibernate will not trigger any query after this line.
			Employee employee2 = session.bySimpleNaturalId(Employee.class).load(username);
			System.out.println(employee2);
		} catch (Exception e) {
			throw e;
		}
	}

	private static void sessionCacheReadByPrimaryKey() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Long employeeId = 1L;
			// Hibernate will trigger a select query and stores it inside the Session cache.
			Employee employee1 = session.get(Employee.class, employeeId);
			System.out.println(employee1);
			System.out.println("----------------------------");
			// Hibernate will retrieve the record from Session cache until there is any change in the query.
			Employee employee2 = session.get(Employee.class, employeeId);
			System.out.println(employee2);
		} catch (Exception e) {
			throw e;
		}
	}

	private static void sessionCacheForInsertRecord() {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = new Employee();
			employee.setEmployeeName("Nithin Prasad");
			employee.setUserName("nithpras");
			employee.setPassword("nthak123");
			employee.setAccessLevel(1);
			
			tx = session.beginTransaction();
			Long employeeId = (Long) session.save(employee);
			tx.commit();
			System.out.println("----------------------------");
			// Below line will not trigger a select query to get the data. 
			// It will first check in the first-level cache. 
			// Until you close the session, Hibernate won't trigger a select query.
			Employee employee2 = session.get(Employee.class, employeeId);
			System.out.println(employee2);
		} catch (Exception e) {
			if(tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
	}

}