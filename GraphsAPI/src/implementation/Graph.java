package implementation;

import java.util.LinkedList;

/**
 * This graph's representation take into account the follows rules:
 * 1- A[i][j] = 1, means that has an edge between i and j,
 * 2- A[i][i] = 1, means that has a loop in vertice i
 * 3 - To representate the weight of edges => A[i][j] = weight
 */
public class Graph {
	
	private double[][] adjacencyMatrix;
	private int numVertices;
	private boolean weighted;
	private static final double DEFAULT_WEIGHT = 1.0;
	private static final double NO_EDGE = 0.0;
	
	public Graph(int v) {
		this.weighted = false;
		this.numVertices = v;
		this.adjacencyMatrix = new double[numVertices][numVertices];
	}

	public boolean isWeighted() {
		return weighted;
	}

	public void setWeighted(boolean weighted) {
		this.weighted = weighted;
	}

	public double[][] getAdjacencyMatrix() {
		return this.adjacencyMatrix;
	}

	public void setAdjacencyMatrix(double[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}

	public int getNumVertices() {
		return numVertices;
	}

	public void setNumVertices(int numVertices) {
		this.numVertices = numVertices;
	}

	public void addEdge(int i, int j){
	    this.adjacencyMatrix[i-1][j-1] = DEFAULT_WEIGHT;
    }
	
	public void addEdge(int i, int j, Double weight) {
		this.adjacencyMatrix[i-1][j-1] = weight;
	}

    public void removeEdge(int i, int j) {
        this.adjacencyMatrix[i-1][j-1] = 0.0;
    }

    public boolean hasEdge(int i, int j) {
	    return this.adjacencyMatrix[i][j] != NO_EDGE;
    }
    
    /**
     * Find the shortest path between two vertexes.
     * @param v1 source vertex
     * @param v2 target vertex
     * @return String containing the shortest path from v1 to v2.
     */
    public String shortestPath(int v1, int v2) {
    		v1 -= 1; v2 -= 1;
    		
    		int V = numVertices;
    		double dist[] = new double[V];
    		int previous[] = new int[V];
    		
    		for (int i = 0; i<V; i++) {
    			dist[i] = Double.MAX_VALUE;
    			previous[i] = -1;
    		}
    		
    		dist[v1] = 0;
    		
    		for (int i = 0; i<V-1; ++i) {
    			for (int j=0; j<V; j++) {
    				for (int k = 0; k<V; ++k) {
    					if (adjacencyMatrix[j][k] != 0 && dist[k] > dist[j] 
    							+ adjacencyMatrix[j][k]) {
    						dist[k] = dist[j] 
        							+ adjacencyMatrix[j][k];
    						previous[k] = j;
    					}
    				}
    			}
    		}
    		
    		for (int i = 0; i < V; i++) {
    			for (int j = 0; j < V; j++) {
    				if (adjacencyMatrix[i][j] != 0 &&
    						dist[j] > dist[i]
    		                           + adjacencyMatrix[i][j])
    					throw new RuntimeException("The Graph contains negative edge cycle");
    			}
    		}
    		
    		return buildAnswer(v1, v2, previous);
    		
    }
    
    private String buildAnswer(int v1, int v2, int[] previous) {
    		LinkedList<Integer> list = new LinkedList();
    		int current = v2;
    		
    		while (current != v1) {
    			list.addFirst(current + 1);
    			if (previous[current] == -1) {
    				throw new RuntimeException("There is no path between v1 and v2");
    			}
    			current = previous[current];
    		}
    		list.addFirst(current + 1);
    		return list.toString();
    }

	/**
	 * Returns the shortest path from a source vertex to a target vertex.
	 *
	 * @param {int} v1, the source vertex.
	 * @param {int} v2, the target vertex.
	 * @return {String} the shortest path between v1 and v2.
	 */
	public String shortestPath(int v1, int v2) {
		int dist[] v = new int[numVertices];
		boolean vis[] = new boolean[numVertices];
		int previous[] = new int[numVertices];

		for (int i = 0; i < numVertices; ++i) {
			dist[i] = Integer.MAX_VALUE;
			vis[i] = false;
			previous[v] = -1;
		}

		dist[v1] = 0;

		for (int i = 0; i < numVertices - 1; ++i) {
			int current = minDistance(dist, vis);

			for (int j = 0; j < numVertices; j++) {
				if (!vis[j] && adjacencyMatrix[current][j] != 0
						&& dist[current] != Integer.MAX_VALUE
						&& dist[current] + adjacencyMatrix[current][j] < dist[j]) {
					dist[j] = dist[current] + adjacencyMatrix[current][j];
					previous[j] = current;
				}
			}

		}

		return buildAnswer(v1, v2, previous);
	}

	public String buildAnswer(int v1, int v2, previous) {
		ArrayList answer = new ArrayList();
		return null;
	}

	private int minDistance(int dist[], boolean vis[]) {
		int min = Integer.MAX_VALUE, min_index = -1;

		for (int i = 0; i < numVertices; i++) {
			if (vis[i] == false && dist[i] <= min) {
				min = dist[i];
				min_index = i;
			}
		}
		return min_index;
	}

    public String toString(){
		String out = "";

		for(int i = 0; i < adjacencyMatrix.length; i++){
			out += lineToString(adjacencyMatrix[i]);
			out += "\n";
		}
		return out;
	}

	private String lineToString(double[] line) {

		String out = "";
		for(int i = 0; i < line.length; i++){
			out += line[i];
			out += "\t";
		}
		return out;

	}
	
}
