import java.util.Arrays;

/*
 * --Strongly connected graph: Graphs in which each node can reach all other nodes in the graph.
 * --In Undirected graphs, This can be easily found by doing a DFS from a node and check if all nodes are visited 
 * or accessible from that node.
 * --In directed graph, a node may be able to reach all nodes, but other nodes may not be able to reach all other nodes.
 * For example 1->2->3->4. 
 * In this graph, if you do a DFS from 1, all nodes are reachable, but if DFS is started from any other node liek 2,3,4
 * not all nodes are reachable from them. So this graph is not strongly connected graph.
 * --Strongly connected components logic can be used here. Do a DFS from node a and check if all nodes are reachable. If not 
 * return false. Then reverse all the edges in the graph and now check if all nodes can reach a. If they are all in a single
 * connected component, then all nodes can reach all other nodes,else they cant.
 */
public class Connectivity {

	public static void main(String args[]) {
		Graph_Con_Nodes[] graph = createGraph(5);
		/*
		// Undirected graph creation
		addEdge(graph,0,1,false);
		addEdge(graph,1,2,false);
		addEdge(graph,2,3,false);
		addEdge(graph,2,4,false);
		
		System.out.println(isStronglyCon_UnDirected(graph));
		*/
		
		addEdge(graph,0,1,true);
		addEdge(graph,1,2,true);
		addEdge(graph,2,3,true);
	//	addEdge(graph,2,4,true);
	//	addEdge(graph,4,2,true);
	//	addEdge(graph,3,0,true);
		
		System.out.println(isStrCon_DirectedGraph(graph));
	}
	
	public static Graph_Con_Nodes[] createGraph(int v) {
		Graph_Con_Nodes[] graph = new Graph_Con_Nodes[v];
		return graph;
	}
	
	public static void addEdge(Graph_Con_Nodes[] graph, int src, int dest, boolean isCon) {
		Graph_Con_Nodes head = graph[src];
		Graph_Con_Nodes newNode = new Graph_Con_Nodes(dest);
		newNode.next = head;
		head = newNode;
		graph[src] = head;
		if(!isCon) {
			head = graph[dest];
			newNode = new Graph_Con_Nodes(src);
			newNode.next = head;
			head = newNode;
			graph[dest] = head;
		}
	}
	// Undirected graph Impl, it uses DFS to find if the graph is SCC.
	public static boolean isStronglyCon_UnDirected(Graph_Con_Nodes[] graph) {
		boolean[] isVisited = new boolean[graph.length];
		isStrCon_UnDirUtils(graph,isVisited,0);
		for(int i=0;i<isVisited.length;i++) {
			if(!isVisited[i])
				return false;
		}
		return true;
	}
	
	private static void isStrCon_UnDirUtils(Graph_Con_Nodes[] graph, boolean[] isVisited, int i) {
		isVisited[i] = true;
		Graph_Con_Nodes head = graph[i];
		while(head != null) {
			if(!isVisited[head.node]) {
				isStrCon_UnDirUtils(graph,isVisited,head.node);
			}
			head = head.next;
		}
	}
	
	// Directed Graph Impl, Uses DFS twice to check if graph is SCC. 
	public static boolean isStrCon_DirectedGraph(Graph_Con_Nodes[] graph) {
		boolean[] isVisited = new boolean[graph.length];
		isStrCon_UnDirUtils(graph,isVisited,0);
		
		for(int i=0;i<isVisited.length;i++){
			if(!isVisited[i])
				return false;
		}
		
		graph = reverseGraph(graph);
		
		Arrays.fill(isVisited, false);
		
		isStrCon_UnDirUtils(graph,isVisited,0);
		
		for(int i=0;i<isVisited.length;i++){
			if(!isVisited[i])
				return false;
		}
		return true;
	}
	
	private static Graph_Con_Nodes[] reverseGraph(Graph_Con_Nodes[] graph) {
		Graph_Con_Nodes[] newGraph = new Graph_Con_Nodes[graph.length];
		for(int i=0;i<graph.length;i++) {
			Graph_Con_Nodes head = graph[i];
			while(head != null) {
				addEdge(newGraph,head.node,i,true);
				head = head.next;
			}
		}
		return newGraph;
	}
}

class Graph_Con_Nodes {
	public int node;
	public Graph_Con_Nodes next;
	
	public Graph_Con_Nodes(int val) {
		node = val;
		next = null;
	}
}