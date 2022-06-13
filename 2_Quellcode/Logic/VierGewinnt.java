package logic;

/**
 * Die Hauptklasse "VierGewinnt", die das eigentliche Spiel in Standardausführung beinhaltet.
 * @author fabian.eiternik
 *
 */
public class VierGewinnt {
	
	
	/**
	 * Konfiguration, in der man Werte für Zeilen und Spalten wählt und bestätigt. 
	 * Das Spielfeld soll schlussendlich diese gewünschte Anzahlen aufweisen.
	 */
	private Konfiguration konfiguration;
	

	/**
	 * Spieler 1, der beiden Spielteilnehmer.
	 */
	private Spieler spieler1;
	
	
	/**
	 * Spieler 2, der beiden Spielteilnehmer.
	 */
	private Spieler spieler2;
	
	

	
	/**
	 * Feld wird initialisiert
	 */
	private int[][] feld;
	//2D Array
	
	/**
	 * Erzeugt das Feld der Schaltfläche, sowie Spieler 1 und Spieler 2. 
	 * Die Konfiguration der Schaltfläche erlaubt der Anwendung das Ändern des Aussehens. 
	 * @param konfiguration
	 * @param spieler1
	 * @param spieler2
	 */
	public VierGewinnt(Konfiguration konfiguration, Spieler spieler1, Spieler spieler2) {
		this.konfiguration = konfiguration;
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		
		// Feld erzeugen, aus den Werten der Konfiguration
		this.feld = new int[this.konfiguration.getAnzahlSpalten()][this.konfiguration.getAnzahlZeilen()];
	}
	
	/**
	 * Methode um einen Spielzug zu setzen.
	 * 
	 * @param spalte - Spaltennummer, in die der Stein eingeworfen wurde.
	 * @param spieler - Spieler, der den Zug gemacht hat.
	 * @return "true" - bei einem gültigem Zug und "false" bei ungültigem Zug.
	 */
	public boolean setzeZug(int spalte, Spieler spieler) {
		//ist der Zug nicht gültig?
		if(!this.gueltigerZug(spalte)) {
			//dann Methode verlassen.
			return false;
		}
		
		//wir wandern in der Spalte von unten nach oben bis wir auf eine "freie Stelle"
		//stoßen (=0)
		int i = 0;
		while(feld[spalte][i] != 0) {
			i++;
		}
		feld[spalte][i] = spieler.getSpielerZeichen();
		return true;
	}
	
	
	/**
	 * Methode analysiert das Spielfeld - sind alle Spalten des Feldes mit Chips belegt, 
	 * ist das Spiel vorbei und die Methode liefert "false". Ist der Spielzug gültig, geht 
	 * das Spiel weiter.
	 *
	 * @param spalte - Spaltennummer, in die der Stein eingeworfen wurde
	 * @return "true" - bei einem gültigem Zug und "false" bei ungültigem Zug.
	 */
	private boolean gueltigerZug(int spalte) {
		//wurde eine gültige Spalte angegeben?
		if(spalte > this.konfiguration.getAnzahlSpalten()) {
			// diese Spalte gibt es garnicht
			return false;
		}
		// zusätzlich prüfen wir, ob die oberste Zelle der Spalte "frei" (=0) ist
		return this.feld[spalte][this.konfiguration.getAnzahlZeilen()-1] == 0;
	}
	
	
	/** 
	 * Methode prüft nach jedem Spielzug, wie das Spiel ausgegangen ist (gewonnen oder unentschieden),
	 * von dem eingeworfenen Chip aus in jegliche Richtung ob 4-in-Reihe der selben Farbe geschmissen wurden.
	 * 
	 * @param spieler - Spieler, der den Zug gemacht hat.
	 * @return "true" - bei einem gültigem Zug und "false" bei ungültigem Zug.
	 */
	public boolean hatGewonnen(Spieler spieler, int spalte) {
		//zuerst finden wir die Position des letztrn Einwurfs
		int zeichen = spieler.getSpielerZeichen();
		int aktuelleZeile = konfiguration.getAnzahlZeilen()-1;
		try {
			while(feld[spalte][aktuelleZeile] == 0) {
				aktuelleZeile--;
			}
		} catch (Exception e) {
			//die Spalte ist noch komplett leer, also nicht gewonnen:
			return false;
		}
		// der Letze Stein des aktuellen Spielers lingt in "feld[spalte][i]"
		
		//liegen horizontal vier Steine nebeneinander
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile] &&
				zeichen == feld[spalte-2][aktuelleZeile] &&
				zeichen == feld[spalte-3][aktuelleZeile]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile] &&
				zeichen == feld[spalte-2][aktuelleZeile] &&
				zeichen == feld[spalte+1][aktuelleZeile]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile] &&
				zeichen == feld[spalte+1][aktuelleZeile] &&
				zeichen == feld[spalte+2][aktuelleZeile]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte+1][aktuelleZeile] &&
				zeichen == feld[spalte+2][aktuelleZeile] &&
				zeichen == feld[spalte+3][aktuelleZeile]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		
		//liegen vertikal vier Steine nebeneinander
		try {
			if(	zeichen == feld[spalte][aktuelleZeile-1] &&
				zeichen == feld[spalte][aktuelleZeile-2] &&
				zeichen == feld[spalte][aktuelleZeile-3]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte][aktuelleZeile-1] &&
				zeichen == feld[spalte][aktuelleZeile-2] &&
				zeichen == feld[spalte][aktuelleZeile+1]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte][aktuelleZeile-1] &&
				zeichen == feld[spalte][aktuelleZeile+1] &&
				zeichen == feld[spalte][aktuelleZeile+2]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte][aktuelleZeile+1] &&
				zeichen == feld[spalte][aktuelleZeile+2] &&
				zeichen == feld[spalte][aktuelleZeile+3]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		//liegen diagonal(1) vier Steine nebeneinander
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile-1] &&
				zeichen == feld[spalte-2][aktuelleZeile-2] &&
				zeichen == feld[spalte-3][aktuelleZeile-3]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile-1] &&
				zeichen == feld[spalte-2][aktuelleZeile-2] &&
				zeichen == feld[spalte+1][aktuelleZeile+1]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile-1] &&
				zeichen == feld[spalte+1][aktuelleZeile+1] &&
				zeichen == feld[spalte+2][aktuelleZeile+2]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte+1][aktuelleZeile+1] &&
				zeichen == feld[spalte+2][aktuelleZeile+2] &&
				zeichen == feld[spalte+3][aktuelleZeile+3]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		//liegen diagonal(2) vier Steine nebeneinander
		try {
			if(	zeichen == feld[spalte+1][aktuelleZeile-1] &&
				zeichen == feld[spalte+2][aktuelleZeile-2] &&
				zeichen == feld[spalte+3][aktuelleZeile-3]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte+1][aktuelleZeile-1] &&
				zeichen == feld[spalte+2][aktuelleZeile-2] &&
				zeichen == feld[spalte-1][aktuelleZeile+1]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte+1][aktuelleZeile-1] &&
				zeichen == feld[spalte-1][aktuelleZeile+1] &&
				zeichen == feld[spalte-2][aktuelleZeile+2]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		try {
			if(	zeichen == feld[spalte-1][aktuelleZeile+1] &&
				zeichen == feld[spalte-2][aktuelleZeile+2] &&
				zeichen == feld[spalte-3][aktuelleZeile+3]) {
				return true;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// nichts zutun, kein Gewinn
		}
		
		return false;
	}
	
	
	/**
	 * Gibt die Konfiguration zurück.
	 * @return Konfiguration
	 */
	
	public Konfiguration getKonfiguration() {
		return konfiguration;
	}
	
	
    /**
     * Gibt den Spieler 1 zurück.
     *
     * @return Spieler 1
     * @see logic.Spieler
     */
	public Spieler getSpieler1() {
		return spieler1;
	}
	
	
    /**
     * Gibt den Spieler 2 zurück.
     *
     * @return Spieler 2
     * @see logic.Spieler
     */
	public Spieler getSpieler2() {
		return spieler2;
	}
	
	public void print() {
		for(int zeile = getKonfiguration().getAnzahlZeilen()-1; zeile != -1; zeile-- ) {
			for(int spalte = 0; spalte < getKonfiguration().getAnzahlSpalten(); spalte++ ) {
				System.out.print(this.feld[spalte][zeile]+"\t");
			}
			System.out.println();
		}
		
	}


}
