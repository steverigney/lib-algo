package com.libalgo.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface TestComparableSort {

  Random random = new Random();

  default ComparableClass generate() {
    final String str = Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong());
    return new ComparableClass(str, 1);
  }

  default List<ComparableClass> generate(final int numberToGenerate) {
    final List<ComparableClass> retVals = new ArrayList<>(numberToGenerate);
    for (int ctr = 0; ctr < numberToGenerate; ctr++) {
      final String str = Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong()) + Long.toHexString(random.nextLong());
      retVals.add(new ComparableClass(str, 1));
    }
    return retVals;
  }

  default List<ComparableClass> generateEquivalentObjects(final int numberToGenerate) {
    final List<ComparableClass> retVals = new ArrayList<>(numberToGenerate);
    for (int ctr = 0; ctr < numberToGenerate; ctr++) {
      retVals.add(new ComparableClass("CAFEBABE", ctr));
    }
    return retVals;
  }

  class ComparableClass implements Comparable<ComparableClass> {

    private final String fieldToCompare;
    private final int fieldToNotCompare;

    ComparableClass(final String fieldToCompare, final int fieldToNotCompare) {
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
