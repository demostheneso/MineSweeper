import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Gui extends JFrame implements MouseListener{
	
	private MineButton[][] buttons;
	
	private final int ROWS = Config.ROWS;
	private final int COLS = Config.COLS;
	private final int SIZE = Config.BUTTON_SIZE;
	
	public Gui() {
		setTitle("MineSweeper");
		setSize(COLS * SIZE + 16, ROWS * SIZE + 40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttons = new MineButton[Config.ROWS][Config.COLS];
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		int number = 0;
		for(int row = 0 ; row < Config.ROWS; row ++) {
			for(int col = 0; col <Config.COLS; col ++) {
				MineButton button = new MineButton(row,col);
				button.setBounds(col*SIZE,row*SIZE,SIZE,SIZE);
				button.addMouseListener(this);
				buttons[row][col] = button;
				panel.add(button);
				
			}
		}
		
		
		
		 
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		MineButton button = (MineButton)e.getSource();
		boolean b = SwingUtilities.isLeftMouseButton(e);
		System.out.println(button.row + "  " + button.col + b);
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
