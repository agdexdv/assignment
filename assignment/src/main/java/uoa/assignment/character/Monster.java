package uoa.assignment.character;

import java.util.Random;


public class Monster extends GameCharacter {

    public Monster(String name) {
        super(name);
        Random random = new Random();
    }

    // Attack
    public void hurtCharacter(GameCharacter character) {
        if (!character.successfulDefense()) {
            int healthValue = character.getHealth();
            healthValue -= 20;
            character.setHealth(healthValue);
        }
    }

    // Defense success or not
    public boolean successfulDefense() {
        Random random = new Random();
        return random.nextInt(2) == 0;
    }

    public String decideMove() {
        Random random = new Random();
        int moveChoice = random.nextInt(4);
        String moveDirection;

        //The numbers represent random directions of movement
        switch (moveChoice) {
            case 0:
                moveDirection = "up";
                break;
            case 1:
                moveDirection = "down";
                break;
            case 2:
                moveDirection = "left";
                break;
            case 3:
                moveDirection = "right";
                break;
            default:
                moveDirection = "";
        }
        return moveDirection;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}