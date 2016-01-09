package com.consensus.prototypes.mongoose.storm.bolt;

import java.util.Map;

import backtype.storm.task.ShellBolt;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;

public class PredictionBolt extends ShellBolt implements IRichBolt {
    private static final long serialVersionUID = -2944145284614143111L;

    public PredictionBolt()	{
	super("python", "prediction_bolt.py");
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
	declarer.declare(new Fields("studentid", "admitprobability", "return-info"));
    }

    public Map<String, Object> getComponentConfiguration() {
	return null;
    }
}
