package uoa.assignment.game;

import java.util.Scanner;

import uoa.assignment.character.Monster;
import uoa.assignment.character.Player;
import uoa.assignment.character.GameCharacter;

public class RunGame {

	private static boolean gameOver = false;

	public static void main(String[] args) {
		int height = Integer.parseInt(args[0]);
        int width = Integer.parseInt(args[1]);

        Game game = new Game(height, width);
        Scanner scanner = new Scanner(System.in); //Read user input

        int round = 1;

        while (!gameOver) {
			System.out.println("Round " + round);
            System.out.print("Please enter a move(up, down, left, right): ");
            String input = scanner.nextLine(); // read input
            
            round++;
		}
	}
}
