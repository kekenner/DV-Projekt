package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Die Klasse "Gamewindow" ist eine Tochterklasse von JFrame aus dem Javax.swing Paket
 * @author SimonFluck, mariusmauth
 *
 */
@SuppressWarnings("serial")
public class Gamewindow extends JFrame {         
	
	private JLabel currentPlayerLabel;
	private GamePanel gamePanel;
	
	/**
	 * Der Konstruktor "Gamewindow" legt Titel, Plazierung und Gr��e des Spilefensters fest.
	 * Schlie�t das Programm auch im Hintergrund und legt das Borderlayout fest.
	 * @param width
	 * @param height
	 */
	public Gamewindow(int width, int height) {       
		
		setTitle("4Gewinnt");                       // Titel des Fensters wird gesetzt
		setDefaultCloseOperation(EXIT_ON_CLOSE);    // Programm wird geschlossen
		setBounds(0,0,width, height);               // Gr��e des Fensters wird festgelegt
		setLocationRelativeTo(null);                // Platzierung des Fensters
		setResizable(false);                        // Fenstergr��e kann nicht ver�ndert werden
		setLayout(new BorderLayout());              // Border Layout festlegen
		
		
		gamePanel = new GamePanel();                                  // Klasse Gamepanel einf�gen
		getContentPane().add(gamePanel, BorderLayout.CENTER);                   // Platzierung des Gamepanels auf dem ContentPane in der Mitte
		
		
		currentPlayerLabel = new JLabel("Laden...");
		getContentPane().add(currentPlayerLabel,BorderLayout.NORTH);
		currentPlayerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		 setVisible(true);
		
		
	}
     
	/**
	 * Set Methode f�r den Parameter s.
	 * @param s
	 */
	public void setCurrentPlayerLabelText(String s) {
		currentPlayerLabel.setText(s);
		
	}

	/**
	 * Get Methode f�r das gamePanel.
	 * @return
	 */
	public GamePanel getGamePanel() {
		return gamePanel;
	}
}
