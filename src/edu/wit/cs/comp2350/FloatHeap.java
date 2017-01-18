package edu.wit.cs.comp2350;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;

/**
 * An implementation of a binary max heap for the purpose of sorting ints
 */
public class FloatHeap {
    //region Instance variables
    private int length;
    private float[] array;
    private boolean maxtree;
    //endregion
    //region constructors

    /**
     * Creates a new FloatHeap from an array
     *
     * @param arr an unordered array that will be sorted into a binary tree
     */
    public FloatHeap(float[] arr) {
        this(arr.length);
        this.insert(arr);
    }

    /**
     * Creates a new FloatHeap with an initial size
     *
     * @param size Initial size of array
     */
    public FloatHeap(int size) {
        this.length = 0;
        this.array = new float[size];
        this.maxtree = true;
    }

    /**
     * Creates a new FloatHeap with an initial size of 16
     */
    public FloatHeap() {
        this(16);
    }
//endregion

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
     * inserts one or more numbers into the heap
     *
     * @param nums Numbers to insert into the heap
     */
    public void insert(float... nums) {
        for (float num : nums) {
            this.incrementArrayLength();
            this.array[this.length - 1] = Float.NEGATIVE_INFINITY;
            increaseKey(num); //TODO: Optimize this by having it only run once on a batch set of numbers
        }
    }

    /**
     * Builds the heap from an unordered array
     */
    public void maxHeap() {
        checkArraySize();
        for (int index = this.length / 2; index >= 1; index--) {
            maxHeapify(index);
        }
    }

    /**
     * Maintains the max heap
     *
     * @param index Index to heapify
     */
    public void maxHeapify(int index) {
        int left = getLeft(index);
        int right = getRight(index);
        int largest = index;
        if (left < this.length && this.array[left] > this.array[largest])
            largest = left;
        else if (right < this.length && this.array[right] > this.array[largest])
            largest = right;
        if (largest != index) {
            swap(this.array, index, largest);
            maxHeapify(largest);
        }
    }

    /**
     * Returns the largest value in the heap
     *
     * @return The largest value in the heap
     */
    public float getMaximum() {
        checkArraySize();
        return this.array[0];
    }

    /**
     * Returns the smallest value in the heap
     *
     * @return The smallest value in the heap
     */
    public float getMinimum() {
        checkArraySize();
        int index = 0;
        while (index < this.length) {
            index = getLeft(index);
        }
        return this.array[index];
    }

    /**
     * Pops the largest value in the heap, removing it from the heap
     *
     * @return The largest value in the heap
     */
    public float extractMaximum() {
        checkArraySize();
        float max = this.array[0];
        this.array[0] = this.array[this.length - 1];
        this.array[this.length - 1] = 0;
        this.length--;
        maxHeapify(0);
        return max;
    }

    public float extractMinimum() {
        checkArraySize();
        throw new NotImplementedException();
    }

    private void increaseKey(float key) {
        int i = this.length - 1;
        if (key < this.array[i]) {
            throw new IllegalArgumentException("New key is smaller than current key");
        }
        this.array[i] = key;
        while (i > 0 && this.array[getParent(i)] < this.array[i]) {
            swap(this.array, i, getParent(i));
            i = getParent(i);
        }
    }

    /**
     * Increases the size of the array if the virtual size is approaching the physical size
     */
    private void incrementArrayLength() {
        this.length++;
        if (this.array.length - 2 >= this.length) {
            this.array = Arrays.copyOf(this.array, this.array.length * 2);
        }
    }

    private void checkArraySize() {
        if (this.length == 0)
            throw new IndexOutOfBoundsException("The heap has no items");
    }

    /**
     * @return Returns a copy of the heap tree array
     */
    public float[] getTreeArray() {
        return Arrays.copyOf(this.array, this.length);
    }
    //endregion

    @Override
    public String toString() {
        //TODO: Implement toString
        return super.toString();
    }
}
