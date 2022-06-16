package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Gamewindow extends JFrame {         // Gamewindow ist eine Tochterklasse von JFrame aus dem Javax.swing Paket
	
	private JLabel currentPlayerLabel;
	
	private JButton Neustart;
	
	public Gamewindow(int width, int height) {       // Konstruktor für das Gamewindow
		
		setTitle("4Gewinnt");                       // Titel des Fensters wird gesetzt
		setDefaultCloseOperation(EXIT_ON_CLOSE);    // Programm wird geschlossen
		setBounds(0,0,width, height);               // Größe des Fensters wird festgelegt
		setLocationRelativeTo(null);                // Platzierung des Fensters
		setResizable(false);                        // Fenstergröße kann nicht verändert werden
		setLayout(new BorderLayout());              // Border Layout festlegen
		
		
		GamePanel gamePanel = new GamePanel();                                  // Klasse Gamepanel einfügen
		getContentPane().add(gamePanel, BorderLayout.CENTER);                   // Platzierung des Gamepanels auf dem ContentPane in der Mitte
		
		// Label Spieler
		currentPlayerLabel = new JLabel("Laden...");
		getContentPane().add(currentPlayerLabel,BorderLayout.NORTH);
		currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Neustart Button
		Neustart = new JButton("Neustart");
		getContentPane().add(Neustart,BorderLayout.SOUTH);
		Neustart.setHorizontalAlignment(SwingConstants.CENTER);
		
		setVisible(true);
		
		
	}
     
	public void setCurrentPlayerLabelText(String s) {
		currentPlayerLabel.setText(s);
		
	}
}
