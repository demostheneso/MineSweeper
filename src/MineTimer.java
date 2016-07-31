import javax.swing.JLabel;

public class MineTimer extends Thread {
	
	private JLabel label;
	private int time;
	
	public MineTimer(JLabel label) {
		label.setText("Time: 0");
		this.label = label;
	}
	
	@Override
	public void run() {
		while(1 == 1) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		time++;
		label.setText("Time: " + time);				
	}}
}
