package com.simple.graph.lib.search;

import com.simple.graph.lib.pojo.Edge;

import java.util.Collection;

public interface SearchService<T> {

  Collection<Edge<T>> getPath(SearchTask<T> task);
}
