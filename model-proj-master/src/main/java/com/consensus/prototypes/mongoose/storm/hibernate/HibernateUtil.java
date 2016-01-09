package com.consensus.prototypes.mongoose.storm.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.consensus.prototypes.mongoose.storm.model.GpaScore;

public class HibernateUtil implements Serializable {
	private final Logger LOG = LoggerFactory.getLogger(HibernateUtil.class);
	private static final long serialVersionUID = 1044842292549556829L;
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		if (sessionFactory != null) {
			this.sessionFactory = sessionFactory;
		} else {
			this.sessionFactory = HibernateSessionFactory
					.createSession("mySql");
		}
	}

	public GpaScore getData(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Criteria criteria = session.createCriteria(GpaScore.class);
		criteria.add(Restrictions.eq("studentId", id));
		List<GpaScore> scores = criteria.list();
		GpaScore score = null;
		if (scores.size() > 0) {
			score = scores.get(0);
		}
		tx.commit();
		session.close();
		return score;
	}

	public void saveData(GpaScore score) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(score);
		session.getTransaction().commit();
		session.close();
	}
}
