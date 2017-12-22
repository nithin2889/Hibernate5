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
			if(address != null) {
				System.out.println(address.getEmployee());
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getEmployeeAndAddressByEmployeeId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = session.get(Employee.class, 1);
			if(employee != null) {
				employee.getAddressList().forEach(System.out::println);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
