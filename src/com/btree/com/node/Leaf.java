/**
 * One of the states that a node can be in.
 * A leaf can have one or two data values.
 * The leaves children will ALWAYS be null,
 *  so there is no need to add them as data members
 */

package com.btree.com.node;

public class Leaf extends Node{

  private int data[];

  Leaf() {
    data = new int[2];
    data[0] = data[1] = 0;
  }
}
