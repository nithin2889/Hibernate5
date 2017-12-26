package com.javabotanist.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.javabotanist.entities.Call;
import com.javabotanist.entities.Person;
import com.javabotanist.entities.Phone;
import com.javabotanist.util.HibernateUtil;

public class HibernateCriteriaJoinQueryClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Phone> criteriaQuery = builder.createQuery(Phone.class);
			
			Root<Phone> root = criteriaQuery.from(Phone.class);
			root.join("person");
			
			criteriaQuery.where(builder.isNotEmpty(root.get("calls")));
			
			Query<Phone> query = session.createQuery(criteriaQuery);
			
			List<Phone> resultList = query.getResultList();
			for (Phone phone : resultList) {
				System.out.println(":::::::::::::Phone Details:::::::::::::");
				System.out.println(phone.getId() + "\t" + phone.getNumber() + "\t" + phone.getType().toString());
				System.out.println(":::::::::::::Person Details:::::::::::::");
				Person person = phone.getPerson();
				System.out.println(person.getId() + "\t" + person.getName() + "\t" + person.getCreatedOn());
				List<Call> calls = phone.getCalls();
				System.out.println(":::::::::::::Phone Call Details:::::::::::::");
				for (Call call : calls) {
					System.out.println(call.getId()+"\t"+call.getDuration()+"\t"+call.getTimestamp());
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
