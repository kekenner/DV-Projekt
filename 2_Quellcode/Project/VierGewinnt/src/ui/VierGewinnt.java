package ui;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.MeinServerTest;

/**
 * "VierGewinnt" ist die Hauptklasse der GUI
 * @author SimonFluck, mariusmauth
 */
public class VierGewinnt { 

	private FieldValue currentPlayer;
	private ArrayList<Field> fields; // Arraylist die Daten vom Typ Field beinhaltet
	private net.MeinServerTest server = new net.MeinServerTest();
	private net.MeinClientTest client = new net.MeinClientTest();
	private boolean iAmServer = false;

	private Gamewindow window;

	public static VierGewinnt instance;

	public static void main(String[] args) {
		instance = new VierGewinnt();

		Object[] options = { "Server", "Client" };
		Component frame = null;
		int n = JOptionPane.showOptionDialog(frame, "Bitte wähle, ob du Server oder Client bist!",
				" Server/Client Auswahl!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		if (n == JOptionPane.NO_OPTION) {
			String serverID = JOptionPane.showInputDialog(null, "Bitte geben sie die Server ID ein!", "Server ID");
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
	 * Konstruktor der Klasse "VierGewinnt" mit der Methode "initGame"
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

		// Erzeugen der Felder der ersten Reihe
		fields.add(new Field(fieldsMarginLeft + 0, fieldsMarginTop + 0, fieldWidth, fieldHeight, 0, 5));
		fields.add(new Field(fieldsMarginLeft + 1 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 1, 5));
		fields.add(new Field(fieldsMarginLeft + 2 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 2, 5));
		fields.add(new Field(fieldsMarginLeft + 3 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 3, 5));
		fields.add(new Field(fieldsMarginLeft + 4 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 4, 5));
		fields.add(new Field(fieldsMarginLeft + 5 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 5, 5));
		fields.add(new Field(fieldsMarginLeft + 6 * fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 6, 5));
		
		// Erzeugen der Felder der zweiten Reihe
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
		
		// Erzeugen der Felder der dritten Reihe
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
		
		// Erzeugen der Felder der vierten Reihe
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
		
		// Erzeugen der Felder der fünften Reihe
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
		
		// Erzeugen der Felder der sechsten Reihe
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
	 * Get methode für den aktuellen Spieler
	 * @return
	 */
	public FieldValue getcurrentPlayer() { 
		return currentPlayer;

	}

	/**
	 * Get Methode für das aktuelle Feld
	 * @return
	 */
	public ArrayList<Field> getFields() { 
		return fields;
	}

	public net.MeinServerTest getServer() {
		return server;
	}

	public net.MeinClientTest getClient() {
		return client;
	}

	public boolean iAmServer() {
		return iAmServer;
	}
}
