import java.util.Arrays;

/*
 * Algorithm for finding the shortest distance from a source vertex to
 * all other vertices in the graph.
 * This algorithm is very similar to the prim's algorithm for MST.
 * 
 * Algorithm:
 * 
 * 1. Maintain two sets. One set contains all the elements for which shortest
 * distance from source vertex is already calculated. In other set remaining 
 * elements are present. 
 * 2. Initially set one is empty. Initialize distance of all vertices from source
 * as INF and distance of source vertex from source as 0, so that it is picked first.
 * 3. while set one doesnt contain all the vertices in the graph, repeat:
 * 		a. Find the vertex with min distance.
 * 		b. Add it to set One.
 * 		c. For all the vertices adjacent to selected, find the distance from it and update
 * 		   the distance if the distance is less than present distance.
 * 
 */
public class DijsktraAdjMat {

	public static void main(String args[]) {
		int[][] graph = {{0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 0, 10, 0, 2, 0, 0},
                {0, 0, 0, 14, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
               };
		shortestDistance(graph);
	}
	
	
	public static void shortestDistance(int[][] graph) {
		int v = graph.length;
		int[] key = new int[v];
		int[] parent = new int[v];
		boolean[] shortest = new boolean[v];
		for(int i=1;i<v;i++) {
			key[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		key[0] = 0;
		parent[0] = -1;
		
		for(int i=0;i<v-1;i++) {
			int min = extractMin(key,shortest);
			shortest[min] = true;
			for(int j=0;j<v;j++) {
				if(graph[min][j] != 0 && !shortest[j] && key[j]>key[min]+graph[min][j]) {
					parent[j] = min;
					key[j] = key[min]+graph[min][j];
				}
			}
		}
		System.out.println(Arrays.toString(parent));
		printPath(key,parent);
	}
	
	private static int extractMin(int[] key, boolean[] shortest) {
		int p = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int i=0;i<key.length;i++) {
			if(key[i]<p && !shortest[i]) {
				p = key[i];
				minIndex = i;
			}
		}
	//	System.out.println(p);
		return minIndex;
	}
	
	private static void printPath(int[] key,int[] parent) {
		for(int i=1;i<key.length;i++) {
		//	System.out.println("From 0 to "+i+" is "+key[i]);
			System.out.print(" The path is ");
			printUtils(parent,i);
			System.out.println();
		}
	}
	
	private static void printUtils(int[] parent, int i) {
		if(parent[i] == -1) {
			System.out.print(i);
			return;
		}
		printUtils(parent,parent[i]);
		System.out.print("->"+i);
	}
}
