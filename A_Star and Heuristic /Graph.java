/*
  Saurav Paudyal
  Artificial Intelligence
  Graph

  I did the A_star Algorithm first and then decided to do the Greedy Serach.
	Because I wanted to try different approach on this problem. I used the Graph
	class for Greedy Search.
	This is a simple graph class which I had used in the past.


*/

import java.util.LinkedList;
import java.util.*;


public class Graph<E> {
	//Instance Variables: to track the vertices, edges and the size of the graph.
	private E[] vertices;
	private LinkedList<Edge>[] edges;
	private int size = 0;

	//Suppress Warnings
	@SuppressWarnings("unchecked")
	//Constructor to make a graph with n vertices and edges.
	public Graph(int n) {
		this.vertices = (E[]) new Object[n];
		this.edges = new LinkedList[n];
	}

	//This method add and Edge between the vertex A and vertex B
	public void addEdge(int A, int B) {
		if(!isConnected(A, B))	{
			if(this.edges[A] == null)
				this.edges[A] = new LinkedList<Edge>();
			this.edges[A].add(new Edge(B));
		}
	}

	//This method checks whether vertex A and Vertex B are connected or not.
	public boolean isConnected(int A, int B) {
		if(this.edges[A] != null)
			return this.edges[A].contains(new Edge(B));
		return false;
	}

	//this method ruturns the neigbors of the vertex being passed in.
	public int[] neighbors(int vertex) {
		int[] neighbors = null;
		if(this.edges[vertex] != null){
			neighbors = new int[this.edges[vertex].size()];
					for(int i = 0; i < this.edges[vertex].size(); i++) {
						neighbors[i] = this.edges[vertex].get(i).getvnum();
					}
  	}
		return neighbors;
	}

	//returns the size of the graph.
	public int size() {
			return this.size;
	}
}

//Subclass Edge that acts as the edge Node between vertices.
class Edge {
	private int vert;
	//Constructor
	public Edge(int vnum) {
		this.vert = vnum;
	}
	//setter method
	public void setvnum(int vnum) {
		this.vert = vnum;
	}
	//getter method
	public int getvnum() {
		return this.vert;
	}
	//equals method to check if the two edges are the same.
	public boolean equals(Object obj) {
		Edge test = (Edge) obj;
		if(test.getvnum() == this.getvnum())
			return true;
		return false;
	}

}
