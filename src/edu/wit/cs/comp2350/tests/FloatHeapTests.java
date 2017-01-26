package edu.wit.cs.comp2350.tests;

import edu.wit.cs.comp2350.FloatHeap;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the floatheap
 */
public class FloatHeapTests {
    //Numbers from: https://brilliant.org/wiki/binary-heap/
    private final static float[] INSERT_ONE = new float[]{2.0f, 17.0f, 7.0f, 19.0f, 3.0f, 90.0f, 25.0f, 36.0f, 1.0f};
    private final static float[] TREE_ONE = new float[]{90.0f, 19.0f, 36.0f, 17.0f, 3.0f, 25.0f, 1.0f, 2.0f, 7.0f};
    private final static float[] INSERT_TWO = new float[]{15.0f, 35.0f, 31.0f, 94.0f, 16.0f, 83.0f, 18.0f, 98.0f,
            5.0f, 80.0f, 66.0f, 96.0f, 71.0f, 93.0f};
    private final static float[] TREE_TWO = new float[]{98.0f, 94.0f, 96.0f, 35.0f, 83.0f, 80.0f, 93.0f,
            15.0f, 31.0f, 16.0f, 18.0f, 5.0f, 66.0f, 71.0f};
    private final static float[] REMOVE_TREE_TWO = new float[]{96.0f, 94.0f, 93.0f, 35.0f, 83.0f, 80.0f, 71.0f,
            15.0f, 31.0f, 16.0f, 18.0f, 5.0f, 66.0f};   //the tree after it's largest item has removed
    private final static float DELTA = 0.0001f;

    @Test
    public void insertSort() throws Exception {
        FloatHeap fh = new FloatHeap();
        fh.insert(INSERT_ONE);
        Assert.assertArrayEquals(TREE_ONE, fh.getTreeArray(), DELTA);
    }

    @Test
    public void insertSortConstructor() throws Exception {
        FloatHeap fh = new FloatHeap(INSERT_ONE);
        float[] tree = fh.getTreeArray();
        Assert.assertArrayEquals(TREE_ONE, tree, DELTA);
    }

    @Test
    public void getMaximum() throws Exception {
        FloatHeap fh = new FloatHeap(INSERT_TWO);
        Assert.assertEquals(TREE_TWO[0], fh.getMaximum(), DELTA);
    }

    @Test
    public void compareTreeTwo() {
        FloatHeap fh = new FloatHeap(INSERT_TWO);
        Assert.assertArrayEquals(TREE_TWO, fh.getTreeArray(), DELTA);
    }

    @Test
    public void extractMaximum() throws Exception {
        FloatHeap fh = new FloatHeap(INSERT_TWO);
        Assert.assertEquals(TREE_TWO[0], fh.extractMaximum(), DELTA);
        Assert.assertArrayEquals(REMOVE_TREE_TWO, fh.getTreeArray(), DELTA);
    }

}
