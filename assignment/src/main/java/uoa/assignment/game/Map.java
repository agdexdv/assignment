package uoa.assignment.game;

import java.util.List;

import uoa.assignment.character.GameCharacter;
import uoa.assignment.character.Monster;
import uoa.assignment.character.Player;


public class Map {

    public String [][] layout;
    public GameCharacter characters [];
    private List<Monster> Monsters;

    // Returns the layout of the map
  public String[][] getLayout() {
    return layout;
  }
  
  public Map (int height, int width) {
    layout = new String[height][width];
    characters = new GameCharacter[4];
    initialiseArray();
    createCharacters();
  }

    // Initializes the map layout array with each element set to "."
  private void initialiseArray() {
    //Iterate over each element of the layout array and assign it to the period character '.'.
    for (int i = 0; i < layout.length; i++) { //Iterate over each row of the two-dimensional array layout
      for (int j = 0; j < layout[i].length; j++) { //Iterate over each element in row i of the two-dimensional array layout.
        layout[i][j] = ".";
      }
    }
  }

    // Print map layout
  public void printLayout() {
    for (int i = 0; i < layout.length; i++) {
      for (int j = 0; j < layout[i].length; j++) {
        System.out.println(layout[i][j]);
      }
      System.out.println();
    }
  }

    // Create characters on the map
  private void createCharacters() {
    // Create a Player instance
    Player player = new Player("Player");
    int playerRow = layout.length - 1; // Get the number of rows and subtract 1 to find the player's row position
    int playerColumn = layout[0].length - 1;
    layout[playerRow][playerColumn] = "*";
    player.setRow(playerRow);
    player.setColumn(playerColumn);
    characters[0] = player;

    // Create three Monster instances
    Monster monster1 = new Monster("Monster1");
    Monster monster2 = new Monster("Monster2");
    Monster monster3 = new Monster("Monster3");
    int monster1Row = 0;
    int monster1Column = layout[0].length - 1;
    int monster2Row = layout.length - 1;
    int monster2Column = 0;
    int monster3Row = 0;
    int monster3Column = 0;
    layout[monster1Row][monster1Column] = "%";
    layout[monster2Row][monster2Column] = "%";
    layout[monster3Row][monster3Column] = "%";
    monster1.setRow(monster1Row);
    monster1.setColumn(monster1Column);
    monster2.setRow(monster2Row);
    monster2.setColumn(monster2Column);
    monster3.setRow(monster3Row);
    monster3.setColumn(monster3Column);
    characters[1] = monster1;
    characters[2] = monster2;
    characters[3] = monster3;
  }

  public List<Monster> getMonsters() {
        return Monsters;
    }
}
