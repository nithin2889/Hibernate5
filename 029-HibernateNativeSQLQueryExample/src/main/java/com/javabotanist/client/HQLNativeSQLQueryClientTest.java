package com.javabotanist.client;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import com.javabotanist.entities.Person;
import com.javabotanist.entities.Phone;
import com.javabotanist.util.HibernateUtil;

public class HQLNativeSQLQueryClientTest {

	public static void main(String[] args) {
		// nativeQuerySelectEntitiesWithManyToOneAssociation();
		nativeQuerySelectEntitiesWithManyToOneAssociationUsingResultTransformer();
	}

	// Hibernate native query selecting entities with many-to-one association
	private static void nativeQuerySelectEntitiesWithManyToOneAssociation() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			List<Phone> phones = session
					.createNativeQuery("SELECT id, phone_number, phone_type, person_id " + "FROM phone_table")
					.addEntity(Phone.class).list();
			for (Phone phone : phones) {
				System.out.println("Phones Details::::::::::::::::::::::");
				System.out.println("Phone Id:" + phone.getId());
				System.out.println("Phone No:" + phone.getNumber());
				System.out.println("Phone Type:" + phone.getType().toString());
				
				Person person = phone.getPerson();
				if (person != null) {
					System.out.println("Person details:::::::::::::::::::::");
					System.out.println("Person Id:" + person.getId());
					System.out.println("Person Name:" + person.getName());
					System.out.println("Person NickName:" + person.getNickName());
					System.out.println("Address:" + person.getAddress());
					System.out.println("CreatedOn:" + person.getCreatedOn());
					System.out.println("Version:" + person.getVersion());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Hibernate native query selecting entities with joined many-to-one association
	// and ResultTransformer
	private static void nativeQuerySelectEntitiesWithManyToOneAssociationUsingResultTransformer() {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			@SuppressWarnings("unchecked")
			List<Person> persons = session
					.createNativeQuery(
							"SELECT * " + 
						    "FROM phone_table ph " + 
							"LEFT JOIN person_table pr ON ph.person_id = pr.id")
					.addEntity("phone", Phone.class)
					.addJoin("pr", "phone.person")
					.setResultTransformer(Criteria.ROOT_ENTITY).list();

			for (Person person : persons) {
				List<Phone> phones = person.getPhones();
				System.out.println("Phone details::::::::::::::::::");
				for (Phone phone : phones) {
					System.out.println("Phone Id:" + phone.getId());
					System.out.println("Phone No:" + phone.getNumber());
					System.out.println("Phone Type:" + phone.getType().toString());
					Person person2 = phone.getPerson();
					if (person2 != null) {
						System.out.println("Person details:::::::::::::::::::::");
						System.out.println("Person Id:" + person.getId());
						System.out.println("Person Name:" + person.getName());
						System.out.println("Person NickName:" + person.getNickName());
						System.out.println("Address:" + person.getAddress());
						System.out.println("CreatedOn:" + person.getCreatedOn());
						System.out.println("Version:" + person.getVersion());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
