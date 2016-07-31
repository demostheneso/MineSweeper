import java.util.ArrayList;

public class Game {
	
	int[][] grid;
	Gui gui;
	static Game game;
	
	private char[] rowMods = {'+','+','+','-','-','-','0','0'};
	private char[] colMods = {'+','-','0','+','-','0','+','-'};
	
	private boolean[][] clicked;
	

	public static void main(String[] args) {
		game = new Game();
		Gui gui = new Gui(game);
		game.gui = gui;
		game.genMines();
		game.genNumbers();
		gui.setVisible(true);
	}
	
	public Game() {
		grid = new int[Config.ROWS][Config.COLS];
		clicked = new boolean[Config.ROWS][Config.COLS];
		for(int row = 0 ; row < Config.ROWS ; row ++) {
			for(int col = 0 ; col < Config.COLS; col ++) {
				clicked[row][col] = false;
				grid[row][col] = 0;
			}
		}
		
	}
	
	private void genMines() {
		for(int i = 0; i < Config.MINES; i++) {
			boolean invalid = true;
			while(invalid) {
				int row = (int)(Math.random() * Config.ROWS);
				int col = (int)(Math.random() * Config.COLS);
				if(grid[row][col] != -1) {
					grid[row][col] = -1;
					invalid = false;
				}
			}
		}
	}
	
	private void genNumbers() {		
		for(int row = 0 ; row < Config.ROWS ; row ++) {
			for(int col = 0 ; col < Config.COLS; col ++) {
				if(grid[row][col] != -1) {
					int number = 0;
					for(int i = 0; i < rowMods.length; i ++) {
						number += checkForAround(row,col,rowMods[i],colMods[i]);
					}
					grid[row][col] = number;
				}
			}
		}
	}
	
	private int checkForAround(int row, int col, char rowMod, char colMod) {
		
		if(rowMod == '+') {
			row++;
		} else if (rowMod == '-'){
			row--;
		}
		
		if(colMod == '+') {
			col++;
		} else if (colMod == '-'){
			col--;
		}
		
		if(row >= 0 && row < Config.ROWS && 
			col >= 0 && col < Config.COLS &&
			grid[row][col] == -1) {	
			return 1;
		}
		return 0;
		
	}
	
	private void displayBoard() {
		for(int row = 0 ; row < Config.ROWS ; row ++) {
			for(int col = 0 ; col < Config.COLS; col ++) {
				String string = Integer.toString(grid[row][col]);
				if(grid[row][col] == -1) {
					string = "M";
				}
				gui.setText(row,col,string);
			}
		}
	}
	
	public boolean clicked(int row, int col) {
		if(grid[row][col] == -1) {
			for(int r = 0 ; r < Config.ROWS ; r ++) {
				for(int c = 0 ; c < Config.COLS; c ++) {
					if(grid[r][c] == -1) {
						gui.setText(r, c, "M");
					}
				}
			}
			return true;
		}
		if(grid[row][col] != 0) {
			gui.addNumber(row,col,grid[row][col]);
			clicked[row][col] = true;
		} else {
			removeBlanks(row,col);
		}
		if(checkWon()) {
			gui.win();
		}
		return false;
		
	}
	
	public boolean checkWon() {
		for(int row = 0 ; row < Config.ROWS ; row ++) {
			for(int col = 0 ; col < Config.COLS; col ++) {
				if(clicked[row][col] == false && grid[row][col] != -1  ) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void removeBlanks(int row, int col) {
		
		ArrayList<Cord> procesed = new ArrayList<Cord>();
		ArrayList<Cord> queue = new ArrayList<Cord>();
		
		Cord cord = new Cord(row, col);
		queue.add(cord);
		procesed.add(cord);
		
		while(!queue.isEmpty()) {
			cord = queue.get(0);
			queue.remove(0);
			procesed.add(cord);
			row = cord.row;
			col = cord.col;
			gui.setClicked(row, col);
			clicked[row][col] = true;
			
			for(int i = 0; i < rowMods.length; i++) {
				
				int tempRow = row;
				int tempCol = col;
				
				if(rowMods[i] == '+') {
					tempRow++;
				} else if (rowMods[i] == '-'){
					tempRow--;
				}
				
				if(colMods[i] == '+') {
					tempCol++;
				} else if (colMods[i] == '-'){
					tempCol--;
				}
				
				Cord queueCord = new Cord(tempRow,tempCol);
				if(tempRow >= 0 && tempRow < Config.ROWS && 
						tempCol >= 0 && tempCol < Config.COLS &&
						(!procesed.contains(queueCord))) {
					if(grid[tempRow][tempCol] == 0) {
						procesed.add(queueCord);
						queue.add(queueCord);
					} else if (grid[tempRow][tempCol] != -1) {
						procesed.add(queueCord);
						gui.setClicked(tempRow,tempCol);
						clicked[tempRow][tempCol] = true;
						gui.addNumber(tempRow, tempCol, grid[tempRow][tempCol]);
					}
				}
				
			}
			
		}
		
		
	}
	
	

}
