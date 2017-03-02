/*
  Saurav Paudyal
  Artificial Intelligence
  Node

  This is the Node class representing the vertices for our A_star search.


*/

import java.util.*;

public class Node implements Comparator<Node>
{
    public int name;//name is pretty much the node number in our case

    //fScore holds the values for each node the total cost of getting from the
    //start node to the goal by passing by that node. That value is  known as
    //being partly heuristic.
    public int fScore;


    //Constructor to create Empty nodes.
    public Node()
    { }

   //Construtor which takes an int and the fScore to construct a Node object
   public Node(int name, int fScore)
    {
        //refrencing the passed values
        this.fScore = fScore;
        this.name = name;
    }

    //Inorder for the PriorityQueue we need to have the Node class be compareable
    //Simple Compare method which returns -1 if the cost of getting from the
    //start node to the g1 node is lesser than the cost of getting from the
    //start node to the g2 node.Returns 1 if the cost of getting from the
    //start node to the g1 node is greater than the cost of getting from the
    //start node to the g2 node. And returns 0 if both the costs are same.
    @Override
   public int compare(Node g1, Node g2)
    {
      if(g1.fScore < g2.fScore){ return -1;}
      if(g1.fScore > g2.fScore){ return 1; }
      return 0;
    }

    //Since our Node implements Comparator we would also need to Override the
    //equals method.If both the object have same reference then we know they
    //are equal. If the object being compared with is null then we know they
    //are not equal. If they do not belong to the same class then they are not
    //equal. For them to be equal both the Node object need to have the same
    //instance variable name.
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (this == null) return false;

        if(this.getClass() != obj.getClass()) return false;

            Node n = (Node) obj;
            if (this.name == n.name)
            {
                return true;
            }
            return false;

    }
}
