package com.javabotanist.client;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Address;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			
			Employee employee1 = new Employee();
			employee1.setEmployeeName("Nithin Prasad");
			employee1.setEmail("nithin@yahoo.com");
			employee1.setSalary(100000.00);
			employee1.setDoj(new Date());
			
			Employee employee2 = new Employee();
			employee2.setEmployeeName("Akhila Martia");
			employee2.setEmail("akhila@yahoo.com");
			employee2.setSalary(90000.00);
			employee2.setDoj(new Date());
			
			Address address1 = new Address();
			address1.setStreet("Sawdust Road");
			address1.setCity("The Woodlands");
			address1.setState("Texas");
			address1.setPincode(77380L);
			
			Address address2 = new Address();
			address2.setStreet("KF Road");
			address2.setCity("Bangalore");
			address2.setState("Karnataka");
			address2.setPincode(560061L);
			
			Address address3 = new Address();
			address3.setStreet("Gandhi Road");
			address3.setCity("Delhi");
			address3.setState("Delhi");
			address3.setPincode(220200L);
			
			employee1.getAddressList().add(address1);
			employee1.getAddressList().add(address2);
			employee1.getAddressList().add(address3);
			
			address1.getEmpList().add(employee1);
			address2.getEmpList().add(employee1);
			address3.getEmpList().add(employee1);
			
			employee2.getAddressList().add(address2);
			employee2.getAddressList().add(address3);
			
			address2.getEmpList().add(employee2);
			address3.getEmpList().add(employee2);
			
			session.persist(employee1);
			session.persist(employee2);
			
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
