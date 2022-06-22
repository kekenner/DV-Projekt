package net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MeinServer {
	public static void main(String[] args) {
		final ServerSocket serverSocket;		//serverSocket hört auf Verbindungsanfragen des Clients
		final Socket clientSocket;				//clientSocket wird vom Server verwendet um Daten zum Client zu senden oder vom Client zu empfangen
		final BufferedReader in;				//BufferedReader wird verwendet, um Daten aus dem clientSocket zu lesen.
		final PrintWriter out;					//PrintWriter wird verwendet, um Daten in das clientSocket zu schreiben.
		final Scanner sc = new Scanner(System.in);
		
		try {
			serverSocket = new ServerSocket(25000); //Wird verwendet um auf Verbindungsanforderungen vom Client zu hören.
			System.out.println("Server wartet auf eine Verbindungsanfrage des Client...");
			clientSocket = serverSocket.accept();	//Der Server Socket verwendet die Methode accept() um auf eine Anforderung des Client zu warten. Sobald er eine erhält, akzeptiert er sie und erstellt eine Instanz der Socket Klasse.
			System.out.println("Der Server ist mit dem Client verbunden.");
			out = new PrintWriter(clientSocket.getOutputStream());		//Instanziieren von out. getOutputStream ist ein Ausgabestrom für den clientSocket, da dieser für das Senden der Daten an den Client verantwortlich ist.
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));		//Instanziieren von in. Erstellt einen Stream Reader für den clientSocket, der die Daten als Bytes getInputStream erhällt. Der BufferedReader wandelt diese in Zeichen um.
			
			Thread sender = new Thread(new Runnable() {		//Hier werden Daten vom Benutzer (der Eingabe) gelesen und an den Client gesendet
				String msg;
				@Override
				public void run() {
					while(true) {
						msg = sc.nextLine();		//liest die Daten aus der Eingabezeile
						out.println(msg);		//schreibt die Daten in den clientSocket
						out.flush();		//sendet die Daten an den Client
					}
				}
			});
			sender.start();
			
			Thread receive = new Thread(new Runnable() {
				String msg;
				@Override
				public void run() {
					try {
						msg = in.readLine();
						while(msg!=null) {
							System.out.println("Client: "+msg);
							msg = in.readLine();
						}
						System.out.println("Client ist nicht mehr verbunden.");
						
						//out.close();
						//clientSocket.close();
						//serverSocket.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			receive.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
