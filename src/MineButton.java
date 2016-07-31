import javax.swing.JButton;

public class MineButton extends JButton {
	
	int row;
	int col;
	
	boolean flaged;
	boolean clicked;
	
	public MineButton(int row, int col) {
		this.row = row;
		this.col = col;
	}

}
