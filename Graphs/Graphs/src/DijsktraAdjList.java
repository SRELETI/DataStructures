import java.util.Arrays;

public class DijsktraAdjList {

	public static void main(String args[]) {
		
		Graph_Node_W[] graph  = createGraph(9);
		addEdge(graph, 0, 1, 4);
	    addEdge(graph, 0, 7, 8);
	    addEdge(graph, 1, 2, 8);
	    addEdge(graph, 1, 7, 11);
	    addEdge(graph, 2, 3, 7);
	    addEdge(graph, 2, 8, 2);
	    addEdge(graph, 2, 5, 4);
	    addEdge(graph, 3, 4, 9);
	    addEdge(graph, 3, 5, 14);
	    addEdge(graph, 4, 5, 10);
	    addEdge(graph, 5, 6, 2);
	    addEdge(graph, 6, 7, 1);
	    addEdge(graph, 6, 8, 6);
	    addEdge(graph, 7, 8, 7);
	    
	    dijsktra(graph);
	}
	
	public static Graph_Node_W[] createGraph(int v) {
		Graph_Node_W[] graph = new Graph_Node_W[v];
		return graph;
	}
	
	public static void addEdge(Graph_Node_W[] graph, int src, int dest, int w) {
		Graph_Node_W node = graph[src];
		Graph_Node_W newNode = new Graph_Node_W(dest,w);
		newNode.next = node;
		node = newNode;
		graph[src] = node;
		node = graph[dest];
		newNode = new Graph_Node_W(src,w);
		newNode.next = node;
		node = newNode;
		graph[dest] = node;
	}
	
	public static void dijsktra(Graph_Node_W[] graph) {
		int v = graph.length;
		Heap_Imp heap = new Heap_Imp(v);
		int[] dist = new int[v];
		for(int i=0;i<v;i++) {
			heap.heap[i] = new Heap_Node(i,Integer.MAX_VALUE);
			heap.pos[i] = i;
			heap.cur_size++;
			dist[i] = Integer.MAX_VALUE;
		}
		heap.heap[0] = new Heap_Node(0,0);
		dist[0] = 0;
		while(!heap.isEmpty()) {
			Heap_Node min = heap.extractMin();
			Graph_Node_W head = graph[min.index];
			while(head != null) {
				if(heap.isInHeap(head.node) && heap.heap[heap.pos[head.node]].key>min.key+head.weight) {
					dist[head.node] = dist[min.index]+head.weight;
					heap.decreaseKey(head.node, min.key+head.weight);
				}
				head = head.next;
			}
		}
		printPath(dist);
		
	}	
	
	private static void printPath(int[] dist) {
		for(int i=1;i<dist.length;i++) {
			System.out.println("From "+i+": Weight: "+dist[i]);
		}
	}
	
	
}

class Heap_Imp {
	public Heap_Node[] heap;
	public int[] pos;
	public int cur_size;
	public int total_size;
	
	public Heap_Imp(int v) {
		heap = new Heap_Node[v];
		pos = new int[v];
		cur_size = 0;
		total_size = v;
	}
	
	public Heap_Node extractMin() {
		if(isEmpty()) return null;
	//	System.out.println("In Extract Min");
		Heap_Node min = heap[0];
		heap[0] = heap[cur_size-1];
		pos[min.index] = cur_size-1;
		pos[heap[0].index] = 0;
		cur_size--;
		minHeapify(0);
		return min;
	}
	
	public boolean isEmpty() {
		return cur_size == 0;
	}
	
	public void decreaseKey(int v, int newValue) {
		int index = pos[v];
		heap[index].key = newValue;
		while(index>0 && heap[index].key<heap[(index-1)/2].key) {
			pos[heap[index].index] = (index-1)/2;
			pos[heap[(index-1)/2].index] = index;
			Heap_Node temp = heap[index];
			heap[index] = heap[(index-1)/2];
			heap[(index-1)/2] = temp;
			index = (index-1)/2;
		}
	}
	public boolean isInHeap(int v) {
		if(pos[v]<cur_size) return true;
		return false;
	}
	
	public void minHeapify(int index) {
		int smallest;
		Heap_Node temp = heap[index];
		while(index<cur_size) {
			smallest = index;
			int lChild = (index<<1)+1;
			int rChild = lChild+1;
			if(lChild<cur_size && heap[lChild].key < heap[index].key)
				smallest = lChild;
			if(rChild<cur_size && heap[rChild].key < heap[smallest].key)
				smallest = rChild;
			if(smallest == index)
				return;
			pos[heap[index].index] = smallest;
			pos[heap[smallest].index] = index;
			Heap_Node t = heap[index];
			heap[index] = heap[smallest];
			heap[smallest] = t;
			index = smallest;
		}
	}
}

class Heap_Node {
	public int index;
	public int key;
	
	public Heap_Node(int index, int key) {
		this.index = index;
		this.key = key;
	}
}

class Graph_Node_W {
	public int node;
	public Graph_Node_W next;
	public int weight;
	
	public Graph_Node_W(int node, int w) {
		this.node = node;
		this.weight = w;
	}

}

