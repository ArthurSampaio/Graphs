package implementation;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * aux entity to work with the graph
 */
public class GraphUtils {


    public static final String DELIMITER = " ";

    public static Graph readGraph(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));

        String line = br.readLine();
        String[] values = line.trim().split(DELIMITER);
        int v = Integer.parseInt(values[0]);
        Graph graph = new Graph(v);

        while ((line = br.readLine()) != null) {
            values = line.split(DELIMITER);

            int i = Integer.parseInt(values[0]);
            int j = Integer.parseInt(values[1]);
            graph.addEdge(i, j);
            graph.addEdge(j, i);
        }
        br.close();
        return graph;
    }
    
    public static Graph readWeightedGraph(String path) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(path));

        String line = br.readLine();
        String[] values = line.trim().split(DELIMITER);
        int v = Integer.parseInt(values[0]);
        Graph graph = new Graph(v);

        while ((line = br.readLine()) != null) {
            values = line.split(DELIMITER);

            int i = Integer.parseInt(values[0]);
            int j = Integer.parseInt(values[1]);
            double weight = Double.parseDouble(values[2]);
            graph.addEdge(i, j, weight);
            graph.addEdge(j, i, weight);
        }
        br.close();
        graph.setWeighted(true);
        return graph;
    }
    
    public static String graphRepresentation (Graph graph, String type) {
    	if(type.equals("AM")) {
    		String matrixRepresentation = putFirstLine(graph.getNumVertices());  		
    		for(int i = 0; i < graph.getNumVertices(); i ++) {
    			String line = "";
    			for(int j = 0; j < graph.getNumVertices(); j++) {
    				line += getValue(graph.getAdjacencyMatrix()[i][j]) + " ";
    			}
    			matrixRepresentation += (i+1) + " " + line + "\n";
    		}
    		return matrixRepresentation;
    	}
    	else if(type.equals("AL")) {
    		String alRep = "";
    		for(int k = 0; k < graph.getNumVertices(); k++){
    			String line = "";
    			for (int l = 0; l < graph.getNumVertices(); l++) {
    				if(graph.isWeighted()) {
    					if(graph.getAdjacencyMatrix()[k][l] != 0.0) {
    						line += (l+1) + "(" + getValue(graph.getAdjacencyMatrix()[k][l]) + ")" + " ";
    					}    					
    				}else {
    					if(graph.getAdjacencyMatrix()[k][l] != 0.0) {
        					line += (l+1) + " ";
        				}
    				}
    				
    			}
    			alRep += (k+1) + "-" + line + "\n";
    		}
    		return alRep;
    	}
    	return "Invalid Type!";
    }
    
    public static boolean connected(Graph graph) {
        boolean visited[] = new boolean[graph.getAdjacencyMatrix().length];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[graph.getNumVertices() - 1] = true;
        queue.add(graph.getNumVertices());
        while(!queue.isEmpty()) {
       		int x = queue.remove();
        	for(int i= 0; i < graph.getAdjacencyMatrix().length;i++) {
    			if((graph.getAdjacencyMatrix()[x-1][i] != 0.0 && (!visited[i]))){
    			  queue.add(i+1);
  	              visited[i] = true;
    			}
    		}
        }
    	return isConnected(visited);
    }
    
    private static boolean isConnected(boolean[] visited) {
    	for(int i =0; i < visited.length; i++) {
    		if(!visited[i])
    			return false;
    	}
    	return true;
    }
    
    public static String BFS(Graph graph, int v) {
    	int[] level = new int [graph.getAdjacencyMatrix().length];
    	boolean visited[] = new boolean[graph.getAdjacencyMatrix().length];
    	LinkedList<Integer> queue = new LinkedList<Integer>();
    	visited[v-1] = true;
    	queue.add(v);
    	level[v-1] = 0;
    	int[] parents = new int[graph.getAdjacencyMatrix().length];
    	while(!queue.isEmpty()) {
    		int x = queue.remove();
    		for(int i= 0; i < graph.getAdjacencyMatrix().length;i++) {
    			if((graph.getAdjacencyMatrix()[x-1][i] != 0.0 && (!visited[i]))){
  	              parents[i] = x;
  	              level[i] = 1 + level[parents[i]-1];
    			  queue.add(i+1);
  	              visited[i] = true;
    			}
    		}
    		
    	}
   
    	return buildBFSOutput("", parents,graph.getAdjacencyMatrix().length,level);
    }
    
    private static String buildBFSOutput(String output, int[] parents,int graphSize,int[]level) {
    	String line = "";
    	for(int i = 0; i < graphSize; i++) {
    		if(parents[i] == 0) {
    			line = (i+1) + "-" + level[i] + " -" + "\n";
    		}else {
    		line = (i+1) + "-" + level[i] + " "  + parents[i] + "\n";
    		}
    		output += line;
    	}
    return output;
    	
    }
    
    private static String getValue(double x) {
    	double y = Math.floor(x);
    	double z = Math.ceil(x);
    	if(y < x && z > x) {
    		return x + "";
    	}
    	return (int) x + "";
    }
    
    private static String putFirstLine(int size) {
    	String fLine = " ";
    	for (int i = 0; i < size; i++) {
    		fLine += " " + (i+1);
    	}
    	fLine += "\n";
    	return fLine;
    }


    public static int getVertexNumber (Graph gragh){

        return gragh.getNumVertices();

    }
    
    public static int getEdgeNumber(Graph graph) {
    	double links = 0.;
    	double loops = 0.;
    	int verticesNum = graph.getNumVertices();
    	double[][] adjacencyMatrix = graph.getAdjacencyMatrix();
    	for(int i = 0; i < verticesNum; i++) {
    		for(int j = 0; j < verticesNum; j++) {
    			if(i == j) {
    				loops += adjacencyMatrix[i][j] != 0.0 ? 1.0 : 0.0;
    			} else {
    				links += adjacencyMatrix[i][j] != 0.0 ? 1.0 : 0.0;
    			}
    		}
    	}
    	Double edgeNumber = links > 0.0 ? ((links / 2) + loops) :  loops;
    	return edgeNumber.intValue();
    }
    
    public static float getMeanEdge(Graph graph) {
    	
    	float meanEdge = ( getEdgeNumber(graph) * 2 ) / getVertexNumber(graph);
    	
    	return meanEdge;
    }
}
