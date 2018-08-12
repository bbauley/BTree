/**
 * This class works with classical functions you would use on a tree
 *  - insert
 *  - display
 *  - search
 */

package com.btree;

import com.btree.com.node.*;

public class BTree {

  private Node root;

  public BTree() { this.root = null; }

  /**
   * Adds a new node in the tree if its empty
   * Otherwise it will call the recursive private
   *  function
   * @param value
   * @return 1 for success
   */
  public int insert(int value) {

    if(root == null) {
      root = new Leaf(value);
      return 1;
    }

    boolean merge[] = new boolean[1];
    merge[0] = false;
    root = insert(root, value, merge);
    return 1;
  }

  /**
   * Recursively traverses to insert a new value into a leaf node
   * If a leaf node is full, it will split and push up the middle
   *  data item to the parent node
   * @param root
   * @param value
   * @return The current node that is being looked at
   */
  private Node insert(Node root, int value, boolean merge[]) {

    //Current node is a leaf
    if(root instanceof Leaf) {
      int success = root.add(value);
      //If we cannot add at leaf, we need to split and push up
      //  middle data item
      if (success != 1) {
        int middle = root.findMiddle(value);
        OneValue parent = new OneValue(middle);
        if(value < middle) {
          parent.setLeft(new Leaf(value));
          parent.setRight(new Leaf(root.getData(1)));
        }
        else if(value > middle) {
          parent.setLeft(new Leaf(root.getData(0)));
          parent.setRight(new Leaf(value));
        }
        else {
          parent.setLeft(new Leaf(root.getData(0)));
          parent.setRight(new Leaf(root.getData(1)));
        }
        merge[0] = true;
        return parent;
      }
    }
    //Current node is a parent with only one value
    //  and two children
    else if(root instanceof OneValue) {
      //Traverse left or right depending on if nodes
      //  data is smaller or greater than value
      switch(root.compare(value)) {
        case 1:
          root.setRight(insert(root.Right(), value, merge));
          if(merge[0] == true) {
            TwoValues newParent = new TwoValues(root.getData(), root.Right().getData());
            newParent.setLeft(instanceType(root.Left()));
            newParent.setMiddle(instanceType(root.Right().Left()));
            newParent.setRight(instanceType(root.Right().Right()));
            merge[0] = false;
            return newParent;
          }
          break;
        case -1:
          root.setLeft(insert(root.Left(), value, merge));
          if(merge[0] == true) {
            TwoValues newParent = new TwoValues(root.Left().getData(), root.getData());
            newParent.setLeft(instanceType(root.Left().Left()));
            newParent.setMiddle(instanceType(root.Left().Right()));
            newParent.setRight(instanceType(root.Right()));
            merge[0] = false;
            return newParent;
          }
          break;
      }
    }
    //Current node is a parent with two values
    // and three children
    else if(root instanceof TwoValues) {
      switch(root.compare(value)) {
        case -1:
          root.setLeft(insert(root.Left(), value, merge));
          if(merge[0] == true) {
            OneValue newParent = new OneValue(root.getData(0));
            newParent.setLeft(new OneValue(root.Left().getData()));
            newParent.setRight(new OneValue(root.getData(1)));
            newParent.Left().setLeft(instanceType(root.Left().Left()));
            newParent.Left().setRight(instanceType(root.Left().Right()));
            newParent.Right().setLeft(instanceType(root.Middle()));
            newParent.Right().setRight(instanceType(root.Right()));
            merge[0] = false;
            return newParent;
          }
          break;
        case 0:
          root.setMiddle(insert(root.Middle(), value, merge));
          if(merge[0] == true) {
            OneValue newParent = new OneValue(root.Middle().getData());
            newParent.setLeft(new OneValue(root.getData(0)));
            newParent.setRight(new OneValue(root.getData(1)));
            newParent.Left().setLeft(instanceType(root.Left()));
            newParent.Left().setRight(instanceType(root.Middle().Left()));
            newParent.Right().setLeft(instanceType(root.Middle().Right()));
            newParent.Right().setRight(instanceType(root.Right()));
            merge[0] = false;
            return newParent;
          }
          break;
        case 1:
          root.setRight(insert(root.Right(), value, merge));
          if(merge[0] == true) {
            OneValue newParent = new OneValue(root.getData(1));
            newParent.setLeft(new OneValue(root.getData(0)));
            newParent.setRight(new OneValue(root.Right().getData()));
            newParent.Left().setLeft(instanceType(root.Left()));
            newParent.Left().setRight(instanceType(root.Middle()));
            newParent.Right().setLeft(instanceType(root.Right().Left()));
            newParent.Right().setRight(instanceType(root.Right().Right()));
            merge[0] = false;
            return newParent;
          }
          break;
      }
    }

    return root;
  }

  public void display() {
    if(root == null) {
      return;
    }
    display(this.root);
  }

  //Debugging purposes
  //Preorder traversal
  private void display(Node root) {
    root.display();
    if(root instanceof OneValue) {
      OneValue parent = (OneValue) root;
      display(parent.Left());
      display(parent.Right());
    }
    else if(root instanceof TwoValues) {
      TwoValues parent = (TwoValues) root;
      display(parent.Left());
      display(parent.Middle());
      display(parent.Right());
    }
  }

  /**
   * Helper function to clean up code in the insert function
   * Based on the type of object being passed in,
   *  it will return a new derived object of that type
   *  calling the correct copy constructor
   * @param copy
   * @return A new dervied object based off of the parameters object type
   */
  private Node instanceType(final Node copy) {
    if(copy instanceof Leaf) {
      return new Leaf((Leaf) copy);
    }
    else if(copy instanceof OneValue) {
      return new OneValue((OneValue) copy);
    }
    else if(copy instanceof TwoValues) {
      return new TwoValues((TwoValues) copy);
    }
    return null;
  }

}
