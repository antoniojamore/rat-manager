import javax.swing.*;

public class Launcher {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setSize(1300, 800);
		f.setContentPane(new Program());
		f.setTitle("Rat Manager v3.8.5");
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

}