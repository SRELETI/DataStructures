import java.util.Arrays;

/*
 * A vertex in an undirected connected graph is an articulation point (or cut vertex) 
 * if removing it (and edges through it) disconnects the graph.
 * 
 * Brute Force: Remove each node and its associated edges and run dfs on remaining nodes to see if all
 * other nodes are reachable.
 * 
 * Better Solution: Run DFS and print a node as articulation point if
 * 1. Node is the root node in  dfs and it has atleast 2 children.
 * 2. Node is non root node and it has a child which doesn't have edge to any of the ancestors of non root node.
 */
public class ArticulationPoint {

	public static void main(String args[]) {
		Graph_Art_Nodes[] graph = createGraph(5);
		addEdge(graph,1,0);
		addEdge(graph,0,2);
		addEdge(graph,2,1);
		addEdge(graph,0,3);
		addEdge(graph,3,4);
		articulationPoints(graph);
	}
	
	public static Graph_Art_Nodes[] createGraph(int v) {
		Graph_Art_Nodes[] graph = new Graph_Art_Nodes[v];
		return graph;
	}
	
	public static void addEdge(Graph_Art_Nodes[] graph, int src, int dest) {
		Graph_Art_Nodes head = graph[src];
		Graph_Art_Nodes newNode = new Graph_Art_Nodes(dest);
		newNode.next = head;
		head = newNode;
		graph[src] = head;
	}
	
	public static void articulationPoints(Graph_Art_Nodes[] graph) {
		int v = graph.length;
		boolean[] isVisited = new boolean[v];
		boolean[] isAP = new boolean[v];
		int[] parent = new int[v];
		int[] disc = new int[v];
		int[] low = new int[v];
		Arrays.fill(parent, -1);
		Wrapper_Timer timer = new Wrapper_Timer();
		articulationPointUtils(graph,isVisited,isAP,parent,disc,low,timer,0);
		for(int i=0;i<isAP.length;i++) {
			if(isAP[i] == true)
				System.out.println(i);
		}
	}
	
	private static void articulationPointUtils(Graph_Art_Nodes[] graph,boolean[] isVisited, boolean[] isAP,
			int[] parent,int[] disc, int[] low, Wrapper_Timer timer, int i) {
		isVisited[i] = true;
		int children = 0;
		low[i] = disc[i] = ++timer.time;
		Graph_Art_Nodes head = graph[i];
		while(head != null) {
			if(!isVisited[head.node]) {
				children++;
				parent[head.node] = i;
				articulationPointUtils(graph,isVisited,isAP,parent,disc,low,timer,head.node);
				low[i] = Math.min(low[head.node], low[i]);
				if(parent[i]==-1 && children>1)
					isAP[i] = true;
				if(parent[i]!=-1 && low[head.node]>=disc[i])
					isAP[i] = true;
			}
			else if(head.node != parent[i]) {
				low[i] = Math.min(low[i], disc[head.node]);
			}
			head = head.next;
		}
	}
}

class Graph_Art_Nodes {
	public int node;
	public Graph_Art_Nodes next;
	
	public Graph_Art_Nodes(int val) {
		node = val;
		next = null;
	}
}

class Wrapper_Timer {
	public int time = 0;
}