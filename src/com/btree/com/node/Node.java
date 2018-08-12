/**
 * A node in a 2-3 tree can be in three different "states"
 *  1. Leaf
 *    - Each leaf can have 1 or two data values
 *  2. Parent with one value
 *    - Must have two children
 *  3. Parent with two values
 *    - Must have three children
 *
 * This class is the base class for all the possible states of a node
 */


package com.btree.com.node;

abstract public class Node {

  protected final int DATA_ARRAY_SIZE = 2;
  protected final int CHILD_ARRAY_SIZE = 3;

  Node(){}

  //Methods that will only be called in Leaf state
  public int add(int value) {return 0;}
  public int findMiddle(int value) {return 0;}
  public int getData(int index) {return 0;}

  //Methods that will only be called in OneValue state
  public int getData() {return 0;}

  //Methods that will only be called in TwoValue state
  public Node Middle() {return null;}
  public void setMiddle(Node node) {}

  //Methods that will be called in OneValue and TwoValues states
  public Node Left() {return null;}
  public Node Right() {return null;}
  public void setLeft(Node node) {}
  public void setRight(Node node) {}
  public int compare(int value) {return 0;}


  //Methods that will be called in all three states
  public void display(){}

}
