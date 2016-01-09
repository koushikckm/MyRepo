package com.consensus.prototypes.mongoose.storm.ruleEngine;

import backtype.storm.tuple.Tuple;

public interface RuleInterface {
	public RuleResult<Integer> validate(Tuple tuple);
}
