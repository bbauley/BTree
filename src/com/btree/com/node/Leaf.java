/**
 * One of the states that a node can be in.
 * A leaf can have one or two data values.
 * The leaves children will ALWAYS be null,
 *  so there is no need to add them as data members
 */

package com.btree.com.node;

public class Leaf extends Node{

  private int data[];

  public Leaf(int value) {
    this.data = new int[DATA_ARRAY_SIZE];
    this.data[0] = value;
    this.data[1] = 0;
  }

  public Leaf(final Leaf copy) {
    this.data = new int[DATA_ARRAY_SIZE];
    this.data[0] = copy.data[0];
    this.data[1] = copy.data[1];
  }
  /**
   * Adds a new value in the Leaf node
   * @param value
   * @return 1 if successful and 0 if failure
   */
  public int add(int value) {
    if(data[1] != 0) {
      return 0;
    }
    //Keep the numbers in the array sorted
    if(value > data[0]) {
      data[1] = value;
    }
    else {
      data[1] = data[0];
      data[0] = value;
    }
    return 1;
  }

  @Override
  public void display() {
    if(data[1] != 0) {
      System.out.println(data[0] + " " + data[1]);
    }
    else {
      System.out.println(data[0]);
    }
  }

  /**
   * This function will only be called when a leaf is full
   *  and we need to figure out which number to push up to
   *  the parent node
   * @param value
   * @return The middle number of the 3 values
   */
  public int findMiddle(int value) {
    if(data[0] <= value && value <= data[1]) {
      return value;
    }
    else if(value <= data[0] && data[0] <= data[1]) {
      return data[0];
    }
    else {
      return data[1];
    }
  }

  /**
   * Returns the data at the given index
   * @param index
   * @return The data at the index given as a parameter. -1 for failure
   */
  public int getData(int index) {
    int result = -1;
    if(index == 0) {
      result = data[0];
    }
    else if(index == 1) {
      result = data[1];
    }
    return result;
  }
}
