/*
  Saurav Paudyal
  Artificial Intelligence
  AStar

  Source: https://en.wikipedia.org/wiki/A*_search_algorithm

  This was the pseudocode I used::

  function A*(start, goal)
    // The set of nodes already evaluated.
    closedSet := {}
    // The set of currently discovered nodes that are not evaluated yet.
    // Initially, only the start node is known.
    openSet := {start}
    // For each node, which node it can most efficiently be reached from.
    // If a node can be reached from many nodes, cameFrom will eventually contain the
    // most efficient previous step.
    cameFrom := the empty map

    // For each node, the cost of getting from the start node to that node.
    gScore := map with default value of Infinity
    // The cost of going from start to start is zero.
    gScore[start] := 0
    // For each node, the total cost of getting from the start node to the goal
    // by passing by that node. That value is partly known, partly heuristic.
    fScore := map with default value of Infinity
    // For the first node, that value is completely heuristic.
    fScore[start] := heuristic_cost_estimate(start, goal)

    while openSet is not empty
        current := the node in openSet having the lowest fScore[] value
        if current = goal
            return reconstruct_path(cameFrom, current)

        openSet.Remove(current)
        closedSet.Add(current)
        for each neighbor of current
            if neighbor in closedSet
                continue		// Ignore the neighbor which is already evaluated.
            // The distance from start to a neighbor
            tt_gScore := gScore[current] + dist_between(current, neighbor)
            if neighbor not in openSet	// Discover a new node
                openSet.Add(neighbor)
            else if tt_gScore >= gScore[neighbor]
                continue		// This is not a better path.

            // This path is the best until now. Record it!
            cameFrom[neighbor] := current
            gScore[neighbor] := tt_gScore
            fScore[neighbor] := gScore[neighbor] + heuristic_cost_estimate(neighbor, goal)

    return failure

    function reconstruct_path(cameFrom, current)
        total_path := [current]
    while current in cameFrom.Keys:
        current := cameFrom[current]
        total_path.append(current)
    return total_path

*/
import java.util.*;


public class AStar
{
    //total number of nodes
    private int totalNodes;

    //array to hold all the values from the heuristic function
    private int hValues[];

    //This pQ acts as the open Set or the Fringe.
    private PriorityQueue<Node> pQ;

    //Constructor takes an int value representing the total number of vertices.
    //It initializes the totalNodes of the current object to the passed value
    //and also creates a new PriorityQueue with empty Nodes and totalNodes
    //number of elements
    public AStar(int totalNodes)
    {
        this.totalNodes = totalNodes;
        this.pQ = new PriorityQueue<Node>(this.totalNodes,new Node());
    }

    //A* Search Algrothim Method which takes an passedGraph, heuristic values
    //source of the graph, and goal. In our Example source 0, goal 5.
    public void aStar(int passedGraph[][], int[] hValues,int source,int goal)
    {
        // Closed set of nodes already evaluated.
        boolean marked[] = new boolean [totalNodes];
        this.hValues = hValues;
        //The distance from start to a neighbor initially .
        int tt_gScore = 0;
        //this acts as a pointer to the current vertex
        int currentNode;

        //cameFrom, fScore and gScore arrays as mentioned in pseudocode.
        // If a node can be reached from many nodes, cameFrom will eventually
        //contain the most efficient previous step. For each node, gscore is the
        // cost of getting from the start node to that node. And fScore holds
        //the values for each node the total cost of getting from the start node
        //to the goal by passing by that node. That value is  known as being
        //partly heuristic.
        int cameFrom[] = new int [totalNodes];
        int fScore[] = new int [totalNodes];
        int gScore[] = new int [totalNodes];

        //Adding the first/source to the queue . For the first node, that value
        //is completely heuristic. We also mark the source to be seen and add
        //a cost of 0 since that is the source.
        pQ.add(new Node(source, this.hValues[source]));
        marked[source] = true;
        gScore[source] = 0;

        //While the priorityQueue, which is our open set, is not empty we
        // we remove the node with the lowest score and mark that node as seen.
        //If the current node is the goal node then we call the method
        //reconstruct_path, which returns the shortest path.
        while (!pQ.isEmpty())
        {
         //get node with lowest score and remove from our open set priorityQueue
            Node n = pQ.remove();
            currentNode = n.name;
            //also mark it as seen.
            marked[currentNode] = true;

            //If the current node is the goal node then we call the method
            //reconstruct_path, which returns the shortest path
            if(currentNode == goal) {
                reconstruct_path(cameFrom, currentNode);
            }

            //For each neighbor of the current node, if the neighbor is in the
            //closed set, we ignore those neighbor since its already evaluated.
            for(int i = 0; i < totalNodes; i++) {
              if(passedGraph[currentNode][i] == 0 ){continue;}
              if(marked[i] != false) continue;
              // The distance from start to a neighbor
              tt_gScore = gScore[currentNode] + passedGraph[currentNode][i];

              //if the neighbor is not present in the openSet priorityQueue,
              //meaning we discovered a new node. We add that node. Else if
              //the tt_gScore is greater than the neighbor's gscore
              //we ignore the neighbor
              if((pQ.contains(new Node(i,fScore[i]))) == false) {
                                    pQ.add(new Node(i,fScore[i]));
              }else if(tt_gScore >= gScore[i]) continue;

              pQ.remove(new Node(i,fScore[i]));

              //Since this path is the best till now
              // we keep track of it and record it.
              cameFrom[i] = currentNode;
              gScore[i] = tt_gScore;
              fScore[i] = gScore[i] + this.hValues[i];

              pQ.add(new Node(i,fScore[i]));
            }
        }
    }

    //This is the helper method which takes array cameFrom and currentNode.
    //If a node can be reached from many nodes, cameFrom will eventually
    //contain the most efficient previous step.
    private void reconstruct_path(int[] cameFrom, int currentNode) {
      int total_path  = currentNode;
      int nodeNumber;
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(new Integer(total_path ));

      //while the current node is in camefrom , we make the current the total
      //path and append the current to the total path to be returned.
      while(total_path  != 0) {
        total_path  = cameFrom[total_path ];
        q.add(new Integer(total_path ));
      }

      int[] result = new int[q.size()];
      int i = 0;

      //returning the total path.
      while(!q.isEmpty()) {
        result[i++] = q.remove();
        }
        //Helper method to print the path
        printPath(result);
    }

    //helper method to print path
    private void printPath(int[] result){
      for(int i = result.length - 1; i >= 1; i--){
        System.out.print(result[i] + ">");
      }
      System.out.println(result[0]);
    }
}
