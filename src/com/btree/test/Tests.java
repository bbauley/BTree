package com.btree.test;

import com.btree.BTree;
import com.btree.com.node.Leaf;
import com.btree.com.node.OneValue;
import com.btree.com.node.TwoValues;

import org.junit.Test;

public class Tests {

  @Test
  public void test_numbers_asc_order() {
    Leaf leaf = new Leaf(20);
    leaf.add(10);
    assert(leaf.getData(0) < leaf.getData(1));
  }

  @Test
  public void test_leaf_full() {
    Leaf leaf = new Leaf(20);
    leaf.add(15);
    int result = leaf.add(3);
    assert(result == 0);
  }

  @Test
  public void test_Split_Into_OneValue() {
    BTree tree = new BTree();
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    assert(tree.getRoot().getData() == 20);
    assert(tree.getRoot().Left().getData(0) == 10);
    assert(tree.getRoot().Right().getData(0) == 30);
  }

  @Test
  public void test_add_at_leaf() {
    BTree tree = new BTree();
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(40);
    tree.insert(5);
    assert(tree.getRoot().Left().getData(1) == 10);
    assert(tree.getRoot().Right().getData(1) == 40);
  }

  @Test
  public void test_Split_Into_TwoValues() {
    BTree tree = new BTree();
    tree.insert(10);
    tree.insert(20);
    tree.insert(30);
    tree.insert(40);
    tree.insert(5);
    tree.insert(1);
    assert(tree.getRoot().getData(0) == 5);
    assert(tree.getRoot().getData(1) == 20);
    assert(tree.getRoot().Left().getData(0) == 1);
    assert(tree.getRoot().Left().getData(1) == 5);
    assert(tree.getRoot().Middle().getData(0) == 10);
    assert(tree.getRoot().Right().getData(0) == 30);
    assert(tree.getRoot().Right().getData(1) == 40);
  }

  @Test
  public void test_Split_Multiple_Merges() {
    BTree tree = new BTree();
    for(int i = 10; i < 80; i += 10) {
      tree.insert(i);
    }
    assert(tree.getRoot().getData() == 40);
    assert(tree.getRoot().Left().getData() == 20);
    assert(tree.getRoot().Right().getData() == 60);
    assert(tree.getRoot().Left().Left().getData() == 10);
    assert(tree.getRoot().Left().Right().getData() == 30);
    assert(tree.getRoot().Right().Left().getData() == 50);
    assert(tree.getRoot().Right().Right().getData() == 70);
  }

  @Test
  public void test_Search() {
    BTree tree = new BTree();
    for(int i = 10; i < 80; i += 10) {
      tree.insert(i);
    }
    assert(tree.search(20) == true);
    assert(tree.search(100) == false);
  }

  @Test
  public void test_Contains() {
    Leaf leaf = new Leaf(10);
    leaf.add(20);
    assert(leaf.contain(40) == false);
    OneValue parent1 = new OneValue(20);
    assert(parent1.contain(20) == true);
    TwoValues parent2 = new TwoValues(40, 60);
    assert(parent2.contain(40) == true);
  }
  @Test
  public void test_OneValue_Compare() {
    OneValue parent = new OneValue(20);
    assert(parent.compare(10) == -1);
  }

  @Test
  public void test_TwoValues_Compare() {
    TwoValues parent = new TwoValues(10, 30);
    assert(parent.compare(20) == -1);
  }
}
