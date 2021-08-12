/*
 * @author Rowena
 * This class implements all the methods needed by the algorithm that plays the board game.
 */

public class Evaluate {
	
	private char[][] gameBoard;
	private int rows;
	private int columns;
	private int winTiles;
	private int levels;
	
	public Evaluate (int boardRows, int boardColumns, int tilesNeeded, int maxLevels) { // constructor
		this.rows = boardRows;
		this.columns = boardColumns;
		this.winTiles = tilesNeeded;
		this.levels = maxLevels;
		gameBoard = new char[boardRows][boardColumns]; // initializes every tile on the board to 'g'
		for(int i = 0; i < boardRows; i++) {
			for(int j = 0; j < boardColumns; j++) {
				gameBoard[i][j] = 'g';
			}
		}
	}
	
	public Dictionary createDictionary() { // creates a dictionary of size 5147
		Dictionary dict = new Dictionary(5147);
		return dict;
	}
	
	public Data repeatedConfig(Dictionary dict) { // finds and returns data if string key exists in dict
		String s = getBoardString();
		return dict.get(s);
	}
	
	public void insertConfig(Dictionary dict, int score, int level) { // inserts a new data entry in dict
		String key = getBoardString();
		Data data = new Data(key, score, level);
		try {
			dict.put(data);
		} catch (DuplicatedKeyException e) {
			e.printStackTrace();
		}
	}
	
	public void storePlay(int row, int col, char symbol) { //  stores symbol in gameboard[row][col]
		gameBoard[row][col] = symbol;
	}
	
	public boolean squareIsEmpty (int row, int col) { // returns true if tiles is empty
		return (gameBoard[row][col] == 'g');
	}
	
	public boolean tileOfComputer (int row, int col) { // returns true if tile is orange
		return (gameBoard[row][col] == 'o');
	}
	
	public boolean tileOfHuman (int row, int col) { // returns true if tile is blue
		return (gameBoard[row][col] == 'b');
	}
	
	public boolean wins (char symbol) { // returns true if somebody wins
		
		String string = getBoardString();

		String winString = ""; // creates a string with a winning pattern (symbol * winTiles)
		for(int i = 0; i < winTiles; i++) {
			winString += symbol;
		}
		
		if(checkHorizontal(symbol, winString) || checkVertical(symbol, winString) || checkRightDiagonal(symbol) || checkLeftDiagonal(symbol)) {
			return true;
		}
		else return false;
	}
	
	public boolean isDraw() { // returns true if board is full
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				if(squareIsEmpty(i,j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int evalBoard() { // returns a score based on the status of the game
		if(wins('o')) { // computer wins
			return 3;
		}
		else if(wins('b')) { // human wins
			return 0;
		}
		else if(isDraw()) { // draw
			return 2;
		}
		else { // not done
			return 1;
		}
	}
	
	private String getBoardString() {
		String s = "";
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				s += gameBoard[i][j];
			}
		}
		return s;
	}
	
	private boolean checkHorizontal(char symbol, String winString) {
		for(int i = 0; i < rows; i++) { // HORIZONTAL WIN
			String s = ""; // creates a string of the values in each row
			for(int j = 0; j < columns; j++) {
				s += gameBoard[i][j];
			}
			if(winString.equals(s) || s.contains(winString)) { // if winString and s are the same, somebody wins
				return true;
			}
		}
		return false;
	}
	
	private boolean checkVertical(char symbol, String winString) {
		for(int i = 0; i < columns; i++) { // VERTICAL WIN
			String s2 = ""; // creates a string of the values in each column
			for(int j = 0; j < rows; j++) {
				s2 += gameBoard[j][i];
			}
			if(winString.equals(s2) || s2.contains(winString)) { // if winString and s2 are the same, somebody wins
				return true;
			}
		}
		return false;
	}
	
	private boolean checkRightDiagonal(char symbol) { // RIGHT DIAGONAL WIN
		int counter = 0; 
		int currentRow = rows-1;
		int currentColumn = 0;
		
		for(int i = rows-1; i >= 0; i--) { // starts from bottom left and traverses upward and rightward to find tiles with "symbol"
			for(int j = 0; j < columns; j++) {
				counter = 0;
				currentRow = i;
				currentColumn = j;
				while(currentRow >= 0 && currentColumn < columns) {
					if(gameBoard[currentRow][currentColumn] == symbol) {
						counter++;
					}
					else {
						counter = 0;
					}
					if(counter == winTiles) { // number of tiles satisfies necessary tiles to win
						return true;
					}
					currentRow--;
					currentColumn++;
				}
			}
		}
		return false;
	}
	
	private boolean checkLeftDiagonal(char symbol) { // LEFT DIAGONAL WIN
		int counter = 0;
		int currentRow = rows-1;
		int currentColumn = 0;
		
		for(int i = rows-1; i >= 0; i--) { // starts from bottom right and traverses upward and leftward to find tiles with "symbol"
			for(int j = columns-1; j >=0; j--) {
				counter = 0;
				currentRow = i;
				currentColumn = j;
				while(currentRow >= 0 && currentColumn >= 0) {
					if(gameBoard[currentRow][currentColumn] == symbol) {
						counter++;
					}
					else {
						counter = 0;
					}
					if(counter == winTiles) { // number of tiles satisfies necessary tiles to win
						return true;
					}
					currentRow--;
					currentColumn--;
				}
			}
		}
		return false;
	}
	
}
