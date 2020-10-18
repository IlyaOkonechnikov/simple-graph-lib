package com.simple.graph.lib.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "instanceOf")
public class Edge<T> {
  private final Vertex<T> first;
  private final Vertex<T> second;
  private final EdgeType type;
}
