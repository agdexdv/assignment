package uoa.assignment.game;

import java.util.Scanner;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Player;
import uoa.assignment.character.Monster;

public class GameLogic {

	private Map gameMap;

    public GameLogic(Map gameMap) {
        this.gameMap = gameMap;
    }
    
    public static void moveCharacter(String input, Map gameMap, GameCharacter character) {
		
         // Check if there are players or monsters in position
        if (character instanceof Player) {
            ifAttackMonster(input, (Player)character, (Monster)gameMap.characters[1], gameMap);
            ifAttackMonster(input, (Player)character, (Monster)gameMap.characters[2], gameMap);
            ifAttackMonster(input, (Player)character, (Monster)gameMap.characters[3], gameMap);
        } 
        else if(character instanceof Monster) {
            ifAttackPlayer(input, (Player)gameMap.characters[0], (Monster)character, gameMap);
        }

        if (input.equals("up")) {
            moveUp(character, gameMap);
        } else if (input.equals("down")) {
            moveDown(character, gameMap);
        } else if (input.equals("left")) {
            moveLeft(character, gameMap);
        } else if (input.equals("right")) {
            moveRight(character, gameMap);
        } else {
            System.out.println("Use only keywords up, down, left, right.");
        }
	}
	
    private static void moveRight(GameCharacter character, Map gameMap) {
        // Characters moves to the right, updating the location and map layout
        int currentRow = character.getRow();
        int currentCol = character.getCol();
        int newRow = currentRow;
        int newCol = currentCol + 1;
        
        // Update position
        character.setRow(newRow);
        character.setCol(newCol);

        // Update map layout
        String[][] layout = gameMap.getLayout();
        layout[currentRow][currentCol] = "Empty";
        if (isValidMove(newRow, newCol, layout)) {
            layout[currentRow][currentCol] = ".";
            if (character instanceof Player) {
                layout[newRow][newCol] = "*";
            } else if (character instanceof Monster) {
                layout[newRow][newCol] = "%";
            }
            // Print new layout
            gameMap.printLayout();

        } else {
            // Restore position
            character.setRow(currentRow);
            character.setCol(currentCol);
            // Print error message
            System.out.println("You can't go right. You lose a move.");
        }
    }

    private static void moveLeft(GameCharacter character, Map gameMap) {
        int currentRow = character.getRow();
        int currentCol = character.getCol();
        int newRow = currentRow;
        int newCol = currentCol - 1;
        
        // Update position
        character.setRow(newRow);
        character.setCol(newCol);

        // Update map layout
        String[][] layout = gameMap.getLayout();
        layout[currentRow][currentCol] = "Empty";
        if (isValidMove(newRow, newCol, layout)) {
            layout[currentRow][currentCol] = ".";
            character.setCol(newCol);
            if (character instanceof Player) {
                layout[newRow][newCol] = "*";
            } else if (character instanceof Monster) {
                layout[newRow][newCol] = "%";
            }
            // Print new layout
            gameMap.printLayout(); 
        } else {
            // Restore position
            character.setRow(currentRow);
            character.setCol(currentCol);
            // Print error message
            System.out.println("You can't go left. You lose a move.");
        }
    }

    private static void moveUp(GameCharacter character, Map gameMap) {
        int currentRow = character.getRow();
        int currentCol = character.getCol();
        int newRow = currentRow - 1;
        int newCol = currentCol;
        
        // Update position
        character.setRow(newRow);
        character.setCol(newCol);

        // Update map layout
        String[][] layout = gameMap.getLayout();
        layout[currentRow][currentCol] = "Empty";
        if (isValidMove(newRow, newCol, layout)) {
            layout[currentRow][currentCol] = ".";
            if (character instanceof Player) {
                layout[newRow][newCol] = "*";
            } else if (character instanceof Monster) {
                layout[newRow][newCol] = "%";
            }

            // Print new layout
            gameMap.printLayout(); 

        } else {
            // Restore position
            character.setRow(currentRow);
            character.setCol(currentCol);
            // Print error message
            System.out.println("You can't go up. You lose a move.");
        }
    }

    private static void moveDown(GameCharacter character, Map gameMap) {
        int currentRow = character.getRow();
        int currentCol = character.getCol();
        int newRow = currentRow + 1;
        int newCol = currentCol;
        
        // Update position
        character.setRow(newRow);
        character.setCol(newCol);
        
        // Update map layout
        String[][] layout = gameMap.getLayout();
        layout[currentRow][currentCol] = "Empty";
        if (isValidMove(newRow, newCol, layout)) {
            layout[currentRow][currentCol] = ".";
            if (character instanceof Player) {
                layout[newRow][newCol] = "*";
            } else if (character instanceof Monster) {
                layout[newRow][newCol] = "%";
            }
            // Print new layout
            gameMap.printLayout(); 
        } else {
            // Restore position
            character.setRow(currentRow);
            character.setCol(currentCol);
            // Print error message
            System.out.println("You can't go down. You lose a move.");
        }   
    }

    private static boolean isValidMove(int row, int col, String[][] layout) {
        // Use a Boolean expression to determine if the new location is empty
        boolean isEmpty = row >= 0 && row < layout.length &&
                        col >= 0 && col < layout[row].length &&
                        layout[row][col].equals(".");

        // Determine if the monster is in that location
        boolean isMonsterOccupied = row >= 0 && row < layout.length &&
                                    col >= 0 && col < layout[row].length &&
                                    layout[row][col].equals("%");

        if (isMonsterOccupied) {
            System.out.println("Monster already there so can't move");
        }
        return isEmpty && !isMonsterOccupied;
    }

    public static void ifAttackMonster(String input, Player player, Monster monster, Map gameMap) {
        if ((input.equals("up")) && player.getRow() - 1 == monster.getRow() && player.getCol() == monster.getCol()){
            player.hurtCharacter(monster);
        } else if ((input.equals("down")) && player.getRow() + 1 == monster.getRow() && player.getCol() == monster.getCol()) {
            player.hurtCharacter(monster);
        } else if ((input.equals("right")) && player.getRow() == monster.getRow() && player.getCol() + 1 == monster.getCol()) {
            player.hurtCharacter(monster);
        } else if ((input.equals("left")) && player.getRow() == monster.getRow() && player.getCol() - 1 == monster.getCol()) {
            player.hurtCharacter(monster);
        }
        if (monster.getHealth() <= 0) {
            gameMap.layout[monster.getRow()][monster.getCol()] = "x";
        }

    }

    public static void ifAttackPlayer(String input, Player player, Monster monster, Map gameMap) {
        if ((input.equals("up")) && monster.getRow() - 1 == player.getRow() && player.getCol() == monster.getCol()){
            monster.hurtCharacter(player);
        } else if ((input.equals("down")) && monster.getRow() + 1 == player.getRow() && monster.getCol() == player.getCol()) {
            monster.hurtCharacter(player);
        } else if ((input.equals("right")) && monster.getRow() == player.getRow() && monster.getCol() + 1 == player.getCol()) {
            monster.hurtCharacter(player);
        } else if ((input.equals("left")) && monster.getRow() == player.getRow() && monster.getCol() - 1 == player.getCol()) {
            monster.hurtCharacter(player);
        }
    }

}