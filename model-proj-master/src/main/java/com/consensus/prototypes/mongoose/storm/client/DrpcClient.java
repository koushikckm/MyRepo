package com.consensus.prototypes.mongoose.storm.client;

import org.apache.thrift7.TException;

import backtype.storm.generated.DRPCExecutionException;
import backtype.storm.utils.DRPCClient;

public class DrpcClient {

	public static void main(String[] args) {

		for (String request : new String[] { "{\"studentId\":\"3\",\"greScore\":800.0}" }) {
			System.out.println("Sending: " + request);
			DRPCClient client = new DRPCClient("127.0.0.1", 3772);
			try {
				System.out.println("Result: "
						+ client.execute("mongoose", request));
			} catch (TException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DRPCExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}