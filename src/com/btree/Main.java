/**
 * Brandon Bauley 2018
 * This program builds a 2-3 Balanced Tree
 *  until the user wants to quit
 */

package com.btree;

public class Main {

    public static void main(String[] args) {
        BTree tree = new BTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(5);
        tree.insert(60);
        tree.insert(70);
        tree.insert(40);
        tree.insert(50);
        tree.display();
    }
}
