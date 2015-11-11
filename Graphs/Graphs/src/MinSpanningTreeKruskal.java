

import java.util.Arrays;
import java.util.Comparator;

/*
 * Input: A weighted undirected graph. 
 * Output: Graph Connecting all vertices with minimum sum of weights of all edges   
 * Algorithm:
 *  1. Maintain a set which contains vertices already in MST. Initially this set is empty
 * 	1. Sort all the edges in ascending order of weights
 *  2. For each Edge, check if the vertices are already in MST set, if they are then ignore this edge, else 
 *     add it to MST set.
 *  3. Repeat step until V-1 edges are added into MST set. In MST with V vertices, there will be V-1 edges.
 */
public class MinSpanningTreeKruskal {

	public static void main(String args[]) {
		Graph_MST graph = create_Graph(4,5);
		graph.edges[0] = new Edge(0,1,10);
		graph.edges[1] = new Edge(0,2,6);
		graph.edges[2] = new Edge(0,3,5);
		graph.edges[3] = new Edge(1,3,15);
		graph.edges[4] = new Edge(2,3,4);
		
		minSpanTree(graph);
	}
	
	
	public static Graph_MST create_Graph(int v,int e) {
		Graph_MST graph = new Graph_MST(v,e);
		return graph;
	}
	
	public static Edge[] getEdges(Graph_MST graph) {
		return graph.edges;
	}
	
	public static void minSpanTree(Graph_MST graph) {
		int v = graph.v;
		// For storing the final edges in the MST
		Edge[] result = new Edge[v-1];
		// Get all the edges in the graph
		Edge[] edges = getEdges(graph);
		Arrays.sort(edges, new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				if(o1.weight<o2.weight)
					return -1;
				else if(o1.weight>o2.weight)
					return 1;
				else return 0;
			}
			
		});
		
		SubSet[] set = new SubSet[v];
		for(int i=0;i<set.length;i++) {
			set[i] = new SubSet(i,0);
		}
		
		int e = 0;
		int itr = 0;
		while(e<v-1) {
			Edge minEdge = edges[itr++];
			int srcSet = find(set,minEdge.src);
			int destSet = find(set,minEdge.dest);
			
			if(srcSet == destSet)
				continue;
			if(set[srcSet].rank>set[destSet].rank)
				set[destSet].parent = srcSet;
			else if(set[destSet].rank> set[srcSet].rank)
				set[srcSet].parent = destSet;
			else {
				set[srcSet].parent = destSet;
				set[destSet].rank++;
			}
			result[e++] = minEdge;
		}
		
		for(int i=0;i<result.length;i++) {
			System.out.println(result[i].src+" "+result[i].dest+" and weight "+result[i].weight);
		}
	}
	
	private static int find(SubSet[] set, int i) {
		if(set[i].parent != i)
			set[i].parent = find(set,set[i].parent);
		return set[i].parent;
	}
	
}


/*
 * Utility class for a defining the Graph
 */

class Graph_MST {
	public int v;
	public Edge[] edges;
	public Graph_MST(int v, int e) {
		this.v = v;
		edges = new Edge[e];
	}
}

/*
 * Utility class for defining a Edge in the Graph
 */
class Edge {
	public int src;
	public int dest;
	public int weight;
	
	public Edge(int src,int dest, int weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
}

/*
 * Utility class for node in Makeset.
 */
class SubSet {
	public int parent;
	public int rank;
	
	public SubSet(int parent,int rank){
		this.parent = parent;
		this.rank = rank;
	}
}