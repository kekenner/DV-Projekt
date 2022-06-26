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
	public BufferedReader in; // BufferedReader wird verwendet, um Daten aus dem clientSocket zu lesen.
	public PrintWriter out; // PrintWriter wird verwendet, um Daten in das clientSocket zu schreiben.
	private Scanner sc = new Scanner(System.in);
	private int spalte;
	
	public void connect(String IPAdresse) {
	try
	{
		clientSocket = new Socket(IPAdresse, 25000); // Socket des Client deklarieren. Dieser bekommt die
															// IP-Adresse des Server und den Port �bergeben.
		out = new PrintWriter(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
	}catch(
			IOException e)
			{
				e.printStackTrace();
			}
	}

	public void send_old(String Spalte) {
		Thread sender = new Thread(new Runnable() { // Hier werden Daten vom Benutzer (der Eingabe) gelesen und an den
													// Client gesendet
			
			@Override
			public void run() {

					 // liest die Daten aus der Eingabezeile
					out.println(Spalte); // schreibt die Daten in den clientSocket
					out.flush(); // sendet die Daten an den Client
					System.out.println("Data send.");
		
			}
		});
		sender.start();
	}
	
	public void send(String Spalte) {
		out.println(Spalte); // schreibt die Daten in den clientSocket
		out.flush(); // sendet die Daten an den Client
	}
	
	public int empfange_old() {
		
		Thread receive = new Thread(new Runnable() {
			String col;
			
			@Override
			public void run() {
				try {
					col = in.readLine();
					while (col != null) {
						//System.out.println("Server: " + msg);
						col = in.readLine();
						spalte = Integer.parseInt(col);
						System.out.println(spalte);
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
		return spalte;
	}
	
	public int empfange() {

		String col;
		try {
			col = in.readLine();
			spalte = Integer.parseInt(col);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return spalte;
	}
}
