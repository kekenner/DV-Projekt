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
 * Sie enthält die Methoden resetGameLogic, PaintComponent, checkField, resetandrepaintFields, checkWin und CheckDraw.
 * Diese Klasse ist mit der Spiellogik verknüpft.
 * 
 * @author mariusmauth SimonFluck
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
	 * Methode resetGameLogic
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
	 * Methode paintComponent. Diese Methode zeichnet sowohl die Felder, als auch auch die Spielsteine.
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
     * Methode checkField. Diese Methode überprüft den Wert eines neu angeklickten Feldes.
     * Hat das angeklickte Feld den Wert "EMPTY" wird erst der Spielstein im Feld platziert, dannach werden
     * die Methoden resetAndRepaintFields, checkWin, checkDraw, spielerWechseln und nextPlayerTurn aufgerufen.
     * 
     * @param x
     * @param y
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
    
    private void sendColumnToOtherPlayer(int col) {
    	if(VierGewinnt.instance.iAmServer()) { // spiele ich als Server?
    		VierGewinnt.instance.getServer().send(String.valueOf(col));
    	} else {
    		VierGewinnt.instance.getClient().send(String.valueOf(col));
    	}
    }
    
    private void enableGameField() {
    	setBackground(Color.BLUE);
    	resetAndRepaintFields();
		this.disabled = false;
	}


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
     * Methode checkWin. Die Methode checkWin greift auf die Methode hatGeewonnen zu, und gibt ein Tectfeld mit dem Sieger aus.
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
