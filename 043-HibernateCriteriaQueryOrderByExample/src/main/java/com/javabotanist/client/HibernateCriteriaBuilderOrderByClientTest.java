package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaBuilderOrderByClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root);
			
			criteriaQuery.orderBy(builder.asc(root.get("salary")));
			System.out.println("query for ascending salary");
			List<Employee> ascResultList = session.createQuery(criteriaQuery).getResultList();
			for (Employee employee : ascResultList) {
				System.out.println(employee.getId()+"\t"+employee.getName()+"\t"+employee.getSalary()+"\t"+employee.getDesignation());
			}
			
			criteriaQuery.orderBy(builder.desc(root.get("salary")));
			System.out.println("query for descending salary");
			List<Employee> descResultList = session.createQuery(criteriaQuery).getResultList();
			for (Employee employee : descResultList) {
				System.out.println(employee.getId()+"\t"+employee.getName()+"\t"+employee.getSalary()+"\t"+employee.getDesignation());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}