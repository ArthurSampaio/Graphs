package implementationtest;

import static org.junit.Assert.*;

import implementation.Graph;
import implementation.GraphUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class GraphsTestSuite {

	Graph graph;
	
	
	
	@Test
	public void readGraph(){
		try{
			double [][] output =  {{0.0,1.0,0.0,0.0,1.0}, {1.0,0.0,0.0,0.0,1.0}, {0.0,0.0,0.0,0.0,1.0}, {0.0,0.0,0.0,0.0,1.0}, {1.0,1.0,1.0,1.0,0.0}};
            graph = GraphUtils.readGraph("resources/input.txt");
            Assert.assertArrayEquals(output, graph.getAdjacencyMatrix());
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void readWeightedGraph() {
		try {
			double [][] output = {
					{0.0, 0.1, 0.0, 0.0, 1.0},
					{0.1, 0.0, 0.0, 0.0, 0.2},
					{0.0, 0.0, 0.0, -9.5, 5.0},
					{0.0, 0.0, -9.5, 0.0, 2.3},
					{1.0, 0.2, 5.0, 2.3, 0.0}
			};
			graph = GraphUtils.readWeightedGraph("resources/inputWithWeight.txt");
            Assert.assertArrayEquals(output, graph.getAdjacencyMatrix());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getEdgeNumber() {
		try {
			double [][] adjacencyMatrix =  {
					//   1  2  3  4  5
					{0.,1.,0.,0.,1.}, //1
					{1.,0.,0.,0.,1.}, //2
					{0.,0.,0.,0.,1.}, //3
					{0.,0.,0.,0.,1.}, //4
					{1.,1.,1.,1.,0.}  //5
					};
			
			double [][] adjacencyMatrixWithLoops =  {
				//   1  2  3  4  5
					{1.,1.,0.,0.,1.}, //1
					{1.,0.,0.,0.,1.}, //2
					{0.,0.,1.,0.,1.}, //3
					{0.,0.,0.,0.,1.}, //4
					{1.,1.,1.,1.,1.}  //5
					};
			
			double [][] adjacencyMatrixWithWeight = {
					{0.0, 0.1, 0.0, 0.0, 1.0},
					{0.1, 0.0, 0.0, 0.0, 0.2},
					{0.0, 0.0, 0.0, -9.5, 5.0},
					{0.0, 0.0, -9.5, 0.0, 2.3},
					{1.0, 0.2, 5.0, 2.3, 0.0}
			};

			Graph graph = new Graph(5);
			
			graph.setAdjacencyMatrix(adjacencyMatrix);
			Assert.assertEquals(GraphUtils.getEdgeNumber(graph), 5);
			
			graph.setAdjacencyMatrix(adjacencyMatrixWithLoops);
			Assert.assertEquals(GraphUtils.getEdgeNumber(graph), 8);
			
			graph.setAdjacencyMatrix(adjacencyMatrixWithWeight);
			Assert.assertEquals(GraphUtils.getEdgeNumber(graph), 6);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void graphRepresentation() {
		try {
			graph = GraphUtils.readGraph("resources/input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String sMatrix = "  1 2 3 4 5\n" + 
				"1 0 1 0 0 1 \n" + 
				"2 1 0 0 0 1 \n" + 
				"3 0 0 0 0 1 \n" + 
				"4 0 0 0 0 1 \n" + 
				"5 1 1 1 1 0 \n" + 
				"";
		
		String sL = "1-2 5 \n" +
				"2-1 5 \n" + 
				"3-5 \n" + 
				"4-5 \n" +
				"5-1 2 3 4 \n";
		
		Assert.assertEquals(sMatrix, GraphUtils.graphRepresentation(graph, "AM"));
		Assert.assertEquals(sL, GraphUtils.graphRepresentation(graph, "AL"));

	}
	
	@Test
	public void BFS() {
		try {
			graph = GraphUtils.readGraph("resources/input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		String output1 = "1-0 -\n" + 
				"2-1 1\n" + 
				"3-2 5\n" + 
				"4-2 5\n" + 
				"5-1 1\n" + 
				"";
		
		String output2 = "1-1 5\n" + 
				"2-1 5\n" + 
				"3-1 5\n" + 
				"4-1 5\n" + 
				"5-0 -\n" + 
				"";
		
		String output3 = "1-2 5\n" + 
				"2-2 5\n" + 
				"3-0 -\n" + 
				"4-2 5\n" + 
				"5-1 3\n" + 
				"";
		
		Assert.assertEquals(output1, GraphUtils.BFS(graph, 1));
		Assert.assertEquals(output2, GraphUtils.BFS(graph, 5));
		Assert.assertEquals(output3, GraphUtils.BFS(graph, 3));

	}
	
	@Test
	public void connectedTestFalse() {
		try {
			graph = GraphUtils.readGraph("resources/notConnected.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(false, GraphUtils.connected(graph));
	}
	
	@Test
	public void connectedTestTrue() {
		try {
			graph = GraphUtils.readGraph("resources/input.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(true, GraphUtils.connected(graph));
	}
	
	
	
	@Test
	public void graphRepresentationWeightedGraph() {
		try {
			graph =  GraphUtils.readWeightedGraph("resources/inputWithWeight.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String sMatrix = "  1 2 3 4 5\n" + 
				"1 0 0.1 0 0 1 \n" + 
				"2 0.1 0 0 0 0.2 \n" + 
				"3 0 0 0 -9.5 5 \n" + 
				"4 0 0 -9.5 0 2.3 \n" + 
				"5 1 0.2 5 2.3 0 \n" +
				"";
		
		String sL = "1-2(0.1) 5(1) \n" + 
				"2-1(0.1) 5(0.2) \n" + 
				"3-4(-9.5) 5(5) \n" + 
				"4-3(-9.5) 5(2.3) \n" + 
				"5-1(1) 2(0.2) 3(5) 4(2.3) \n";
		
		Assert.assertEquals(sMatrix, GraphUtils.graphRepresentation(graph, "AM"));
		Assert.assertEquals(sL, GraphUtils.graphRepresentation(graph, "AL"));
	}
	

	@Test
	public void getMeanEdge() {
		try {
            
			graph = GraphUtils.readGraph("resources/input.txt");
            
			Assert.assertEquals(2.0, GraphUtils.getMeanEdge(graph), 0.01);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

    @Test
    public void dfs() {
        try {

            graph = GraphUtils.readGraph("resources/input.txt");

            System.out.print(GraphUtils.DFS(graph, 1));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @Test
	public void shortestPath() {
		try {
			graph =  GraphUtils.readWeightedGraph("resources/inputWeight2.txt");
			Assert.assertEquals("[1, 2, 5, 4, 3]", GraphUtils.shortestPath(graph, 1, 3)); 
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void mst() {
		try {
			graph =  GraphUtils.readWeightedGraph("resources/inputWeight2.txt");
			String ans = "1 - - 0\n" + 
					"2 - 1 1\n" + 
					"3 - 4 4\n" + 
					"4 - 5 3\n" + 
					"5 - 2 2";
			Assert.assertEquals(ans, GraphUtils.mst(graph) );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}