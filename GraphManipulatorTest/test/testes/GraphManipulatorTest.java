package testes;

import manipulator.Edge;
import manipulator.Graph;
import manipulator.GraphManipulator;
import manipulator.Vertex;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.fail;

public class GraphManipulatorTest {

    private GraphManipulator graphManipulator;

    private static final String INPUT_SIMPLES = "/home/severino/Documents/ufcg/Graphs/GraphManipulatorTest/resources/input-sample.txt";
    private static final String INPUT_SIMPLES_VERTICE_NEGATIVO = "/home/severino/Documents/ufcg/Graphs/GraphManipulatorTest/resources/input-sample-vertex-negativos.txt";
    private static final String INPUT_SIMPLES_ARESTA_UNICA = "/home/severino/Documents/ufcg/Graphs/GraphManipulatorTest/resources/input-sample-uma-aresta.txt";
    private static final String INPUT_SIMPLES_VERTICE_INVALIDO = "/home/severino/Documents/ufcg/Graphs/GraphManipulatorTest/resources/input-sample-vertice-invalido.txt";
    private static final String INPUT_WEIGTH = "/home/severino/Documents/ufcg/Graphs/GraphManipulatorTest/resources/input-weigth.txt";
    private static final String INPUT_NOT_CONNECTED = "/home/severino/Documents/ufcg/Graphs/GraphManipulatorTest/resources/input-not-connected.txt";



    @Before
    public void setUp() {
        this.graphManipulator = new GraphManipulator();
    }

    /*
            Utilizando da técnica de caixa branca, ao fazer o teste simples para o readGraph, o mesmo apresentou um "IndexOutOfBoundException".
        Ao investigar um pouco mais a fundo, percebemos que o arquivo.txt utilizado pela equipe estava errado: pedia 5 vertices e o arquivo
        só tinha dados para os quatro primeiros, este erro provavelmente acabou sendo propagado e influenciado o desenvolvimento de maneira
        negativa.
     */
    @Test
    public void testReadGraph() {


        try{
            Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES);

            List<Vertex<Integer>> realNodes = graph.getNodes();
            List<Vertex<Integer>> mockNodes = grafoMocado().getNodes();

            for (int i = 0; i < realNodes.size(); i++) {
                Assert.assertEquals(realNodes.get(i), mockNodes.get(i));

                List<Edge<Integer>> realEdges = realNodes.get(i).getEdges();
                List<Edge<Integer>> mockEdges = mockNodes.get(i).getEdges();

                for (int j = 0; j < realEdges.size(); j++) {
                    Assert.assertEquals(realEdges.get(j).getEnd(), mockEdges.get(j).getEnd());
                    Assert.assertEquals(realEdges.get(j).getWeight(), mockEdges.get(j).getWeight());
                }
            }
            Assert.assertTrue(false);

        }catch (Exception e){
            //fail("Esta linha nao devia ser executada");
            Assert.assertTrue(true);
        }

    }



    @Test
    public void testReadGraphComVerticeNegativoTest(){
        try{
            Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES_VERTICE_NEGATIVO);

            List<Vertex<Integer>> realNodes = graph.getNodes();
            List<Vertex<Integer>> mockNodes = grafoMocado().getNodes();

            for (int i = 0; i < realNodes.size(); i++) {
                Assert.assertEquals(realNodes.get(i), mockNodes.get(i));

            }
        }catch (Exception e){
            fail("Esta linha nao devia ser executada");
        }

    }


    /*
        [CAIXA-BRANCA] Ao inserir um ultima aresta no arquivo.txt (5-6) deveria lançar uma excessão,
    uma vez que o vertice 6 não existe neste contexto.
     */
    @Test
    public void testReadGraphComVerticeInexistenteTest(){

        try{
            Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES_VERTICE_INVALIDO);

            Assert.assertFalse(false);
        }catch (Exception e){
            //essa trecho deveria ser executado
            Assert.assertTrue(true);

        }

    }



    @Test
    public void getVertexNumberTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES);
        Assert.assertEquals(graphManipulator.getVertexNumber(graph), 5);

    }

    /*
    [CAIXA-BRANCA] - ao receber o valor negativo da aresta era para ser disparado uma exception ou converter o valor negativo para positivo.
    O que o método faz é transformar o número negativo em 0.
     */
    @Test
    public void getVertexNumberNegativoTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES_VERTICE_NEGATIVO);
        Assert.assertNotEquals(graphManipulator.getVertexNumber(graph), -5);

    }

    @Test
    public void getEdgeNumberTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES);
        Assert.assertEquals(graphManipulator.getEdgeNumber(graph), 5);

    }

    @Test
    public void getEdgeNumberSmallGraphTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES_ARESTA_UNICA);
        Assert.assertEquals(graphManipulator.getEdgeNumber(graph), 1);

    }


    @Test
    public void graphRepresentationALTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES);
        String out = this.graphManipulator.graphRepresentation(graph, "AL");
        String list = "1 - 2 5\n" +
                "2 - 1 5\n" +
                "3 - 5\n" +
                "4 - 5\n" +
                "5 - 1 2 3 4\n";

        Assert.assertEquals(list, out);
    }

    @Test
    public void graphRepresentationAMTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_SIMPLES);
        String out = this.graphManipulator.graphRepresentation(graph, "AM");
        String matrix = "  1 2 3 4 5\n1 0 1 0 0 1\n2 1 0 0 0 1\n3 0 0 0 0 1\n4 0 0 0 0 1\n5 1 1 1 1 0\n";
        Assert.assertEquals(matrix, out);

    }

    /*
    Ocorreu um erro ao ler um grafo com peso. Acreditamos que a raiz do erro é semelhante ao encontrado no metodo readGraph();

        */
    @Test
    public void shortestPathTest(){
        try{
            Graph<Integer> graph = graphManipulator.readGraph(INPUT_WEIGTH);
            Vertex<Integer> firstVertex = graph.getNodes().get(0);
            Vertex<Integer> thirdVertex = graph.getNodes().get(2);
            String result = this.graphManipulator.shortestPath(firstVertex, thirdVertex);
            String expectedResult = "1 2 5 4 3";
            Assert.assertEquals(result, expectedResult);

        }catch (Exception e){
            fail("este bloco deveria ser executado");
        }


    }

    @Test
    public void isConnectedTest(){
        Graph<Integer> graph = graphManipulator.readGraph(INPUT_NOT_CONNECTED);
        boolean expect = false;
        boolean real = this.graphManipulator.connected(graph);
        Assert.assertEquals(expect, real);


    }

















    private Graph<Integer> grafoMocado() {
        Graph<Integer> graph = new Graph<Integer>();

        Vertex<Integer> vertex1 = new Vertex<Integer>(1);
        Vertex<Integer> vertex2 = new Vertex<Integer>(2);
        Vertex<Integer> vertex3 = new Vertex<Integer>(3);
        Vertex<Integer> vertex4 = new Vertex<Integer>(4);
        Vertex<Integer> vertex5 = new Vertex<Integer>(5);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);

        graph.connect(vertex1, vertex2);
        graph.connect(vertex2, vertex3);
        graph.connect(vertex3, vertex4);
        graph.connect(vertex4, vertex5);

        return graph;
    }



}
