package logic;

/**
 * Die Klasse "Konfiguration" enth�lt die M�glichkeit, die Spielfelddimension anzupassen.
 * @author fabian.eiternik
 *
 */
public class Konfiguration {
	
	
	/**
	 * Anzahl der Spalten des Spielfelds.
	 */
	private int anzahlSpalten;
	
	
	/**
	 * Anzahl der Zeilen des Spielfelds.
	 */
	private int anzahlZeilen;
	
	/**
	 * Konstruktor, um eine Konfiguration zu erzeugen
	 * @param anzahlSpalten Anzahl Spalten im Spiel
	 * @param anzahlZeilen Anzahl Zeilen im Spiel
	 */
	public Konfiguration(int anzahlSpalten, int anzahlZeilen) {
		super();
		this.anzahlSpalten = anzahlSpalten;
		this.anzahlZeilen = anzahlZeilen;
	}


	/**
	 * Gibt die Anzahl der Spalten zur�ck.
	 * @return Anzahl Spalten
	 */
	public int getAnzahlSpalten() {
		return anzahlSpalten;
	}
	
	
	/**
	 * Die Anzahl der Spalten des Spielfeld wird ausgew�hlt.
	 * @param anzahlSpalten
	 */
	public void setAnzahlSpalten(int anzahlSpalten) {
		this.anzahlSpalten = anzahlSpalten;
	}
	
	
	/**
	 * Gibt die Anzahl der Zeilen zur�ck.
	 * @return Anzahl Zeilen
	 */
	public int getAnzahlZeilen() {
		return anzahlZeilen;
	}
	
	
	/**
	 * Die Anzahl der Zeilen des Spielfeld wird ausgew�hlt.
	 * @param anzahlZeilen
	 */
	public void setAnzahlZeilen(int anzahlZeilen) {
		this.anzahlZeilen = anzahlZeilen;
	}
}
