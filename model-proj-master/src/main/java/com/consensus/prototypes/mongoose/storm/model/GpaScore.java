package com.consensus.prototypes.mongoose.storm.model;

import java.io.Serializable;

public class GpaScore implements Serializable {
	private static final long serialVersionUID = 783776226320442696L;
	private int studentOId;
	private String studentId;
	private Double gpaScore;
	private Double prestige;
	
	public int getStudentOId() {
		return studentOId;
	}

	public void setStudentOId(int studentOId) {
		this.studentOId = studentOId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Double getGpaScore() {
		return gpaScore;
	}

	public void setGpaScore(Double gpaScore) {
		this.gpaScore = gpaScore;
	}

	public Double getPrestige() {
		return prestige;
	}

	public void setPrestige(Double prestige) {
		this.prestige = prestige;
	}
}
