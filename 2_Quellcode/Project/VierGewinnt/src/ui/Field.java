package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Die Klasse Field ist eine Tochterklasse der Klasse Rectangle.
 * Hier werden die einzelnen Felder des Spielfelds erstellt. Diese werden in der Klasse VierGewinnt 
 * in einer Arraylist gespeichert.
 * 
 * @author mariusmauth SimonFluck
 * 
 */
public class Field extends Rectangle  {                               
	private FieldValue value;                                         // Werte des Feldes werden durch ein Enum gespeichert
	private int col;
	private int row;
	
	/**
	 * Konstruktor der Klasse Field
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param col
	 * @param row
	 */
	public Field(int x, int y, int width, int height, int col, int row) {               
		super(x, y, width, height);                                   
		value = FieldValue.EMPTY;                                     // Standardmäßig ist das Feld vor dem anklicken empty
		this.col = col;
		this.row = row;
	}
	/**
	 * Methode "draw" erstellt Rahmen und Symbole
	 * @param g2d
	 */
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
	
	/**
	 * get Methode für den value
	 * @return
	 */
	public FieldValue getValue() {                   
		return value;
	}
	
	/**
	 * set Methode für den value 
	 * @param value
	 */
	public void setValue(FieldValue value) {         
		this.value = value;
	}
	
	/**
	 * get Methode für die Spalte
	 * @return
	 */
	public int getCol() {         					 
		return col;
	}
	
	/**
	 * get Methode für die Zeile
	 * @return
	 */
	public int getRow() {         					 
		return row;
	}






}

