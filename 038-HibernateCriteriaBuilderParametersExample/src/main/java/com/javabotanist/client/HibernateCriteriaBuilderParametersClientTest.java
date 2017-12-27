package com.javabotanist.client;

import java.text.ParseException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Person;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaBuilderParametersClientTest {

	public static void main(String[] args) throws ParseException {
		String nickName = "Sam";
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Person> criteriaQuery = builder.createQuery(Person.class);
			Root<Person> root = criteriaQuery.from(Person.class);
			
			ParameterExpression<String> nickNameParameter = builder.parameter(String.class);
			criteriaQuery.where(builder.equal(root.get("nickName"), nickNameParameter));
			
			Query<Person> query = session.createQuery(criteriaQuery);
			query.setParameter(nickNameParameter, nickName);
			
			List<Person> personList = query.getResultList();
			for (Person person : personList) {
				System.out.println(person);
			}
		} catch(HibernateException e) {
			e.printStackTrace();
		}
	}

}
