package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Field extends Rectangle  {                               // Klasse Field ist eine Tochterklasse der Klasse Rectangle

	private FieldValue value;                                         // Werte des Feldes werden durch ein Enum gespeichert, Datentyp ist nichtmehr int oder anderes, sondern vom Datentyp value
	private int col;
	private int row;
	
	public Field(int x, int y, int width, int height, int col, int row) {               // Konstruktor der Klasse Field
		super(x, y, width, height);                                   // Konstruktor der Vorgängerklasse wird aufgerufen
		value = FieldValue.EMPTY;                                     // Standardmäßig ist das Feld vor dem anklicken empty
		this.col = col;
		this.row = row;
	}

	public void draw(Graphics2D g2d) {
		// Rahmen Zeichnen 
		g2d.setColor(Color.BLACK);Color c;
		g2d.drawRect(x, y, width, height);
		
		// Symbole Zeichnen
		if(value == FieldValue.Spieler2) {
			
			g2d.setPaint(Color.RED);
			g2d.drawOval(x + 5, y + 5, width - 10, height - 10);
			g2d.fillOval(x + 5, y + 5, width - 10, height - 10);
			
		}  else if(value == FieldValue.Spieler1) {
			g2d.setPaint(Color.YELLOW);
			g2d.drawOval(x + 5, y + 5, width - 10, height - 10);
			g2d.fillOval(x + 5, y + 5, width - 10, height - 10);
		}
	}
	
	
	public FieldValue getValue() {                   // get Methode für den value
		return value;
	}

	public void setValue(FieldValue value) {         // set Methode für den value
		this.value = value;
	}

	public int getCol() {         					 // get Methode für die Spalte
		return col;
	}

	public int getRow() {         					 // get Methode für die Zeile
		return row;
	}






}

