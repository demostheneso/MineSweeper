
public class Cord {
	public int row;
	public int col;
	
	public Cord(int r, int c) {
		row = r;
		col = c;
	}
	
	public boolean equals(Object o) {
		return ((Cord)o).row == this.row && ((Cord)o).col == this.col;
	}
}
