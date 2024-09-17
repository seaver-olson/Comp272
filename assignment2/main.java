
/**********************************************************
 *
 * Place your name / section number in the 'HW2.java' file
 * (NOT this file). This main routine is a driver routine for
 * testing the methods in the file 'HW1.java'.
 *
 * DO NOT MODIFY THIS FILE
 *
 *********************************************************/

public class Main {

  /*
   * Function main() should be used for your UNIT TESTING. Feel free to change
   * the code here in order to enhance your testing and / or add debugging code.
   * However, your program must pass the existing tests provided in order to
   * get 100%.
   */
  
  public static void main(String[] args) {
      int     assignmentScore = 0;
      boolean errorFlag = false;
      BinaryTree tree1 = new BinaryTree();
      BinaryTree tree2 = new BinaryTree();

      System.out.println("\nProgram test driver starting  ...");

      // Initial population of the binary tree
      tree1.insert(50);
      tree1.insert(55);
      tree1.insert(2);
      tree1.insert(15);
      tree1.insert(20);
      tree1.insert(0);
      tree1.insert(99);
      tree1.insert(3);
      tree1.insert(45);
      tree1.insert(8);

      // Validate nothing changed in HW2.java such that the tree was built incorrectly.
      String treeContents = tree1.preOrder();
      if ( ! treeContents.toString().equals("50 55 15 3 45 20 8 2 0 99 ") ) {
          System.out.println("HW2.java was modified resulting incorrectly constructing the tree!");
          return;
      }

      /**************************************
       *
       * Testing of the replaceValue method
       *
       ***************************************/

      // Replace values in the binary tree constructed above.

      tree1.replaceValue(3, -3);
      tree1.replaceValue(0, -11);
      tree1.replaceValue(2, 99);

      // Validate the tree was updated correctly.
      treeContents = tree1.preOrder();
      if ( ! treeContents.toString().equals("50 55 15 -3 45 20 8 99 -11 99 ") ) {
          System.out.println("Failure (1): replaceValue method failed testing!");
          errorFlag = true;
      }

      // Adjust assignment score
      if ( ! errorFlag )
          assignmentScore += 25;
      else
          errorFlag = false;


      /**************************************
       *
       * Testing of the findMin method
       *
       ***************************************/

      // find the minimum value from tree1 used in previous test, should be -11
      if ( tree1.findMin() != -11 ) {
          System.out.println("Failure (2): findMin method failed testing!");
          errorFlag = true;
      }

      // Construct a second binary tree, and use that for testing as well
      tree2.insert(5);
      tree2.insert(-6);
      tree2.insert(-24);
      tree2.insert(32);
      tree2.insert(21);
      tree2.insert(12);

      // find the minimum value from tree that was just created, should be -24
      if ( tree2.findMin() != -24 ) {
          System.out.println("Failure (3): findMin method failed testing!");
          errorFlag = true;
      }

      // Adjust assignment score
      if ( ! errorFlag )
          assignmentScore += 25;
      else
          errorFlag = false;


      /**************************************
       *
       * Testing of the nodesGT method
       *
       ***************************************/

      // find the number of nodes in tree 1 having a data value > 20, should be 5
      if ( tree1.nodesGT(20) != 5 ) {
          System.out.println("Failure (4): nodesGT method failed testing!");
          errorFlag = true;
      }

      // find the number of nodes in tree 2 having a data value > 20, should be 2
      if ( tree2.nodesGT(20) != 2 ) {
          System.out.println("Failure (5): nodesGT method failed testing!");
          errorFlag = true;
      }

      // Adjust assignment score
      if ( ! errorFlag )
          assignmentScore += 25;
      else
          errorFlag = false;


      /**************************************
       *
       * Testing of the average method
       *
       ***************************************/

      // find the number of nodes in tree 1 having a data value > 20, should be 5
      if ( tree1.average() != 37.7 ) {
          System.out.println("Failure (6): nodesGT method failed testing!");
          errorFlag = true;
      }

      // find the number of nodes in tree 2 having a data value > 20, should be 2
      if ( tree2.average() != 6.666666666666667 ) {
          System.out.println("Failure (7): nodesGT method failed testing!");
          errorFlag = true;
      }

      // Adjust assignment score
      if ( ! errorFlag )
          assignmentScore += 25;
      else
          errorFlag = false;


      System.out.println("\nFinal assignment score is " + assignmentScore );

  } // end main()

} // end class Main()
