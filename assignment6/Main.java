/**********************************************************
 *
 * Homework # 6 (Programming Assignment). This assignment has several parts.
 *
 * The first part requires the implementation of the methods add() and contains()
 * within the provided PriorityObject class. This first portion requires a basic
 * understanding on an implementation of a min-heap to code these two methods.
 *
 * The second part requires is the Priority Game problem. This part provides
 * more practice with teh Java Collection Framework library's PriorityQueue Class.
 *
 * The third and forth parts are sorting problems. The first requires solving
 * removing duplicates from an ArrayList. The second sorting problem requires
 * solving various combinations of numbers that add up to some sum.
 *
 *             *** DO NOT MANIPULATE / CHANGE THIS FILE ***
 *
 *********************************************************/

 import java.util.ArrayList;
 import java.util.Arrays;
 
 public class Main {
     public static void main(String[] args) {
         int score = 0;
         boolean PriorityQueueFail = false;
         boolean PQGameFail = false;
         boolean Sorting1Fail = false;
         boolean Sorting2Fail = false;
 
 
         /*************************************************************
          *
          *  Test cases for the PriorityQueue Object.
          *
          *  Verify methods add() and contains() work correctly.
          *
          *************************************************************/
 
         PriorityQueue<String, Integer> pq = new PriorityQueue<>();
         PriorityQueue<String, Integer>.Node element;
 
         /*
          * Perform an initial queue population, utilizing both add() and offer().
          */
 
         pq.add("element2", 20);
         pq.add("element1", 10);
         pq.add("element3", 30);
         pq.offer("element4", 30);
         pq.offer("element5", 5);
 
         /*
          * Perform basic tests for peek() and contains().
          */
 
         if ( !pq.isEmpty() && pq.peek().value() != "element5" && pq.peek().priority() != 5 ) {
             PriorityQueueFail = true;
             System.out.println("Priority Queue Test 1 Failure, value: "
                                + pq.peek().value() + " priority: " + pq.peek().priority());
         }
 
         if ( ! PriorityQueueFail && !pq.contains("element3")) {
             PriorityQueueFail = true;
             System.out.println("Priority Queue Test 2 Failure");
         }
 
         if ( ! PriorityQueueFail && pq.contains("NOT IN QUEUE") ) {
             PriorityQueueFail = true;
             System.out.println("Priority Queue Test 3 Failure");
         }
 
 
         /*
          * Add a new element. Once added, change it's priority after adding.
          * After changing priority, it should be repositioned in the min heap.
          *
          * Only perform the change priority operation if no prior errors.
          */
 
         element = pq.add("element6", 45);        // Add as low priority
         if ( !pq.isEmpty() && ! PriorityQueueFail ) {
             element.changePriority(2);           // Change to highest priority
         } else {
             PriorityQueueFail = true;
         }
 
         if ( ! PriorityQueueFail && pq.peek().value() != "element6" && pq.peek().priority() != 2) {
             PriorityQueueFail = true;
             System.out.println("Priority Queue Test 4 Failure");
         }
 
 
         /*
          * Now change the element's, "element6", priority, which we have a handle to in the
          * queue, from the highest priority to a lower priority. It should be repositioned in
          * the min heap as a result.
          *
          * Only perform operation if no prior erros.
          */
 
         if ( ! PriorityQueueFail ) {
             element.changePriority(35);         // Re-update priority, change lower
         }
 
         /*
          * We now have 6 elements in the queue/ Based on each elements priority,
          * they should dequeue in this order, 5, 1, 2, 3, 4, and 6. Any other
          * order is not correct. The following will verify this order.
          */
 
         String[] verifyOrder1 = { "element5", "element1", "element2", "element3",
                                  "element4", "element6" };
 
         for (int i=0; !PriorityQueueFail && !pq.isEmpty() ; i++ ) {
             element = pq.remove();
 
             // There are only 6 elements in the queue, if more are returned, an error
             if ( i >= verifyOrder1.length ) {
                 PriorityQueueFail = true;
                 System.out.println("Priority Queue Test 5 Failure - too many elements");
                 break;
             }
 
             if ( element.value != verifyOrder1[i] ) {
                 PriorityQueueFail = true;
                 System.out.println("Priority Queue Test 6 Failure, queue returned: "
                                     + element.value() + " should be: " + verifyOrder1[i]);
                 break;
             }
         }
 
 
         /*
          * Queue is empty, now add and remove more elements. After the following
          * operations, the queue should contain [ (ghi,1), (abc,5), ], in that priority
          * order. We will verify this after the enqueue and dequeue operations.
          */
 
         String[] verifyOrder2 = { "ghi", "abc" };
 
         pq.add("abc", 5);
         pq.add("def", 2);
         pq.poll();
         pq.offer("ghi", 1);
 
         for (int i=0; !PriorityQueueFail && !pq.isEmpty() ; i++ ) {
             element = pq.remove();
 
             // There are only 2 elements in the queue, if more are returned, an error
             if ( i >= verifyOrder2.length ) {
                 PriorityQueueFail = true;
                 System.out.println("Priority Queue Test 7 Failure - too many elements");
                 break;
             }
 
             if ( element.value != verifyOrder2[i] ) {
                 PriorityQueueFail = true;
                 System.out.println("Priority Queue Test 8 Failure, queue returned: "
                         + element.value() + " should be: " + verifyOrder2[i]);
                 break;
             }
         }
 
 
         /*************************************************************
          *
          *  Test cases for the PQ Game.
          *
          *************************************************************/
 
         ProblemSolutions ps = new ProblemSolutions();
         int list1[] = {2,7,4,1,8,1};                            // Expected answer is '1'
         int list2[] = {2,7,4,1,8,1,15,0,19,-2,-100,100,8,2,7};  // Expected answer is '128'
         int list3[] = {7,4,7,4,7,2,6,8,6,7};                    // Expected answer is '0'
         int list4[] = {14,7,24,1,8,1};                          // Expected answer is '3'
         int list5[] = {14,7,-24,-1,8,-1};                       // Expected answer is '27'
 
         if ( ps.lastBoulder(list1) != 1 ) {
             PQGameFail = true;
             System.out.println("PQ Game Test 1 Failure, returned was " + ps.lastBoulder(list1));
         }
 
         if ( ! PQGameFail && ps.lastBoulder(list2) != 128 ) {
             PQGameFail = true;
             System.out.println("PQ Game Test 2 Failure, returned was " + ps.lastBoulder(list2));
         }
 
         if ( ! PQGameFail && ps.lastBoulder(list3) != 0 ) {
             PQGameFail = true;
             System.out.println("PQ Game Test 3 Failure, returned was " + ps.lastBoulder(list3));
         }
 
         if ( ! PQGameFail && ps.lastBoulder(list4) != 3 ) {
             PQGameFail = true;
             System.out.println("PQ Game Test 4 Failure, returned was " + ps.lastBoulder(list4));
         }
 
         if ( ! PQGameFail && ps.lastBoulder(list5) != 27 ) {
             PQGameFail = true;
             System.out.println("PQ Game Test 5 Failure, returned was " + ps.lastBoulder(list5));
         }
 
 
         /*************************************************************
          *
          *  Test cases for the Sorting 1 and 2 problems
          *
          *************************************************************/
 
         /*
          * Testing for sorting problem 1
          */
 
         ArrayList<String> sort1InputList1 = new ArrayList<>(Arrays.asList("apple", "apple",
                 "banana", "banana", "banana",
                 "cherry", "cherry", "cherry",
                 "cherry"));
         ArrayList<String> sort1AnswerList1 = new ArrayList<>(Arrays.asList("apple",
                 "banana", "cherry"));
 
         if ( ! ps.showDuplicates(sort1InputList1).equals(sort1AnswerList1) ) {
             Sorting1Fail = true;
             System.out.println("Sorting 1 Test 1 Failure, returned String: "
                                 + ps.showDuplicates(sort1InputList1));
         }
 
 
         ArrayList<String> sort1InputList2 = new ArrayList<>(Arrays.asList("close", "part",
                 "PART", "learn", "close",
                 "start", "learn", "start",
                 "concur"));
         ArrayList<String> sort1AnswerList2 = new ArrayList<>(Arrays.asList("close",
                 "learn", "start"));
 
         if ( !Sorting1Fail && ! ps.showDuplicates(sort1InputList2).equals(sort1AnswerList2) ) {
             Sorting1Fail = true;
             System.out.println("Sorting 1 Test 2 Failure, returned String: "
                     + ps.showDuplicates(sort1InputList2));
         }
 
 
 
         /*
          * Testing for sorting problem 2
          */
 
         int[] sorting2Input1 = new int[]{2, 3, 3, 4, 5, 6, 7};
         int k = 9;
 
         ArrayList<String> sort2AnswerList1 = new ArrayList<>(Arrays.asList("(2, 7)", "(3, 6)",
                 "(4, 5)"));
 
         // With k==9, expected: [(2, 7), (3, 6), (4, 5)]
         if ( ! ps.pair(sorting2Input1, k).equals(sort2AnswerList1) ) {
             Sorting2Fail = true;
             System.out.println("Sorting 2 Test 1 Failure, returned String: "
                     + ps.pair(sorting2Input1, k));
         }
 
 
         int[] sorting2Input2 = new int[]{1, 4, 4, 4, 6, 6, 7, 7, 8, 10};
         k = 8;
 
         ArrayList<String> sort2AnswerList2 = new ArrayList<>(Arrays.asList("(1, 7)", "(4, 4)"));
 
         // With k==8, expected: [(1, 7), (4, 4)]
         if ( !Sorting2Fail && ! ps.pair(sorting2Input2, k).equals(sort2AnswerList2) ) {
             Sorting2Fail = true;
             System.out.println("Sorting 2 Test 2 Failure, returned String: "
                     + ps.pair(sorting2Input2, k));
         }
 
 
         /*********************************************************
          *
          * Display Pass / Fail results along with final score.
          *
          *********************************************************/
 
         if ( ! PriorityQueueFail ) {
             score += 19;
             System.out.println("Priority Queue - PASSED");
         } else {
             System.out.println("Priority Queue - *** FAILED ***");
         }
 
         if ( ! PQGameFail ) {
             score += 27;
             System.out.println("PQ Game        - PASSED");
         } else {
             System.out.println("PQ Game        - *** FAILED ***");
         }
 
         if ( ! Sorting1Fail ) {
             score += 27;
             System.out.println("Sort 1         - PASSED");
         } else {
             System.out.println("Sort 1         - *** FAILED ***");
         }
 
         if ( ! Sorting2Fail ) {
             score += 27;
             System.out.println("Sort 2         - PASSED");
         } else {
             System.out.println("Sort 2         - *** FAILED ***");
         }
 
         System.out.println("\nTotal Score is: " + score);
     }
 }