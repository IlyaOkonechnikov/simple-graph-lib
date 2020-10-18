package com.simple.graph.lib;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;
import com.simple.graph.lib.pojo.Vertex;
import com.simple.graph.lib.search.SearchService;
import com.simple.graph.lib.search.SearchTask;
import com.simple.graph.lib.search.impl.SearchServiceImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SearchServiceImplTest {

  private final SearchTask<Integer> TEST_TASK = new SearchTask<Integer>() {

    @Override
    public Vertex<Integer> getFrom() {
      return Vertex.instanceOf(0);
    }

    @Override
    public Vertex<Integer> getTo() {
      return Vertex.instanceOf(1000);
    }

    @Override
    public Collection<Edge<Integer>> getVertexEdges(Vertex<Integer> vertex) {
      return Stream.of(Edge.instanceOf(Vertex.instanceOf(vertex.getValue()), Vertex.instanceOf(vertex.getValue() + 10),
          EdgeType.DIRECTED)).collect(Collectors.toList());
    }
  };

  @Test
  public void getPathTest() {
    SearchService<Integer> service = new SearchServiceImpl<>();
    Collection<Edge<Integer>> result = service.getPath(TEST_TASK);
    Assert.assertFalse(result.isEmpty());
    Assert.assertEquals(100L, result.size());
  }
}