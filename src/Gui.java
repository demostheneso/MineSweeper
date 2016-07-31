import java.awt.Color;
import java.awt.Insets;
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
	private Game game;
	private boolean lost = false;
	
	public Gui(Game game) {
		this.game = game;
		setTitle("MineSweeper");
		setSize(COLS * SIZE + 16, ROWS * SIZE + 90);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttons = new MineButton[Config.ROWS][Config.COLS];
		
		JPanel panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		int number = 0;
		for(int row = 0 ; row < Config.ROWS; row ++) {
			for(int col = 0; col <Config.COLS; col ++) {
				MineButton button = new MineButton(row,col);
				button.setBounds(col*SIZE,row*SIZE+50,SIZE,SIZE);
				button.addMouseListener(this);
				button.setMargin(new Insets(0,0,0,0));
				buttons[row][col] = button;
				panel.add(button);
				
			}
		}	
	}
	
	public void addNumber(int row, int col, int number) {;
		buttons[row][col].setLabel(Integer.toString(number));
	}
	
	public void setText(int row, int col, String text) {;
		buttons[row][col].setLabel(text);
	}
	

	
	public void addFlag(int row, int col, int number) {;
		buttons[row][col].setLabel("F");
	}

	


	@Override
	public void mouseClicked(MouseEvent e) {
		MineButton button = (MineButton)e.getSource();
		if(!button.clicked) {
			if(SwingUtilities.isRightMouseButton(e)) {
				if(button.flaged) {
					button.setText("");
					button.flaged = false;
				} else {
					button.setText("F");
					button.flaged = true;
				}
			} else if(SwingUtilities.isLeftMouseButton(e) && button.flaged == false) {
				setClicked(button.row,button.col);
				lost = game.clicked(button.row,button.col);
				if(lost == true) {
					//dolater
				}
				
				
			}	
		}
	}
	
	public void setClicked(int row, int col) {
		MineButton button = buttons[row][col];
		button.setEnabled(false);
		button.setBackground(Color.WHITE);
		button.clicked = true;
		button.setText("");
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
