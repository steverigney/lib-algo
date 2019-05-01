package com.libalgo.datastructure;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayHeap<T extends Comparable<T>> implements Heap<T> {

  private final Class<T[]> type;
  private final T[] memory;
  private int lastIdx = -1;

  public ArrayHeap(final Class<T[]> type, final int size) {
    memory = (T[]) Array.newInstance(Comparable.class, size);
    this.type = type;
  }

  @Override
  public boolean add(final T value) {

    if (lastIdx == memory.length - 1)
      return false;

    memory[++lastIdx] = value;
    upHeap(lastIdx);
    return true;
  }

  @Override
  public T remove() {

    if (isEmpty())
      return null;

    final T head = memory[0];
    memory[0] = memory[lastIdx];
    memory[lastIdx] = null;
    lastIdx--;

    if (!isEmpty())
      downHeap();

    return head;
  }

  @Override
  public boolean isEmpty() {
    return lastIdx < 0;
  }

  private void downHeap() {
    for (int idx = 0; idx <= lastIdx; ) {
      final int chosenChildIdx = chooseChildNode(idx);
      if (chosenChildIdx < 0)
        break;

      if (memory[idx].compareTo(memory[chosenChildIdx]) > 0) {
        final T temp = memory[idx];
        memory[idx] = memory[chosenChildIdx];
        memory[chosenChildIdx] = temp;
        idx = chosenChildIdx;
      } else {
        break;
      }
    }
  }

  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      @Override
      public boolean hasNext() {
        return !isEmpty();
      }

      @Override
      public T next() {
        return ArrayHeap.this.remove();
      }
    };
  }

  @Override
  public T[] dump() {
    return Arrays.copyOf(memory, memory.length, type);
  }

  private int chooseChildNode(final int idx) {
    final int leftChildIdx = (idx << 1) + 1;
    int chosenChildIdx = -1;

    if (leftChildIdx <= lastIdx) {
      chosenChildIdx = leftChildIdx;
      final int rightChildIdx = leftChildIdx + 1;
      if (rightChildIdx <= lastIdx) {
        if (memory[leftChildIdx].compareTo(memory[rightChildIdx]) > 0) {
          chosenChildIdx = rightChildIdx;
        }
      }
    }
    return chosenChildIdx;
  }

  private void upHeap(final int idx) {
    if (idx == 0) {
      return;
    }

    final int parentIdx = (idx - (2 - (idx % 2))) >> 1;
    if (memory[idx].compareTo(memory[parentIdx]) < 0) {
      final T temp = memory[idx];
      memory[idx] = memory[parentIdx];
      memory[parentIdx] = temp;
      upHeap(parentIdx);
    }
  }

  @Override
  public String toString() {
    return "Heap{" +
        "memory=" + Arrays.toString(memory) +
        '}';
  }
}
