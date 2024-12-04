
/**********************************************************
 *
 * Homework # 8 (Programming Assignment). This assignment has several parts.
 *
 * The first part, you are to implement the findRoot method in the Graph class. This
 * method should determine the "root" of the graph, defined as the vertex with no
 * incoming edges. The method should return the ‘value’ of the root vertex. If there
 * is no such unique vertex, or more than one root vertex is found, then return -1.
 *
 * The second part is a problem solution required for a method that will ensure
 * that any student pursuing the certificate of 'master programmer', can complete 'n'
 * certification exams, each being specific to a topic. In this case, some exams of
 * these exams may have prerequisites of needing to take and pass earlier certificate
 * exams. You do not want to force a student in taking specific orders the exams, but
 * you want to make sure that at least one order that can be possible and taken.
 *
 * The third part is a problem solution required to be implemented for a method that
 * that will return the number of groups of connected friends. In this case, you are
 * given 'n' people. Some of them are connected as friends forming groups. Not all
 * these groups of friends are interconnected, meaning that there are 1 or more groups
 * non-interconnected groups of friends. The problem is to determine teh number of groups.
 *
 *             *** DO NOT MANIPULATE / CHANGE THIS FILE ***
 *
 *********************************************************/


 public class Main {

    public static void main(String[] args) {
  
      boolean graphRootFailure = false;
      boolean masterProgrammerFail = false;
      boolean numGroupsFail = false;
      int score = 0;
  
      /***************************************************************
       *
       *  Testing the graph traversal locating the root node
       *
       ***************************************************************/
  
      Graph graph = new Graph(7);   // Graph with 7 nodes
  
      // Add vertices with associated values
      graph.setValue(0, 10);
      graph.setValue(1, 20);
      graph.setValue(2, 30);
      graph.setValue(3, 40);
      graph.setValue(4, 50);
      graph.setValue(5, 60);
      graph.setValue(6, 70);
  
      // Add directed edges 
      graph.addEdge(3, 4);
      graph.addEdge(4, 5);
      graph.addEdge(4, 2);
      graph.addEdge(2, 5);
      graph.addEdge(5, 1);
      graph.addEdge(1, 0);
      graph.addEdge(1, 2);
  
  
      // The following should fail, there are two vertices qualifying as root vertex
      int rootValue = graph.findRoot();
      if (rootValue != -1) {
        graphRootFailure = true;
        System.out.println("Graph Traversal error 1: Root vertex returned: " + rootValue);
      }
  
      // Add an edge, so now only one root vertex
      graph.addEdge(0, 6);
      
      rootValue = graph.findRoot();
      if (!graphRootFailure && rootValue != 40) {
        graphRootFailure = true;
        System.out.println("Graph Traversal error 2: Root vertex returned: " + rootValue);
      }
  
      // Add an edge, so now no root vertex
      graph.addEdge(4, 3);
  
      rootValue = graph.findRoot();
      if (!graphRootFailure && rootValue != -1) {
        graphRootFailure = true;
        System.out.println("Graph Traversal error 3: Root vertex returned: " + rootValue);
      }
  
  
      /***************************************************************
       *
       *  Testing the Master Programmer solution problem
       *
       ***************************************************************/
  
      ProblemSolutions ps = new ProblemSolutions();
  
      /*
       * Defining 2-dim arrays containing directed edges for a graphs. These
       * edges represent prerequisites for taking certification exams. If the
       * 2-dim array is empty, there are no prerequisites irrespective of how
       * large the graph is.
       */
  
      // Prereq edges for graph g1 - 1 prereq (graph will have 2 nodes)
      int[][] g1 = new int[][]{{0, 1}};
  
      // Prereq edges for graph g2 - 2 prereqs (graph will have 2 nodes)
      int[][] g2 = new int[][]{{1, 0}, {0, 1}};
  
      // Prereq edges for graph g3 - 2 prereqs (graph will have 4 nodes)
      int[][] g3 = new int[][]{{1, 0}, {3, 1}};
  
      if (! ps.canFinish(2, g1)) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 1");
      }
  
      if (! masterProgrammerFail && ps.canFinish(2, g2)) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 2");
      }
  
      if ( ! masterProgrammerFail && ! ps.canFinish(4, g3) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 3");
      }
  
      /*
       * Define additional graphs with and without cycles in these directed graphs.
       */
  
      int[][] g4 = new int[][] { }; // no prereqs
      int[][] g5 = new int[][] { {1,0}, {3,1}, {3,0} };
      int[][] g6 = new int[][] { {1,0}, {3,1}, {0,3} };
      int[][] g7 = new int[][] { {10,1}, {12,2}, {0,3}, {6,3}, {8,7}, {4,5}, {9,11} };
      int[][] g8 = new int[][] { {10,1}, {12,2}, {0,3}, {6,3}, {8,7}, {4,5}, {9,11},
                                 {7,4}, {5,8}};
  
      if ( ! masterProgrammerFail && ! ps.canFinish(2, g4) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 4");
      }
  
      if ( ! masterProgrammerFail && ! ps.canFinish(15, g4) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 5");
      }
  
      if ( ! masterProgrammerFail && ! ps.canFinish(4, g5) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 6");
      }
  
      if ( ! masterProgrammerFail && ! ps.canFinish(20, g5) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 7");
      }
  
      if ( ! masterProgrammerFail && ps.canFinish(4, g6) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 8");
      }
  
      if ( ! masterProgrammerFail && ! ps.canFinish(13, g7) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 9");
      }
  
      if ( ! masterProgrammerFail && ps.canFinish(13, g8) ) {
        masterProgrammerFail = true;
        System.out.println("Master programmer error 10");
      }
  
  
      /***************************************************************
       *
       *  Testing the number of groups solution problem
       *
       ***************************************************************/
  
      int[][] adjMatrix1 = new int[][] { {0,1,0},
              {1,0,0},
              {0,0,0} };
      int[][] adjMatrix2 = new int[][] { {0,0,0},
              {0,0,0},
              {0,0,0} };
      int[][] adjMatrix3 = new int[][] { {0,1,0},
              {1,0,0},
              {0,1,0} };
  
      if ( ! numGroupsFail && ps.numGroups(adjMatrix1) != 2 ) {   // should be 2
        numGroupsFail = true;
        System.out.println("Number of Groups error 1");
      }
  
      if ( ! numGroupsFail && ps.numGroups(adjMatrix2) != 3 ) {   // should be 3
        numGroupsFail = true;
        System.out.println("Number of Groups error 2");
      }
  
      if ( ! numGroupsFail && ps.numGroups(adjMatrix3) != 1 ) {   // should be 1
        numGroupsFail = true;
        System.out.println("Number of Groups error 3");
      }
  
  
      int[][] adjMatrix4 = new int[][] { {0,1,0,0},
              {1,0,0,0},
              {0,0,0,0},
              {0,0,0,0} };
      int[][] adjMatrix5 = new int[][] { {0,0,0,0},
              {0,0,0,0},
              {0,0,0,0},
              {0,0,0,0} };
      int[][] adjMatrix6 = new int[][] { {0,1,0,0},
              {1,0,0,0},
              {0,1,0,0},
              {1,0,0,0} };
  
      if ( ! numGroupsFail && ps.numGroups(adjMatrix4) != 3 ) {   // should be 3
        numGroupsFail = true;
        System.out.println("Number of Groups error 4");
      }
  
      if ( ! numGroupsFail && ps.numGroups(adjMatrix5) != 4 ) {   // should be 4
        numGroupsFail = true;
        System.out.println("Number of Groups error 5");
      }
  
      if ( ! numGroupsFail && ps.numGroups(adjMatrix6) != 1 ) {   // should be 1
        numGroupsFail = true;
        System.out.println("Number of Groups error 6");
      }
  
      
      /***************************************************************
       *
       *  Calculate the final score
       *
       ***************************************************************/
  
      if ( ! graphRootFailure ) {
        score += 33;
        System.out.println("Graph traversal locating root - PASSED");
      } else {
        System.out.println("Graph traversal locating root - *** FAILED ***");
      }
  
      if ( ! masterProgrammerFail ) {
        score += 33;
        System.out.println("Master programmer problem     - PASSED");
      } else {
        System.out.println("Master programmer problem     - *** FAILED ***");
      }
  
      if ( ! numGroupsFail ) {
        score += 34;
        System.out.println("Number of groups problem      - PASSED");
      } else {
        System.out.println("Number of groups problem      - *** FAILED ***");
      }
  
      System.out.println("\nTotal Score is: " + score);
    }
  }