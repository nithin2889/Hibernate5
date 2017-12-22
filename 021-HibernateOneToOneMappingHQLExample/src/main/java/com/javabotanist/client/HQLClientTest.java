package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Address;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HQLClientTest {

	public static void main(String[] args) {
		// getEmployeeAndAddressByEmployeeId();
		getEmployeeAndAddressByAddressId();
	}

	private static void getEmployeeAndAddressByAddressId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Address address = session.get(Address.class, 1);
			String HQL = "FROM Address addr LEFT JOIN FETCH addr.employee WHERE addr.addressId = :addrId";
			Address address = session.createQuery(HQL, Address.class).setParameter("addrId", 1).uniqueResult();
			System.out.println(address);
			System.out.println(address.getEmployee());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void getEmployeeAndAddressByEmployeeId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// employee = session.get(Employee.class, 1);
			String HQL = "FROM Employee emp LEFT JOIN FETCH emp.address WHERE emp.employeeId = :empId";
			Query<Employee> query = session.createQuery(HQL, Employee.class);
			query.setParameter("empId", 1);
			Employee employee = query.uniqueResult();
			System.out.println(employee);
			Address address = employee.getAddress();
			System.out.println(address);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
