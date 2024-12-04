/******************************************************************
 *
 *   Seaver Olson / 002
 *
 *   This java file contains the problem solutions of canFinish and
 *   numGroups methods.
 *
 ********************************************************************/

 import java.lang.reflect.Array;
import java.util.*;

 class ProblemSolutions {
 
     /**
      * Method canFinish
      *
      * You are building a course curriculum along with required intermediate
      * exams certifications that must be taken by programmers in order to obtain
      * a new certification called 'master programmer'. In doing so, you are placing
      * prerequisites on intermediate exam certifications that must be taken first.
      * You are allowing the flexibility of taking the exams in any order as long as
      * any exam prerequisites are satisfied.
      *
      * Unfortunately, in the past, your predecessors have accidentally published
      * curriculums and exam schedules that were not possible to complete due to cycles
      * in prerequisites. You want to avoid this embarrassment by making sure you define
      * a curriculum and exam schedule that can be completed.
      *
      * You goal is to ensure that any student pursuing the certificate of 'master
      * programmer', can complete 'n' certification exams, each being specific to a
      * topic. Some exams have prerequisites of needing to take and pass earlier
      * certificate exams. You do not want to force any order of taking the exams, but
      * you want to make sure that at least one order is possible.
      *
      * This method will save your embarrassment by returning true or false if
      * there is at least one order that can taken of exams.
      *
      * You wrote this method, and in doing so, you represent these 'n' exams as
      * nodes in a graph, numbered from 0 to n-1. And you represent the prerequisite
      * between taking exams as directed edges between two nodes which represent
      * those two exams.
      *
      * Your method expects a 2-dimensional array of exam prerequisites, where
      * prerequisites[i] = [ai, bi] indicating that you must take exam 'bi' first
      * if you want to take exam 'ai'. For example, the pair [0, 1], indicates that
      * to take exam certification '0', you have to first have the certification for
      * exam '1'.
      *
      * The method will return true if you can finish all certification exams.
      * Otherwise, return false (e.g., meaning it is a cyclic or cycle graph).
      *
      *     Example 1:
      *     Input: numExams = 2, prerequisites = [[1,0]]
      *     Output: true
      *     Explanation: There are a total of 2 exams to take.
      *     To take exam 1 you should have completed the
      *     certification of exam 0. So, it is possible (no
      *     cyclic or cycle graph of prereqs).
      *
      *
      *     Example 2:
      *     Input: numExams = 2, prerequisites = [[1,0],[0,1]]
      *     Output: false
      *     Explanation: There are a total of 2 exams to take.
      *     To take exam 1 you should have completed the
      *     certification of exam 0, and to take exams 0 you
      *     should also have completed the certification of exam
      *     1. So, it is impossible (it is a cycle graph).
      *
      * @param numExams          - number of exams, which will produce a graph of n nodes
      * @param prerequisites     - 2-dim array of directed edges.
      * @return boolean          - True if all exams can be taken, else false.
      */
 
     public boolean canFinish(int numExams, 
                              int[][] prerequisites) {
       
        int numNodes = numExams;  // # of nodes in graph
 
         // Build directed graph's adjacency list
        ArrayList<Integer>[] adj = getAdjList(numExams, prerequisites); 
        int[] edges = new int[numExams];
        for (int i=0; i <numExams; i++){
            for (int neighbor: adj[i]){
                edges[neighbor]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        //means no prerequisites
        for (int i=0; i < numExams; i++){
            if (edges[i] == 0){
                queue.add(i);
            }
        }
        int i =0;
        while (!queue.isEmpty()){
            int node= queue.poll();
            i++;
            for (int neighbor: adj[node]){
                edges[neighbor]--;
                if (edges[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }
        if (i == numExams){
            return true;
        }
        return false;
     }
 
 
     /**
      * Method getAdjList
      *
      * Building an Adjacency List for the directed graph based on number of nodes
      * and passed in directed edges.
      *
      * @param numNodes      - number of nodes in graph (labeled 0 through n-1) for n nodes
      * @param edges         - 2-dim array of directed edges
      * @return ArrayList<Integer>[]  - An adjacency list representing the provided graph.
      */
 
     private ArrayList<Integer>[] getAdjList(
             int numNodes, int[][] edges) {
 
         ArrayList<Integer>[] adj 
                     = new ArrayList[numNodes];      // Create an array of ArrayList ADT
 
         for (int node = 0; node < numNodes; node++){
             adj[node] = new ArrayList<Integer>();   // Allocate empty ArrayList per node
         }
         for (int[] edge : edges){
             adj[edge[0]].add(edge[1]);              // Add connected node edge [1] for node [0]
         }
         return adj;
     }
 
 
     /*
      * Assignment Graphing - Number of groups.
      *
      * There are n people. Some of them are connected
      * as friends forming a group. If person 'a' is
      * connected to person 'b', and person 'b' is
      * connected to person 'c', they form a connected
      * group.
      *
      * Not all groups are interconnected, meaning there
      * can be 1 or more groups depending on how people
      * are connected.
      *
      * This example can be viewed as a graph problem,
      * where people are represented as nodes, and
      * edges between them represent people being
      * connected. In this problem, we are representing
      * this graph externally as an non-directed
      * Adjacency Matrix. And the graph itself may not
      * be fully connected, it can have 1 or more
      * non-connected compoents (subgraphs).
      *
      * Example 1:
      *   Input :
          AdjMatrix = [[0,1,0], [1,0,0], [0,0,0]]
      *   Output: 2
      *   Explanation: The Adjacency Matrix defines an
      *   undirected graph of 3 nodes (indexed 0 to 2).
      *   Where nodes 0 and 1 aee connected, and node 2
      *   is NOT connected. This forms two groups of
      *   nodes.
      *
      * Example 2:
      *   Input : AdjMatrix = [ [0,0,0], [0,0,0], [0,0,0]]
      *   Output: 3
      *   Explanation: The Adjacency Matrix defines an
      *   undirected graph of 3 nodes (indexed 0 to 2).
      *   There are no connected nodes, hence forming
      *   three groups.
      *
      * Example 3:
      *   Input : AdjMatrix = [[0,1,0], [1,0,0], [0,1,0]]
      *   Output: 1
      *   Explanation, The adjacency Matrix defined an
      *   undirected graph of 3 nodes (index 0 to 2).
      *   All three nodes are connected by at least one
      *   edge. So they form on large group.
      */
 
     public int numGroups(int[][] adjMatrix) {
         int numNodes = adjMatrix.length;
         Map<Integer,List<Integer>> graph = new HashMap();
         int i = 0, j =0;
 
         /*
          * Converting the Graph Adjacency Matrix to
          * an Adjacency List representation. This
          * sample code illustrates a technique to do so.
          */
 
         for(i = 0; i < numNodes ; i++){
             for(j = 0; j < numNodes; j++){
                 if( adjMatrix[i][j] == 1 && i != j ){
                     // Add AdjList for node i if not there
                     graph.putIfAbsent(i, new ArrayList());
                     // Add AdjList for node j if not there
                     graph.putIfAbsent(j, new ArrayList());
 
                     // Update node i adjList to include node j
                     graph.get(i).add(j);
                     // Update node j adjList to include node i
                     graph.get(j).add(i);
                 }
             }
         }

        boolean[] visited = new boolean[numNodes];
        int count = 0;
        for (i=0; i<numNodes;i++){
            if (!visited[i]){
                DfSearch(i, visited, graph);
                count++;
            }
        }
        return count;
     }
    void DfSearch(int node, boolean[] visited, Map<Integer, List<Integer>> graph){
        visited[node] = true;//visited
        if (graph.get(node) == null){
            return;
        }
        for (int neighbor: graph.get(node)){
            if (!visited[neighbor]){
                DfSearch(neighbor, visited, graph);//recursion BS stuff here 
                //jk i love recursion please dont hurt me professor T_T
            }
        }
    }
 
 }