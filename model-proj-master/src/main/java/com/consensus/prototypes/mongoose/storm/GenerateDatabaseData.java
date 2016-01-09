package com.consensus.prototypes.mongoose.storm;

import java.io.File;
import java.util.Scanner;

import com.consensus.prototypes.mongoose.storm.hibernate.HibernateSessionFactory;
import com.consensus.prototypes.mongoose.storm.hibernate.HibernateUtil;
import com.consensus.prototypes.mongoose.storm.model.GpaScore;

public class GenerateDatabaseData {

	public static void main(String[] args) {
		String csvFile = "etc/resources/sampleData.csv";
		Scanner br = null;
		String cvsSplitBy = ",";

		try {

			br = new Scanner(new File(csvFile));
			int studentId = 0;
			HibernateUtil hibernateUtil = new HibernateUtil();
			hibernateUtil.setSessionFactory(HibernateSessionFactory
					.createSession("mySql"));
			
			while (br.hasNext()) {
				String line = br.nextLine();
				if (studentId != 0) {
					String[] student = line.split(cvsSplitBy);
					GpaScore gpaScore = new GpaScore();
					gpaScore.setStudentId(new Integer(studentId).toString());
					gpaScore.setGpaScore(new Double(student[2]));
					gpaScore.setPrestige(new Double(student[3]));
					hibernateUtil.saveData(gpaScore);
				}
				studentId++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
