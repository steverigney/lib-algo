package com.libalgo.datastructure;


import com.libalgo.sorting.TestComparableSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


class TestArrayHeap implements TestComparableSort {

  private final Random random = new Random();

  @BeforeEach
  void setUp() {
    random.setSeed(System.nanoTime());
  }

  @Test
  void testAddElements() {

    final int size = 1000000;
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, size);

    final IntStream elementStream = random.ints(size);
    elementStream.forEach(e -> assertTrue(heap.add(e)));

    final Integer[] heapDump = heap.dump();

    assertEquals(size, heapDump.length);

    for (int idx = 0; idx < heapDump.length; idx++) {
      if (idx == 0) continue;
      final int parentIdx = (idx - (2 - (idx % 2))) / 2;
      assert (heapDump[parentIdx] <= heapDump[idx]);
    }
  }

  @Test
  void testAddAndRemoveSingleElement() {

    //Assemble
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 1);
    final int item = random.nextInt();

    //Act
    heap.add(item);


    //Assert
    assertEquals(item, heap.remove().intValue());
  }

  @Test
  void testAddNewSmallestElementAndRemoveIt() {

    //Assemble
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 2);
    final int largerItem = 10;
    final int smallerItem = 9;

    //Act
    assert heap.add(largerItem);
    assert heap.add(smallerItem);

    //Assert
    assertEquals(smallerItem, heap.remove().intValue());
    assertEquals(largerItem, heap.remove().intValue());
  }

  @Test
  void testAddNewLargestElementAndRemoveAllElements() {
    //Assemble
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 2);
    final int largerItem = 10;
    final int smallerItem = 9;

    //Act
    assert heap.add(smallerItem);
    assert heap.add(largerItem);

    //Assert
    assertEquals(smallerItem, heap.remove().intValue());
    assertEquals(largerItem, heap.remove().intValue());
  }

  @Test
  void testRemoveElements() {

    final int size = 1000000;
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, size);

    final int rounds = 50;

    for (int round = 0; round < rounds; round++) {
      final IntStream elementStream = random.ints(size);
      elementStream.forEach(heap::add);

      removeFromHeap(size / 2, heap, Integer.MIN_VALUE);
      addToHeap(size / 4, heap);
      removeFromHeap(size / 4, heap, Integer.MIN_VALUE);
      addToHeap(size / 2, heap);
      removeFromHeap(size, heap, Integer.MIN_VALUE);

      assert heap.isEmpty();
    }
  }

  @Test
  void testRemoveFromEmptyHeapShouldReturnNull() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 1);
    assertNull(heap.remove());
  }

  @Test
  void testAddingToFullHeapShouldReturnFalse() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 1);

    assert heap.add(random.nextInt());

    assertFalse(heap.add(random.nextInt()));
  }

  @Test
  void testRemoveFromHeapOfSizeZeroShouldReturnNull() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 0);
    assertNull(heap.remove());
  }

  @Test
  void testAddingToHeapOfSizeZeroShouldReturnFalse() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 0);
    assertFalse(heap.add(random.nextInt()));
  }

  private void removeFromHeap(final int numberToRemove, final Heap<Integer> heap, final int previousElement) {
    int removedCount = 0;
    int prevElement = previousElement;

    while (removedCount < numberToRemove) {
      final int element = heap.remove();
      assert (element >= prevElement);
      prevElement = element;
      removedCount++;
    }
  }

  private void addToHeap(final int numberOfEntriesToAdd, final Heap<Integer> heap) {
    final IntStream elementStream = random.ints(numberOfEntriesToAdd);
    elementStream.forEach(e -> assertTrue(heap.add(e)));
  }
}
