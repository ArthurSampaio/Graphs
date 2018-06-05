package testes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import manipulator.Graph;
import manipulator.Vertex;

public class GraphTest {

	Graph graph;
	Vertex vertex1;
	Vertex vertex2;
	Vertex vertex3;
	Vertex vertex4;
	
	@Before
	public void setup() {
		graph = new Graph();
		vertex1 = new Vertex(1);
		vertex2 = new Vertex(2);
		vertex3 = new Vertex(3);
		vertex4 = new Vertex(4);
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addVertex(vertex3);
	}
	
	
	@Test
	public void testContains() {
		Assert.assertFalse(graph.contains(vertex4));
		Assert.assertTrue(graph.contains(vertex1));
		Assert.assertTrue(graph.contains(vertex2));
		Assert.assertTrue(graph.contains(vertex3));
	}
	
	@Test
	public void testNumberVertex() {
		Assert.assertEquals(3, graph.numberVertex());
		graph.addVertex(vertex4);
		Assert.assertEquals(4, graph.numberVertex());
	}
	
	@Test 
	public void testAddVertex() {
		int num = graph.numberVertex();
		graph.addVertex(vertex4);
		Assert.assertEquals(num + 1, graph.numberVertex());
	}
	
	@Test
	public void testConnect() {
		graph.connect(vertex1, vertex2);
		List lista = vertex1.neighbors();
		
		Assert.assertEquals(lista.get(0), vertex2);
	}
	
	@Test
	public void testConnectInvalid() {
		Assert.assertFalse(graph.connect(vertex1, vertex4));
	}
	
	@Test
	public void testConnectWeight() {
		graph.setWeight(true);
		graph.connect(vertex1, vertex2, 2f);
		List lista = vertex1.neighbors();
		
		Assert.assertEquals(lista.get(0), vertex2);
		
	}
	
	@Test
	public void testConnectWeightInvalid() {
		graph.setWeight(true);
		Assert.assertFalse(graph.connect(vertex1, vertex4, 2f));
	}
	
	@Test
	public void testSetWeight() {
		graph.setWeight(true);
		Assert.assertTrue(graph.getWeight());
	}

}
