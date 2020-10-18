package com.simple.graph.lib;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;
import com.simple.graph.lib.pojo.Vertex;
import com.simple.graph.lib.repository.GraphRepositoryImpl;
import com.simple.graph.lib.repository.GraphRepository;
import com.simple.graph.lib.search.SearchService;
import com.simple.graph.lib.search.SearchTask;
import com.simple.graph.lib.search.impl.SearchServiceImpl;
import com.simple.graph.lib.search.impl.SearchTaskImpl;

import java.util.Collection;
import java.util.HashMap;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GraphImpl<T> implements Graph<T> {

  private final GraphRepository<T> repository;
  private final SearchService<T> service;

  public static <T> Graph<T> getInstance() {
    return new GraphImpl<>(new GraphRepositoryImpl<>(new HashMap<>()), new SearchServiceImpl<>());
  }

  @Override
  public Graph<T> addVertex(T vertex) {
    repository.addVertex(Vertex.instanceOf(vertex));
    return this;
  }

  @Override
  public Graph<T> addEdge(T first, T second, EdgeType type) {
    repository.addEdge(Edge.instanceOf(Vertex.instanceOf(first), Vertex.instanceOf(second), type));
    return this;
  }

  @Override
  public Collection<Edge<T>> getPath(T first, T second) {
    final SearchTask<T> task = SearchTaskImpl.<T>builder()
        .from(Vertex.instanceOf(first))
        .to(Vertex.instanceOf(second))
        .searchFunction(repository::getVertexEdges)
        .build();
    return service.getPath(task);
  }
}
