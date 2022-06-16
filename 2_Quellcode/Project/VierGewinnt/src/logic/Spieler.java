package logic;

/**
 * Klasse "Spieler", die s�mtliche Informationen der Spieler enth�lt, 
 * auf die die Klasse "VierGewinnt" zugreift.
 * @author fabian.eiternik
 *
 */
public class Spieler {
	
	
	/**
	 * Namen von Spieler 1 und Spieler 2.
	 */
	private String name;
	
	
	/**
	 * Farbauswahl f�r Spieler 1 und Spieler 2.
	 */
	private String farbe;
	
	/**
	 * das technische Zeichen des Spielers, 
	 * das im Spielfeld f�r diesen Spieler benutzt werde soll.
	 */
	private int spielerZeichen;
	
	/**
	 * Konstruktor um einen Spieler zu erzeugen
	 * 
	 * @param name Name des Spielers
	 * @param farbe Farbe seiner Steine
	 * @param spielerZeichen technisches Zeichen des Spielers, das im Spielfeld f�r diesen Spieler benutzt werde soll
	 */
	public Spieler(String name, String farbe, int spielerZeichen) {
		super();
		this.name = name;
		this.farbe = farbe;
		this.spielerZeichen = spielerZeichen;
	}
	
	
	/**
	 * Gibt die Namen der Spieler zur�ck.
	 * @return Name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Namen von Spieler 1 und Spieler 2 werden eingegeben.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * Gibt die Farbe der Spieler zur�ck.
	 * @return Farbe
	 */
	public String getFarbe() {
		return farbe;
	}
	
	
	/**
	 * Farben von Spieler 1 und Spieler 2 werden ausgew�hlt.
	 * @param farbe
	 */
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	/**
	 * Gibt das technische Zeichen des Spielers zur�ck, 
	 * das im Spielfeld f�r diesen Spieler benutzt werde soll.
	 * @return Farbe
	 */
	public int getSpielerZeichen() {
		return spielerZeichen;
	}

	/**
	 * setzt das technische Zeichen des Spielers, 
	 * das im Spielfeld f�r diesen Spieler benutzt werde soll.
	 * @return Farbe
	 */
	public void setSpielerZeichen(int spielerZeichen) {
		this.spielerZeichen = spielerZeichen;
	}

}
