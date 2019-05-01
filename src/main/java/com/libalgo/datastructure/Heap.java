package com.libalgo.datastructure;

public interface Heap<T extends Comparable<T>> extends Iterable<T> {

  boolean add(T item);

  T remove();

  boolean isEmpty();

  T[] dump();
}
