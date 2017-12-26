package com.javabotanist.client;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.javabotanist.entities.Partner;
import com.javabotanist.entities.Person;
import com.javabotanist.entities.Phone;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaQueryWithMultipleRootClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteriaQuery = builder.createQuery(Tuple.class);
			
			Root<Person> personRoot = criteriaQuery.from(Person.class);
			Root<Partner> partnerRoot = criteriaQuery.from(Partner.class);
			criteriaQuery.multiselect(personRoot, partnerRoot);
			
			Predicate personRestriction = builder.and(
				builder.equal(personRoot.get("address"), "Bank of Canada,234 Wellington Street"),
				builder.isNotEmpty(personRoot.get("phones"))
			);
			
			Predicate partnerRestriction = builder.and(
				builder.like(partnerRoot.get("name"), "%Nit%"),
				builder.equal(partnerRoot.get("version"), 1)
			);
			
			criteriaQuery.where(builder.and(personRestriction, partnerRestriction));
			
			List<Tuple> tuples = session.createQuery(criteriaQuery).getResultList();
			for (Tuple tuple : tuples) {
				Person person = (Person) tuple.get(0);
				if(person != null) {
					System.out.println(person);
					List<Phone> phones = person.getPhones();
					for (Phone phone : phones) {
						System.out.println(phone.getId() + "\t" + phone.getNumber() + "\t" + phone.getType().toString());
					}
				}
				
				Partner partner = (Partner) tuple.get(1);
				System.out.println(partner);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
