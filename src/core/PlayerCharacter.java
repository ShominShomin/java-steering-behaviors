package core;

import java.awt.MouseInfo;

import javax.vecmath.Vector2d;

public class PlayerCharacter {
	private Vector2d position = new Vector2d();
	public static final int MAX_SPEED = 2;
	public static double X;
	public static double Y;

	public PlayerCharacter() {
		position.x = 655;
		position.y = 300;
	}

	public void move() {
		position.x = MouseInfo.getPointerInfo().getLocation().x - X;
		position.y = MouseInfo.getPointerInfo().getLocation().y - Y;
	}

	public int getX() {
		return (int) position.x;
	}

	public int getY() {
		return (int) position.y;
	}

	public Vector2d getPosition() {
		return position;
	}
}
