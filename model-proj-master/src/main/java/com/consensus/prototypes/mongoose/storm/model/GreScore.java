package com.consensus.prototypes.mongoose.storm.model;

import java.io.Serializable;

public class GreScore implements Serializable {
	private static final long serialVersionUID = 2961438809102579972L;
	private String studentId;
	private Double greScore;

	public Double getGreScore() {
		return greScore;
	}

	public void setGreScore(Double greScore) {
		this.greScore = greScore;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

}
