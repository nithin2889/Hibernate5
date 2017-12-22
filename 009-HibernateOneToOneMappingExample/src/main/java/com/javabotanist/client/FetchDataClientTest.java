package com.javabotanist.client;

import org.hibernate.Session;

import com.javabotanist.entities.Address;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class FetchDataClientTest {

	public static void main(String[] args) {
		getEmployeeAndAddressByEmployeeId();
		getEmployeeAndAddressByAddressId();
	}

	private static void getEmployeeAndAddressByAddressId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Address address = session.get(Address.class, 1);
			System.out.println(address);
			System.out.println(address.getEmployee());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getEmployeeAndAddressByEmployeeId() {
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			employee = session.get(Employee.class, 1);
			System.out.println(employee);
			Address address = employee.getAddress();
			System.out.println(address);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
