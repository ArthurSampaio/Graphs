package testes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


import manipulator.Vertex;
import manipulator.Edge;

public class VertexTest {
	
	Vertex startVertex;
	Vertex otherVertex;
	Vertex endVertex;
	Edge simpleEdge;
	Edge edgeWithWeight;
	Edge negativeEdge;
	
    @Before
    public void setUp() {
    	this.simpleEdge = new Edge(new Vertex(1), new Vertex(2), null);
    	this.edgeWithWeight = new Edge(new Vertex(1), new Vertex(2), (float) 9.5); 
        this.negativeEdge = new Edge(new Vertex(1), new Vertex(2), (float) -9.5);
        List<Edge> simpleEdges = Arrays.asList(this.simpleEdge, this.simpleEdge);
        List<Edge> edgesWithWeight = Arrays.asList(this.edgeWithWeight, this.edgeWithWeight);
    	List<Edge> negativeEdges = Arrays.asList(this.negativeEdge, this.negativeEdge);
        this.startVertex = new Vertex(1, simpleEdges);
        this.otherVertex = new Vertex(2, edgesWithWeight);
        this.endVertex = new Vertex(3, negativeEdges);
   }
    
   @Test
   public void testGetValue() {
	   Assert.assertEquals(this.startVertex.getValue(), 1);
	   Assert.assertEquals(this.otherVertex.getValue(), 2);
	   Assert.assertEquals(this.endVertex.getValue(), 3);
   }
   
   @Test
   public void testSetValue() {
	   Assert.assertEquals(this.startVertex.getValue(), 1);
	   this.startVertex.setValue(5);
	   Assert.assertEquals(this.startVertex.getValue(), 5);
   }
   
   @Test
   public void testGetEdge() {
	   List<Edge> listEdgesToCompare = Arrays.asList(this.simpleEdge, this.simpleEdge);
	   Assert.assertEquals(this.startVertex.getEdges(),listEdgesToCompare);
   }
   
   @Test
   public void testGetEdgeWithWeight() {
	   List<Edge> listEdgesToCompare = Arrays.asList(this.edgeWithWeight, this.edgeWithWeight);
	   Assert.assertEquals(this.otherVertex.getEdges(),listEdgesToCompare);
   }
   
   @Test
   public void testGetNegativeEdge() {
	   List<Edge> listEdgesToCompare = Arrays.asList(this.negativeEdge, this.negativeEdge);
	   Assert.assertEquals(this.endVertex.getEdges(),listEdgesToCompare);
   }
 
   @Test
   public void testSetEdge() {
	   List<Edge> listEdgesToCompare = Arrays.asList(this.simpleEdge, this.simpleEdge);
	   Assert.assertEquals(this.startVertex.getEdges(),listEdgesToCompare);
	   
	   List<Edge> otherEdges = Arrays.asList(this.edgeWithWeight, this.edgeWithWeight);
	   this.startVertex.setEdges(otherEdges);
	   
	   Assert.assertEquals(this.startVertex.getEdges(),otherEdges);
   }
   
   @Test
   public void testDegree() {
	   Assert.assertEquals(this.startVertex.degree(), 2);
   }
}
