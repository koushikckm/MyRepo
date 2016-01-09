package com.consensus.prototypes.mongoose.storm.topology;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import backtype.storm.LocalDRPC;
import backtype.storm.drpc.DRPCSpout;
import backtype.storm.drpc.ReturnResults;
import backtype.storm.topology.TopologyBuilder;

import com.consensus.prototypes.mongoose.storm.bolt.PredictionBolt;
import com.consensus.prototypes.mongoose.storm.bolt.ReadDatabaseBolt;
import com.consensus.prototypes.mongoose.storm.bolt.RuleBolt;
import com.consensus.prototypes.mongoose.storm.ruleEngine.RuleInterface;
import com.consensus.prototypes.mongoose.storm.ruleEngine.rule.ValidatorRule;

public class MongooseTopologyCreator {

	public static TopologyBuilder CreateTopologyBuilder(LocalDRPC drpc,
			SessionFactory sessionFactory) {

		TopologyBuilder builder = new TopologyBuilder();
		if (drpc == null) {
			builder.setSpout("spout", new DRPCSpout("mongoose"), 1);
		} else {
			builder.setSpout("spout", new DRPCSpout("mongoose", drpc), 1);
		}
		builder.setBolt("gpabolt", new ReadDatabaseBolt(sessionFactory), 1)
				.shuffleGrouping("spout");
		builder.setBolt("predictbolt", new PredictionBolt(), 1)
				.shuffleGrouping("gpabolt");

		// inject the rules to ruleBolt
		List<RuleInterface> rules = new ArrayList<RuleInterface>();
		rules.add(new ValidatorRule());

		builder.setBolt("rulebolt", new RuleBolt(rules), 1).shuffleGrouping(
				"predictbolt");
		return builder;
	}
}
