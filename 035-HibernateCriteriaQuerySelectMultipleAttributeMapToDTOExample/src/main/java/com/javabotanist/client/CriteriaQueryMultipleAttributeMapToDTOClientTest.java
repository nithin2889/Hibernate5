package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.DTO.EmployeeDTO;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class CriteriaQueryMultipleAttributeMapToDTOClientTest {
	
	public static void main(String[] args) {
		List<EmployeeDTO> empInfo = getEmployeesInfo();
		for (EmployeeDTO employeeDTO : empInfo) {
			System.out.println("--------------------------");
			System.out.println("Employee Name: " + employeeDTO.getEmployeeName());
			System.out.println("Employee Name: " + employeeDTO.getEmail());
			System.out.println("Employee Name: " + employeeDTO.getSalary());
			System.out.println("--------------------------");
		}
	}

	private static List<EmployeeDTO> getEmployeesInfo() {
		List<EmployeeDTO> empList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<EmployeeDTO> criteriaQuery = builder.createQuery(EmployeeDTO.class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Path<Object> empNamePath = root.get("employeeName");
			Path<Object> emailPath = root.get("email");
			Path<Object> salaryPath = root.get("salary");
		
			criteriaQuery.select(builder.construct(EmployeeDTO.class, empNamePath, emailPath, salaryPath));
			
			Query<EmployeeDTO> query = session.createQuery(criteriaQuery);
			empList = query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return empList;
	}
	
}
