package ui;

import java.util.ArrayList;

public class VierGewinnt {                           // Hauptklasse
	
	private FieldValue currentPlayer;
	private ArrayList<Field> fields;                 // Arraylist die Daten vom Typ Field beinhaltet
	
	private Gamewindow window; 
	
	public static VierGewinnt instance; 
	
	public static void main(String[] args) {
		instance = new VierGewinnt();
	}

	public VierGewinnt() {                            // Konstruktor
		window = new Gamewindow(450, 510);            // Größe des Gamewindow wird festgelegt
		initGame();                                   // Methode initGame
		
		
	}
	
	public void initGame() { 
		// Game Objekte
		// PLayer
		   currentPlayer = FieldValue.Spieler1;
		   nextPlayerTurn();
		// Fields   
		int fieldsMarginLeft = 15;        // Abstand linker Rand in Pixel
		int fieldsMarginTop = 11;         // Abstand oberer Rand in Pixel
		int fieldWidth = 411/7;           // Breite geteilt durch 7 Spalten
		int fieldHeight = 411/6;          // Höhe geteilt durch 6 Zeilen
		
		fields = new ArrayList<Field>();  // Fields Arraylist werden die Felder hinzugefügt
		
		// Erzeugen der Felder der ersten Reihe
		fields.add(new Field(fieldsMarginLeft + 0,            fieldsMarginTop + 0, fieldWidth, fieldHeight, 0, 5));
		fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 1, 5));
		fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 2, 5));
		fields.add(new Field(fieldsMarginLeft + 3*fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 3, 5));
		fields.add(new Field(fieldsMarginLeft + 4*fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 4, 5));
		fields.add(new Field(fieldsMarginLeft + 5*fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 5, 5));
		fields.add(new Field(fieldsMarginLeft + 6*fieldWidth, fieldsMarginTop + 0, fieldWidth, fieldHeight, 6, 5));
		
		fields.add(new Field(fieldsMarginLeft + 0,            fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 0, 4));
		fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 1, 4));
		fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 2, 4));
		fields.add(new Field(fieldsMarginLeft + 3*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 3, 4));
		fields.add(new Field(fieldsMarginLeft + 4*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 4, 4));
		fields.add(new Field(fieldsMarginLeft + 5*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 5, 4));
		fields.add(new Field(fieldsMarginLeft + 6*fieldWidth, fieldsMarginTop + 1*fieldHeight, fieldWidth, fieldHeight, 6, 4));
		
		fields.add(new Field(fieldsMarginLeft + 0,            fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 0, 3));
		fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 1, 3));
		fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 2, 3));
		fields.add(new Field(fieldsMarginLeft + 3*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 3, 3));
		fields.add(new Field(fieldsMarginLeft + 4*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 4, 3));
		fields.add(new Field(fieldsMarginLeft + 5*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 5, 3));
		fields.add(new Field(fieldsMarginLeft + 6*fieldWidth, fieldsMarginTop + 2*fieldHeight, fieldWidth, fieldHeight, 6, 3));
		
		fields.add(new Field(fieldsMarginLeft + 0,            fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 0, 2));
		fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 1, 2));
		fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 2, 2));
		fields.add(new Field(fieldsMarginLeft + 3*fieldWidth, fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 3, 2));
		fields.add(new Field(fieldsMarginLeft + 4*fieldWidth, fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 4, 2));
		fields.add(new Field(fieldsMarginLeft + 5*fieldWidth, fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 5, 2));
		fields.add(new Field(fieldsMarginLeft + 6*fieldWidth, fieldsMarginTop + 3*fieldHeight, fieldWidth, fieldHeight, 6, 2));
		
		fields.add(new Field(fieldsMarginLeft + 0,            fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 0, 1));
		fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 1, 1));
		fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 2, 1));
		fields.add(new Field(fieldsMarginLeft + 3*fieldWidth, fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 3, 1));
		fields.add(new Field(fieldsMarginLeft + 4*fieldWidth, fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 4, 1));
		fields.add(new Field(fieldsMarginLeft + 5*fieldWidth, fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 5, 1));
		fields.add(new Field(fieldsMarginLeft + 6*fieldWidth, fieldsMarginTop + 4*fieldHeight, fieldWidth, fieldHeight, 6, 1));
		
		fields.add(new Field(fieldsMarginLeft + 0,            fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 0, 0));
		fields.add(new Field(fieldsMarginLeft + 1*fieldWidth, fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 1, 0));
		fields.add(new Field(fieldsMarginLeft + 2*fieldWidth, fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 2, 0));
		fields.add(new Field(fieldsMarginLeft + 3*fieldWidth, fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 3, 0));
		fields.add(new Field(fieldsMarginLeft + 4*fieldWidth, fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 4, 0));
		fields.add(new Field(fieldsMarginLeft + 5*fieldWidth, fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 5, 0));
		fields.add(new Field(fieldsMarginLeft + 6*fieldWidth, fieldsMarginTop + 5*fieldHeight, fieldWidth, fieldHeight, 6, 0));
		
		
		
		
		
		 
		
		
	}
	
	public void nextPlayerTurn() {                           // Methode die, Reihenfolge der Spieler festlegt
		if(currentPlayer == FieldValue.Spieler1) {
			currentPlayer = FieldValue.Spieler2;
			
		}else {
			currentPlayer = FieldValue.Spieler1;
		}
		window.setCurrentPlayerLabelText("Aktueller Spieler ist " + currentPlayer.name() + "!");
	}
	
	public FieldValue getcurrentPlayer() {              // Get methode für den aktuellen Spieler
		return currentPlayer;
		
	}
	
        public ArrayList<Field> getFields(){            // Get Methode für das aktuelle Feld
        	return fields;
        }
}
