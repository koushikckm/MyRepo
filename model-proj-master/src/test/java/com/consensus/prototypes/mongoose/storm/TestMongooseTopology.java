package com.consensus.prototypes.mongoose.storm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import junit.framework.TestCase;

import org.hibernate.SessionFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import backtype.storm.Config;
import backtype.storm.ILocalCluster;
import backtype.storm.LocalDRPC;
import backtype.storm.Testing;
import backtype.storm.generated.StormTopology;
import backtype.storm.testing.CompleteTopologyParam;
import backtype.storm.testing.MkClusterParam;
import backtype.storm.testing.MockedSources;
import backtype.storm.testing.TestJob;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Values;

import com.consensus.prototypes.mongoose.storm.hibernate.HibernateSessionFactory;
import com.consensus.prototypes.mongoose.storm.hibernate.HibernateUtil;
import com.consensus.prototypes.mongoose.storm.model.GpaScore;
import com.consensus.prototypes.mongoose.storm.model.InputJson;
import com.consensus.prototypes.mongoose.storm.topology.MongooseTopologyCreator;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class TestMongooseTopology extends TestCase {

	public void testBasicTopology() {
		MkClusterParam mkClusterParam = new MkClusterParam();
		mkClusterParam.setSupervisors(4);
		Config daemonConf = new Config();
		daemonConf.put(Config.STORM_LOCAL_MODE_ZMQ, false);
		mkClusterParam.setDaemonConf(daemonConf);

		Testing.withLocalCluster(mkClusterParam, new TestJob() {
			public void run(ILocalCluster cluster) {
				SessionFactory sessionFactory = HibernateSessionFactory
						.createSession("hsqlDb");
				LocalDRPC drpc = new LocalDRPC();
				TopologyBuilder builder = MongooseTopologyCreator
						.CreateTopologyBuilder(drpc, sessionFactory);

				StormTopology topology = builder.createTopology();

				/* Read from yaml file and load test data in hsqldb database */
				HibernateUtil hibernateUtil = new HibernateUtil();
				hibernateUtil.setSessionFactory(sessionFactory);
				try {
					Scanner reader;
					String dir = System.getProperty("user.dir")
							+ "\\etc\\resources\\input_test\\dbdata.yaml";

					File files = new File(dir);
					InputStream inputStream = new FileInputStream(files);

					Constructor constructor = new Constructor(GpaScore.class);
					TypeDescription resultDescription = new TypeDescription(
							GpaScore.class);
					constructor.addTypeDescription(resultDescription);
					Yaml yaml = new Yaml(constructor);
					
					for(Object data: yaml.loadAll(inputStream))
					{
						GpaScore gpaScore = (GpaScore) data;
						hibernateUtil.saveData(gpaScore);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				/* Read from json file to mock the data */
				List<Values> scores = new ArrayList<Values>();
				List<Values> expectedResults = new ArrayList<Values>();

				try {
					Scanner reader;
					String dir = System.getProperty("user.dir")
							+ "\\etc\\resources\\input_test\\testdata.yaml";

					File files = new File(dir);
					InputStream inputStream = new FileInputStream(files);

					Constructor constructor = new Constructor(InputJson.class);
					TypeDescription resultDescription = new TypeDescription(
							InputJson.class);
					constructor.addTypeDescription(resultDescription);
					Yaml yaml = new Yaml(constructor);
					
					for(Object data: yaml.loadAll(inputStream))
					{
						//InputJson lineData = (InputJson) yaml.load(data);
						InputJson lineData = (InputJson) data;
						String randomId = UUID.randomUUID().toString();
						String retInfo = "{\"port\":0,\"id\":\""
								+ lineData.getInput().getStudentId()
								+ "\",\"host\":\"85ad1bfe-388a-4c63-9bbe-e1eab2c50a1f\"}";
	
						System.out
								.println("========================================"
										+ inputStream);
	
						String input = new JSONSerializer().serialize(lineData
								.getInput());
	
						Values value = new Values(input, retInfo);
						scores.add(value);
	
						Values result = new Values(lineData.getResult(), retInfo);
						expectedResults.add(result);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				MockedSources mockedSources = new MockedSources();
				Values[] arr = new Values[scores.size()];
				mockedSources.addMockData("spout", scores.toArray(arr));

				// prepare the config
				Config conf = new Config();
				conf.setNumWorkers(2);
				CompleteTopologyParam completeTopologyParam = new CompleteTopologyParam();
				completeTopologyParam.setMockedSources(mockedSources);
				completeTopologyParam.setStormConf(conf);

				Map allBoltResults = Testing.completeTopology(cluster, topology,
						completeTopologyParam);

				System.out.println("****All Bolt Results*****" + allBoltResults);
				System.out.println("****Expected Result*****" + expectedResults);
				System.out.println("****Returned Result*****" + Testing.readTuples(allBoltResults, "rulebolt"));
				assertTrue(Testing.multiseteq(scores,
						Testing.readTuples(allBoltResults, "spout")));
				assertTrue(Testing.multiseteq(expectedResults,
						Testing.readTuples(allBoltResults, "rulebolt")));
			}
		});
	}
}
