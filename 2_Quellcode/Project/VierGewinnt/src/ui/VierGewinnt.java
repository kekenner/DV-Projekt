package ui;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.MeinServerTest;

/**
 * "VierGewinnt" ist die Hauptklasse der GUI
 * @author SimonFluck, mariusmauth, Edit: Marven Schwarz, Kevin Kenner
 */
public class VierGewinnt { 

	private FieldValue currentPlayer;
	private ArrayList<Field> fields; // Arraylist die Daten vom Typ Field beinhaltet
	private net.MeinServerTest server = new net.MeinServerTest();
	private net.MeinClientTest client = new net.MeinClientTest();
	private boolean iAmServer = false;

	private Gamewindow window;

	/**
	 * Instanz des Objekt VierGewinnt
	 */
	public static VierGewinnt instance;

	/**
	 * In der main Methode wird ein Objekt der Klasse VierGewinnt erzeugt.
	 * Außerdem wird bei jedem Spieler ein Fenster erzeugt in dem der
	 * Spieler wählen kann ob er selber einen Server startet oder sich mit einem Server verbinden will.
	 * @param args Übergabearray für die main Methode
	 */
	public static void main(String[] args) {
		instance = new VierGewinnt();

		Object[] options = { "Server starten", "Mit Server verbinden..." };
		Component frame = null;
		int n = JOptionPane.showOptionDialog(frame, "Bitte wähle, ob du einen Server starten möchtest oder dich mit einem Server verbinden möchtest!",
				" Server/Client Auswahl!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		if (n == JOptionPane.NO_OPTION) {
			String serverID = JOptionPane.showInputDialog(null, "Bitte geben sie die IP Adresse des Server ein!", "Server IP Adresse");
			instance.client.connect(serverID);
			instance.iAmServer = false;
			String spieler2 = JOptionPane.showInputDialog(null, "Gib deinen Name ein:","Dein Name");
			instance.window.getGamePanel().getGameLogic().getSpieler2().setName(spieler2);
			instance.window.getGamePanel().disableGameField();
			instance.window.getGamePanel().handleOtherPlayerMove();
		} else if (n == JOptionPane.YES_OPTION) {
			instance.server.connect();
			instance.iAmServer = true;
			JOptionPane.showMessageDialog(null,"Gegenspieler Gefunden!","Klicke auf OK um fortzufahren!",JOptionPane.INFORMATION_MESSAGE);
			String spieler1 = JOptionPane.showInputDialog(null, "Gib deinen Name ein:", "Dein Name");
			instance.window.getGamePanel().getGameLogic().getSpieler1().setName(spieler1);
			instance.window.setCurrentPlayerLabelText("Aktueller Spieler ist "+spieler1);
		}


		instance.window.setCurrentPlayerLabelText("");
	}
	
	/**
	 * Konstruktor der Klasse "VierGewinnt" in der ein Objekt der Klasse Gamewindow erzeugt wird, der aktuelle Spieler gesetzt wird und
	 * mit der Methode initGame ein neues Spielfeld initialisiert wird.
	 */
	public VierGewinnt() { 
		window = new Gamewindow(650, 650); // Größe des Gamewindow wird festgelegt
		currentPlayer = FieldValue.Spieler1;
		initGame(); 

	}
	/**
	 * Methode "initGame" platziert das Spielfeld und unterteilt es in 42 einzelne Felder. 
	 */
	public void initGame() {
		
		int fieldsMarginLeft = 13; // Abstand linker Rand in Pixel
		int fieldsMarginTop = 35; // Abstand oberer Rand in Pixel
		int fieldWidth = 610 / 7; // Breite geteilt durch 7 Spalten
		int fieldHeight = 510 / 6; // Höhe geteilt durch 6 Zeilen

		fields = new ArrayList<Field>(); // Fields Arraylist werden die Felder hinzugefügt

		// Platzierung der Felder der ersten Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 0, fieldWidth, fieldHeight, 0, 5));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 1, 5));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 2, 5));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 3, 5));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 4, 5));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 5, 5));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 6, 5));
		
		// Platzierung der Felder der zweiten Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 1 * fieldHeight, fieldWidth, fieldHeight, 0, 4));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 1 * fieldHeight, fieldWidth,
				fieldHeight, 1, 4));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 1 * fieldHeight, fieldWidth,
				fieldHeight, 2, 4));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 1 * fieldHeight, fieldWidth,
				fieldHeight, 3, 4));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 1 * fieldHeight, fieldWidth,
				fieldHeight, 4, 4));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 1 * fieldHeight, fieldWidth,
				fieldHeight, 5, 4));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 1 * fieldHeight, fieldWidth,
				fieldHeight, 6, 4));
		
		// Platzierung der Felder der dritten Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 2 * fieldHeight, fieldWidth, fieldHeight, 0, 3));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 2 * fieldHeight, fieldWidth,
				fieldHeight, 1, 3));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 2 * fieldHeight, fieldWidth,
				fieldHeight, 2, 3));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 2 * fieldHeight, fieldWidth,
				fieldHeight, 3, 3));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 2 * fieldHeight, fieldWidth,
				fieldHeight, 4, 3));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 2 * fieldHeight, fieldWidth,
				fieldHeight, 5, 3));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 2 * fieldHeight, fieldWidth,
				fieldHeight, 6, 3));
		
		// Platzierung der Felder der vierten Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 3 * fieldHeight, fieldWidth, fieldHeight, 0, 2));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 3 * fieldHeight, fieldWidth,
				fieldHeight, 1, 2));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 3 * fieldHeight, fieldWidth,
				fieldHeight, 2, 2));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 3 * fieldHeight, fieldWidth,
				fieldHeight, 3, 2));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 3 * fieldHeight, fieldWidth,
				fieldHeight, 4, 2));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 3 * fieldHeight, fieldWidth,
				fieldHeight, 5, 2));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 3 * fieldHeight, fieldWidth,
				fieldHeight, 6, 2));
		
		// Platzierung der Felder der fünften Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 4 * fieldHeight, fieldWidth, fieldHeight, 0, 1));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 4 * fieldHeight, fieldWidth,
				fieldHeight, 1, 1));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 4 * fieldHeight, fieldWidth,
				fieldHeight, 2, 1));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 4 * fieldHeight, fieldWidth,
				fieldHeight, 3, 1));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 4 * fieldHeight, fieldWidth,
				fieldHeight, 4, 1));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 4 * fieldHeight, fieldWidth,
				fieldHeight, 5, 1));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 4 * fieldHeight, fieldWidth,
				fieldHeight, 6, 1));
		
		// Platzierung der Felder der sechsten Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 5 * fieldHeight, fieldWidth, fieldHeight, 0, 0));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 5 * fieldHeight, fieldWidth,
				fieldHeight, 1, 0));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 5 * fieldHeight, fieldWidth,
				fieldHeight, 2, 0));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 5 * fieldHeight, fieldWidth,
				fieldHeight, 3, 0));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 5 * fieldHeight, fieldWidth,
				fieldHeight, 4, 0));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 5 * fieldHeight, fieldWidth,
				fieldHeight, 5, 0));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 5 * fieldHeight, fieldWidth,
				fieldHeight, 6, 0));

	}
	
	/**
	 * Die Methode "nextPlayerTurn" legt Reihenfolge der Spieler fest.
	 * Zeigt an welcher Spieler gerade am Zug ist.
	 */
	public void nextPlayerTurn() { 
		if (currentPlayer == FieldValue.Spieler1) {
			currentPlayer = FieldValue.Spieler2;

		} else {
			currentPlayer = FieldValue.Spieler1;
		}
		window.setCurrentPlayerLabelText(
				"Spieler " + window.getGamePanel().getGameLogic().getAktuellerSpieler().getName() + " ist am Zug!");
	}
	
	/**
	 * Get Methode für den Spieler der gerade am Zug ist.
	 * @return currentPlayer Aktueller Spieler
	 */
	public FieldValue getcurrentPlayer() { 
		return currentPlayer;

	}

	/**
	 * Get Methode für das aktuelle Feld eines Spielsteins.
	 * @return fields Spielfelder
	 */
	public ArrayList<Field> getFields() { 
		return fields;
	}

	/**
	 * Get Methode für die erzeugte Serverinstanz. Diese Get Methode ermöglicht das Zugreifen auf das server Objekt aus anderen Klassen heraus.
	 * @return server Server
	 */
	public net.MeinServerTest getServer() {
		return server;
	}

	/**
	 * Get Methode für die erzeugte Clientinstanz. Diese Get Methode ermöglicht das Zugreifen auf das client Objekt aus anderen Klassen heraus.
	 * @return client Client
	 */
	public net.MeinClientTest getClient() {
		return client;
	}

	/**
	 * Get Methode für die Abfrage ob gerade der Server oder der Client der aktuelle Spieler ist.
	 * @return iAmServer Server Client Abfrage
	 */
	public boolean iAmServer() {
		return iAmServer;
	}
}
