/**
 * One of the three states a node can be in.
 * This state means that this node will have
 *  UP to 2 data items and MUST have 3 children.
 */


package com.btree.com.node;

public class TwoValues extends Node{

  private int data[];
  private Node child[];

  TwoValues() {
    data = new int[2];
    data[0] = data[1] = 0;
    child = new Node[3];
    for(int i = 0; i < 3; ++i) {
      child[i] = null;
    }
  }

}
