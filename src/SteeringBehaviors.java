
import javax.swing.JFrame;

import core.PlayerCharacter;

public class SteeringBehaviors extends JFrame {
	private static final long serialVersionUID = 1L;
	public static Board board = new Board();
	
	public SteeringBehaviors() {
        add(board);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setTitle("Java Steering Behaviors");
        setResizable(false);
        setVisible(true);
        PlayerCharacter.X= board.getLocationOnScreen().getX();
        PlayerCharacter.Y= board.getLocationOnScreen().getY();
    }

    public static void main(String[] args) {
        new SteeringBehaviors();
    }
}