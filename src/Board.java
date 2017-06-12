
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.vecmath.Vector2d;

import core.Boid;
import core.PlayerCharacter;
import enums.BehaviorEnum;

public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private Boid boid;
	private PlayerCharacter playerCharacter;
	private BehaviorEnum behavior;
	private Graphics2D graphics2d;

	public Board() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.WHITE);
		setDoubleBuffered(true);

		boid = new Boid(new Vector2d(400, 300));
		playerCharacter = new PlayerCharacter();
		behavior = BehaviorEnum.SEEK;

		timer = new Timer(10, this);
		timer.start();
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		graphics2d = (Graphics2D) g;
		drawCircles();
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	private void drawCircles() {
		graphics2d.setColor(Color.GRAY);
		graphics2d.fillOval(boid.getX(), boid.getY(), 30, 30);
		graphics2d.setColor(Color.GREEN);
		graphics2d.fillOval(playerCharacter.getX(), playerCharacter.getY(), 30, 30);
	}

	public void actionPerformed(ActionEvent e) {
		playerCharacter.move();
		boid.update(playerCharacter.getPosition(), behavior);
		repaint();
	}

	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_S) {
				behavior = BehaviorEnum.SEEK;
			}
			if (key == KeyEvent.VK_F) {
				behavior = BehaviorEnum.FLEE;
			}
		}
	}
}
