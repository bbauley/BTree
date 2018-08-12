/**
 * One of the three states a node can be in.
 * This state means that this node will have
 *  UP to 2 data items and MUST have 3 children.
 */


package com.btree.com.node;

public class TwoValues extends Node{

  private int data[];
  private Node child[];

  public TwoValues() {
    this.data = new int[DATA_ARRAY_SIZE];
    this.data[0] = this.data[1] = 0;
    this.child = new Node[CHILD_ARRAY_SIZE];
    for(int i = 0; i < CHILD_ARRAY_SIZE; ++i) {
      this.child[i] = null;
    }
  }

  //Assumes that value1 < value2
  public TwoValues(int value1, int value2) {
    this.data = new int[DATA_ARRAY_SIZE];
    this.data[0] = value1;
    this.data[1] = value2;
    this.child = new Node[CHILD_ARRAY_SIZE];
    for(int i = 0; i < CHILD_ARRAY_SIZE; ++i) {
      this.child[i] = null;
    }
  }

  public TwoValues(final TwoValues copy) {
    this.data = new int[DATA_ARRAY_SIZE];
    this.data[0] = copy.data[0];
    this.data[1] = copy.data[1];
    for(int i = 0; i < CHILD_ARRAY_SIZE; ++i) {
      this.child[i] = copy.child[i];
    }
  }

  public Node Left() {
    return this.child[0];
  }

  public Node Middle() {
    return this.child[1];
  }

  public Node Right() {
    return this.child[2];
  }

  public void setLeft(Node node) {
    this.child[0] = node;
  }

  public void setMiddle(Node node) {
    this.child[1] = node;
  }

  public void setRight(Node node) {
    this.child[2] = node;
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
   * Checks to see which range the value is in so we know
   *  which path to traverse
   * @param value
   * @return -1, 0, or 1 depending on what range value is in
   * value < data[0] -> -1
   * value >= data[0] and value < data[1] -> 0
   * value >= data[1] -> 2
   */
  public int compare(int value) {
    int result;
    if(value < data[0]) {
      result = -1;
    }
    else if(value >= data[0] && value < data[1]) {
      result = 0;
    }
    else {
      result = 1;
    }
    return result;
  }

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

  @Override
  public boolean contain(int value) {
    if(data[0] == value || data[1] == value) {
      return true;
    }
    return false;
  }
}
