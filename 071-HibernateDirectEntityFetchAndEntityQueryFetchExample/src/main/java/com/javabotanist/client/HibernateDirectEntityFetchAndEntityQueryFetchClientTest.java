package com.javabotanist.client;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;
/**
 * Direct Entity Fetching: Normal fetching of an entity. In OneToOne, OneToMany, ManyToOne 
 * or ManyToMany mapping the default behavior is EAGER loading. Only in case of Collection 
 * properties the default behavior in hibernate is LAZY loading.
 */
public class HibernateDirectEntityFetchAndEntityQueryFetchClientTest {

	public static void main(String[] args) {
		// directEntityFetching();
		// entityQueryFetching();
	}

	public static void entityQueryFetching() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Entity query fetch example.
			Long employeeId = 1L;
			Query<?> query = session.createQuery("SELECT e FROM Employee e WHERE e.id=:empId");
			query.setParameter("empId", employeeId);
			Object result = query.getSingleResult();
			Employee employee = (Employee) result;
			
			if(employee != null) {
				System.out.println("::Employee Details::");
				System.out.println(employee.getId()+"\t"+employee.getEmployeeName()+"\t"+employee.getUserName());
				System.out.println("::Employee Department Details::");
				Department department = employee.getDepartment();
				if(department != null) {
					System.out.println("::Department Details::");
					System.out.println(department.getId()+"\t"+department.getDeptName());
				} else {
					System.out.println("Department not found for an employee with id "+employee.getId());
				}
			} else {
				System.out.println("Employee not found for id "+employeeId);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static void directEntityFetching() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			// Direct entity fetch example.
			Long employeeId = 1L;
			Employee employee = session.get(Employee.class, employeeId);
			if(employee != null) {
				System.out.println("::Employee Details::");
				System.out.println(employee.getEmployeeName()+"\t"+employee.getUserName());
				Department department = employee.getDepartment();
				if(department != null) {
					System.out.println("::Department Details::");
					System.out.println(department.getId()+"\t"+department.getDeptName());
				} else {
					System.out.println("Department not found for an employee with id "+employee.getId());
				}
			} else {
				System.out.println("Employee not found for id "+employeeId);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}