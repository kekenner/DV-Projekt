package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MeinClientTest {
	
	private Socket clientSocket; // clientSocket wird vom Server verwendet um Daten zum Client zu senden oder vom
								// Client zu empfangen
	private BufferedReader in; // BufferedReader wird verwendet, um Daten aus dem clientSocket zu lesen.
	private PrintWriter out; // PrintWriter wird verwendet, um Daten in das clientSocket zu schreiben.
	private Scanner sc = new Scanner(System.in);
	
	public void connect() {
	try
	{
		clientSocket = new Socket("192.168.2.39", 25000); // Socket des Client deklarieren. Dieser bekommt die
															// IP-Adresse des Server und den Port übergeben.
		out = new PrintWriter(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
	}catch(
			IOException e)
			{
				e.printStackTrace();
			}
	}

	public void send() {
		Thread sender = new Thread(new Runnable() { // Hier werden Daten vom Benutzer (der Eingabe) gelesen und an den
													// Client gesendet
			String msg;

			@Override
			public void run() {
				while (true) {
					msg = sc.nextLine(); // liest die Daten aus der Eingabezeile
					out.println(msg); // schreibt die Daten in den clientSocket
					out.flush(); // sendet die Daten an den Client
				}
			}
		});
		sender.start();
	}
	
	public void empfange() {
		Thread receive = new Thread(new Runnable() {
			String msg;

			@Override
			public void run() {
				try {
					msg = in.readLine();
					while (msg != null) {
						System.out.println("Server: " + msg);
						msg = in.readLine();
					}
					System.out.println("Client disconnected");

					// out.close();
					// clientSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		receive.start();
	}
}
