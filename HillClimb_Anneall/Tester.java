
/*Saurav Paudyal
CS470
PA2

Tester program to test the implementation of Hill Climbing and Simualated
Annealing

We run thorught the loop 10000 times generating a random board everytime.
For each board we run both the Hill Climbing and Simmulating Annealing
algorithms. Then we display the Success Rate of Hill Climbing, Average moves if
Success and Average moves if Stuck.

*/

public class Tester {
  public static void main(String[] args) {

    for(int i = 1; i <= 10000; i++){
      QueensBoard qb = new QueensBoard();
      SearchMethods.hill(qb);
      SearchMethods.simulatedAnnealing(qb, 100);
    }
    System.out.println("Hill Climbing resutls : ");
    double stuckMoves = SearchMethods.totalMovesHill() - SearchMethods.totalSuccessMoves();
    double succRate =  SearchMethods.succRateHC() / (10000.0);
    double avgStuckRate = stuckMoves / (10000 - SearchMethods.succRateHC());
    double avgSuccMove = SearchMethods.totalSuccessMoves() / SearchMethods.succRateHC();
    System.out.println("Success Rate of Hill Climbing : " + String.format("%.2f", 100 * succRate));
    System.out.println("Average moves if Success : " + String.format("%.2f", avgSuccMove));
    System.out.println("Average moves if Stuck : " + String.format("%.2f", avgStuckRate));

    System.out.println("\nSimulated Annealing resutls : ");
    stuckMoves = SearchMethods.totalMovesSims() - SearchMethods.totalSuccessMovesSimms();
    succRate  =  SearchMethods.succRateSimms() / 10000;
    avgStuckRate = stuckMoves / (10000 - SearchMethods.succRateSimms());
    avgSuccMove = SearchMethods.totalSuccessMovesSimms() / SearchMethods.succRateSimms();
    System.out.println("Success Rate of Hill Climbing : " + String.format("%.2f", 100 * succRate));
    System.out.println("Average moves if Success : " + String.format("%.2f", avgSuccMove));
    System.out.println("Average moves if Stuck : " + String.format("%.2f", avgStuckRate));

  }
}
