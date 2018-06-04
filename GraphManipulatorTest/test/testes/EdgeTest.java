package testes;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;

import manipulator.Edge;
import manipulator.Vertex;

import org.junit.Test;


public class EdgeTest {

	Vertex end, start;
	Edge edge;
	
	@Before
	public void setup() {
		end = new Vertex(2);
		start = new Vertex(1);
		edge = new Edge(end, start, 10f);
	}
	
	@Test
	public void testGetEnd() {
		Assert.assertEquals(end, edge.getEnd());
	}
	
	@Test
	public void testGetStart() {
		Assert.assertEquals(start, edge.getStart());
	}
	
	public void testSetEnd() {
		Vertex newvertex = new Vertex(3);
		edge.setEnd(newvertex);
		Assert.assertEquals(newvertex, edge.getEnd());
	}
	
	public void testSetStart() {
		Vertex newvertex = new Vertex(3);
		edge.setStart(newvertex);
		Assert.assertEquals(newvertex, edge.getStart());
	}
	
	public void testGetWeight() {
		Assert.assertEquals(10f, edge.getWeight(), 0.00001);
	}

	public void testSetWeight() {
		edge.setWeight(9f);
		Assert.assertEquals(9f, edge.getWeight(), 0.000001);
	}
}
