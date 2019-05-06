package com.libalgo.sorting;

import java.util.Random;

public class InPlaceQuickSort {

  private static final Random random = new Random(System.nanoTime());

  public static <T extends Comparable<T>> void sort(final T[] items) {
    final int pivotIdx = items.length - 1;
    final int startIdx = 0;
    final Comparable[] tempHolder = new Comparable[1];
    sortInner(items, tempHolder, startIdx, pivotIdx);
  }

  @SuppressWarnings("unchecked")
  private static void sortInner(final Comparable[] items, final Comparable[] tempHolder, final int startIdx, final int endIdx) {

    if (startIdx >= endIdx) {
      return;
    }

    int ltIdx = startIdx;
    int gtIdx = endIdx - 1;
    randomizePivot(items, tempHolder, startIdx, endIdx);

    final Comparable pivot = items[endIdx];
    while (ltIdx <= gtIdx) {
      while (ltIdx <= gtIdx && items[ltIdx].compareTo(pivot) <= 0) {
        ltIdx++;
      }
      while (gtIdx >= ltIdx && items[gtIdx].compareTo(pivot) >= 0) {
        gtIdx--;
      }

      if (ltIdx < gtIdx) {
        tempHolder[0] = items[ltIdx];
        items[ltIdx] = items[gtIdx];
        items[gtIdx] = tempHolder[0];
        ltIdx++;
        gtIdx--;
      }
    }

    items[endIdx] = items[ltIdx];
    items[ltIdx] = pivot;

    sortInner(items, tempHolder, startIdx, ltIdx - 1);
    sortInner(items, tempHolder, ltIdx + 1, endIdx);
  }

  private static void randomizePivot(final Comparable[] items, final Comparable[] temp, final int startIdx, final int endIdx) {
    final int randomPivotIdx = startIdx + Math.abs(random.nextInt(endIdx - startIdx));
    temp[0] = items[endIdx];
    items[endIdx] = items[randomPivotIdx];
    items[randomPivotIdx] = temp[0];
  }
}