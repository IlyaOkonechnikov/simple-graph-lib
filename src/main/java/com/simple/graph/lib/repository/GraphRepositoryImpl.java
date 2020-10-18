package com.simple.graph.lib.repository;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;
import com.simple.graph.lib.pojo.Vertex;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static java.util.Objects.isNull;

@Getter
@RequiredArgsConstructor
public class GraphRepositoryImpl<T> implements GraphRepository<T> {

  private final Map<Vertex<T>, Set<Edge<T>>> storage;

  @Override
  public void addVertex(Vertex<T> vertex) {
    storage.putIfAbsent(vertex, ConcurrentHashMap.newKeySet());
  }

  @Override
  public void addEdge(Edge<T> edge) {
    storage.compute(edge.getFirst(), (key, value) -> this.add(value, edge));
    if (EdgeType.UNDIRECTED.equals(edge.getType())) {
      storage.compute(edge.getSecond(), (key, value) -> this.add(value, edge));
    }
  }

  private Set<Edge<T>> add(Set<Edge<T>> set, Edge<T> item) {
    Set<Edge<T>> result = isNull(set) ? ConcurrentHashMap.newKeySet() : set;
    result.add(item);
    return result;
  }

  @Override
  public Collection<Edge<T>> getVertexEdges(Vertex<T> vertex) {
    return storage.getOrDefault(vertex, Collections.emptySet());
  }
}
