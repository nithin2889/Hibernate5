package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class CriteriaQuerySingleEntityAttributeSelectionClientTest {
	
	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			criteriaQuery.select(root.get("employeeName"));
			
			Query<String> query = session.createQuery(criteriaQuery);
			List<String> empNameList = query.getResultList();
			empNameList.forEach(System.out::println);
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
}
