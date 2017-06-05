import java.util.Arrays;


public class HillClimbing {

    public static void randBord(boolean[][] temp){
      System.out.println("Rand Board " );
      for(int i = 0; i < 8; i++){
        temp[(int)(Math.random() * (7 - 0) + 0)][i] = true;
      }
    }

    public static boolean succesor(boolean[][] temp){
        System.out.println("succesor " );
      int h = 0;
      boolean goalState;
      int movesSucc, moveFail ;
      for(int c = 0; c < 56; c++){
        for (int r = 0;r < 8; r++ ) {
            if(temp[r][c % 8] == true){
                  temp[r][c % 8] = false;
                  temp[r][(c + 1) % 8] = true;
                  h = hValue(temp);
                  if(h == 0){
                    goalState = true;
                    return goalState;
                  }
                  //find h using three diff loops
                  //diag vert hor
                  //return h value
                  break;
              }
           }
        }
        return false;
    }

    private static int hValue(boolean[][] temp){
      System.out.println("hValue " );

      int n;
      int h = 0;

      for(int c = 0; c < 8; c++){
        n = 0;
        for(int r = 0; r < 8; r++){
          if(temp[r][c] == true){ n++; }
        }
        h = (n * (n-1))/2 + h ;

      }

      for(int r = 0; r < 8; r++){
        n = 0;
        for(int c = 0; c < 8; c++){
          if(temp[r][c] == true){ n++; }
        }
        h = (n * (n-1))/2 + h ;
      }

      int dim = 8;
      n=0;
      for( int k = 0 ; k < dim * 2 ; k++ ) {
        for( int j = 0 ; j <= k ; j++ ) {
            int i = k - j;
            if( i < dim && j < dim ) {
                //System.out.print( board1[i][j] + " " );
          //      System.out.print("[" + i + "," + j + "]");
                if(temp[i][j] == true){ n++; }
            }
        }
        h = (n * (n-1))/2 + h ;
        n=0;
        //System.out.println();
      }

//System.out.println("LADO LADO LADO");

      //number of reverse diagonal
      for (int slice = 0; slice < 2 * dim - 1; ++slice) {
          //printf("Slice %d: ", slice);
          int z = (slice < dim) ? 0 : slice - dim + 1;
          for (int j = z; j <= slice - z; ++j) {
              //System.out.print(temp[j][(dim-1)-(slice-j)] + " ");
              if(temp[j][(dim-1)-(slice-j)] == true){ n++; }

          }
          //printf("\n");
          //System.out.println();
          h = (n * (n-1))/2 + h ;
          n=0;
      }

      return h;
    }
//for(int c = 0; c < )

    public static void main(String[] args) {
        //FIrst generate the board
        //make a function to generate random queen places
        //run through 10 thousand loop
        //for each loop generate the 56 succesor by moving each queen down the
        //col
        //for ever succesor generate the h value and keep track of the min.
        //also keep track of the Success rate of Hill Climbing,
        //Average move if success, Average move if stuck

        boolean board[][] = new boolean[8][8];
        //System.out.println(Arrays.deepToString(board));

        boolean board1[][] = {
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,false,false,false,false,false},
        {false,false,false,true,false,false,false,false},
        {true,false,false,false,true,false,false,false},
        {false,true,false,false,false,true,false,true},
        {false,false,true,false,false,false,true,false},
        {false,false,false,false,false,false,false,false}};

        System.out.println(hValue(board1));

        double gS = 0;

        for(int i = 0; i < 10000; i++){
          randBord(board);
          if(succesor(board)){
            gS= gS +1;
            System.out.println(gS);
          }
        }
        System.out.println("Success Rate: " + gS/10000.0);
    }

}
