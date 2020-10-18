package com.simple.graph.lib.search.impl;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.Vertex;
import com.simple.graph.lib.search.SearchTask;

import java.util.Collection;
import java.util.function.Function;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearchTaskImpl<T> implements SearchTask<T> {

  private final Vertex<T> from;
  private final Vertex<T> to;
  private final Function<Vertex<T>, Collection<Edge<T>>> searchFunction;

  @Override
  public Collection<Edge<T>> getVertexEdges(Vertex<T> vertex) {
    return searchFunction.apply(vertex);
  }
}
