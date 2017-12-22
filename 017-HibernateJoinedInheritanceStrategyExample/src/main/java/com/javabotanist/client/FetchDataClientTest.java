package com.javabotanist.client;

import java.text.ParseException;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.javabotanist.entities.Employee;
import com.javabotanist.entities.Person;
import com.javabotanist.entities.Student;
import com.javabotanist.util.HibernateUtil;

public class FetchDataClientTest {

	public static void main(String[] args) throws ParseException {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			Person person = session.get(Person.class, 3);
			if(person instanceof Person && !(person instanceof Employee) && !(person instanceof Student)) {
				System.out.println(person);
			} else if((person instanceof Person) && (person instanceof Employee)) {
				Employee employee = (Employee) person;
				System.out.println(employee);
			} else if((person instanceof Person) && (person instanceof Student)) {
				Student student = (Student) person;
				System.out.println(student);
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
