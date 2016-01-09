package com.consensus.prototypes.mongoose.storm.bolt;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.consensus.prototypes.mongoose.storm.hibernate.HibernateUtil;
import com.consensus.prototypes.mongoose.storm.model.GpaScore;
import com.consensus.prototypes.mongoose.storm.model.GreScore;

import flexjson.JSONDeserializer;

public class ReadDatabaseBolt extends BaseBasicBolt {
	private static final long serialVersionUID = 5367444069616478627L;
	private final Logger LOG = LoggerFactory.getLogger(ReadDatabaseBolt.class);
	private SessionFactory sessionFactory = null;

	public ReadDatabaseBolt(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void execute(Tuple input, BasicOutputCollector collector) {
		try {
			String jsonData = input.getString(0);
			Object retInfo = input.getString(1);

			HibernateUtil hibernateUtil = new HibernateUtil();
			hibernateUtil.setSessionFactory(sessionFactory);

			LOG.info("=================================== deserializing: "
					+ jsonData);
			GreScore inputData = new JSONDeserializer<GreScore>().deserialize(
					jsonData, GreScore.class);
			String studentId = inputData.getStudentId();
			double greScore = inputData.getGreScore();
			GpaScore gpaScore = hibernateUtil.getData(studentId);
			collector.emit(new Values(studentId, greScore, gpaScore
					.getGpaScore(), gpaScore.getPrestige(), retInfo));
		} catch (Exception ex) {
			LOG.error("Failed in ReadCustomerDetailsBolt!!!! ", ex);
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("studentid", "grescore", "gpascore",
				"prestige", "return-info"));
	}

}
