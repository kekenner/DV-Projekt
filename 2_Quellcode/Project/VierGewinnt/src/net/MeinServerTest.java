package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Die Klasse MeinServerTest umfasst die Serverseite der Netzwerkanwendung mit den Methoden connect(); send(String spalte) und empfange().
 * 
 * @author Marven Schwarz, Kevin Kenner
 */
public class MeinServerTest {

	/**
	 * serverSocket hört auf Verbindungsanfragen des Clients
	 */
	private ServerSocket serverSocket;
	/**
	 * clientSocket wird vom Server verwendet um Daten zum Client zu senden oder vom Client zu empfangen.
	 */
	private Socket clientSocket;
	/**
	 * BufferedReader wird verwendet, um Daten aus dem clientSocket zu lesen.
	 */
	public BufferedReader in;
	/**
	 * PrintWriter wird verwendet, um Daten in das clientSocket zu schreiben.
	 */
	public PrintWriter out;
	/**
	 * Spaltennummer, die versendet wird.
	 */
	private int spalte;
	
	/**
	 * Die Methode connect() der Serverseite erzeugt einen neuen ServerSocket.
	 * Dieser Socket ist für die Verbindung zwischen Server und Client verantwortlich.
	 * Wenn eine Verbindungsanfrage vom Client kommt wird diese über die accept() Methode akzeptiert. Ab diesem Zeitpunkt besteht eine Verbindung zwischen
	 * Server und Client.
	 * Der clientSocket ist der Socket über den Daten zwischen Server und Client gesendet und empfangen werden.
	 * Außerdem wird out als ein Objekt der Klasse PrintWriter initialisiert. PrintWriter ist ein Ausgabestrom für den clientSocket.
	 * Das Attribut in wird als Objekt der Klasse BufferedReader initialisiert. Der BufferedReader ist der Eingabestrom, der die eingegebenen Daten in
	 * ein nutzbares Format umwandelt.
	 */
	public void connect() {
	try
	{
		serverSocket = new ServerSocket(25000); // Wird verwendet um auf Verbindungsanforderungen vom Client zu hï¿½ren.
		System.out.println("Server wartet auf eine Verbindungsanfrage des Client...");
		JOptionPane.showMessageDialog(null,
                "Warten auf Gegenspieler...",
                "Du wirst benachrichtigt sobald dein Gegenspeieler eingetroffen ist!",
                JOptionPane.INFORMATION_MESSAGE);
		clientSocket = serverSocket.accept(); // Der Server Socket verwendet die Methode accept() um auf eine
												// Anforderung des Client zu warten. Sobald er eine erhï¿½lt, akzeptiert
												// er sie und erstellt eine Instanz der Socket Klasse.
		System.out.println("Der Server ist mit dem Client verbunden.");
		out = new PrintWriter(clientSocket.getOutputStream()); // Instanziieren von out. getOutputStream ist ein
																// Ausgabestrom fï¿½r den clientSocket, da dieser fï¿½r das
																// Senden der Daten an den Client verantwortlich ist.
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // Instanziieren von in. Erstellt
																						// einen Stream Reader fï¿½r den
																						// clientSocket, der die Daten
																						// als Bytes getInputStream
																						// erhï¿½llt. Der BufferedReader
																						// wandelt diese in Zeichen um.
	}catch(
			IOException e)
			{
				e.printStackTrace();
			}
	}
	
	/**
	 * In der Methode send() wird die übergebene Spaltennummer in den clientSocket geschrieben und anschließend gesendet.
	 * @param Spalte Spaltennummer
	 */
	public void send(String Spalte) {
		out.println(Spalte); 
		out.flush(); 
	}
	
	/**
	 * In der Methode empfange() wird ausgelesen ob im BufferedReader Daten enthalten sind.
	 * Da die Spaltennummer als String versendet wird, wird der String in eine Integer umgewandelt und als Rückgabewert zurückgegeben.
	 * @return spalte Spaltennummer
	 */
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
