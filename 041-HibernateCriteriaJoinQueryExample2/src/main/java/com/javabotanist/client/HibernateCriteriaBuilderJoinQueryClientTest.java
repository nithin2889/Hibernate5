package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Department;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaBuilderJoinQueryClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			
			CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Employee> empRoot = criteriaQuery.from(Employee.class);
			Root<Department> deptRoot = criteriaQuery.from(Department.class);
			
			criteriaQuery.multiselect(empRoot, deptRoot);
			
			criteriaQuery.where(builder.equal(empRoot.get("department"), deptRoot.get("id")));
			
			List<Object[]> list = session.createQuery(criteriaQuery).getResultList();
			for (Object[] objects : list) {
				Employee emp = (Employee) objects[0];
				System.out.println(emp.getId() + "\t" + emp.getName());
				System.out.println("----------------------------------");
				Department dept = (Department) objects[1];
				System.out.println(dept.getId() + "\t" + dept.getName());
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
