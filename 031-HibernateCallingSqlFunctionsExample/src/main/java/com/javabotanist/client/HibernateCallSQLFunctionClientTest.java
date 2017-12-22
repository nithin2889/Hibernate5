package com.javabotanist.client;

import java.sql.CallableStatement;
import java.sql.Types;
import java.util.concurrent.atomic.AtomicReference;

import org.hibernate.Session;

import com.javabotanist.util.HibernateUtil;

public class HibernateCallSQLFunctionClientTest {

	public static void main(String[] args) {
		try(Session session = HibernateUtil.getSessionFactory().openSession()) {
			int personId = 1;
			final AtomicReference<Integer> phoneCount = new AtomicReference<>();
			session.doWork(connection -> {
				try(CallableStatement callableStatement = connection.prepareCall("{ ? = call fn_count_phones(?) }")) {
					callableStatement.registerOutParameter(1, Types.INTEGER);
					callableStatement.registerOutParameter(2, personId);
					callableStatement.execute();
					phoneCount.set(callableStatement.getInt(1));
				}
			});
			if(phoneCount != null) {
				System.out.println("Phone Count: " + phoneCount.get());
			}
		}
	}

}
