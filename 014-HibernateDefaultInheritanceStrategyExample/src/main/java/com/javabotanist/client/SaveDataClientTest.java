package com.javabotanist.client;

import java.math.BigDecimal;
import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Person;
import com.javabotanist.entities.Student;
import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) throws ParseException {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Person person = new Person();
			person.setName("Nithin Prasad");
			person.setGender("Male");
			
			Employee employee = new Employee();
			employee.setBonus(new BigDecimal("277.389"));
			employee.setDeptName("IT");
			employee.setDoj(HibernateUtil.getDOJ("18/03/2017"));
			employee.setEmail("akhila@gmail.com");
			employee.setName("Akhila");
			employee.setSalary(80000.1222);
			employee.setGender("Female");
			
			Student student = new Student();
			student.setName("Brooke");
			student.setGender("Female");
			student.setFee(2000.00F);
			student.setSchoolName("MIT");
			student.setSectionName("MS");
			
			session.beginTransaction();
			session.save(person);
			session.save(student);
			session.save(employee);
			
			session.getTransaction().commit();
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
