package com.simple.graph.lib;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;

import java.util.Collection;

public interface Graph<T> {

  Graph<T> addVertex(T vertex);

  Graph<T> addEdge(T first, T second, EdgeType type);

  Collection<Edge<T>> getPath(T first, T second);
}