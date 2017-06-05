/*Saurav Paudyal
CS470
PA2

SearchMethods program implements both Hill Climbing and Simulated Annealing on
8-queens problems.Given an 8×8 chessboard. We need to put 8 queens on it and
no two queens threaten each other. Thus, a solution requires that no two queens
share the same row, column, or diagonal.

In Hill Climbing algorithm  we had  to find a successor state which have the lowest h value.
If there are  multiple equal good successor states, we just randomly move to
one of them. Else we store the currQ state as the local optimal.

For Simulated Annealing we only move to a successor state which have a high h
value .

*/


import java.util.*;
import java.lang.*;

public class SearchMethods {

  //Instance Variables declarations
  //temperature of simms method
   static double	temp;
  //number of  starts goes to a goalstate
   static double hcSucc;
   //total number of moves
   static double hcTotalMoves;
   //number of successful HillClimbing moves
   static double hcSuccMv;
   //number of start goes to a goalstate
   static double simmsSucc;
   //number of moves taken
   static double simmsTotalMoves;
   //number of successful moves
   static double simmsSuccMV;

   //getter methods
   //returns number of starts that leads to success
    public static double succRateHC(){
      return SearchMethods.hcSucc;
    }
    //returns the total number of succesful moves.
    public static double totalSuccessMoves(){
      return SearchMethods.hcSuccMv;
    }
    //returns the total number of moves for Hill Climbing
    public static double totalMovesHill(){
      return SearchMethods.hcTotalMoves;
    }
    //returs=ns the number of succesful starts that leads to goal
    public static double succRateSimms(){
      return SearchMethods.simmsSucc;
    }
    //returns the number of moves for those succesful starts
    public static double totalSuccessMovesSimms(){
      return SearchMethods.simmsSuccMV;
    }
    //returns the total number of moves of Simmulated Annealing.
    public static double totalMovesSims(){
      return SearchMethods.simmsTotalMoves;
    }

    //priavte method that returns makes a new random board and returns it
   private static QueensBoard randMv(QueensBoard qb) {
     ArrayList<Node> qList =  new ArrayList<>(qb.queenLocs());
 	   Node del = qList.remove(new Random().nextInt(8));
 	   return createBoard(qList, del);
   }

   //private method that chooses the move that lowers the hvalue of the state.
   //first we make sure the temperature is 0 or less then we generate cooling
   //factor and then check if a random number * temp is greater than cooling
   //factor * random number. If it is we return true if not we return false.
   /*decides whether to take a move that results in a lower state */
   private static boolean pickMove(double tempScale) {
     if (tempScale <= 0){
       return false;
     }
 	   double cooling = SearchMethods.temp * 1.4;
 	   return (tempScale * Math.random()) > (cooling * Math.random()) ? true : false;
   }

   //Another private method that creates a board with queens from the passed ,
   //queens postions and moving queen.First we generate a queen in a random row
   //and column. Then we also check if we have to jump or not .
   private static QueensBoard createBoard(ArrayList<Node> lastPostion, Node moving) {
 	   while (lastPostion.size() == 7) {
 	      Node temp = new Node(new Random().nextInt(8), new Random().nextInt(8));
        if (temp.equals(moving) || lastPostion.contains(temp))
        {
          continue;
        }
     	  if (temp.col() == moving.col())	{
          lastPostion.add(temp);
        }
     	  if (temp.row() == moving.row()){
          		lastPostion.add(temp);
        }
     	  if (Math.abs(moving.row() - temp.row()) == Math.abs(moving.col() - temp.col())){
          lastPostion.add(temp);
          }
     }
     return new QueensBoard(lastPostion);
   }

   //private function to test the if two queens are on the same postion.
   private static boolean jump(Node newQueen, ArrayList<Node> currentQueens) {
 	    for (Node x: currentQueens)	{
        if(newQueen.equals(x))	{
          return true;
      }}
     return false;
   }

  //Hill Climbing method that calls the private method searchBoard that performs
  //the hill climbing search . The method also stores the number of moves
  //for each board , stores the number of moves for each look up, and also
  //store the successful number of boards. We pass in a board with queens in
  //random postions.
  public static void hill(QueensBoard qb) {
	   searchBoard(qb, null, qb.boardHValue(), 0, false, 0);
  }

  //Simmulated Annealing method that calls the private method searchBoard that performs
  //the Simmulated Annealing search . The method also stores the number of moves
  //for each board ,cools the temperature, stores the number of moves for each
  //look up, and also store the successful number of boards. We pass in a board
  //with queens in random postions and also the temperature for the Annealing
  //process.
  public static void simulatedAnnealing(QueensBoard qb, double tempScale) {
    SearchMethods.temp = tempScale;
    searchBoard(qb, null, qb.boardHValue(), 0, true, tempScale);
  }

  //private method searchBoard that takes a board, previous state, h values,
  //number of moves initially 0, binary value to determine which proess :
  // Hill Climbing or Simmulated Annealing, and the temperature scale for
  //annealing process. This function will either give us the solution or the
  //state if its stuck.
  private static void searchBoard(QueensBoard qb, QueensBoard prevState,
              int hScores,int tMoves, boolean hORs, double tempScale) {
      //First we store the passed in value as our previous State
      prevState = qb;
      //We decalre a new board to store our next state.
      QueensBoard newSt;
      //then we check if we are doing Hill climbing search or the simulated
      //Annealing. If the search is false we will use the hillClimbing method
      //else we use the simmulated Annealing method.
    	if (!hORs){
        newSt = newMv(qb);
      }	else{
        newSt = newMv(qb, hORs, tempScale);
      }//endif

      //Now we check if the new State we previusly created is the same as the
      //the previous if it is we check the h values. If the h Value is 0 then
      //then we know we founf a goal state so we just check right search method
      //and increasea the successful starts and number of succful moves.
    	if (newSt.equals(prevState)) {
    	  if (hScores == 0) {
    	    if (!hORs) {
    	      SearchMethods.hcSucc++; SearchMethods.hcSuccMv += tMoves;
    	    }	else{
    	      SearchMethods.simmsSucc++; SearchMethods.simmsSuccMV += tMoves;
    	    }
    	  }
    	  return;
    	}// We update the hVlaues and then return

      //we again check which search method first then we increase the total
      //number of moves taken to raech that specific state.
    	if (!hORs) {
    	  SearchMethods.hcTotalMoves++;
    	  searchBoard(newSt, prevState, newSt.boardHValue(), ++tMoves, hORs, 0);
    	}	else {
    	  SearchMethods.simmsTotalMoves++;
    	  searchBoard(newSt, prevState, newSt.boardHValue(), ++tMoves, hORs, --tempScale);
    	}
      }

  //Private method new Move which finds the next move to be taken for the
  //specified search algorithm. It takes a Queen Board object as an argument and
  // returns a new QueensBoard object with new queen postion. This is for Hill
  //Climbing method.
  private static QueensBoard newMv(QueensBoard prevState) {
    return newMv(prevState, false, 0);
  }

  //Private method new Move which finds the next move to be taken for the
  //specified search algorithm. It takes a Queen Board object as an argument and
  // returns a new QueensBoard object with new queen postion. This is for
  //simmulated Annealing method.
  private static QueensBoard newMv(QueensBoard prevState, boolean simms, double tempScale){

    //Declaring the variables.
    //We use a hash map to store the moves according with the boards.This maps
    //other available moves with the board.
  	HashMap<Integer ,QueensBoard> otherMoves = new HashMap<>();
    //stores the best hvalues for the currQ board being pointed at.
  	Integer lowHVal = prevState.boardHValue();
    //we put the prevstate its hvalue as the best for now.
  	otherMoves.put(lowHVal, prevState);
    //list of quuens.
  	ArrayList<Node> qList = new ArrayList<>();

    //If the argument passed is a simmulated annealing method then
    // we first make a random board and check if the hvalue is lower than the
    //previuous boards hValue if it is we return the random the board. We also
    // check if the random move leads to a goal state . If it does we return the
    //random board.
  	if (simms) {
  		  QueensBoard  r = SearchMethods.randMv(prevState);
  		  if(r.boardHValue() < prevState.boardHValue()){
          return r;
        }
  		  if(pickMove(tempScale)){
          return r;
        }
  	}
    //Now we check for every queen in the list of the previous state and add
    //that queen in the list.
  	for (Node x: prevState.queenLocs()){
      	qList.add(x);
    }
    //We check again in the list but we remove the currQ queen from the list
    //and store the values of currQ rows and columns.
  	for (Node currQ: prevState.queenLocs()) {
  	  qList.remove(currQ);
  	  int currCols = currQ.col();
      int currRows = currQ.row();
      //First we run a diogonally checking for the pair of queens in each
      //diagonal and then we update the current rows and columns. For the
      //diagonal checking we check while the current colmun and rows are not out
      //of bound. First we make a new queen with current rows and colmuns and
      //add that to the list. Then we check if the new Queen is the same as the
      //current queen or not. if its not then we create the board that is
      //tested against the current queen.
  	  while (++currCols < 8 && --currRows > 0) {
  		Node newQueen = new Node(currCols, currRows);
  	    qList.add(newQueen);
  	    if (!newQueen.equals(currQ)) {
          //It jumps to a new state when two queens are in the same spot.
  	      if (!jump(newQueen, prevState.queenLocs())) {
  	         QueensBoard tempBoard = new QueensBoard(qList);
  			        if (tempBoard.boardHValue() < otherMoves.get(lowHVal).boardHValue()) {
  			             lowHVal = tempBoard.boardHValue();
  		               otherMoves.put(lowHVal, tempBoard);
              }
          } qList.remove(newQueen); } }
    //Updating the current rows and colmuns.
	  currCols = currQ.col();
	  currRows = currQ.row();

    //Second diagonal check
    ////First we run a diogonally checking for the pair of queens in each
    //diagonal and then we update the current rows and columns. For the
    //diagonal checking we check while the current colmun and rows are not out
    //of bound. First we make a new queen with current rows and colmuns and
    //add that to the list. Then we check if the new Queen is the same as the
    //current queen or not. if its not then we create the board that is
    //tested against the current queen.
	  while (--currCols > 0 && ++currRows < 8) {
	    Node newQueen = new Node(currCols, currRows);
    	qList.add(newQueen);
	    if (!newQueen.equals(currQ)) {
	      if (!jump(newQueen, prevState.queenLocs())) {
	         QueensBoard tempBoard = new QueensBoard(qList);
	   	      if (tempBoard.boardHValue() < otherMoves.get(lowHVal).boardHValue()) {
		            lowHVal = tempBoard.boardHValue();
		            otherMoves.put(lowHVal, tempBoard);
            }
		  }qList.remove(newQueen); } }
    //Updating the current rows and colmuns.
	  currCols = currQ.col();
	  currRows = currQ.row();

    //third diagonal check
    ////First we run a diogonally checking for the pair of queens in each
    //diagonal and then we update the current rows and columns. For the
    //diagonal checking we check while the current colmun and rows are not out
    //of bound. First we make a new queen with current rows and colmuns and
    //add that to the list. Then we check if the new Queen is the same as the
    //current queen or not. if its not then we create the board that is
    //tested against the current queen.
	  while (--currCols > 0 && --currRows > 0) {
        Node newQueen = new Node(currCols, currRows);
 		qList.add(newQueen);
		if (!newQueen.equals(currQ)) {
		  if (!jump(newQueen, prevState.queenLocs())) {
	        QueensBoard tempBoard = new QueensBoard(qList);// creates board that is tested against currQ optimal board
		    if (tempBoard.boardHValue() < otherMoves.get(lowHVal).boardHValue()) {
		      lowHVal = tempBoard.boardHValue();	// record value of currQ lowHVal
			  otherMoves.put(lowHVal, tempBoard);	// saves state of board when a better move is found
		  	}
		  }
		  qList.remove(newQueen); } }
      //Updating the current rows and colmuns.
	  currCols = currQ.col();
	  currRows = currQ.row();

    //fourth diagonal check
    ////First we run a diogonally checking for the pair of queens in each
    //diagonal and then we update the current rows and columns. For the
    //diagonal checking we check while the current colmun and rows are not out
    //of bound. First we make a new queen with current rows and colmuns and
    //add that to the list. Then we check if the new Queen is the same as the
    //current queen or not. if its not then we create the board that is
    //tested against the current queen.
	  while (++currCols < 8 && ++currRows < 8) {
	    Node newQueen = new Node(currCols, currRows);
		  qList.add(newQueen);
	    if (!newQueen.equals(currQ)) {
	      if (!jump(newQueen, prevState.queenLocs())) {
	        QueensBoard tempBoard = new QueensBoard(qList);// creates board that is tested against currQ optimal board
			    if (tempBoard.boardHValue() < otherMoves.get(lowHVal).boardHValue()) {
	    	      lowHVal = tempBoard.boardHValue();	// record value of currQ lowHVal
			        otherMoves.put(lowHVal, tempBoard);
          }
        }
		  qList.remove(newQueen);	}}
    //now we store the row and and column values so that we can place
    //different queens in the same row and co,lumns
	  int alterRow = currQ.row(); int alterColumn = currQ.col();

    //Horizontal check
    //First we create a new Node that is used for moving around. Then we add a
    // node for testing purposes and then we create a board that is already
    //tested with the current queen postion. At the end we record the values of
    //current queen and save the state of board when an alternative move is
    //found
    for (currCols=0; currCols<8; currCols++) {
        Node newQueen = new Node(currCols , alterRow);
      	qList.add(newQueen);
      		if (!newQueen.equals(currQ)) {
      		  if (!jump(newQueen, prevState.queenLocs())) {
      		    QueensBoard tempBoard = new QueensBoard(qList);
      		    if (tempBoard.boardHValue() < otherMoves.get(lowHVal).boardHValue()) {
      		      lowHVal = tempBoard.boardHValue();
      		      otherMoves.put(lowHVal, tempBoard);
      		    }
      		}
      }
    qList.remove(newQueen);		// remove test scenario queen from test qList
    }

    //Vertiacal check
    //First we create a new Node that is used for moving around. Then we add a
    // node for testing purposes and then we create a board that is already
    //tested with the current queen postion. At the end we record the values of
    //current queen and save the state of board when an alternative move is
    //found
	  for (currRows=0; currRows<8; currRows++) {
		    Node newQueen = new Node(alterColumn , currRows);	// Create a new Node that is used for moving around
		    qList.add(newQueen);			// add new Node to testing scenario
		    if (!newQueen.equals(currQ)) {
		        if (!jump(newQueen, prevState.queenLocs())) {
		            QueensBoard tempBoard = new QueensBoard(qList);	// creates board that is tested against currQ optimal board
		              if (tempBoard.boardHValue() < otherMoves.get(lowHVal).boardHValue()) {
		                  lowHVal = tempBoard.boardHValue();// record value of currQ lowHVal
		                  otherMoves.put(lowHVal, tempBoard);// saves state of board when a better move is found
		              }
             }
        }qList.remove(newQueen);
	  }qList.add(currQ);
	}

  //For the simmulated annealing if the game does not changes we try again
  //by reducing the temperature untill it reaches 0.
	if (simms && Math.random() > 0.1 && tempScale > 0){
	  if (otherMoves.get(lowHVal) == prevState && otherMoves.get(lowHVal).boardHValue() != 0){
	    return SearchMethods.randMv(prevState);
    }
  }
  //Else return the best score.
	return otherMoves.get(lowHVal);
  }
}
