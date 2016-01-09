package com.consensus.prototypes.mongoose.storm;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.consensus.prototypes.mongoose.storm.bolt.ReadDatabaseBolt;
import com.consensus.prototypes.mongoose.storm.hibernate.HibernateSessionFactory;
import com.consensus.prototypes.mongoose.storm.hibernate.HibernateUtil;
import com.consensus.prototypes.mongoose.storm.model.GpaScore;

public class TestReadGpaScoreBolt {

	SessionFactory sessionFactory = HibernateSessionFactory
			.createSession("hsqlDb");

	@Before
	public void setUp() throws Exception {
		// instantiate database
		HibernateUtil hibernateUtil = new HibernateUtil();
		hibernateUtil.setSessionFactory(sessionFactory);
		GpaScore saveGPAScore = new GpaScore();
		saveGPAScore.setStudentId("1");
		saveGPAScore.setGpaScore(400.0);
		saveGPAScore.setPrestige(3.00);
		hibernateUtil.saveData(saveGPAScore);
	}

	@Test
	public void test() {
		// given
		Tuple tuple = mock(Tuple.class);
		when(tuple.getSourceComponent()).thenReturn("irrelevant_component_id");
		when(tuple.getSourceStreamId()).thenReturn("irrelevant_stream_id");
		when(tuple.getString(1)).thenReturn("randomstring");
		when(tuple.getString(0)).thenReturn(
				"{\"studentId\":\"1\",\"greScore\":3.33}");

		ReadDatabaseBolt bolt = new ReadDatabaseBolt(sessionFactory);
		BasicOutputCollector collector = mock(BasicOutputCollector.class);

		// when
		bolt.execute(tuple, collector);

		// then
		verify(collector).emit(new Values("1", 3.33, 400.0, 3.00, "randomstring"));
	}

}