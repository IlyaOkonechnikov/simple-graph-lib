package com.simple.graph.lib;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class GraphTest {

  @Test
  public void getPathOneVertexTest() {
    Collection<Edge<String>> path = GraphImpl.<String>getInstance()
        .addVertex(TestConstants.FIRST_TEST_VERTEX)
        .getPath(TestConstants.FIRST_TEST_VERTEX, TestConstants.FIRST_TEST_VERTEX);
    Assert.assertNotNull(path);
    Assert.assertTrue(path.isEmpty());
  }

  @Test
  public void getPathTwoVertexesWithoutEdgeTest() {
    Collection<Edge<String>> path = GraphImpl.<String>getInstance()
        .addVertex(TestConstants.FIRST_TEST_VERTEX)
        .addVertex(TestConstants.SECOND_TEST_VERTEX)
        .getPath(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX);
    Assert.assertNotNull(path);
    Assert.assertTrue(path.isEmpty());
  }

  @Test
  public void getPathTwoVertexesWithUndirectedEdgeTest() {
    Collection<Edge<String>> path = GraphImpl.<String>getInstance()
        .addVertex(TestConstants.FIRST_TEST_VERTEX)
        .addVertex(TestConstants.SECOND_TEST_VERTEX)
        .addEdge(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX, EdgeType.UNDIRECTED)
        .getPath(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX);
    Assert.assertNotNull(path);
    Assert.assertFalse(path.isEmpty());
    Assert.assertEquals(1, path.size());
  }

  @Test
  public void getPathTwoVertexesWithDirectedEdgeTest() {
    Collection<Edge<String>> path = GraphImpl.<String>getInstance()
        .addVertex(TestConstants.FIRST_TEST_VERTEX)
        .addVertex(TestConstants.SECOND_TEST_VERTEX)
        .addEdge(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX, EdgeType.DIRECTED)
        .getPath(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX);
    Assert.assertNotNull(path);
    Assert.assertFalse(path.isEmpty());
    Assert.assertEquals(1, path.size());
  }

  @Test
  public void getPathTwoVertexesWithReversedDirectedEdgeTest() {
    Collection<Edge<String>> path = GraphImpl.<String>getInstance()
        .addVertex(TestConstants.FIRST_TEST_VERTEX)
        .addVertex(TestConstants.SECOND_TEST_VERTEX)
        .addEdge(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX, EdgeType.DIRECTED)
        .getPath(TestConstants.SECOND_TEST_VERTEX, TestConstants.FIRST_TEST_VERTEX);
    Assert.assertNotNull(path);
    Assert.assertTrue(path.isEmpty());
  }

  @Test
  public void getPathThreeVertexesWithEdgeTest() {
    Collection<Edge<String>> path = GraphImpl.<String>getInstance()
        .addVertex(TestConstants.FIRST_TEST_VERTEX)
        .addVertex(TestConstants.SECOND_TEST_VERTEX)
        .addVertex(TestConstants.THIRD_TEST_VERTEX)
        .addEdge(TestConstants.FIRST_TEST_VERTEX, TestConstants.SECOND_TEST_VERTEX, EdgeType.UNDIRECTED)
        .addEdge(TestConstants.SECOND_TEST_VERTEX, TestConstants.THIRD_TEST_VERTEX, EdgeType.UNDIRECTED)
        .getPath(TestConstants.FIRST_TEST_VERTEX, TestConstants.THIRD_TEST_VERTEX);
    Assert.assertNotNull(path);
    Assert.assertFalse(path.isEmpty());
    Assert.assertEquals(2, path.size());
  }
}