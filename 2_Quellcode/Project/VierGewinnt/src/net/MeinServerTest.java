package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class MeinServerTest {

	private ServerSocket serverSocket; // serverSocket h�rt auf Verbindungsanfragen des Clients
	private Socket clientSocket; // clientSocket wird vom Server verwendet um Daten zum Client zu senden oder vom
								// Client zu empfangen.
	public BufferedReader in; // BufferedReader wird verwendet, um Daten aus dem clientSocket zu lesen.
	public PrintWriter out; // PrintWriter wird verwendet, um Daten in das clientSocket zu schreiben.
	private Scanner sc = new Scanner(System.in);
	private int spalte;

	public void connect() {
	try
	{
		serverSocket = new ServerSocket(25000); // Wird verwendet um auf Verbindungsanforderungen vom Client zu h�ren.
		System.out.println("Server wartet auf eine Verbindungsanfrage des Client...");
		JOptionPane.showMessageDialog(null,
                "Warten auf Gegenspieler...",
                "Du wirst benachrichtigt sobald dein Gegenspeieler eingetroffen ist!",
                JOptionPane.INFORMATION_MESSAGE);
		clientSocket = serverSocket.accept(); // Der Server Socket verwendet die Methode accept() um auf eine
												// Anforderung des Client zu warten. Sobald er eine erh�lt, akzeptiert
												// er sie und erstellt eine Instanz der Socket Klasse.
		System.out.println("Der Server ist mit dem Client verbunden.");
		out = new PrintWriter(clientSocket.getOutputStream()); // Instanziieren von out. getOutputStream ist ein
																// Ausgabestrom f�r den clientSocket, da dieser f�r das
																// Senden der Daten an den Client verantwortlich ist.
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Instanziieren von in. Erstellt
																						// einen Stream Reader f�r den
																						// clientSocket, der die Daten
																						// als Bytes getInputStream
																						// erh�llt. Der BufferedReader
																						// wandelt diese in Zeichen um.
	}catch(
			IOException e)
			{
				e.printStackTrace();
			}
	}
	
//	public void send_old(String Spalte) {
//		Thread sender = new Thread(new Runnable() { // Hier werden Daten vom Benutzer (der Eingabe) gelesen und an den
//													// Client gesendet
//			
//			@Override
//			public void run() {
//
//					out.println(Spalte); // schreibt die Daten in den clientSocket
//					out.flush(); // sendet die Daten an den Client
//					System.out.println("Data send.");
//		
//			}
//		});
//		sender.start();
//	}
	
	public void send(String Spalte) {
		out.println(Spalte); // schreibt die Daten in den clientSocket
		out.flush(); // sendet die Daten an den Client
	}
	
//	public int empfange_old() {
//		
//		Thread receive = new Thread(new Runnable() {
//			String col;
//			
//			@Override
//			public void run() {
//				try {
//					col = in.readLine();
//					while (col != null) {
//						//System.out.println("Client: " + msg);
//						col = in.readLine();
//						spalte = Integer.parseInt(col);
//						System.out.println(spalte);
//					}
//					System.out.println("Client ist nicht mehr verbunden.");
//
//					// out.close();
//					// clientSocket.close();
//					// serverSocket.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		receive.start();
//		return spalte;
//	}
	
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
