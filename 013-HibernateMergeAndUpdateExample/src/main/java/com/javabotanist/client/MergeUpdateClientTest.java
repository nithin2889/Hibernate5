package com.javabotanist.client;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class MergeUpdateClientTest {

	public static void main(String[] args) {
		Employee employee1 = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			employee1 = session.get(Employee.class, 1);
			System.out.println(employee1);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		// this is detached object as session has ended
		employee1.setSalary(150000.00);
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Employee employee2 = session.get(Employee.class, 1);
			// trying to update employee1 in another sesison
			// session.update(employee1);
			
			// instead of update we should use merge
			// this object will be updated which was retrieved in another session without throwing an exception
			// merge internally uses cascade type merge
			session.merge(employee1);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
