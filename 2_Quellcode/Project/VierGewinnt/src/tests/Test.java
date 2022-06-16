package tests;

import java.util.Scanner;

import logic.Konfiguration;
import logic.Spieler;
import logic.VierGewinnt;

public class Test {

	public static void main(String[] args) {
		//testSetzeZug();
		//testIstGueltig();
		//testHatGewonnenHorizontal();
		//testHatGewonnenVertikal();
		testSpiel();
		//scannerTest();
	}
	
	private static void scannerTest() {
		System.out.println("Das ist ein Test!");
		Scanner scanner = new Scanner(System.in);
		
		int myInt = scanner.nextInt();
		
		System.out.println("deine Zahl war " + myInt);
		
	}

	private static void testSpiel() {
		Konfiguration konfiguration = new Konfiguration(8, 8);
		Spieler spieler1 = new Spieler("Tom", "blau", 1);
		Spieler spieler2 = new Spieler("Fabian", "rot", -1);
		VierGewinnt spiel = new VierGewinnt(konfiguration,spieler1, spieler2);
		
		Spieler aktuelleSpieler = spieler1;
		int aktuelleSpalte = 0;
		Scanner in = new Scanner(System.in);
		while(!spiel.hatGewonnen( )) {
			spiel.spielerWechseln();
			System.out.println("Spieler/in " + aktuelleSpieler.getName() + " ist am Zug. Wähle eine Spalte 0~" + konfiguration.getAnzahlSpalten() + ":");
			aktuelleSpalte = in.nextInt();
			while(!spiel.setzeZug(aktuelleSpalte)) {
				System.out.println("Der Zug war illegal, bitte valide Spalte eingeben!");
				aktuelleSpalte = in.nextInt();
			}
			spiel.print();
		}
		System.out.println("Spieler/in " + aktuelleSpieler.getName() + " hat gewonnen!");
	}

	private static void testHatGewonnenVertikal() {
		//Spiel und Spieler erzeugen:
		Konfiguration konfiguration = new Konfiguration(7, 6);
		Spieler spieler1 = new Spieler("Max", "blau", 1);
		Spieler spieler2 = new Spieler("Eva", "rot", -1);
		VierGewinnt spiel = new VierGewinnt(konfiguration,spieler1, spieler2);
		
		spiel.setzeZug(0);
		spiel.setzeZug(0);
		spiel.spielerWechseln();
		spiel.setzeZug(0);
		spiel.setzeZug(0);
		spiel.setzeZug(0);
		
		spiel.print();
		System.out.println(spiel.hatGewonnen());	
		System.out.println("--------------------");
		spiel.setzeZug(0);
		spiel.print();
		System.out.println(spiel.hatGewonnen());
	}
	
	private static void testHatGewonnenHorizontal() {
		//Spiel und Spieler erzeugen:
		Konfiguration konfiguration = new Konfiguration(7, 6);
		Spieler spieler1 = new Spieler("Max", "blau", 1);
		Spieler spieler2 = new Spieler("Eva", "rot", -1);
		VierGewinnt spiel = new VierGewinnt(konfiguration,spieler1, spieler2);
		
		spiel.setzeZug(0);
		spiel.setzeZug(1);
		spiel.setzeZug(2);

		spiel.print();
		
		System.out.println(spiel.hatGewonnen());
		spiel.setzeZug(3);
		System.out.println("--------------------");
		spiel.print();
		System.out.println(spiel.hatGewonnen());
				
	}

	private static void testIstGueltig() {
		//Spiel und Spieler erzeugen:
		Konfiguration konfiguration = new Konfiguration(7, 6);
		Spieler spieler1 = new Spieler("Max", "blau", 1);
		Spieler spieler2 = new Spieler("Eva", "rot", -1);
		VierGewinnt spiel = new VierGewinnt(konfiguration,spieler1, spieler2);
		
		//Ein Zug in eine, nicht existirende Spalte sollte FALSE zurückliefern
		System.out.println(spiel.setzeZug(10));
		
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		
		// noch ein letzter Zug auf Spalte 1 soll möglich sein. Methode soll TRUE zurückliefern
		System.out.println(spiel.setzeZug(1));
		
		// hier dagegen sollte die Spalte schon voll sein und die Methode ein FALSE zurückliefertn
		System.out.println(spiel.setzeZug(1));
	}

	private static void testSetzeZug() {
		//Spiel und Spieler erzeugen:
		Konfiguration konfiguration = new Konfiguration(7, 6);
		Spieler spieler1 = new Spieler("Max", "blau", 1);
		Spieler spieler2 = new Spieler("Eva", "rot", -1);
		VierGewinnt spiel = new VierGewinnt(konfiguration,spieler1, spieler2);
		
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		spiel.setzeZug(1);
		
		spiel.spielerWechseln();
		
		spiel.setzeZug(0);
		spiel.setzeZug(2);
		spiel.setzeZug(3);
		spiel.setzeZug(4);
		spiel.setzeZug(5);
		spiel.print();
	}
}
