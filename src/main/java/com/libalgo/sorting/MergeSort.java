package com.libalgo.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeSort {

  public static <T extends Comparable<T>> List<T> sort(final T[] items) {

    final LinkedList<List<T>> structure = new LinkedList<>();
    for (int idx = 0; idx + 1 < items.length; idx += 2) {
      structure.add(merge(items[idx], items[idx + 1]));
    }
    if (items.length % 2 > 0)
      structure.add(Arrays.asList(items[items.length - 1]));

    while (structure.size() > 1) {
      final List<T> left = structure.poll();
      final List<T> right = structure.poll();
      structure.add(right != null ? merge(left, right) : left);
    }
    return structure.poll();
  }

  private static <T extends Comparable<T>> List<T> merge(final List<T> left, final List<T> right) {
    final int leftSize = left.size();
    final int rightSize = right.size();
    final List<T> sortedItems = new ArrayList(leftSize + rightSize);
    int leftIdx = 0;
    int rightIdx = 0;
    while (leftIdx < leftSize || rightIdx < rightSize) {
      if (leftIdx < leftSize && rightIdx < rightSize && left.get(leftIdx).compareTo(right.get(rightIdx)) <= 0) {
        sortedItems.add(left.get(leftIdx++));
      } else if (leftIdx < leftSize && rightIdx >= rightSize) {
        sortedItems.add(left.get(leftIdx++));
      } else {
        sortedItems.add(right.get(rightIdx++));
      }
    }
    return sortedItems;
  }

  private static <T extends Comparable<T>> List<T> merge(final T left, final T right) {
    final List<T> sortedItems = new ArrayList(2);
    if (left.compareTo(right) <= 0) {
      sortedItems.add(left);
      sortedItems.add(right);
    } else {
      sortedItems.add(right);
      sortedItems.add(left);
    }
    return sortedItems;
  }
}
