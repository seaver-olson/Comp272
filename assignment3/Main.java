
/**********************************************************
 *
 * Homework # 3 (Programming Assignment). This assignment has two parts, the first
 * being that you must complete select method(s) for an AVL Tree object provided
 * in file AVLTree.java. Second,is solving simple collection problems in file
 * TreeProblems.java. Specifically, you will be using the TreeSet and TreeMap Java
 * Collection Framework library.
 *
 * This main routine is a driver  for testing the methods that you wrote within
 * the files 'AVLTree.java' and 'TreeProblems.java'. Your work will need to pass all
 * these tests for 100%
 *
 *             *** DO NOT MANIPULATE / CHANGE THIS FILE ***
 *
 *********************************************************/

import java.util.*;

public class Main {
    public static void main(String[] args) {

        LUC_AVLTree tree = new LUC_AVLTree();
        int     assignmentScore = 0;
        boolean avlErrorFlag = false;
        boolean removeEvenErrorFlag = false;
        boolean differentErrorFlag = false;
        boolean equalsErrorFlag = false;
        String  treeContents;


        System.out.println("Starting automated testing ...");

        /*****************************************************************
         *
         * TEST 1 - AVL Tree: Deletions which do NOT cause a tree rotation.
         *
         *****************************************************************/

        tree.removeAll();
        tree.insert(50);
        tree.insert(10);
        tree.insert(55);
        tree.insert(53);
        tree.insert(25);
        tree.insert(60);
        tree.insert(65);
        tree.insert(5);
        tree.insert(1);
        treeContents = tree.preorderTraversal();

        // Validate Insertion worked correctly.
        if ( ! treeContents.toString().equals("50 10 5 1 25 55 53 60 65 ") ) {
            System.out.println("AVL TEST 1 - Failure (1): building tree properly failed!");
            avlErrorFlag = true;
        }

        // Delete a leaf node, no rotation caused.
        tree.delete(1);
        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("50 10 5 25 55 53 60 65 ") ) {
            System.out.println("AVL TEST 1 - Failure (2): deleting leaf failed!");
            avlErrorFlag = true;
        }

        // Delete another leaf node, no rotation caused.
        tree.delete(65);
        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("50 10 5 25 55 53 60 ") ) {
            System.out.println("AVL TEST 1 - Failure (3): deleting leaf failed!");
            avlErrorFlag = true;
        }

        // Delete root node, no rotation caused, but next minimum value node brought to root
        tree.delete(50);
        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("53 10 5 25 55 60 ") ) {
            System.out.println("AVL TEST 1 - Failure (4): deleting root failed!");
            avlErrorFlag = true;
        }

        // Delete a leaf node, no rotation caused.
        tree.delete(5);
        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("53 10 25 55 60 ") ) {
            System.out.println("AVL TEST 1 - Failure (5): deleting leaf failed!");
            avlErrorFlag = true;
        }

        // Delete an interior node, no rotation caused, next minimum value node replaces it
        tree.delete(10);
        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("53 25 55 60 ") ) {
            System.out.println("AVL TEST 1 - Failure (6): deleting interior failed!");
            avlErrorFlag = true;
        }

        /*****************************************************************
         *
         * TEST 2 - AVL Tree: Deletions causing a RR Rotation.
         *
         *****************************************************************/

        tree.removeAll();
        tree.insert(50);
        tree.insert(10);
        tree.insert(55);
        tree.insert(53);
        tree.insert(25);
        tree.insert(60);
        tree.insert(65);
        tree.insert(5);
        tree.insert(1);
        tree.delete(53); // Deletion will cause a RR rotation

        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("50 10 5 1 25 60 55 65 ") ) {
            System.out.println("AVL TEST 2 - Failure: Delete causing RR rotation failed!");
            avlErrorFlag = true;
        }

        /*****************************************************************
         *
         * TEST 3 - AVL Tree: Deletions causing a LL Rotation.
         *
         *****************************************************************/

        tree.removeAll();
        tree.insert(50);
        tree.insert(10);
        tree.insert(55);
        tree.insert(53);
        tree.insert(25);
        tree.insert(60);
        tree.insert(65);
        tree.insert(5);
        tree.insert(1);
        tree.delete(25); // Deletion will cause a LL rotation

        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("50 5 1 10 55 53 60 65 ") ) {
            System.out.println("AVL TEST 3 - Failure: Delete causing LL rotation failed!");
            avlErrorFlag = true;
        }

        /*****************************************************************
         *
         * TEST 4 - AVL Tree: Deletions causing a RL Rotation.
         *
         *****************************************************************/

        tree.removeAll();
        tree.insert(50);
        tree.insert(10);
        tree.insert(55);
        tree.insert(53);
        tree.insert(25);
        tree.insert(60);
        tree.insert(57);
        tree.insert(5);
        tree.insert(6);
        tree.delete(53); // Deletion will cause a RL rotation

        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("50 10 5 6 25 57 55 60 ") ) {
            System.out.println("AVL TEST 4 - Failure (1): Delete causing RL rotation failed!");
            avlErrorFlag = true;
        }


        tree.removeAll();
        tree.insert(45);
        tree.insert(40);
        tree.insert(60);
        tree.insert(35);
        tree.insert(65);
        tree.insert(53);
        tree.insert(52);
        tree.delete(35); // Deletion will cause a RL Rotation

        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("53 45 40 52 60 65 ") ) {
            System.out.println("AVL TEST 4 - Failure (2): Delete causing RL rotation failed!");
            avlErrorFlag = true;
        }


        /*****************************************************************
         *
         * TEST 5 - AVL Tree: Deletions causing a LR Rotation.
         *
         *****************************************************************/

        tree.removeAll();
        tree.insert(50);
        tree.insert(10);
        tree.insert(55);
        tree.insert(53);
        tree.insert(25);
        tree.insert(60);
        tree.insert(57);
        tree.insert(5);
        tree.insert(6);
        tree.delete(25); // Deletion will cause a LR rotation

        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("50 6 5 10 55 53 60 57 ") ) {
            System.out.println("AVL TEST 5 - Failure (1): Delete causing LR rotation failed!");
            avlErrorFlag = true;
        }


        tree.removeAll();
        tree.insert(45);
        tree.insert(40);
        tree.insert(50);
        tree.insert(51);
        tree.insert(35);
        tree.insert(41);
        tree.insert(42);
        tree.delete(51); // Deletion will cause a LR Rotation

        treeContents = tree.preorderTraversal();
        if ( ! avlErrorFlag && ! treeContents.toString().equals("41 40 35 45 42 50 ") ) {
            System.out.println("AVL TEST 5 - Failure (2): Delete causing LR rotation failed!");
            avlErrorFlag = true;
        }


        /*
         * Tree Problem Testing
         *
         * Automated tests for testing the Collections Framework Library
         * classes.
         *
         */

        /*****************************************************************
         *
         * TEST 6 - Tree Problem Testing - TreeMap, RemoveEven testing
         *
         *****************************************************************/

        TreeProblems problem = new TreeProblems();
        int removeEvenCount = 0;

        Map<Integer, String> ages = new TreeMap<>();
        ages.put(10, "James");
        ages.put(13, "Tony");
        ages.put(15, "Britany");
        ages.put(16, "Maria");
        ages.put(17, "Geoffrey");

        problem.removeEven(ages);

        Iterator<Map.Entry<Integer, String>> iterator = ages.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if ( entry.getKey() % 2 == 0 )
                removeEvenErrorFlag = true;
            else
                removeEvenCount++;
        }



        /*****************************************************************
         *
         * TEST 7 - Tree Problem Testing - TreeSet, Difference testing
         *
         *****************************************************************/

        Set<Integer> treeSet0 = new TreeSet<>();
        treeSet0.add(9);
        treeSet0.add(7);
        treeSet0.add(6);
        treeSet0.add(3);

        Set<Integer> treeSet1 = new TreeSet<>();
        treeSet1.add(4);
        treeSet1.add(7);
        treeSet1.add(6);
        treeSet1.add(2);

        // Iterate through the difference set, it should only contain the set
        // [2, 3, 4, 9], if missing any, and or any additional then failed.

        int differentCount = 0;
        for (Integer value : problem.different(treeSet0, treeSet1)) {
            switch (value) {
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 9:
                    break;
                default:
                    differentErrorFlag = true;
            }
            differentCount++;
        }


        /*****************************************************************
         *
         * TEST 8 - Tree Problem Testing - TreeMap, equal testing
         *
         *****************************************************************/

        Map<Integer, String> tree1 = new TreeMap<>();
        tree1.put(10, "James");
        tree1.put(13, "Tony");
        tree1.put(15, "Britany");
        tree1.put(16, "Maria");
        tree1.put(17, "Geoffrey");

        Map<Integer, String> tree2 = new TreeMap<>();
        tree2.put(16, "Maria");
        tree2.put(17, "Geoffrey");
        tree2.put(10, "James");
        tree2.put(13, "Tony");

        if ( ! equalsErrorFlag && problem.treesEqual(tree1, tree2) ) {
            System.out.println("TreeMap TEST 8 - Failure (1): equals tests failed!");
            equalsErrorFlag = true;
        }

        tree2.put(15, "Britany");

        if ( ! equalsErrorFlag && ! problem.treesEqual(tree1, tree2) ) {
            System.out.println("TreeMap TEST 8 - Failure (2): equals tests failed!");
            equalsErrorFlag = true;
        }


        /*
         * All automated tests complete, calculate and display final score
         */

        // Adjust assignment score for AVL testing
        if ( ! avlErrorFlag ) {
            assignmentScore += 20;
            System.out.println("Automated AVL tests terminated                -- tests PASSED");
        } else {
            System.out.println("Automated AVL tests terminated                -- tests FAILED ***");
        }

        // Adjust assignment score for TreeMap removeEven testing
        if ( ! removeEvenErrorFlag && removeEvenCount == 3 ) {
            assignmentScore += 25;
            System.out.println("Automated TreeMap removeEven tests terminated -- tests PASSED");
        } else {
            System.out.println("Automated TreeMap removeEven tests terminated -- tests FAILED ***");
        }

        // Adjust assignment score for TreeMap removeEven testing
        if ( ! differentErrorFlag && differentCount == 4 ) {
            assignmentScore += 30;
            System.out.println("Automated TreeSet difference tests terminated -- tests PASSED");
        } else {
            System.out.println("Automated TreeSet difference tests terminated -- tests FAILED ***");
        }

        // Adjust assignment score for TreeMap equals testing
        if ( ! equalsErrorFlag ) {
            assignmentScore += 25;
            System.out.println("Automated TreeMap equals tests terminated     -- tests PASSED");
        } else {
            System.out.println("Automated TreeMap equals tests terminated     -- tests FAILED ***");
        }

        System.out.println("\nAssignment score is: " + assignmentScore);
        return;
    }
}