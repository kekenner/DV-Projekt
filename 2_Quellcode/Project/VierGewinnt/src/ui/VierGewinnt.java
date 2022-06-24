package ui;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.MeinServerTest;

public class VierGewinnt { // Hauptklasse

	private FieldValue currentPlayer;
	private ArrayList<Field> fields; // Arraylist die Daten vom Typ Field beinhaltet
	private static net.MeinServerTest server = new net.MeinServerTest();
	private static net.MeinClientTest client = new net.MeinClientTest();

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
			client.connect(serverID);
		} else if (n == JOptionPane.YES_OPTION) {
			server.connect();
			if (server.isText()) {
				// hier Label einfügen: "Warten auf Gegenspieler..."
			} else {
				// Label deaktivieren
			}
		}


	instance.window.setCurrentPlayerLabelText("");

	String spieler1 = JOptionPane.showInputDialog(null, "Wie heißt der erste Spieler?", "Spieler 1");
	String spieler2 = JOptionPane.showInputDialog(null, "Wie heißt der zweite Spieler?",
			"Spieler 2");instance.window.getGamePanel().getGameLogic().getSpieler1().setName(spieler1);instance.window.getGamePanel().getGameLogic().getSpieler2().setName(spieler2);instance.window.setCurrentPlayerLabelText("Aktueller Spieler ist "+spieler1);

	}

	public VierGewinnt() { // Konstruktor
		window = new Gamewindow(650, 650); // Größe des Gamewindow wird festgelegt
		initGame(); // Methode initGame

	}

	public void initGame() {
		// Game Objekte
		// PLayer
		currentPlayer = FieldValue.Spieler1;
		// Fields
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

	public void nextPlayerTurn() { // Methode die, Reihenfolge der Spieler festlegt
		if (currentPlayer == FieldValue.Spieler1) {
			currentPlayer = FieldValue.Spieler2;

		} else {
			currentPlayer = FieldValue.Spieler1;
		}
		window.setCurrentPlayerLabelText(
				"Spieler " + window.getGamePanel().getGameLogic().getAktuellerSpieler().getName() + " ist am Zug!");
	}

	public FieldValue getcurrentPlayer() { // Get methode für den aktuellen Spieler
		return currentPlayer;

	}

	public ArrayList<Field> getFields() { // Get Methode für das aktuelle Feld
		return fields;
	}
}
