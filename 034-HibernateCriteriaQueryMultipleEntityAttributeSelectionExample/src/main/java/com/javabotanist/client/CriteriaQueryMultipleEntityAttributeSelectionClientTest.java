package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class CriteriaQueryMultipleEntityAttributeSelectionClientTest {
	
	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Path<Object> empNamePath = root.get("employeeName");
			Path<Object> emailPath = root.get("email");
			Path<Object> salaryPath = root.get("salary");
		
			// criteriaQuery.select(builder.array(empNamePath, emailPath, salaryPath));
			// or
			criteriaQuery.multiselect(empNamePath, emailPath, salaryPath);
			
			Query<Object[]> query = session.createQuery(criteriaQuery);
			List<Object[]> empList = query.list();
			
			for (Object[] objects : empList) {
				System.out.println("Employee Name: " + (String)objects[0]);
				System.out.println("Email: " + (String)objects[1]);
				System.out.println("Salary: " + (Double)objects[2]);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
}
