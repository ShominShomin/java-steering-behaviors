package core;

import javax.vecmath.Vector2d;

import enums.BehaviorEnum;

public class Boid {
	public static final Float MASS = 1f;
	public static final int MAX_SPEED = 2;

	private Vector2d position;
	private Vector2d velocity;
	private Vector2d steerForce;
	private double distanceToTarget;
	private Vector2d desiredVelocity;

	public Boid(Vector2d initialPosition) {
		desiredVelocity = new Vector2d();
		position = initialPosition;
		velocity = new Vector2d(0, 0);
		steerForce = new Vector2d();
		distanceToTarget = 0d;
	}

	public void seek(Vector2d targetPosition) {
		desiredVelocity.sub(targetPosition, position);
		distanceToTarget = desiredVelocity.length();
		desiredVelocity.normalize();
		desiredVelocity.scale(MAX_SPEED);
		steerForce.sub(desiredVelocity, velocity);
	}

	public void flee(Vector2d targetPosition) {
		desiredVelocity.sub(position, targetPosition);
		distanceToTarget = desiredVelocity.length();
		desiredVelocity.normalize();
		desiredVelocity.scale(MAX_SPEED);
		steerForce.sub(desiredVelocity, velocity);
	}

	public void update(Vector2d targetPosition, BehaviorEnum behavior) {
		chooseBehavior(targetPosition, behavior);
		Vector2d acceleration = new Vector2d();
		acceleration.x = steerForce.x / MASS;
		acceleration.y = steerForce.y / MASS;
		velocity.add(acceleration);
		position.add(velocity, position);
	}

	private void chooseBehavior(Vector2d targetPosition, BehaviorEnum behavior) {
		switch (behavior) {
		case SEEK:
			seek(targetPosition);
			break;
		case FLEE:
			flee(targetPosition);
			break;
		}
	}

	public Vector2d getSteerForce() {
		return steerForce;
	}

	public int getX() {
		return (int) position.x;
	}

	public int getY() {
		return (int) position.y;
	}

	public int getDistanceToTarget() {
		return (int) distanceToTarget;
	}
}
