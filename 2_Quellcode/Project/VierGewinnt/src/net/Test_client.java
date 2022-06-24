package net;

public class Test_client {

	public static void main(String[] args) {
		
		final MeinClientTest client = new MeinClientTest();
		
		client.connect();
		client.send();
		client.empfange();
	}
}
