package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.Konfiguration;
import logic.Spieler;

public class GamePanel extends JPanel implements MouseListener {
	
	private logic.VierGewinnt gameLogic;
	private Spieler spieler1;
	private Spieler spieler2;
	
	public GamePanel() {
		setBackground(Color.BLUE);
		requestFocus();
		addMouseListener(this);
		this.resetGameLogic();
	}
	
	private void resetGameLogic() {
		Konfiguration konf = new Konfiguration(7, 6);
		this.spieler1 = new Spieler("dummy1","rot", 1);
		this.spieler2 = new Spieler("dummy2","gruen", -1);
		gameLogic = new logic.VierGewinnt(
				konf,
				this.spieler1,
				this.spieler2
		);
	}
	
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Unsere eigenen Zeichnungen
		Graphics2D g2d = (Graphics2D) g; 
		
		// Felder Zeichnen
	for(Field field : VierGewinnt.instance.getFields()) {
		field.draw(g2d);
	}
		
		
	}
    
    private void checkField(int x, int y) {
    	Rectangle cursorHitbox = new Rectangle(x, y, 1, 1);
    	for(Field field : VierGewinnt.instance.getFields()) {
    		if(cursorHitbox.intersects(field)) {
    			
    			if(field.getValue() == FieldValue.EMPTY) {
    				
    				this.gameLogic.setzeZug(field.getCol());
    				resetAndRepaintFields();
    				checkWin();
    				this.gameLogic.spielerWechseln();
    				VierGewinnt.instance.nextPlayerTurn();
    			}
    			break;
    		}
    	}
    	
    }
    
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
    
    private void checkWin() {
    	if(this.gameLogic.hatGewonnen()) {
    		JOptionPane.showMessageDialog(this, "Spieler " + VierGewinnt.instance.getcurrentPlayer().name()+ " hat Gewonnen!", "Game Over!",JOptionPane.INFORMATION_MESSAGE);
    		VierGewinnt.instance.initGame();
    		this.resetGameLogic();
    		
    		
    		repaint();
    	}
    }
	
	@Override
	public void mouseClicked(MouseEvent e) {
		checkField(e.getX(), e.getY());
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

}
