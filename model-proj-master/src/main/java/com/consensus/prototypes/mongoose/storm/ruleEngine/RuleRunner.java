package com.consensus.prototypes.mongoose.storm.ruleEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RuleRunner {
	public List<RuleResult> execute(Set<RuleInterface> ruleSet) {

		ArrayList<RuleResult> resultList = new ArrayList<RuleResult>();
		for (RuleInterface rule : ruleSet) {
			// resultList.add(rule.validate());
		}
		return resultList;
	}
}
