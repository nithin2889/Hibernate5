package com.javabotanist.client;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.dto.EmployeeStatistics;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaBuilderAggregateFunctionsResultsMapWithDTOClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<EmployeeStatistics> criteriaQuery = builder.createQuery(EmployeeStatistics.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Expression<Long> totalNumberOfEmployees = builder.count(root);
			Expression<Long> distinctNumberOfEmployees = builder.countDistinct(root);
			Expression<Double> maxSalaryOfEmployees = builder.max(root.get("salary"));
			Expression<Double> avgSalaryOfEmployees = builder.avg(root.get("salary"));
			Expression<Double> sumSalaryOfEmployees = builder.sum(root.get("salary"));
			
			criteriaQuery.select(builder.construct(EmployeeStatistics.class, totalNumberOfEmployees, distinctNumberOfEmployees,
					maxSalaryOfEmployees, avgSalaryOfEmployees, sumSalaryOfEmployees));
			
			EmployeeStatistics singleResult = session.createQuery(criteriaQuery).getSingleResult();
			System.out.println(singleResult);
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
