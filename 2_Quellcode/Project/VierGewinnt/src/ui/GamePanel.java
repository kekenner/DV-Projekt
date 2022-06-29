package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import logic.Konfiguration;
import logic.Spieler;

/**
 * Die Klasse GamePanel ist eine Tochterklasse der Java Klasse JPanel und implementiert den MouseListener.
 * Sie enthält die Methoden resetGameLogic, PaintComponent, checkField, handleOtherPlayerMove, sendColumnToOtherPlayer, enableGameField, disableGameField, 
 * resetandrepaintFields, checkWin und CheckDraw.
 * Diese Klasse ist mit der Spiellogik verknüpft.
 * 
 * @author Marius Mauth, Simon Fluck, Edit: Marven Schwarz, Kevin Kenner
 *
 */
public class GamePanel extends JPanel implements MouseListener {
	
	private logic.VierGewinnt gameLogic;
	private Spieler spieler1;
	private Spieler spieler2;
	private boolean disabled = false;
	
	public GamePanel() {
		setBackground(Color.BLUE);
		requestFocus();
		addMouseListener(this);
		this.resetGameLogic();
	}
	
	
	/**
	 * Die Methode resetGameLogic erzeugt nach der Standartkonfiguration ein neues Spielfeld.
	 * Wenn bereits ein Spiel gespielt wurde, wird das Spielfeld nur geleert, sodass das Spiel von vorne losgehen kann.
	 */
	private void resetGameLogic() {
		Konfiguration konf = new Konfiguration(7, 6);
		if(this.spieler1 == null) {
			this.spieler1 = new Spieler("", 1);
			this.spieler2 = new Spieler("", -1);
		}
		if(this.gameLogic != null) {
			this.gameLogic.spielFeldLeeren();
		} else {
			this.gameLogic = new logic.VierGewinnt(konf, spieler1, spieler2);
		}
	}
	
	/**
	 * Methode paintComponent. Diese Methode zeichnet sowohl die Felder, als auch die Spielsteine.
	 */
	
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g; 
		
	for(Field field : VierGewinnt.instance.getFields()) {
		field.draw(g2d);
	}
		
		
	}
    
    /**
     * Die Methode checkField überprüft den Wert eines neu angeklickten Feldes.
     * Hat das angeklickte Feld den Wert "EMPTY" wird erst der Spielstein im Feld platziert, danach wird das Spielfeld aktualisiert, auf Sieg oder
     * Unentschieden überprüft, der Spielerwechsel gemacht, das Spielfeld für den Spieler der gerade einen Zug gemacht hat gesperrt,
     * die Spaltennummer an den Gegner gesendet und die Methode handleOtherPlayerMove zum Umgang mit einem gegnerischen Zug aufgerufen.
     * 
     * @param int x
     * @param int y
     */
	private void checkField(int x, int y) {
		Rectangle cursorHitbox = new Rectangle(x, y, 1, 1);
		for (Field field : VierGewinnt.instance.getFields()) {
			if (cursorHitbox.intersects(field)) {

				if (field.getValue() == FieldValue.EMPTY) {
					this.gameLogic.setzeZug(field.getCol());
					resetAndRepaintFields();
					checkWin();
					checkDraw();
					this.gameLogic.spielerWechseln();
					VierGewinnt.instance.nextPlayerTurn();
					this.disableGameField();
					this.sendColumnToOtherPlayer(field.getCol());

					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							handleOtherPlayerMove();
						}
					});
				}
				break;
			}
		}

	}
    
	/**
	 * Die Methode handleOtherPlayerMove umfasst den Umgang mit einem gegnerischen Zug.
	 * Es wird als erstes überprüft welcher Spieler an der Reihe ist.
	 * Der andere Spiel wird dann in eine wartende Position gestellt und die Methode empfange ausgeführt, sodass die Spaltennummer des Zugs des Gegners
	 * empfangen werden kann. Danach wird der Zug des Gegners gesetzt, das Spielfeld aktualisiert, auf Sieg oder
     * Unentschieden überprüft, der Spielerwechsel gemacht, die Spaltennummer an den Gegner gesendet und die Methode enableGameField, die das 
     * Spielfeld für den Spieler der gerade Daten empfangen hat entsperrt, aufgerufen.
	 */
    public void handleOtherPlayerMove() {

    	int col = -1;
    	if(VierGewinnt.instance.iAmServer()) { // spiele ich als Server?
    		col = VierGewinnt.instance.getServer().empfange();
    	} else {
    		col = VierGewinnt.instance.getClient().empfange();
    	}
    	this.gameLogic.setzeZug(col);
		resetAndRepaintFields();
		checkWin();
		checkDraw();
		this.gameLogic.spielerWechseln();
		VierGewinnt.instance.nextPlayerTurn();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				enableGameField();
			}
		});
    }
    
    /**
     * Die Methode sendColumnToOtherPlayer überprüft welcher Spieler gerade an der Reihe ist.
     * Je nach dem wer an der Reihe ist wird dann 
     * entweder die send Methode des Servers oder des Client aufgerufen und die übergebene Spaltennummer gesendet.
     * @param int col
     */
    private void sendColumnToOtherPlayer(int col) {
    	if(VierGewinnt.instance.iAmServer()) { // spiele ich als Server?
    		VierGewinnt.instance.getServer().send(String.valueOf(col));
    	} else {
    		VierGewinnt.instance.getClient().send(String.valueOf(col));
    	}
    }
    
    /**
     * Die Methode enableGameField gibt das Spielfeld für einen Zug frei.
     */
    private void enableGameField() {
    	setBackground(Color.BLUE);
    	resetAndRepaintFields();
		this.disabled = false;
	}


    /**
     * Die Methode disableGameField sperrt das Spielfeld und verhindert das Klicken.
     */
	public void disableGameField() {
		setBackground(Color.GRAY);
		resetAndRepaintFields();
		this.disabled = true;
	}


	/**
     * Methode resetAndRepaintFields. Die Methode zeichnet nach jedem ausgeführten Zug das Spielfeld neu, somit wird der neu 
     * gesetzte Spielstein angezeigt.
     */
    private void resetAndRepaintFields() {
    	for(Field field : VierGewinnt.instance.getFields()) {
    		int fieldVal = this.gameLogic.getFieldValue(field.getCol(), field.getRow());
    		if(fieldVal == 1) {
    			field.setValue(FieldValue.Spieler1);
    		} else if(fieldVal == -1) {
    			field.setValue(FieldValue.Spieler2);
    		} else if(fieldVal == 0) {
    			field.setValue(FieldValue.EMPTY);
    		} 
    	}
    	repaint();
    }
    
    /**
     * Methode checkWin. Die Methode checkWin greift auf die Methode hatGewonnen zu und gibt ein Textfeld mit dem Sieger aus.
     */
    private void checkWin() {
    	if(this.gameLogic.hatGewonnen()) {
    		JOptionPane.showMessageDialog(this, "Spieler " + this.gameLogic.getAktuellerSpieler().getName() + " hat Gewonnen!", "Game Over!",JOptionPane.INFORMATION_MESSAGE);
    		VierGewinnt.instance.initGame();
    		this.resetGameLogic();
    		
    		
    		repaint();
    	}
    }
    
    /**
     * Methode checkDraw. Die Methode checkDraw greift auf die Methode istUnentschieden zu und gibt ein Textfeld mit der 
     * Benachrichtung "Unentschieden" aus.
     */
    private void checkDraw() {
    	if(this.gameLogic.istUnentschieden()) {
    		JOptionPane.showMessageDialog(this, "Unentschieden :/ !", "Kein Gewinner!", JOptionPane.INFORMATION_MESSAGE);
    		VierGewinnt.instance.initGame();
    		this.resetGameLogic();
    		
    		repaint();
    	}
    }
	
    /**
     * Methode mouseClicked. Erkennt in welches Feld die Maus geklickt hat.
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		if(!this.disabled) {
			checkField(e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	public logic.VierGewinnt getGameLogic() {
		return gameLogic;
	}

}
