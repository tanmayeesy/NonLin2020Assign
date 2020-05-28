
import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;

/**
	* Graph implementation that uses Adjacency Lists to store edges. It
	* contains one linked-list for every vertex i of the graph. The list for i
	* contains one instance of VertexAdjList for every vertex that is adjacent to i.
	* For directed graphs, if there is an edge from vertex i to vertex j then there is
	* a corresponding element in the adjacency list of node i (only). For
	* undirected graphs, if there is an edge between vertex i and vertex j, then there is a
	* corresponding element in the adjacency lists of *both* vertex i and vertex j. The
	* edges are not sorted; they contain the adjacent nodes in *order* of
	* edge insertion. In other words, for a graph, the node at the head of
	* the list of some vertex i corresponds to the edge involving i that was
	* added to the graph least recently (and has not been removed, yet). 
	*/

	public class GraphAdjList  implements Graph {

		// ATTRIBUTES:
		private int vertex;
		private int edgeCount;
		private LinkedList<Edge> list[];
		private boolean directed;


		/*
		 * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges.
		 * It initializes the array of adjacency edges so that each list is empty.
		 */

		public GraphAdjList(int V, boolean directed) {
			vertex = V;
			this.directed = directed;
			list = new LinkedList[vertex];
			for (int i = 0; i < vertex; i++) {
				list[i] = new LinkedList<>();
			}

		}

		// 1. IMPLEMENTATION METHOD numVerts:
		public int numVerts() {
			return vertex;

		}

		// 2. IMPLEMENTATION METHOD numEdges:
		public int numEdges() {
			return edgeCount;
		}


		//  3. IMPLEMENTATION METHOD addEdge:
		public void addEdge(int v1, int v2, int w) {

			Edge edge = new Edge(v2, w);
			if(!hasEdge(v1, v2)) {
				edgeCount++;
			}
			list[v1].addFirst(edge);

			if (!this.directed) {
				if(!hasEdge(v1, v2)) {
					edgeCount++;
				}
				list[v2].addFirst(edge);
			}


		}

		// 4. IMPLEMENTATION METHOD removeEdge:
		public void removeEdge(int v1, int v2) {
			for (int i = 0; i < list[v1].size(); i++)
			{
				if (list[v1].get(i).getVertex() == v2)
				{
					list[v1].remove(i);
					edgeCount --;
					break;
				}
			}

		}

		// 5. IMPLEMENTATION METHOD hasEdge:
		public boolean hasEdge(int v1, int v2) {
			for(Edge i: list[v1]) {
				if(i.getVertex() == v2)
					return true;
			}
			return false;
		}

		// 6. IMPLEMENTATION METHOD getWeightEdge:
		public int getWeightEdge(int v1, int v2) {
			for(Edge i : list[v1]) {
				if(i.getVertex() == v2) {
					return (i.getWeight());
				}
			}
			return 0;
		}

		// 7. IMPLEMENTATION METHOD getNeighbors:
		public LinkedList getNeighbors(int v) {

			LinkedList<Integer> neighbors = new LinkedList<>();
			for (int i = 0; i < vertex; i++) {
				if(list[i].size() > 0) {
					for (Edge e: list[i]) {
						if(e.getVertex() == v) {
							if(!neighbors.contains(i)) {
								neighbors.add(i);
							}
						}
					}
				}
			}
			if(list[v].size() > 0) {
				neighbors.add(list[v].getFirst().getVertex());
			}
			return neighbors;
		}

		// 8. IMPLEMENTATION METHOD getDegree:
		public int getDegree(int v) {

			int inDegree = 0;
			int outDegree = 0;
			for (int i = 0; i < vertex; i++) {
				if(list[i].size() > 0) {
					for (Edge e: list[i]) {
						if(e.getVertex() == v) {
							inDegree++;
						}
					}

				}
			}
			outDegree = list[v].size();
			return inDegree + outDegree;

		}

		// 9. IMPLEMENTATION METHOD toString:
		public String toString() {
			String graph = "";
		 for (int i = 0; i <vertex ; i++) {
			 if(list[i].size()>0) {
				 System.out.print("Vertex " + i + " is connected to: ");
				 for (int j = 0; j < list[i].size(); j++) {
					 System.out.print(list[i].get(j) + " ");
					 graph = graph + list[i].get(j) + " ";
				 }
				 System.out.println();
				 graph = graph + "\n";
			 }
		 }

			return graph;
		}
	}


