package uoa.assignment.character;

import java.util.Random;

public class Player extends GameCharacter{
    // Generate random numbers
    private Random random = new Random();

	public Player(String name) {
		super(name);
	}

    // Attack
	public void hurtCharacter(GameCharacter character) {
		if (!character.successfulDefense()) {
            int healthValue = character.getHealth();
			healthValue = healthValue - 50;
			character.setHealth(healthValue);
        }
	}
	
    // Defense success or not
	public
	boolean successfulDefense() {
		return Math.random() < 0.3;
	}
    
    public int getHealth() {
        return 100;
    }
    
    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
