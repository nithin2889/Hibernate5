package com.javabotanist.client;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.util.HibernateUtil;

public class HQLClientTest {

	public static void main(String[] args) {
		getEmployeeAndAddressByEmployeeId();
	}
	
	private static void getEmployeeAndAddressByEmployeeId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			String HQL = "SELECT emp.employeeName, emp.doj, emp.salary, addr.city, addr.pincode "
					+ "FROM Employee emp LEFT JOIN emp.address addr WHERE emp.employeeId = :empId";
			Query<Object[]> query = session.createQuery(HQL);
			query.setParameter("empId", 1);
			List<Object[]> list = query.list();
			for (Object[] objects : list) {
				String employeeName = (String)objects[0];
				Date doj = (Date)objects[1];
				Double salary = (Double)objects[2];
				String city = (String)objects[3];
				Long pincode = (Long)objects[4];
				
				System.out.println(employeeName + "\t" + doj + "\t" + salary + "\t" + city + "\t" + pincode);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/*private static void getEmployeeAndAddressByEmployeeId() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
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
	}*/

}
