
public class Game {
	
	int[][] grid;
	Gui gui;
	static Game game;
	

	public static void main(String[] args) {
		Gui gui = new Gui(game);
		game = new Game(gui);
		game.genMines();
		game.genNumbers();
		gui.setVisible(true);	
	}
	
	public Game(Gui gui) {
		this.gui = gui;
		grid = new int[Config.ROWS][Config.COLS];
		for(int row = 0 ; row < Config.ROWS ; row ++) {
			for(int col = 0 ; col < Config.COLS; col ++) {
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
		char[] rowMods = {'+','+','+','-','-','-','0','0'};
		char[] colMods = {'+','-','0','+','-','0','+','-'};
		
		for(int row = 0 ; row < Config.ROWS ; row ++) {
			for(int col = 0 ; col < Config.COLS; col ++) {
				if(grid[row][col] != -1) {
					int number = 0;
					for(int i = 0; i < rowMods.length; i ++) {
						number += checkForNumber(row,col,rowMods[i],colMods[i]);
					}
					grid[row][col] = number;
				}
			}
		}
	}
	
	private int checkForNumber(int row, int col, char rowMod, char colMod) {
		
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
		return false;
	}

}
