package com.consensus.prototypes.mongoose.storm.hibernate;

import org.hibernate.SessionFactory;

public class HibernateSessionFactory {

	public static SessionFactory createSession(String dbType) {

		if ("mySql".equals(dbType)) {
			ISessionFactory sf = new HibernateSessionFactoryMySQL();
			return sf.getSession();
		} else if ("hsqlDb".equals(dbType)) {
			ISessionFactory sf = new HibernateSessionFactoryHSQLDB();
			return sf.getSession();
		} else {
			return null;
		}

	}

}
