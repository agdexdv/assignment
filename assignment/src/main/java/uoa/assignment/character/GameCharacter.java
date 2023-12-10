package uoa.assignment.character;

public abstract class GameCharacter {
    
	private String name ="";
	
	private int health; 
	
	public int row;
	public int column;
    
	// Constructor to create the GameCharacter object
	public GameCharacter (String name) {
		this.name = name;
		this.health = 100;
	}
	
	public abstract void hurtCharacter (GameCharacter character);
	
	public abstract boolean successfulDefense ();
	
	// Return the name of the game character
	public String sayName() {
	    return this.name; 
	}

	// Return the health of the game character
	public int getHealth() {
		return health;
	}

	// Set the health of the game character
	public void setHealth(int health) {
		this.health = health;
	}

	// Return the row where the game character is 
    public int getRow() {
        return row;
    }

	// Return the column where the game character is 
    public int getCol() {
        return column;
    }

	// Set a new row 
    public void setRow(int newRow) {
        this.row = newRow;
    }
    
	// Set a new column
    public void setCol(int newCol) {
        this.column = newCol;
    }


}