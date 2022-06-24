package net;

public class Test_server {

	public static void main(String[] args) {
		
		final MeinServerTest server = new MeinServerTest();
		
		server.connect();
		server.send();
		server.empfange();
	}
}
