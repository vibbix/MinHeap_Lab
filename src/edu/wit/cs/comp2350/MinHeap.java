package edu.wit.cs.comp2350;

import java.util.Arrays;

/**
 * A minheap implementation
 */
public class MinHeap {
    private static final int MIN_SIZE = 2;
    private float[] heap;
    private int length;

    /**
     * Creates a new MinHeap with a user defined size
     *
     * @param size Size to set(minimum: 2)
     */
    public MinHeap(int size) {
        if (size < MIN_SIZE) {
            throw new IllegalArgumentException("Size must be at least " + MIN_SIZE);
        }
        this.heap = new float[size];
        this.length = 0;
    }

    /**
     * Creates a new MinHeap with a size of 2.
     */
    public MinHeap() {
        this(MIN_SIZE);
    }

    //region static helpers
    private static int getLeft(int n) {
        return 2 * n;
    }

    private static int getRight(int n) {
        return (2 * n) + 1;
    }

    private static int getParent(int n) {
        return n / 2;
    }

    private static void swap(float[] a, int i, int j) {
        float temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void minHeapify(int length, float[] heap, int parentIndex) {
        int smallest = parentIndex;
        int l = getLeft(parentIndex);
        int r = getRight(parentIndex);
        if (l < length && heap[smallest] > heap[l]) {
            smallest = l;
        }
        if (r < length && heap[smallest] > heap[r]) {
            smallest = r;
        }
        if (smallest != parentIndex) {
            swap(heap, parentIndex, smallest);
            minHeapify(length, heap, smallest);
        }
    }

    /**
     * Adds one or more values to the minheap
     *
     * @param values The value(s) to append to the minheap
     */
    public void insert(float... values) {
        for (float val : values) {
            incrementHeapLength();
            if (this.length == 1) {
                this.heap[this.length] = val;
            } else {
                this.heap[this.length] = val;
                this.siftUp(this.length);
            }
        }
    }

    /**
     * Sifts the minheap up, pushing smaller values up the tree
     *
     * @param index Index to run siftUp from
     */
    public void siftUp(int index) {
        while (index > 0 && this.heap[getParent(index)] > this.heap[index]) {
            swap(this.heap, index, getParent(index));
            index = getParent(index);
            //System.out.println("siftUp: index = " + index +"; "+ Arrays.toString(this.getTree()));
        }
    }

    /**
     * Iterates through heap and rebuilds tree to maintain the heap characteristic
     *
     * @param parentIndex Index to run minHeapify
     */
    public void minHeapify(int parentIndex) {
        minHeapify(this.length, this.heap, parentIndex);
    }

    /**
     * gets the smallest value in the tree, removes it from the tree, and then resorts the tree
     *
     * @return the root value
     */
    public float popMin() {
        float min = this.heap[1];
        swap(this.heap, 1, this.length);
        this.heap[this.length] = 0;
        this.length--;
        minHeapify(1);
        return min;
    }

    /**
     * Increases the size of the array if the virtual size is approaching the physical size
     */
    private void incrementHeapLength() {
        this.length++;
        int actual = this.heap.length;
        int sub = this.length;
        if (actual <= sub) {
            this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
        }
    }

    public int getLength() {
        return this.length;
    }

    /**
     * Sums all the floats
     *
     * @return the sum of all the floats in the heap
     */
    public float sum() {
        float[] h = Arrays.copyOf(this.heap, this.length + 1);
        int length = this.length;
        float sum = 0.0f;
        float c = 0.0f;
        while (length > 0) {
            float min = h[1];
            swap(h, 1, length);
            h[length] = 0;
            length--;
            minHeapify(length, h, 1);
            float y = min - c;
            float t = sum + y;
            c = (t - sum) - y;
            sum = t;
        }
        return sum;
    }

    /**
     * gets the minheap tree array
     *
     * @return The minheap tree array
     */
    public float[] getTree() {
        float[] copy = Arrays.copyOf(this.heap, this.length + 1);
        for (int i = 1; i < copy.length; i++) {
            copy[i - 1] = copy[i];
        }
        return Arrays.copyOf(copy, this.length);
    }

    //endregion
    @Override
    public String toString() {
        return Arrays.toString(this.getTree());
    }
}