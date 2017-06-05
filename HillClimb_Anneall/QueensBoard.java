/*Saurav Paudyal
CS470
PA2

QueensBoard program to generate the h value and total amount of moves to
justify success and stuck rates.

*/

import java.util.*;
import java.lang.*;

public class QueensBoard  {

  //Instance Variables
  //Using an Arraylist to represnt all the queens on the board.
  private ArrayList<Node> ql;
  //heuristic values for the board.
  private int hValue = 0;

  //Constructor for our class QueensBoard that consturcts a board with 8 queens
  //in random locations. We also initialize a temp ArrayList to track the queens
  //just in case they are placed in the same position. This Constructor takes
  //no argument
  public QueensBoard() {
    ArrayList<Integer> tempQL = new ArrayList<>();	ql = new ArrayList<>();

    //Here we go through the loop seven times generating two random numbers
    //between 1 and 8 . We then find the queens position and check if that
    //postions already contatins a queen. If it does we continue if not
    //we add the queen in that postion and increment the value of i.
    for(int i = 0; i<8; ){
      int rFirst = new Random().nextInt(8);
      int rSecond = new Random().nextInt(8);
      int qPostion = rFirst*10+rSecond;
      if(tempQL.contains(qPostion)){
        continue;
      }
      tempQL.add(qPostion);
      ql.add(new Node(rFirst, rSecond));
      i++;
    }

    //Then looping through all the queens we find the h h for each and total
    //it up to save in the variable hValues.
    for (Node current: ql) {
      hFinder(current); hValue += current.ghValS();
      }

    }

    //Constructor for our class QueensBoard that consturcts a board with 8 queens
    //in random locations. We also initialize a temp ArrayList to track the queens
    //just in case they are placed in the same position. This Constructor takes
    //an Arraylist as an argument.
    public QueensBoard(ArrayList<Node> al) {
	     this.ql = new ArrayList<Node>(al);
	     for (Node current: ql) {
	         hFinder(current);
	          hValue += current.ghValS();
	     }
     }

    //This is our function that finds the h value for a specific board.
    //Here we go though every node which represnts a queen we check if the passed
    //queen is the same as the current queen being pointed out, if yes we do
    //nothing and continue. We again check if the passed queens row equals the
    //current being pointed at's row, if yes we add one more pair thus
    //increasing the hValue. We also check if the passed queens column is the
    //same as the queens being pointed out if yes we add 1 to h. And At last
    //we check if the row difference is the same as the column difference
    //if yes we add one more to h.
    private void hFinder(Node currentQ) {
        int h = 0;
        for (Node current: ql) {
          if (currentQ.equals(current))	{continue;}
          if (currentQ.row() == current.row()){h++;}
          if (currentQ.col() == current.col()){h++;}
          if (Math.abs(currentQ.row() - current.row()) == Math.abs(currentQ.col() - current.col())){
            h++;
          }
        }
        currentQ.update(h);
      }//end of method hFinder

  //checks if this board and the passed that board are euqel or not. IF they
  //are returns true if not returns false.
  public boolean equals(QueensBoard that) {
        	return this.ql.equals(that.ql);
  }//end of equals method

  //this method returns the boards HhValeue
  public int boardHValue() {	return this.hValue;	}
  //this method reutrns the list of all the queens on the board with their positons.
  public ArrayList<Node> queenLocs() {	return ql;}
}
/*
Node sub class where each node is just a representaiton of the queen being used
in the board. It also stores the positon of the queen on the board and the
h values which determines number of pairs of queens that are attacking each
other, either directly or indirectly
*/
 class Node {
   //instance variables row column and h values for queens.
   private int r, c, hValS;

  //Constructor to construct a queen object with the paseed in row and columns
  public Node(int c, int r) {
    this.c = c; this.r = r;
  }

  //method to return the row number.
  public int row() {
    return this.r;
  }

  //method to return the column number.
  public int col() {
    return this.c;
  }

  //method updates the old hvalue with the passed one
  public void update(int hValS) {
    this.hValS = hValS;
  }

  //method to return the hVals
  public int ghValS() {
     return this.hValS;
   }
}
