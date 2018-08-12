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
   *  alias root node to traverse through the tree
   * @param value
   *  value being added to the list
   * @param merge
   *  flag to tell us when we need to split and push up
   *
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
      switch(root.compare(value)) {
        case 1:
          root.setRight(insert(root.Right(), value, merge));
          //Check if roots right child is trying to merge
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
          //Check if roots left child is trying to merge
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

  /**
   * Wrapper function for the recursive display function
   */
  public void display() {
    if(root == null) {
      return;
    }
    display(this.root);
  }

  /**
   * Recursively displays the tree in pre order traversal
   * This function is more of a debugging tool
   * @param root
   */
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

  /**
   * Searches for a value in the 2-3 tree
   * @param value
   * @return success or failure for finding a value in the tree
   */
  public boolean search(int value) {

    if(this.root == null) {
      return false;
    }

    return search(this.root, value);
  }

  /**
   * Recursive function to traverse through the tree and find
   *  the value passed in as a parameter
   * @param root
   * @param value
   * @return success or failure for finding a value in the tree
   */
  private boolean search(Node root, int value) {

    boolean result = root.contain(value);
    if(root instanceof Leaf) {
      return result;
    }
    else if(root instanceof OneValue) {
      result |= search(root.Left(), value) || search(root.Right(), value);
    }
    else if(root instanceof TwoValues) {
      result |= search(root.Left(), value) || search(root.Middle(), value) ||
                search(root.Right(), value);
    }
    return result;
  }


  /**
   * ONLY use is for unit tests to make sure that tree is splitting correctly
   *  after inserts
   * @return The root node
   */
  public Node getRoot() {
    return root;
  }

}
