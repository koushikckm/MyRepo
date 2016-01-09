package com.consensus.prototypes.mongoose.storm.ruleEngine.rule;

import java.io.Serializable;

import backtype.storm.tuple.Tuple;

import com.consensus.prototypes.mongoose.storm.ruleEngine.RuleInterface;
import com.consensus.prototypes.mongoose.storm.ruleEngine.RuleResult;
import com.consensus.prototypes.mongoose.storm.ruleEngine.RuleValue;

public class ValidatorRule implements RuleInterface, Serializable {

	private static final long serialVersionUID = -5391558468379518937L;

	public RuleResult<Integer> validate(Tuple tuple) {
		double admitprobability = Double.parseDouble(tuple
				.getStringByField("admitprobability"));

		RuleResult<Integer> ruleResult = new RuleResult<Integer>();
		ruleResult.setRid(1);
		if (admitprobability >= 1.0) {
			ruleResult.setValue(RuleValue.Success);
			return ruleResult;
		} else {
			ruleResult.setValue(RuleValue.Error);
			return ruleResult;
		}
	}
}
