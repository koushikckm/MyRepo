package com.consensus.prototypes.mongoose.storm.hibernate;

import org.hibernate.SessionFactory;

public interface ISessionFactory {
	public SessionFactory getSession();
}
