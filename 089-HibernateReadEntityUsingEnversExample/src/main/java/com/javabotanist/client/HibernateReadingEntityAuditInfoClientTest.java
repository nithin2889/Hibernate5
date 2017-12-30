package com.javabotanist.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import com.javabotanist.entities.Customer;
import com.javabotanist.util.HibernateUtil;

/**
 * NOTE: An API to fetch the audit related historical data to be fetched from the table.
 */

public class HibernateReadingEntityAuditInfoClientTest {

	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long customerId = 1L;

			AuditReader auditReader = AuditReaderFactory.get(session);
			// List of revisions will be populated from the Customer_AUD table
			List<Number> revisions = AuditReaderFactory.get(session).getRevisions(Customer.class, customerId);
			for (Number rev : revisions) {
				// For every revision id present the customer details will be pulled from the database.
				Customer customer = auditReader.find(Customer.class, customerId, rev);
				System.out.println("Revision No:" + rev);
				System.out.println(customer);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}