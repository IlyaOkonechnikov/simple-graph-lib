package com.simple.graph.lib.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "instanceOf")
public class Vertex<T> {
  private final T value;
}