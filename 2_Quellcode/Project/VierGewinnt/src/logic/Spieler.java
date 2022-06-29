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
	 * das technische Zeichen des Spielers, 
	 * das im Spielfeld f�r diesen Spieler benutzt werden soll.
	 */
	private int spielerZeichen;
	
	/**
	 * Konstruktor um einen Spieler zu erzeugen
	 * 
	 * @param name Name des Spielers
	 * @param spielerZeichen technisches Zeichen des Spielers, das im Spielfeld f�r diesen Spieler benutzt werde soll
	 */
	public Spieler(String name, int spielerZeichen) {
		super();
		this.name = name;
		this.spielerZeichen = spielerZeichen;
	}
	
	
	/**
	 * Gibt die Namen der Spieler zur�ck.
	 * @return name Name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * Namen von Spieler 1 und Spieler 2 werden eingegeben.
	 * @param name Name
	 */
	public void setName(String name) {
		this.name = name;
	}
	

	/**
	 * Gibt das technische Zeichen des Spielers zur�ck, 
	 * das im Spielfeld f�r diesen Spieler benutzt werde soll.
	 * @return spielerZeichen Farbe des Spielers
	 */
	public int getSpielerZeichen() {
		return spielerZeichen;
	}

	/**
	 * setzt das technische Zeichen des Spielers, 
	 * das im Spielfeld f�r diesen Spieler benutzt werde soll.
	 * @param spielerZeichen Farbe des Spielers
	 */
	public void setSpielerZeichen(int spielerZeichen) {
		this.spielerZeichen = spielerZeichen;
	}

}
