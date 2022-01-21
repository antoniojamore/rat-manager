import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class FatRatManager {

	private double fatRatPop;
	private Random r;
	private Image imgFatRat;
	private Toolkit tk;

	public FatRatManager(double startingPop) {
		fatRatPop = startingPop;
		r = new Random();
		tk = Toolkit.getDefaultToolkit();
		imgFatRat = tk.getImage("photos/fat-rat.png");
	}

	public double breed() {
		double addToSkinny = 0;
		if (fatRatPop >= 2) {
			addToSkinny = fatRatPop * 0.15;
		}
		return addToSkinny;
	}

	public void feed(double skinnyToFat) {
		fatRatPop = skinnyToFat + fatRatPop;
	}

	public double zombify() {
		double fatToZombie = fatRatPop * 0.04;
		fatRatPop = fatRatPop - fatToZombie;
		return fatToZombie;
	}

	public double sendCat(double totalPopulation) {
		double ratsToEat = totalPopulation * 0.21;
		if (ratsToEat > fatRatPop) {
			double skinnyToEat = ratsToEat - fatRatPop;
			fatRatPop = 0;
			return skinnyToEat; 
		} else {
			fatRatPop = fatRatPop - ratsToEat;
			return 0;
		}
	}

	public void infest(double fatToZombie) {
		fatRatPop = fatRatPop - fatToZombie;
	}

	public void eat(double fatToEat) {
		fatRatPop = fatRatPop - fatToEat;
	}

	public void useHoly(double zombieToFat) {
		fatRatPop = zombieToFat + fatRatPop;
	}

	public void nuke(boolean specialRat) {
		if (specialRat) {
			fatRatPop = 1;
		} else {
			fatRatPop = 0;
		}
	}

	public double getPop() {
		return fatRatPop;
	}

	public String toString() {
		return "There are currently " + fatRatPop + " fat rats.";
	}

	public void draw(Graphics g) {
		for(int currentRat = 1; currentRat <= fatRatPop; currentRat++) {
			int x = r.nextInt(1300);
			int y = r.nextInt(800);
			g.drawImage(imgFatRat, x, y, 50, 50, null);
		}
	}

}