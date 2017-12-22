package com.javabotanist.dao.impl;

import org.hibernate.Session;

import com.javabotanist.dao.EmployeeDAO;
import com.javabotanist.entities.Employee;
import com.javabotanist.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO {

	@Override
	public void addEmployee(Employee employee) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Integer id = (Integer) session.save(employee);
			System.out.println("Employee created with id: " +id);
			session.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Employee fetchEmployeeById(int employeeId) {
		Employee employee = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			employee = session.get(Employee.class, employeeId);
			if(employee != null) {
				return employee;
			} else {
				System.out.println("Employee id " + employeeId + " not found");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void updateEmployeeById(int employeeId, Double newSal) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = session.get(Employee.class, employeeId);
			if(employee != null) {
				employee.setSalary(newSal);
				session.beginTransaction();
				session.update(employee);
				session.getTransaction().commit();
			} else {
				System.out.println("Employee doesn't exist for the given id ");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Employee employee = session.get(Employee.class, employeeId);
			if(employee != null) {
				session.beginTransaction();
				session.delete(employee);
				session.getTransaction().commit();
			} else {
				System.out.println("Employee doesn't exist for the given id " + employeeId);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
