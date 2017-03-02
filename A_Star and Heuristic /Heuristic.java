/*
  Saurav Paudyal
  Artificial Intelligence
  Heuristic based Greedy Search

  This Heuristic class construts Heuristic object and greedily searches in an
  order imposed by the Heuristic function hSearch. Our greedy search assumes
  that the next best move would be to go to the next largest node that is also
  the neighbor of the current node. We assume this because if the size of our
  graph is 6 then our source is 0 and goal is 5. Since we are going from smaller
  nodes to larger ones, our Heuristic function hSearch assumes that the
  shortest path can be acheived by only going to the larger node first as long
  as their are outgoing edges.

*/


import java.util.*;
import java.io.*;


public class Heuristic
{
  //FileName
  private String fName;
  //Graph for doing our Greedy Search.
  private Graph g;
  //Queue that holds the shortest path.
  Queue<Integer> q = new LinkedList<Integer>();

  //Our constructor takes the filename as an input and creates a graph, using
  //the Graph.java class. After we have a graph we search in an order imposed by
  // our heuristic function, hSearch. This function, hSearch, assumes that the
  //next best move would be to go to the next largest node that is also the
  //neighbor of the current node. So for example if we are in node 0 and our
  //neighbors are node 1 and node 2 then, our Algorithm will pick the next
  //largest node. Another example lets say we are on Node 0 and our neighbors
  //are node 1, node 2 and node3 , and if node 3 has no outgoing edges from it,
  //then it will pick 2 and then the next possible node that has outgoing edges.
  public Heuristic(String fn){

      // The name of the file to open.
      this.fName = fn;
      // This will reference one line at a time
      String line = null;
      //tVertices for finding the total number of nodes. We use that to form
      //our graph and also to find the goal and source. initially zero if total
      //nodes is zero.
      int tVertices = 0;

      //Array splits keep both the parts after of the input after
      //spliting the input using space as a delimeter.
      String[] splits;
      //forming an empty graph for the constructor
      g = new Graph(tVertices);

      //reading from the file input
      try {
          // FileReader reads text files in the default encoding.
          FileReader fileReader = new FileReader(fName);

          // Always wrap FileReader in BufferedReader.
          BufferedReader bufferedReader = new BufferedReader(fileReader);
          tVertices = Integer.parseInt(bufferedReader.readLine());

          //forming the graph with the total number of vertices provided
          g = new Graph(tVertices);

          //while the file does not end, we keep reading the file line by line
          //splitting the line with the space delimeter and adding edges for
          //for everyother corresponding vertices.
          while((line = bufferedReader.readLine()) != null) {
              splits = line.split(" ");
              g.addEdge(Integer.parseInt(splits[0]),Integer.parseInt(splits[1]));
              }

          // Always close files.
          bufferedReader.close();
      }
      catch(FileNotFoundException ex) {
          System.out.println(
              "Unable to open file '" +
              fName + "'");
      }
      catch(IOException ex) {
          System.out.println(
              "Error reading file '"
              + fName + "'");
      }
      //setting up our source and goal for the Greedy search in an order imposed
      //by our heuristic function, hSearch
      int src = tVertices - tVertices;
      int goal = tVertices - 1;

      //Calling our heuristic function, hSearch
      hSearch(src,goal,g);

  }

  //hSearch, assumes that the next best move would be to go to the next largest
  //node that is also the neighbor of the current node.
  private void hSearch(int source, int goal, Graph gg)
  {
    //boolean array to keep track of the vertices which have no outgoing edges
    boolean[] marked = new boolean[goal+1];
    //if the vertex in the array does not have any outgoing vertices then
    //we mark it True and dont even bother taking that path.
    for(int i = 0; i < goal+1; i++){
      if(g.neighbors(i) == null){
        marked[i] = true;
      }
    }
    //First Add source in the queue since thats where we start.
    q.add(source);

    //Check for our base Case. Since I desinged it as a recurssive function
    //we need to have base case that checks if we reach the goal or not.
    //If we reached the goal, meaing our current node source is the goal
    //then we print the path using the helper method printPath and return.
    if(source == goal){
      //System.out.println(q.toString());
      printPath(q);
      return;
    }

    //here we store the neighbors in an array so that we can find the max
    //between them. To find the max value first we sort the array then we
    //return the last number in the array. We would also have to check the
    //conditon if max is the goal because if it is our calculation might
    //go out of bound and if our max is also the goal we know we found the
    //goal.
    int[] n = g.neighbors(source);
    Arrays.sort(n);
    int max = n[n.length - 1];
    if( max != goal){
      int i = 2;
      while(marked[max] == true ){
        max = n[n.length - i];
        i++;
    }
  }
    //recurssively call the function as max our source and goal is the same.
    hSearch(max, goal, gg);
  }

  //helper method to print path
  private void printPath(Queue ss){
    int t = 0;
    //System.out.println(ss.size());

    for(int i = 0; i <= ss.size(); i++){
      System.out.print(ss.remove() + ">");
    }
    System.out.println(ss.remove());
  }

}
