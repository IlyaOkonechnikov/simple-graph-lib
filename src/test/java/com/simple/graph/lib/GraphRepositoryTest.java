package com.simple.graph.lib;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;
import com.simple.graph.lib.pojo.Vertex;
import com.simple.graph.lib.repository.GraphRepository;
import com.simple.graph.lib.repository.GraphRepositoryImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;

public class GraphRepositoryTest {

  @Test
  public void addVertexTest() {
    GraphRepository<String> repository = new GraphRepositoryImpl<>(new HashMap<>());
    repository.addVertex(Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX));
    Map<Vertex<String>, Set<Edge<String>>> storage = repository.getStorage();
    Assert.assertFalse(storage.isEmpty());
    Assert.assertTrue(storage.containsKey(Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX)));
    Assert.assertNotNull(storage.get(Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX)));
  }

  @Test
  @SuppressWarnings("unchecked, rawtypes")
  public void addEdgeTest() {
    GraphRepository<String> repository = new GraphRepositoryImpl<>(new HashMap<>());
    Edge edge = Edge.instanceOf(
        Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX), Vertex.instanceOf(TestConstants.SECOND_TEST_VERTEX), EdgeType.UNDIRECTED
    );
    repository.addEdge(Edge.instanceOf(
        Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX), Vertex.instanceOf(TestConstants.SECOND_TEST_VERTEX), EdgeType.UNDIRECTED
    ));
    Map<Vertex<String>, Set<Edge<String>>> storage = repository.getStorage();
    Assert.assertFalse(storage.isEmpty());
    Assert.assertTrue(storage.containsKey(Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX)));
    Assert.assertTrue(storage.containsKey(Vertex.instanceOf(TestConstants.SECOND_TEST_VERTEX)));
    Assert.assertThat(storage.get(Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX)), hasItems(edge));
    Assert.assertThat(storage.get(Vertex.instanceOf(TestConstants.SECOND_TEST_VERTEX)), hasItems(edge));
  }

  @Test
  @SuppressWarnings("unchecked, rawtypes")
  public void getEdgesForVertexTest() {
    GraphRepository<String> repository = new GraphRepositoryImpl<>(new HashMap<>());
    Edge edge = Edge.instanceOf(
        Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX), Vertex.instanceOf(TestConstants.SECOND_TEST_VERTEX), EdgeType.UNDIRECTED
    );
    repository.addEdge(Edge.instanceOf(
        Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX), Vertex.instanceOf(TestConstants.SECOND_TEST_VERTEX), EdgeType.UNDIRECTED
        ));
    Collection<Edge<String>> edges = repository.getVertexEdges(Vertex.instanceOf(TestConstants.FIRST_TEST_VERTEX));
    Assert.assertFalse(edges.isEmpty());
    Assert.assertThat(edges, hasItems(edge));
  }
}