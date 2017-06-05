/*
  Saurav Paudyal
  Artificial Intelligence
  MainMethod

To test the program just compile this program and run it with the input file
as command line argument:

Example input file: pa1.in

6       <- There is totally 6 nodes which is 0,1,2,3,4,5
0 1 2   <- A path from node 0 to 1 which distance is 2.
0 2 5   <- All following lines have the same format
1 3 2
1 4 4
2 4 1
2 5 5
4 3 3
4 5 2

*/

import java.util.*;
import java.io.*;

public class MainMethod {

public static void main(String[] args) throws IOException
{       if(args.length != 1){
            System.out.println("\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_");
            System.out.println("ERROR: NO INPUT FILE FOUND");
            System.out.println("Please provide a input file as the command line argument.");
            System.out.println("EXAMPLE : java MainMethod pa1.in");
            System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_\n");
            System.exit(0);
        }

       // The name of the file to open.
        String fileName = args[0];

        // This will reference one line at a time
        String line = null;
        //Other instance  variables. Array splits keep both the parts after
        //spliting the input using space as a delimeter
        String[] splits;
        //dimensionsension for our adjency matrix representing our graph, and
        //also to keep track of total number of nodes . We can figure out the
        //source and the goal from these.
        int dimensions = 0;
        //Array graph that represnts the adjency matrix
        int graph[][] = new int[dimensions][dimensions];
        //Array holding the hvalues or cost.
        int hValues[] = new int[dimensions];

        //reading from the file input
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                new BufferedReader(fileReader);

                //dimensions for our adjency matrix
                dimensions = Integer.parseInt(bufferedReader.readLine());
                graph = new int[dimensions][dimensions];//new graph matrix
                hValues = new int[dimensions];
            //while the file does not end, we keep reading the file line by line
            //splitting the line with the space delimeter and filling up the
            //edges in our adjency matrix
            while((line = bufferedReader.readLine()) != null) {
                splits = line.split(" ");
                graph[Integer.parseInt(splits[0])][Integer.parseInt(splits[1])]
                = Integer.parseInt(splits[2]);
            }

            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" +
                fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '"
                + fileName + "'");
        }

    //Greedy Search results.
    System.out.println("Shortest Path using the Greedy Search:");
    Heuristic h = new Heuristic("pa1.in");

    //setting up our source and goal for the A_star search.
    int s = dimensions - dimensions;
    int g = dimensions - 1;

    //A_star search results.
    System.out.println("Shortest Path using A* search:");
    AStar a = new AStar(dimensions);//Making a new AStar object
    a.aStar(graph, hValues,s,g); //calling the aStar function

     }
}
