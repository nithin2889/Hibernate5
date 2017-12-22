package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class Hibernate5HQLClientTest {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		getAllEmployees(sf);
		getEmployeeById(sf);
		getEmployeeByIdAndEmail(sf);
		getAllEmployeeNames(sf);
		getAllEmployeesIdAndName(sf);
	}

	private static void getAllEmployees(SessionFactory sf) {
		try(Session session = sf.openSession()) {
			String HQL = "FROM Employee";
			Query<Employee> query = session.createQuery(HQL, Employee.class);
			List<Employee> list = query.list();
			list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getEmployeeById(SessionFactory sf) {
		int empId = 2;
		try(Session session = sf.openSession()) {
			String HQL = "FROM Employee WHERE employeeId=?";
			Query<Employee> query = session.createQuery(HQL, Employee.class);
			
			query.setParameter(0, empId);
			
			Employee employee = query.uniqueResult();
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getEmployeeByIdAndEmail(SessionFactory sf) {
		int empId = 1;
		try(Session session = sf.openSession()) {
			String HQL = "FROM Employee WHERE employeeId=:empId AND email=:email";
			Query<Employee> query = session.createQuery(HQL, Employee.class);
			
			query.setParameter("empId", empId);
			query.setParameter("email", "nithin@yahoo.com");
			
			Employee employee = query.uniqueResult();
			System.out.println(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getAllEmployeeNames(SessionFactory sf) {
		try(Session session = sf.openSession()) {
			String HQL = "SELECT employeeName FROM Employee";
			Query<String> query = session.createQuery(HQL);
			query.list().forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void getAllEmployeesIdAndName(SessionFactory sf) {
		try(Session session = sf.openSession()) {
			String HQL = "SELECT employeeId, employeeName FROM Employee";
			Query query = session.createQuery(HQL);
			List<Object[]> list = query.list();
			
			for (Object[] objects : list) {
				Integer employeeId = (Integer) objects[0];
				String employeeName = (String) objects[1];
				System.out.println(employeeId + "-" + employeeName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
