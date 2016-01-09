package com.consensus.prototypes.mongoose.storm.ruleEngine;

import java.io.Serializable;

public class RuleResult<T> implements Serializable {
	private static final long serialVersionUID = -814338735061137442L;
	private int rid;
	private T value;
	private String desc;

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
