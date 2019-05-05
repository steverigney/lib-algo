package com.libalgo.sorting;

public interface SortingAlgo {
  <T extends Comparable<T>> void sort(T[] items);
}
