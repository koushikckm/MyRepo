package com.consensus.prototypes.mongoose.storm.model;

import java.io.Serializable;

public class InputJson implements Serializable {

	private static final long serialVersionUID = 1L;

	GreScore input;
	String result;

	public GreScore getInput() {
		return input;
	}

	public void setInput(GreScore input) {
		this.input = input;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
