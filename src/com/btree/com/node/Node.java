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

  Node(){}

}
