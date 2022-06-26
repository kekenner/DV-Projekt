package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Die Klasse MeinClientTest umfasst die Clientseite der Netzwerkanwendung mit den Methoden connect(); send(String spalte) und empfange().
 * 
 * @author Marven Schwarz, Kevin Kenner
 *
 */
public class MeinClientTest {
	
	private Socket clientSocket; // clientSocket wird vom Server verwendet um Daten zum Client zu senden oder vom
								// Client zu empfangen
	public BufferedReader in; // BufferedReader wird verwendet, um Daten aus dem clientSocket zu lesen.
	public PrintWriter out; // PrintWriter wird verwendet, um Daten in das clientSocket zu schreiben.
	private int spalte;
	
	/**
	 * Die Methode connect() der Clientseite erzeugt einen neuen clientSocket. Dieser Socket ist für den Datenverkehr zwischen Server und Client verantwortlich.
	 * Außerdem wird über den clientSocket die Verbindungsanfrage an den Server mit der zuvor übergebenen IP Adresse des Servers und einer Portnummer gestellt.
	 * Das Attribut out wird als ein Objekt der Klasse PrintWriter initialisiert. PrintWriter ist ein Ausgabestrom für den clientSocket.
	 * Das Attribut in wird als Objekt der Klasse BufferedReader initialisiert. Der BufferedReader ist der Eingabestrom, der die eingegebenen Daten in
	 * ein nutzbares Format umwandelt.
	 * @param IPAdresse
	 */
	public void connect(String IPAdresse) {
	try
	{
		clientSocket = new Socket(IPAdresse, 25000); // Socket des Client deklarieren. Dieser bekommt die
															// IP-Adresse des Server und den Port ï¿½bergeben.
		out = new PrintWriter(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
	}catch(
			IOException e)
			{
				e.printStackTrace();
			}
	}

	/**
	 * In der Methode send() wird die übergebene Spaltennummer in den clientSocket geschrieben und anschließend gesendet.
	 * @param String Spalte
	 */
	public void send(String Spalte) {
		out.println(Spalte); // schreibt die Daten in den clientSocket
		out.flush(); // sendet die Daten an den Client
	}
	
	/**
	 * In der Methode empfange wird ausgelesen ob im BufferedReader Daten enthalten sind. Da wir die Spaltennummer als String versenden
	 * wird der String in eine Integer umgewandelt und als Rückgabewert zurückgegeben.
	 * @return int spalte
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
