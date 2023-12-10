package uoa.assignment.game;

import uoa.assignment.character.Monster;
import uoa.assignment.character.Player;
import uoa.assignment.character.GameCharacter;

public class Game {
    private Map map;
    private Player player;
    private Monster monster;
    private static boolean gameOver = false;
    
    public Game (int height, int width) {
        map = new Map(height, width);
        // Print layout to console
        map.printLayout();
    }
    			
    public Map getMap() {
        return map;
    }
    
    public boolean nextRound (String input) {
        // Player move
        player = new Player("Player");
        GameLogic.moveCharacter(input, map, player);
        System.out.println("Player is moving " + input);
    
        // Monster move
        for (GameCharacter character : map.characters) {
            if (character instanceof Monster && character.getHealth() > 0) {
                Monster monster = (Monster) character;
                String monsterMove = ((Monster) character).decideMove();
                GameLogic.moveCharacter(monsterMove, map, character);
                System.out.println(character.sayName() + " is moving " + ((Monster) character).decideMove());
                
            }
        }        

        // No living monsters, the player wins
        if (allMonstersDead()) {
            System.out.println("YOU HAVE WON!");
            return true;
        }

        // Check the player's health
        for (GameCharacter character : map.characters) {
            if (character instanceof Player && character.getHealth() <= 0) {
                gameOver = true;
                System.out.println("YOU HAVE DIED!");
                return true;
            }
        }

        // Print the health status
        for (GameCharacter character : map.characters) {
            System.out.println("Health " + character.sayName() + ":" + character.getHealth());
        }
        return true;
    }
    
    // Check to see if there are any living monsters
    private boolean allMonstersDead() {
        for (GameCharacter character : map.characters) {
            if (character instanceof Monster && character.getHealth() > 0) {
                return false;
            }
        }
        return true;
    }
}