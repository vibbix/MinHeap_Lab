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

    /**
     * Adds one or more values to the minheap
     *
     * @param values The value(s) to append to the minheap
     */
    public void insert(float... values) {
        for (float val : values) {
            if (this.length == 0) {
                this.heap[1] = val;
                this.length = 2;
            } else {
                incrementHeapLength();
                this.heap[this.length] = val;
                this.siftUp(this.length - 1);
            }
        }
    }

    /**
     * Sifts the minheap up, pushing smaller values up the tree
     *
     * @param index Index to run siftUp from
     */
    public void siftUp(int index) {
        while (index > 0 && this.heap[getLeft(index)] > this.heap[index]) {
            swap(this.heap, index, getParent(index));
            index = getParent(index);
        }
    }

    /**
     * Iterates through heap and rebuilds tree to maintain the heap characteristic
     *
     * @param index Index to run minHeapify
     */
    public void minHeapify(int index) {
        int smallest = index;
        int l = getLeft(index);
        int r = getRight(index);
        if (l < this.length && this.heap[smallest] > this.heap[l]) {
            smallest = l;
        } else if (r < this.length && this.heap[smallest] > this.heap[r]) {
            smallest = r;
        }
        if (smallest != index) {
            swap(this.heap, index, smallest);
            this.minHeapify(smallest);
        }
    }

    /**
     * gets the smallest value in the tree, removes it from the tree, and then resorts the tree
     *
     * @return the root value
     */
    public float extractMin() {
        float min = this.heap[1];
        swap(this.heap, 1, this.length - 1);
        this.length--;
        minHeapify(1);
        return min;
    }

    /**
     * Increases the size of the array if the virtual size is approaching the physical size
     */
    private void incrementHeapLength() {
        this.length++;
        if (this.heap.length - 2 >= this.length) {
            this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
        }
    }

    //endregion
    public int getLength() {
        return this.length;
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

    @Override
    public String toString() {
        return Arrays.toString(this.heap);
    }
}