import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Program extends JPanel implements ActionListener {

	// constants
	final int SKINNY_START = 2;
	final int FAT_START = 0;
	final int ZOMBIE_START = 0;
	final int TIMER_INTERVAL = 1000;

	// variables
	int currentSecond;

	// imported classes
	Timer t;
	Random r;

	// rat managers
	SkinnyRatManager srm;
	FatRatManager frm;
	ZombieRatManager zrm;

	// panels
	JPanel buttons;
	JPanel info;

	// panel components
	JButton btnBreed;
	JButton btnFeed;
	JButton btnSpray;
	JButton btnZombify;
	JButton btnSendCat;
	JButton btnUseHoly;
	JButton btnNuke;
	JButton btnRestart;
	JCheckBox cbTimer;
	JTextField tfInfo;
	JTextField tfSRM;
	JTextField tfFRM;
	JTextField tfZRM;

	public Program() {
		setLayout(new BorderLayout());
		
		t = new Timer(TIMER_INTERVAL, this);
		t.start();
		r = new Random();

		currentSecond = 0;

		srm = new SkinnyRatManager(SKINNY_START);
		frm = new FatRatManager(FAT_START);
		zrm = new ZombieRatManager(ZOMBIE_START);

		buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,9));

		btnBreed = new JButton("Breed");
		buttons.add(btnBreed);
		btnBreed.addActionListener(this);

		btnFeed = new JButton("Feed");
		buttons.add(btnFeed);
		btnFeed.addActionListener(this);

		btnSpray = new JButton("Spray");
		buttons.add(btnSpray);
		btnSpray.addActionListener(this);

		btnZombify = new JButton("Zombify");
		buttons.add(btnZombify);
		btnZombify.addActionListener(this);

		btnSendCat = new JButton("Send Cat");
		buttons.add(btnSendCat);
		btnSendCat.addActionListener(this);

		btnUseHoly = new JButton("Use Holy");
		buttons.add(btnUseHoly);
		btnUseHoly.addActionListener(this);

		btnNuke = new JButton("Nuke");
		buttons.add(btnNuke);
		btnNuke.addActionListener(this);

		cbTimer = new JCheckBox("Run Simulation");
		buttons.add(cbTimer);
		cbTimer.addActionListener(this);
		cbTimer.setSelected(true);

		btnRestart = new JButton("Restart");
		buttons.add(btnRestart);
		btnRestart.addActionListener(this);

		info = new JPanel();
		info.setLayout(new GridLayout(1,4));
 
		tfSRM = new JTextField("Skinny Rats: ");
		tfSRM.setEditable(false);
		info.add(tfSRM);
 
		tfFRM = new JTextField("Fat Rats: ");
		tfFRM.setEditable(false);
		info.add(tfFRM);
 
		tfZRM = new JTextField("Zombie Rats: ");
		tfZRM.setEditable(false);
		info.add(tfZRM);
 
		tfInfo = new JTextField("Update messages will be posted here.");
		tfInfo.setEditable(false);
		info.add(tfInfo);

		add(buttons, BorderLayout.NORTH);
		add(info, BorderLayout.SOUTH);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		srm.draw(g);
		frm.draw(g);
		zrm.draw(g);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBreed) {
			updateMsg("The breed button has been pressed!");
			srm.breed();
			srm.breed(frm.breed());
			if (srm.getPop() <= 0) {
				frm.eat(zrm.eat(frm.getPop(), true));
			} else {
				srm.eat(zrm.eat(srm.getPop(), false));
				frm.infest(zrm.infest(frm.getPop()));
			}
		} else if(e.getSource() == btnFeed) {
			updateMsg("The feed button has been pressed!");
			frm.feed(srm.feed());
		} else if(e.getSource() == btnSpray) {
			updateMsg("The spray button has been pressed!");
			srm.spray();
		} else if(e.getSource() == btnZombify) {
			updateMsg("The zombify button has been pressed!");
			zrm.zombify(frm.zombify());
		} else if(e.getSource() == btnSendCat) {
			updateMsg("The send cat button has been pressed!");
			srm.sendCat(frm.sendCat(totalPop()));
		} else if(e.getSource() == btnUseHoly) {
			updateMsg("The use holy button has been pressed!");
			frm.useHoly(zrm.useHoly());
		} else if(e.getSource() == btnNuke) {
			updateMsg("The nuke button has been pressed!");
			int specialRat;
			specialRat = r.nextInt(3);
			if (specialRat == 0) {
				srm.nuke(true);
				frm.nuke(false);
				zrm.nuke(false);
			} else if (specialRat == 1) {
				srm.nuke(false);
				frm.nuke(true);
				zrm.nuke(false);
			} else {
				srm.nuke(false);
				frm.nuke(false);
				zrm.nuke(true);
			}
		} else if(e.getSource() == btnRestart) {
			updateMsg("The use restart button has been pressed!");
			srm = new SkinnyRatManager(SKINNY_START);
			frm = new FatRatManager(FAT_START);
			zrm = new ZombieRatManager(ZOMBIE_START);
		} else if(e.getSource() == cbTimer) {
			if (t.isRunning()) {
				cbTimer.setSelected(false);
				t.stop();
				updateMsg("The simulation has been stopped!");
			} else {
				cbTimer.setSelected(true);
				t.restart();
				updateMsg("The simulation has been started!");
			}
		} else if(e.getSource() == t) {
			if(currentSecond == 3) {
				updateMsg("Automatic breeding has occured.");
				srm.breed();
				srm.breed(frm.breed());
				if (srm.getPop() <= 0) {
					frm.eat(zrm.eat(frm.getPop(), true));
				} else {
					srm.eat(zrm.eat(srm.getPop(), false));
					frm.infest(zrm.infest(frm.getPop()));
				}
				currentSecond = -1;
			}
			currentSecond++;
			System.out.println("----------------[Current Stats]----------------");
			System.out.println(srm);
			System.out.println(frm);
			System.out.println(zrm);
		}

		tfSRM.setText("Skinny Rats: " + srm.getPop());
		tfFRM.setText("Fat Rats: " + frm.getPop());
		tfZRM.setText("Zombie Rats: " + zrm.getPop());

		repaint();
	}

	public void updateMsg(String message) {
			System.out.println("--------------------[Update]-------------------");
			System.out.println(message);
			tfInfo.setText(message);
	}

	public double totalPop() {
		return srm.getPop() + frm.getPop() + zrm.getPop();
	}

}