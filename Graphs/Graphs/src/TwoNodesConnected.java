
/*
 * Checks if two nodes in a graph are connected or not. 
 * Both of BFS and DFS can be used.
 */
public class TwoNodesConnected {

	public static void main(String args[]){
		Graph_Nodes[] graph = createGraph(4);
		addEdge(graph,0,1);
		addEdge(graph,0,2);
		addEdge(graph,1,2);
		addEdge(graph,2,0);
		addEdge(graph,2,3);
		addEdge(graph,3,3);
		System.out.println(isConnected(graph,1,3));
	}
	
	public static Graph_Nodes[] createGraph(int v) {
		Graph_Nodes[] graph = new Graph_Nodes[v];
		return graph;
	}
	
	public static void addEdge(Graph_Nodes[] graph, int src, int dest) {
		Graph_Nodes head = graph[src];
		Graph_Nodes newNode = new Graph_Nodes(dest);
		newNode.next = head;
		head = newNode;
		graph[src] = head;
	}
	
	
	public static boolean isConnected(Graph_Nodes[] graph, int src, int dest) {
		boolean[] isVisited = new boolean[graph.length];
		return isConnectedUtils(graph,src,dest,isVisited);
	}
	// THis is a DFS implementation, can easily be done using BFS.
	private static boolean isConnectedUtils(Graph_Nodes[] graph, int src, int dest, boolean[] isVisited) {
		isVisited[src] = true;
		Graph_Nodes head = graph[src];
		while(head != null) {
			if(!isVisited[head.node]) {
				if(head.node == dest)
					return true;
				else
					if(isConnectedUtils(graph,head.node,dest,isVisited))
						return true;
				head = head.next;
			}
		}
		return false;
	}
}


class Graph_Nodes {
	public int node;
	public Graph_Nodes next;
	
	public Graph_Nodes(int val) {
		node = val;
		next = null;
	}
}