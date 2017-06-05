/* John Allen 3.31.2017 */

import java.util.ArrayList;
import java.util.Random;
import java.lang.StringBuilder;
import java.lang.Math;

public class ChessBoard  {
/* 
 * A CheesBoard object is just an ArrayList<Queen> of size 8; upon construction 
 * of a ChessBoard, the heuristic value of the 8-Queen value is calculated and 
 * totals the amount of moves that can be made for Queen's to capture each other
 */

  private ArrayList<Queen> theQueensList;	// list of all the Queens on the Board
  private int totalScore = 0;				// sum of scores of all Queen's on board
	
  public ChessBoard() {	      				/*Initializing a chess board: 
  -Creates the game board and places 8 queen's in random position. */
    theQueensList = new ArrayList<>();
	ArrayList<Integer> contains = new ArrayList<>();	// just in case two queens are 
	int i = 0;                                        // are placed in same spot
	while (i<8) {	
	  int int1 = new Random().nextInt(8), int2 = new Random().nextInt(8);
	  int int3 = int1*10+int2; 
	  if (contains.contains(int3))	continue;
	  contains.add(int3);
	  theQueensList.add(new Queen(int1, int2));
	  i++;
	} 
	  for (Queen current: theQueensList) {	
	    setScore(current);
	    totalScore += current.getScore();
	  }
  }
  
  public ChessBoard(ArrayList<Queen> list) {
	this.theQueensList = new ArrayList<Queen>(list);
	for (Queen current: theQueensList) {	
	  setScore(current);
	  totalScore += current.getScore();
	}
  }
  
  private void setScore(Queen herHighness) { // Heuristic Function
    int score = 0;	// number of queens in striking distance										
    for (Queen current: theQueensList) {
   
      int rowDifference = Math.abs(herHighness.getRow() - current.getRow());
      int columnDifference = Math.abs(herHighness.getColumn() - current.getColumn());
    		  
      if (herHighness.equals(current))						continue;
      if (herHighness.getRow() == current.getRow())			score++;	// add queens on same vertical axis
      if (herHighness.getColumn() == current.getColumn())	score++;	// add queens on same horizontal axis 
      if (rowDifference == columnDifference)				score++;	// add queens on the diagonals 		  
    }  
    herHighness.changeScore(score);
  }
  
  public int getBoardScore() {	return this.totalScore;	}
  public ArrayList<Queen> getQueensList() {	return theQueensList;	}
  public boolean equals(ChessBoard that) {	return this.getQueensList().equals(that.getQueensList());	}
  
  public String toString() {	/* print the board as a matrix */
	StringBuilder result = new StringBuilder();
    int[][] board = new int[8][8];
    for (int j=0; j<8; j++) {
      for (int k=0; k<8; k++)	board[j][k] = 0;	  
    } 
    for (Queen current: theQueensList)	
      board[current.getColumn()][current.getRow()] = 1;
	for (int j=0; j<8; j++) {
	  for (int k=0; k<8; k++) {
	    result.append((board[k][j] + " "));
	    if (k==7) result.append(("\n"));
	  }
	} 
	System.out.print("\n");
	return result.toString();
  } 
}