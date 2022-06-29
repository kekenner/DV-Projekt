package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Die Klasse Field ist eine Tochterklasse der Klasse Rectangle.
 * Hier werden die einzelnen rechteckigen Felder des Spielfelds erstellt. Diese werden in der Klasse VierGewinnt 
 * in einer Arraylist gespeichert.
 * 
 * @author Marius Mauth, Simon Fluck
 * 
 */
public class Field extends Rectangle  {                              
	private FieldValue value;                                         // Werte des Feldes werden durch ein Enum gespeichert
	private int col;
	private int row;
	
	/**
	 * Konstruktor der Klasse Field
	 * @param x X-Koordinate des Mouseklicks
	 * @param y Y-Koordinate des Mouseklicks
	 * @param width Breite des Spielfeldes
	 * @param height Höhe des Spielfeldes
	 * @param col Spaltenanzahl des Spielfeldes
	 * @param row Zeilenanzahl des Spielfeldes
	 */
	public Field(int x, int y, int width, int height, int col, int row) {               
		super(x, y, width, height);                                   
		value = FieldValue.EMPTY;                                     // Standardmäßig ist das Feld vor dem anklicken empty
		this.col = col;
		this.row = row;
	}
	/**
	 * Methode "draw" erstellt Rahmen und Symbole des Spielfeldes.
	 * @param g2d
	 */
	public void draw(Graphics2D g2d) {
		// Rahmen Zeichnen 
		g2d.setColor(Color.BLACK);
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
	
	/**
	 * Get Methode für den value eines einzelnen Feldes.
	 * @return value Wert eines einzelnen Feldes (Spieler1, Spieler2, Empty)
	 */
	public FieldValue getValue() {                   
		return value;
	}
	
	/**
	 * Set Methode für den value eines einzelnen Feldes.
	 * @param value Wert eines einzelnen Feldes (Spieler1, Spieler2, Empty)
	 */
	public void setValue(FieldValue value) {         
		this.value = value;
	}
	
	/**
	 * Get Methode für die Spalte des Spielfeldes.
	 * @return col Spaltennummer
	 */
	public int getCol() {         					 
		return col;
	}
	
	/**
	 * Get Methode für die Zeile des Spielfeldes.
	 * @return row Zeilennummer
	 */
	public int getRow() {         					 
		return row;
	}






}

