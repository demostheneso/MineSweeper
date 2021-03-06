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
	private boolean gameStarted = false;
	private JPanel panel;
	private JLabel label;
	private JButton startButton;
	private JLabel MineLabel;
	private JLabel FlagLabel;
	private int flagsUsed;
	private JLabel timer;
	private MineTimer mineTimer;
	
	public ImageIcon[] numbers;
	public ImageIcon flag;
	public ImageIcon mine;
	
	public Gui(Game game) {
		this.game = game;
		setTitle("MineSweeper");
		setSize(COLS * SIZE + 16, ROWS * SIZE + 90);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buttons = new MineButton[Config.ROWS][Config.COLS];
		
		panel = new JPanel();
		getContentPane().add(panel);
		panel.setLayout(null);
		label = new JLabel();
		label.setBounds(120, 5, 100, 50);
		panel.add(label);
		
		MineLabel = new JLabel("Mines: "+ Config.MINES);
		MineLabel.setBounds(10, 10, 100, 15);
		panel.add(MineLabel);
		
		FlagLabel = new JLabel("Flags: 0");
		FlagLabel.setBounds(10, 25, 100, 15);
		panel.add(FlagLabel);
		
		timer = new JLabel();
		timer.setBounds(225, 10, 110, 30);
		panel.add(timer);	
				
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
		
		genIcons();
		
		startButton = new JButton("Start Game");
		startButton.addMouseListener(this);
		startButton.setActionCommand("start");
		startButton.setBounds(100, 10, 110, 30);
		panel.add(startButton);
		
	}
	
	public void genIcons() {
		numbers = new ImageIcon[8];
		for(int i = 0; i < 8; i++) {
			int num = i + 1;
			String file = "images/MineSweeper-" + num + ".png";
			numbers[i] = new ImageIcon(getClass().getResource(file));
			
		}
		
		flag = new ImageIcon(getClass().getResource("images/MineSweeper-flag.png"));
		mine = new ImageIcon(getClass().getResource("images/MineSweeper-mine.png"));
		
	}
	
	public void addNumber(int row, int col, int number) {;
		buttons[row][col].setDisabledIcon(numbers[number-1]);
		buttons[row][col].setIcon(numbers[number-1]);
		
	}
	
	public void setText(int row, int col, String text) {;
		buttons[row][col].setLabel(text);
	}
	
	public void addMine(int row, int col) {
		buttons[row][col].setIcon(mine);
		buttons[row][col].setDisabledIcon(mine);
	}
	

	
	private void addFlag(int row, int col) {;
		buttons[row][col].setIcon(flag);
	}
	
	private void removeFlag(int row, int col) {;
		buttons[row][col].setIcon(null);
	}
	
	public void win() {
		gameStarted = false;;
		label.setText("You Won");
		mineTimer.stop();
	}

	


	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == startButton) {
			panel.remove(startButton);
			repaint();
			gameStarted = true;
			mineTimer = new MineTimer(timer);
			mineTimer.start();
		} else {
		MineButton button = (MineButton)e.getSource();
		if(!button.clicked && !lost && gameStarted) {
			if(SwingUtilities.isRightMouseButton(e)) {
				if(button.flaged) {
					flagsUsed--;
					removeFlag(button.row,button.col);
					button.flaged = false;
				} else {
					flagsUsed++;
					addFlag(button.row,button.col);
					button.flaged = true;
				}
				FlagLabel.setText("Flags: "+flagsUsed);
			} else if(SwingUtilities.isLeftMouseButton(e) && button.flaged == false) {
				setClicked(button.row,button.col);
				lost = game.clicked(button.row,button.col);
				if(lost == true) {
					label.setText("You Lost!");
					mineTimer.stop();
				}
				
				
			}}	
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
