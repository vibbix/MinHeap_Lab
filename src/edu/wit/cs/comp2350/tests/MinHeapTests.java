package edu.wit.cs.comp2350.tests;

import edu.wit.cs.comp2350.MinHeap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Tests for MinHeap
 */
public class MinHeapTests {
    private float[] ONE_INSERT = new float[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f, 16.0f, 17.0f};
    private float[] ONE_COMPARE = new float[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f, 16.0f, 17.0f};
    private float[] TWO_INSERT = new float[]{10.0f, 16.0f, 12.0f, 3.0f, 2.0f, 1.0f, 7.0f, 8.0f, 4.0f};
    private float[] TWO_COMPARE = new float[]{1.0f, 3.0f, 2.0f, 4.0f, 10.0f, 12.0f, 7.0f, 16.0f, 8.0f};

    @Test
    public void insertTest() {
        MinHeap mh = new MinHeap();
        mh.insert(ONE_INSERT);
        float[] comp = mh.getTree();
        Assert.assertArrayEquals(ONE_COMPARE, comp, 0);
    }

    @Test
    public void addTest() {
        MinHeap mh = new MinHeap();
        mh.insert(TWO_INSERT);
        float[] comp = mh.getTree();
        Assert.assertArrayEquals(TWO_COMPARE, comp, 0);
    }

    @Test
    public void extractMin() {
        MinHeap mh = new MinHeap(17);
        mh.insert(ONE_INSERT);
        float last = mh.popMin();
        while (mh.getLength() > 0) {
            float current = mh.popMin();
            Assert.assertTrue(current >= last);
            last = current;
        }
    }

    @Test
    public void extractMinPreSort() {
        MinHeap mh = new MinHeap(17);
        mh.insert(ONE_INSERT);
        float[] compare = new float[17];
        for (int i = 0; i < compare.length; i++) {
            compare[i] = mh.popMin();
        }
        Assert.assertArrayEquals(compare, ONE_INSERT, 0);
    }

    @Test
    public void extractMinByCompareSort() {
        MinHeap mh = new MinHeap();
        mh.insert(TWO_INSERT);
        float[] compare = new float[9];
        float[] sorted = Arrays.copyOf(TWO_COMPARE, TWO_COMPARE.length);
        Arrays.sort(sorted);
        for (int i = 0; i < compare.length; i++) {
            compare[i] = mh.popMin();
        }
        Assert.assertArrayEquals(sorted, compare, 0);
    }
}
