package com.simple.graph.lib.search.impl;

import com.simple.graph.lib.pojo.Edge;
import com.simple.graph.lib.pojo.EdgeType;
import com.simple.graph.lib.pojo.Vertex;
import com.simple.graph.lib.search.SearchService;
import com.simple.graph.lib.search.SearchTask;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class SearchServiceImpl<T> implements SearchService<T> {

  @Override
  public Collection<Edge<T>> getPath(SearchTask<T> task) {
    return getPath(task.getFrom(), task.getTo(), Collections.emptyMap(), task::getVertexEdges);
  }

  private Collection<Edge<T>> getPath(Vertex<T> from, Vertex<T> to, Map<Vertex<T>, Edge<T>> path,
                                      Function<Vertex<T>, Collection<Edge<T>>> searchFunction) {
    return searchFunction.apply(from)
        .stream()
        .map(edge -> checkEdge(edge, from, to, path, searchFunction))
        .filter(edges -> !edges.isEmpty())
        .min(Comparator.comparing(Collection::size))
        .orElse(Collections.emptySet());
  }

  private Collection<Edge<T>> checkEdge(Edge<T> edge, Vertex<T> from, Vertex<T> to, Map<Vertex<T>, Edge<T>> path,
                                        Function<Vertex<T>, Collection<Edge<T>>> searchFunction) {
    Vertex<T> next = getSecondVertexFromEdge(edge, from);
    return Objects.equals(next, to) ? addEdgeToPath(path, edge).values() :
        path.containsKey(next) ? Collections.emptySet() :
            getPath(next, to, addEdgeToPath(path, edge), searchFunction);
  }

  private static <T> Vertex<T> getSecondVertexFromEdge(Edge<T> edge, Vertex<T> from) {
    return EdgeType.DIRECTED.equals(edge.getType()) ? edge.getSecond() :
        Objects.equals(from, edge.getFirst()) ? edge.getSecond() : edge.getFirst();
  }

  private static <T> Map<Vertex<T>, Edge<T>> addEdgeToPath(Map<Vertex<T>, Edge<T>> map, Edge<T> value) {
    Map<Vertex<T>, Edge<T>> resultMap = new LinkedHashMap<>(map);
    resultMap.put(value.getFirst(), value);
    return resultMap;
  }
}
