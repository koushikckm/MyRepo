package com.consensus.prototypes.mongoose.storm.bolt;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.consensus.prototypes.mongoose.storm.ruleEngine.RuleInterface;
import com.consensus.prototypes.mongoose.storm.ruleEngine.RuleResult;

public class RuleBolt extends BaseBasicBolt {
    private final Logger LOG = LoggerFactory.getLogger(RuleBolt.class);
    private static final long serialVersionUID = -4176044879978324666L;
    List<RuleInterface> rules;

    public RuleBolt(List<RuleInterface> rules) {
	this.rules = rules;
    }

    public void execute(Tuple tuple, BasicOutputCollector collector) {
	Object retInfo = tuple.getValueByField("return-info");
	List<RuleResult<Integer>> ruleResults = new ArrayList<RuleResult<Integer>>();
	for (RuleInterface rule : rules) {
	    RuleResult<Integer> ruleResult = rule.validate(tuple);
	    System.out.println("Value : " + ruleResult.getValue());
	    ruleResults.add(ruleResult);
	}
	String result = ruleResults.get(ruleResults.size()-1).getValue().toString();
	
	collector.emit(new Values(result, retInfo));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
	declarer.declare(new Fields("result", "return-info"));
    }

}
