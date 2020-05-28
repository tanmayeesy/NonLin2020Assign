import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

 
public class GraphAdjMatrix implements Graph{

	// ATTRIBUTES: 
     private int adjMatrix[][];
     private int numVertices;
     private boolean directed;
     private  int numEdges;
     private boolean isSetMatrix[][];
    
    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no edges
    public GraphAdjMatrix(int V, boolean directed) {
        this.numVertices = V;
        this.directed = directed;
        this.adjMatrix = new int[this.numVertices][this.numVertices];
        this.isSetMatrix = new boolean[this.numVertices][this.numVertices];
    }


    // 1. IMPLEMENTATION METHOD numVerts: 
    public int numVerts() { 
      return this.numVertices;
    }
    
   
    // 2. IMPLEMENTATION METHOD numEdges:
    public int numEdges() { 
        return this.numEdges;
    }


   //  3. IMPLEMENTATION METHOD addEdge:
    public void addEdge(int v1, int v2, int w) {
        this.adjMatrix[v1][v2] = w;
        if(!this.hasEdge(v1, v2)) {
            this.numEdges += 1;
        }
        this.isSetMatrix[v1][v2] = true;
        if (!this.directed) {
            this.adjMatrix[v2][v1] = w;
            if(!this.hasEdge(v1, v2)) {
                this.numEdges += 1;
            }
            this.isSetMatrix[v2][v1] = true;
        }
    }
    
   // 4. IMPLEMENTATION METHOD removeEdge:
   public void removeEdge (int v1, int v2)
   {
       this.adjMatrix[v1][v2] = 0;
       this.numEdges --;
   }

    // 5. IMPLEMENTATION METHOD hasEdge:
    public boolean hasEdge(int v1, int v2) {
        return this.isSetMatrix[v1][v2];
    }
    
    // 6. IMPLEMENTATION METHOD getWeightEdge:
	public int getWeightEdge(int v1, int v2) {
        if (!this.isSetMatrix[v1][v2])
            return 0;
        return this.adjMatrix[v1][v2];
	}

    
	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v)
	{
    	LinkedList<Integer> neighbors = new LinkedList<>();

        for (int i = 0; i < this.numVertices; i++) {
            if (hasEdge(i, v)) {
                if(!neighbors.contains(i)) {
                    neighbors.add(i);
                }
            }
        }
        for (int j = 0; j < this.numVertices; j++) {
            if (hasEdge(v, j)) {
                if(!neighbors.contains(j)) {
                    neighbors.add(j);
                }
            }
        }
        return  neighbors;
	}
   	
	// 8. IMPLEMENTATION METHOD getDegree:
	public int getDegree(int v) 
	{
        int inDegree = 0;
        int outDegree = 0;

        for (int i = 0; i < this.numVertices; i++) {
            if (hasEdge(i, v)) {
                outDegree++;
            }
        }
        for (int j = 0; j < this.numVertices; j++) {
            if (hasEdge(v, j)) {
                inDegree++;
            }
        }
        return  inDegree + outDegree;
	}

	// 9. IMPLEMENTATION METHOD toString:
   	public String toString()
    {
        String graph = "";
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                if (isSetMatrix[i][j]) {
                    System.out.format("%8s", String.valueOf(this.adjMatrix[i][j]));
                    graph = String.valueOf(this.adjMatrix[i][j]);
                }
                else  {
                    System.out.format("%8s", " 0 ");
                    graph = graph + " 0 ";
                }

            }
            System.out.println();
            graph = graph + "\n";
        }
        return  graph;
    }    
}