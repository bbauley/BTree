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

  public OneValue() {
    this.data = 0;
    this.left = this.right = null;
  }

  public OneValue(int value) {
    this.data = value;
    this.left = this.right = null;
  }

  public OneValue(final OneValue copy) {
    this.data = copy.data;
    this.left = copy.left;
    this.right = copy.right;
  }


  public Node Left() {
    return this.left;
  }

  public Node Right() {
    return this.right;
  }

  public void setLeft(Node node) {
    this.left = node;
  }

  public void setRight(Node node) {
    this.right = node;
  }

  @Override
  public void display() {
    System.out.println(this.data);
  }

  /**
   * This function checks to see if the nodes data is larger
   *  or smaller than the value being passed in
   * @param value
   * @return -1 if the value is smaller than the nodes data and
   *          1 if the value is bigger than the nodes data
   */
  public int compare(int value) {

    return (value < this.data) ? -1 : 1;
  }

  public int getData() {
    return data;
  }

}
