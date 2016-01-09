package com.consensus.prototypes.mongoose.storm.topology;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.StormSubmitter;
import backtype.storm.drpc.ReturnResults;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.topology.TopologyBuilder;

public class MongooseTopology {

	public static void main(String[] args) {

		Config conf = new Config();
		conf.setDebug(true);
		// SessionFactory sessionFactory =
		// HibernateSessionFactory.createSession("mySql");

		if (args != null && args.length > 0) {
			TopologyBuilder builder = MongooseTopologyCreator
					.CreateTopologyBuilder(null, null);
			builder.setBolt("result", new ReturnResults(), 1).shuffleGrouping(
					"rulebolt");
			conf.setNumWorkers(1);
			try {
				StormSubmitter.submitTopologyWithProgressBar(args[0], conf,
						builder.createTopology());
			} catch (AlreadyAliveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidTopologyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			LocalDRPC drpc = new LocalDRPC();
			TopologyBuilder builder = MongooseTopologyCreator
					.CreateTopologyBuilder(drpc, null);
			builder.setBolt("result", new ReturnResults(), 1).shuffleGrouping(
					"rulebolt");
			conf.setMaxTaskParallelism(3);
			LocalCluster cluster = new LocalCluster();
			cluster.submitTopology("mongooseTopologyLocal", conf,
					builder.createTopology());

			for (String request : new String[] { "{\"studentId\":\"1\",\"greScore\":380.0}" }) {
				System.out.println("Result for: " + request + "\": "
						+ drpc.execute("mongoose", request));
			}
			cluster.shutdown();
			drpc.shutdown();
		}
	}
}
