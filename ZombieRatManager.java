import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ZombieRatManager {

	private double zombieRatPop;
	private Random r;
	private Image imgZombieRat;
	private Toolkit tk;

	public ZombieRatManager(double startingPop) {
		zombieRatPop = startingPop;
		r = new Random();
		tk = Toolkit.getDefaultToolkit();
		imgZombieRat = tk.getImage("photos/zombie-rat.png");
	}

	public void zombify(double fatToZombie) {
		zombieRatPop = zombieRatPop + fatToZombie;
	}

	public double useHoly() {
		double zombieToFat = zombieRatPop * 0.11;
		zombieRatPop = zombieRatPop - zombieToFat;
		return zombieToFat;
	}

	public void nuke(boolean specialRat) {
		if (specialRat) {
			zombieRatPop = 1;
		} else {
			zombieRatPop = 0;
		}
	}

	public double infest(double fatRatPop) {
		if(zombieRatPop >= 1) {
			double fatToZombie = fatRatPop * 0.03;
			zombieRatPop = zombieRatPop + fatToZombie;
			return fatToZombie;
		}
		return 0;
	}

	public double eat(double specificRatPop, boolean eatFatRats) {
		if(zombieRatPop >= 1) {
			if(eatFatRats) {
				return specificRatPop * 0.017;
			} else {
				return specificRatPop * 0.06;
			}
		}
		return 0;
	}

	public double getPop() {
		return zombieRatPop;
	}

	public String toString() {
		return "There are currently " + zombieRatPop + " zombie rats.";
	}

	public void draw(Graphics g) {
		for(int currentRat = 1; currentRat <= zombieRatPop; currentRat++) {
			int x = r.nextInt(1300);
			int y = r.nextInt(800);
			g.drawImage(imgZombieRat, x, y, 50, 50, null);
		}
	}

}