import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Cord a = new Cord(2,3);
		Cord b = new Cord(5,3);
		Cord c = new Cord(2,3);
		ArrayList<Cord> array = new ArrayList<Cord>();
		array.add(a);
		array.add(b);
		System.out.println(array.contains(c));
		
	}	
}
