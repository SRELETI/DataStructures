/*
 * Finds the shortest distance from a source vertex to all others vertex in a graph with negative edges.
 * Returns the shortest path if there are no negative cycles. Else it reports that a negative cycle is present.
 * 
 */
public class BellmanFord {

	public static void main(String args[]) {
		Graph_Bell graph = new Graph_Bell(5,8);
		
		graph.edges[0] = new Edges(0,1,-1);
		graph.edges[1] = new Edges(0,2,4);
		graph.edges[2] = new Edges(1,2,3);
		graph.edges[3] = new Edges(1,3,2);
		graph.edges[4] = new Edges(1,4,2);
		graph.edges[5] = new Edges(3,2,5);
		graph.edges[6] = new Edges(3,1,1);
		graph.edges[7] = new Edges(4,3,-3);
		
		bellFord(graph);
		
	}
	
	public static void bellFord(Graph_Bell graph) {
		int v = graph.V;
		int e = graph.E;
		int[] dist = new int[v];
		for(int i=1;i<dist.length;i++)
			dist[i] = Integer.MAX_VALUE;
		dist[0] = 0;
		
		for(int i=1;i<dist.length;i++) {
			for(int j=0;j<e;j++) {
				int u = graph.edges[j].src;
				int uu = graph.edges[j].dest;
				int weight = graph.edges[j].weight;
				if(dist[u] != Integer.MAX_VALUE && dist[u]+weight<dist[uu])
					dist[uu] = dist[u]+weight;
			}
		}
		
		for(int j=0;j<e;j++) {
			int u = graph.edges[j].src;
			int uu = graph.edges[j].dest;
			int weight = graph.edges[j].weight;
			if(dist[u] != Integer.MAX_VALUE && dist[u]+weight<dist[uu])
				System.out.println("Has Negative Cycles");
		}
		
		for(int i=0;i<dist.length;i++) {
			System.out.println("Distance to "+i+" from source 0 is "+dist[i]);
		}
	}
	
	
	
}

class Graph_Bell {
	public int V;
	public int E;
	public Edges[] edges;
	
	public Graph_Bell(int v, int e) {
		V = v;
		E = e;
		edges = new Edges[E];
	}
}

class Edges {
	public int src;
	public int dest;
	public int weight;
	
	public Edges(int src, int dest, int w) {
		this.src = src;
		this.dest = dest;
		this.weight = w;
	}
}

