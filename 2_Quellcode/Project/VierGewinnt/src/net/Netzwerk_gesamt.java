package net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/**
 * Auszuführende Klasse, die die Spiellogik, Benutzerinterface und Netzwerkanwendung kombiniert.
 * 
 * @author Marven Schwarz, Kevin Kenner
 * @version 1.0 2022-06-22
 *
 */
public class Netzwerk_gesamt implements Runnable{

	
	private String ip = "localhost";
	private int port = 25000;
	private Scanner scanner = new Scanner(System.in);
	private Thread thread;

	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;

	private ServerSocket serversocket;

	private boolean accepted = false;
	private boolean unableToCommunicateWithOpponent = false;
	private boolean yourTurn;

	/**
	 * Konstruktor 
	 * 
	 * Konstruktor, der die Eingabe der IP Adresse und des Ports ermöglicht. Anschließend wird auf Verbindung überprüft, falls keine Verbindung besteht wird ein Server initialisiert.
	 * Gegenspieler kann sich daraufhin verbinden. Es wird ein Thread gestartet.
	 * 
	 * @author Marven Schwarz, Kevin Kenner
	 * @version 1.0 2022-06-22
	 */
	public Netzwerk_gesamt() {

		System.out.println("Bitte geben Sie die IP Adresse ein: ");
		ip = scanner.nextLine();
		System.out.println("Bitte geben Sie den Port ein: ");
		port = scanner.nextInt();
		while (port < 1 || port > 65535) {
			System.out.println("Der eingegebene Port ist nicht erreichbar. Bitte geben Sie einen anderen Port ein:");
			port = scanner.nextInt();
		}
		
		if (!connect()) initializeServer();
		
		
		thread = new Thread(this, "Netzwerk_gesamt");
		thread.start();
	}
	
	/**
	 * Methode run ermöglicht das Setzen eines Zugs und das Aktualisieren des Spielfeldes. Anschließend wird auf eine neue Server Request gewartet.
	 * 
	 * @author Marven Schwarz, Kevin Kenner
	 * @version 1.0 2022-06-22
	 */
	public void run() {
		
		while (true) {
			//setzeZug() mit Variable dis ;
			//repaint();
			if (!accepted) listenForServerRequest();
		}
	}
	
	/**
	 * Methode listenForServerRequest wartet auf eine Verbindungsanfrage vom Gegenspieler und akzeptiert diese. 
	 * 
	 * @author Marven Schwarz, Kevin Kenner
	 * @version 1.0 2022-06-22
	 */
	public void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serversocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("Client hat eine Verbindung angefragt und diese wurde bestätigt.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Methode connect überprüft ob eine Verbindung zwischen Server und Client besteht und gibt entweder ein true oder ein false zurück.
	 * 
	 * @return Wenn keine Verbindung zwischen Server und Client besteht gibt die Methode ein false zurück, wenn eine Verbindung besteht gibt die Methode ein true zurück.
	 * @author Marven Schwarz, Kevin Kenner
	 * @version 1.0 2022-06-22
	 */
	private boolean connect() {
		try {
			socket = new Socket(ip,port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		}catch(IOException e){
			System.out.println("Verbindung zum Server mit der Adresse: " + ip + ":" + port + " nicht möglich. Server wird gestartet.");
			return false;
		}
		System.out.println("Verbindung zum Server war erfolgreich.");
		return true;
	}
	
	/**
	 * Methode initializeServer 
	 * 
	 * @author Marven Schwarz, Kevin Kenner
	 * @version 1.0 2022-06-22
	 */
	private void initializeServer() {
		try {
			serversocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
		yourTurn = true;
	}
	
	public static void main (String[] args) {
		Netzwerk_gesamt netzwerk = new Netzwerk_gesamt();
	}
	
	//Hier muss unserem Verständnis nach die Klasse GamePanel eingefügt werden, da diese Klasse die Methode mouseClicked beinhaltet. In der Methode mouseClicked wird die Position an der 
	//der Stein eingeworfen wurde in die Variable dos geschrieben und mit dos.flush() an den dis in der Methode setzeZug() gesendet.
}
