package com.simple.graph.lib.repository;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.Vertex;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface GraphRepository<T> {

  Map<Vertex<T>, Set<Edge<T>>> getStorage();
    
  void addVertex(Vertex<T> vertex);

  void addEdge(Edge<T> edge);

  Collection<Edge<T>> getVertexEdges(Vertex<T> vertex);
}