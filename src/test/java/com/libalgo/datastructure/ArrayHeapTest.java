package com.libalgo.datastructure;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


public class ArrayHeapTest {

  private final Random random = new Random();

  @BeforeEach
  public void setup() {
    random.setSeed(System.nanoTime());
  }

  @Test
  public void addElementsTest() {

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
  public void addAndRemoveSingleElement() {

    //Assemble
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 1);
    final int item = random.nextInt();

    //Act
    heap.add(item);


    //Assert
    assertEquals(item, heap.remove().intValue());
  }

  @Test
  public void addNewSmallestElementAndRemoveIt() {

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
  public void addNewLargestElementAndRemoveAllElements() {
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
  public void removeElementsTest() {

    final int size = 1000000;
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, size);

    final int rounds = 50;

    for (int round = 0; round < rounds; round++) {
      final IntStream elementStream = random.ints(size);
      elementStream.forEach(e -> heap.add(e));

      removeFromHeap(size / 2, heap, Integer.MIN_VALUE);
      addToHeap(size / 4, heap);
      removeFromHeap(size / 4, heap, Integer.MIN_VALUE);
      addToHeap(size / 2, heap);
      removeFromHeap(size, heap, Integer.MIN_VALUE);

      assert heap.isEmpty();
    }
  }

  @Test
  public void removeFromEmptyHeapShouldReturnNull() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 1);
    assertNull(heap.remove());
  }

  @Test
  public void addingToFullHeapShouldReturnFalse() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 1);

    assert heap.add(random.nextInt());

    assertFalse(heap.add(random.nextInt()));
  }

  @Test
  public void removeFromHeapOfSizeZeroShouldReturnNull() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 0);
    assertNull(heap.remove());
  }

  @Test
  public void addingToHeapOfSizeZeroShouldReturnFalse() {
    final Heap<Integer> heap = new ArrayHeap<>(Integer[].class, 0);
    assertFalse(heap.add(random.nextInt()));
  }

  @Test
  public void heapShouldBeStable() {
    final ComparableClass c1 = new ComparableClass("abc", 1);
    final ComparableClass c2 = new ComparableClass("abc", 2);
    final ComparableClass c3 = new ComparableClass("xyz", 3);
    final ComparableClass c4 = new ComparableClass("aaa", 4);

    final Heap<ComparableClass> heap = new ArrayHeap<>(ComparableClass[].class, 4);
    heap.add(c1);
    heap.add(c3);
    heap.add(c2);
    heap.add(c4);


    heap.remove();
    assertEquals(c1.fieldToNotCompare, heap.remove().getFieldToNotCompare());
    assertEquals(c2.fieldToNotCompare, heap.remove().getFieldToNotCompare());
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

  private static class ComparableClass implements Comparable<ComparableClass> {

    private final String fieldToCompare;
    private final int fieldToNotCompare;

    public ComparableClass(final String fieldToCompare, final int fieldToNotCompare) {
      this.fieldToCompare = fieldToCompare;
      this.fieldToNotCompare = fieldToNotCompare;
    }

    public String getFieldToCompare() {
      return fieldToCompare;
    }

    public int getFieldToNotCompare() {
      return fieldToNotCompare;
    }


    @Override
    public int compareTo(final ComparableClass o) {
      return fieldToCompare.compareTo(o.fieldToCompare);
    }
  }
}
