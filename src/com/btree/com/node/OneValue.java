/**
 * One of the three states a node can be in.
 * This state means that this specific node
 *  will have one data value and two children.
 */

package com.btree.com.node;

public class OneValue extends Node{

  private int data;
  private Node left;
  private Node right;

  OneValue() {
    data = 0;
    left = right = null;
  }
}
