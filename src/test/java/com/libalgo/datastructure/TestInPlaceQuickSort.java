package com.libalgo.datastructure;

import com.libalgo.sorting.InPlaceQuickSort;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestInPlaceQuickSort implements TestComparableSort {

  private final InPlaceQuickSort quickSort = new InPlaceQuickSort();

  @Test
  void testSortEmptyList() {
    final String[] elements = new String[0];
    quickSort.sort(elements);

    assertEquals(0, elements.length);
  }

  @Test
  void testSortSingleElementList() {
    final String[] elements = new String[]{"a"};
    quickSort.sort(elements);

    assertEquals("a", elements[0]);
  }

  @Test
  void testSortNonEmptyList() {
    final Integer[] elements = new Integer[]{8, 5, 1, 3, 2, 89, 4, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
    quickSort.sort(elements);

    int previous = Integer.MIN_VALUE;
    for (final int element : elements) {
      assertTrue(element >= previous);
      previous = element;
    }
  }

  @Test
  void testSortNonEmptyListLotsOfTimes() {
    final Random random = new Random();
    random.setSeed(System.nanoTime());

    final int numberOfElements = 100_000;
    final int numberOfIterations = 100;
    final Long[] elements = new Long[numberOfElements];

    for (int currentIteration = 0; currentIteration < numberOfIterations; currentIteration++) {
      for (int idx = 0; idx < numberOfElements; idx++) {
        elements[idx] = random.nextLong();
      }
      quickSort.sort(elements);

      long previous = Long.MIN_VALUE;
      for (final long element : elements) {
        assertTrue(element >= previous);
        previous = element;
      }
    }
  }

  @Test
  void testSortAlreadySortedList() {
    final Random random = new Random();
    random.setSeed(System.nanoTime());

    final int numberOfElements = 100_000;
    final int numberOfIterations = 100;
    final long[] values = random.longs(numberOfElements).sorted().toArray();
    final Long[] elements = new Long[numberOfElements];

    for (int currentIteration = 0; currentIteration < numberOfIterations; currentIteration++) {
      for (int idx = 0; idx < numberOfElements; idx++) {
        elements[idx] = values[idx];
      }
      quickSort.sort(elements);

      long previous = Long.MIN_VALUE;
      for (final long element : elements) {
        assertTrue(element >= previous);
        previous = element;
      }
    }
  }

  @Test
  void testSortAllElementsTheSame() {
    final Integer[] elements = new Integer[]{7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
    quickSort.sort(elements);

    int previous = Integer.MIN_VALUE;
    for (final int element : elements) {
      assertTrue(element >= previous);
      previous = element;
    }
  }
}
