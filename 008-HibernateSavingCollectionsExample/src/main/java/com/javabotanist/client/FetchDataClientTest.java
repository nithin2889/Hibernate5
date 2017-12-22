package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class FetchDataClientTest {

	public static void main(String[] args) {
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			employee = session.get(Employee.class, 1);
			System.out.println(employee);
		} catch(Exception e) {
			e.printStackTrace();
		}
		if(employee != null) {
			employee.getAddressList().forEach(System.out::println);
		}
	}

}
