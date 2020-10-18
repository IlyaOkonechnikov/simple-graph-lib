package com.simple.graph.lib.search;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.Vertex;

import java.util.Collection;

public interface SearchTask<T> {
  Vertex<T> getFrom();

  Vertex<T> getTo();

  Collection<Edge<T>> getVertexEdges(Vertex<T> vertex);
}
