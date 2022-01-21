import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SkinnyRatManager {

	private double skinnyRatPop;
	private Random r;
	private Image imgSkinnyRat;
	private Toolkit tk;

	public SkinnyRatManager(double startingPop) {
		skinnyRatPop = startingPop;
		r = new Random();
		tk = Toolkit.getDefaultToolkit();
		imgSkinnyRat = tk.getImage("photos/skinny-rat.png");
	}

	public void spray() {
		double skinnyToDead = skinnyRatPop * 0.09;
		skinnyRatPop = skinnyRatPop - skinnyToDead;
	}

	public void breed() {
		if (skinnyRatPop >= 2) {
			skinnyRatPop = skinnyRatPop * 1.15;
		}
	}

	public void breed(double fatToSkinny) {
		skinnyRatPop = skinnyRatPop + fatToSkinny;
	}

	public double feed() {
		double skinnyToFat = skinnyRatPop * 0.18;
		skinnyRatPop = skinnyRatPop - skinnyToFat;
		return skinnyToFat;
	}

	public void eat(double skinnyToEat) {
		skinnyRatPop = skinnyRatPop - skinnyToEat;
	}

	public void sendCat(double skinnyToEat) {
		skinnyToEat = skinnyToEat * 2;
		skinnyRatPop = skinnyRatPop - skinnyToEat;
		if (skinnyRatPop < 0) {
			skinnyRatPop = 0;
		}
	}

	public void nuke(boolean specialRat) {
		if (specialRat) {
			skinnyRatPop = 1;
		} else {
			skinnyRatPop = 0;
		}
	}

	public double getPop() {
		return skinnyRatPop;
	}

	public String toString() {
		return "There are currently " + skinnyRatPop + " skinny rats.";
	}

	public void draw(Graphics g) {
		for(int currentRat = 1; currentRat <= skinnyRatPop; currentRat++) {
			int x = r.nextInt(1250);
			int y = r.nextInt(750);
			g.drawImage(imgSkinnyRat, x, y, 50, 50, null);
		}

	}

}