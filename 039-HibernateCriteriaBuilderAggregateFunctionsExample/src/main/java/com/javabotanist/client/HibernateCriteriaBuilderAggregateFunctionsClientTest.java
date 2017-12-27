package com.javabotanist.client;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaBuilderAggregateFunctionsClientTest {

	public static void main(String[] args) {
		// getTotalNumberOfEmployees();
		// getMaxSalaryOfEmployee();
		// getAvgSalaryOfEmployee();
		// getSumSalaryOfEmployee();
		// getTotalDistinctEmployees();
	}

	private static void getTotalDistinctEmployees() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(builder.countDistinct(root));
			
			Long result = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println("Distinct Count Of Employees are: " + result);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

	private static void getSumSalaryOfEmployee() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(builder.sum(root.get("salary")));
			
			Double result = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println("Sum of salary of all employee: " + result);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

	private static void getAvgSalaryOfEmployee() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(builder.avg(root.get("salary")));
			
			Double result = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println("Average salary of an employee: " + result);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

	private static void getMaxSalaryOfEmployee() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Double> criteriaQuery = builder.createQuery(Double.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(builder.max(root.get("salary")));
			
			Double result = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println("Maximum salary of an employee: " + result);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

	private static void getTotalNumberOfEmployees() {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(builder.count(root));
			
			Long result = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println("Total number of employees: " + result);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
