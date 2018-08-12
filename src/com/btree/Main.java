/**
 * Brandon Bauley 2018
 * This program builds a 2-3 Balanced Tree
 *  until the user wants to quit
 */

package com.btree;

import java.util.Scanner;

public class Main {

  private static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    BTree tree = new BTree();
    Main.homeScreen();
    int user_input = input.nextInt();
    while(user_input != 4) {
      int value = 0;
      switch(user_input) {
        case 1:
          System.out.println("What value would you like to insert?");
          value = input.nextInt();
          tree.insert(value);
          break;
        case 2:
          System.out.println("What value would you like to search for?");
          value = input.nextInt();
          boolean found = tree.search(value);
          if(found) {
            System.out.println(value + " is in the tree!");
          }
          else {
            System.out.println((value + " not found in the tree"));
          }
          break;
        case 3:
          tree.display();
          break;
        default:
          System.out.println("Please enter a number inbetween 1-4 inclusive");
      }
      if(user_input != 4) {
        Main.homeScreen();
        user_input = input.nextInt();
      }
    }
  }

  public static void homeScreen() {
    System.out.println("What would you like to do with the 2-3 Tree?");
    System.out.println("1. Insert a value");
    System.out.println("2. Search for a number");
    System.out.println("3. Display tree");
    System.out.println("4. Exit");
  }

}
