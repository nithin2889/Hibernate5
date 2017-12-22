package com.javabotanist.client;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;

import com.javabotanist.util.HibernateUtil;

public class SaveDataClientTest {

	public static void main(String[] args) {

		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			StoredProcedureQuery procedureQuery = session.createStoredProcedureCall("sp_count_phones");
			procedureQuery.registerStoredProcedureParameter("personId", Integer.class, ParameterMode.IN);
			procedureQuery.registerStoredProcedureParameter("phoneCount", Integer.class, ParameterMode.OUT);
			
			procedureQuery.setParameter("personId", 2);
			
			procedureQuery.execute();
			Integer phoneCount = (Integer) procedureQuery.getOutputParameterValue("phoneCount");
			
			System.out.println("Phone count: " + phoneCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
