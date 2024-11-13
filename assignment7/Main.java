
/**********************************************************
 *
 * Homework # 7 (Programming Assignment). This assignment has several parts.
 *
 * The first part requires the implementation of selection sort. The method optionally
 * accepts a boolean argument for performing an ascending or descending sorting. The
 * parameter should be set to true for True is ascending sort, else it will perform
 * a descending sort if set to false.
 *
 * The second part requires the implementation of mergeSort. However, it will accept
 * a parameter 'k', and any numbers dividable by 'k' should be first. See the method's
 * prologue in ProblemSolutions.java for more details on the expected sorting output.
 *
 * The third and fourth parts are simple gaming problems. The first is on if asteroids
 * will be destroyed colliding with a planet, or if the asteroids will destroy the
 * planet. The fourth part is on determining the minimum number of rescue sleds needed
 * to carry people stranded, There are weight limits on the sleds.
 *
 *             *** DO NOT MANIPULATE / CHANGE THIS FILE ***
 *
 *********************************************************/

 import java.util.Arrays;

 class Main {
 
     public static void main(String[] args) {
         ProblemSolutions ps = new ProblemSolutions();
         boolean selectionSortFailure = false;
         boolean mergeSortDivisibleByKFirstFailure = false;
         boolean destoryAsteroidsFail = false;
         boolean numRescueSledsFail = false;
         int score = 0;
 
         /***************************************************************
          *
          *  Testing the selection sort method
          *
          ***************************************************************/
 
         int[] values1 = { 5, -5, 1, -9, 10, 2, 7, -3 };      // Initial array list
         int[] ascending = { -9, -5, -3, 1, 2, 5, 7, 10 };   // Ascending array list
         int[] descending = { 10, 7, 5, 2, 1, -3, -5, -9 };  // Descending array list
 
         ps.selectionSort(values1);
         if ( ! Arrays.equals(values1, ascending) ) {
             selectionSortFailure = true;
             System.out.println("SelectionSort Error 1, values: " + Arrays.toString(values1));
         }
 
         ps.selectionSort(values1, false);
         if ( ! selectionSortFailure && ! Arrays.equals(values1, descending) ) {
             selectionSortFailure = true;
             System.out.println("SelectionSort Error 2, values: " + Arrays.toString(values1));
         }
 
         ps.selectionSort(values1, true);
         if ( ! selectionSortFailure && ! Arrays.equals(values1, ascending) ) {
             selectionSortFailure = true;
             System.out.println("SelectionSort Error 3, values: " + Arrays.toString(values1));
         }
 
         int[] values2  = new int[] { 5 };
         int[] expected = new int[] { 5 };
         ps.selectionSort(values2);
         if ( ! selectionSortFailure && ! Arrays.equals(values2, expected) ) {
             selectionSortFailure = true;
             System.out.println("SelectionSort Error 4, values: " + Arrays.toString(values2));
         }
 
 
         /***************************************************************
          *
          *  Testing the merge sort, divisible by k method
          *
          ***************************************************************/
 
         int[][] inputArrays = {                         // Test input arrays
                 { 10, 3, 25, 8, 6 },
                 { 15, 13, 11, 14, 20 },
                 { 2, 5, 6, 8, 1, 10, 3, 11, 7, 9 },
                 { 30, 45, 22, 9, 18, 39, 6, 12 },
                 { 1, 2, 3, 4, 5, 6, 7, 8, 9 }
         };
 
         int[] testKs = { 5, 3, 2, 6, 4 };               // Test k's
 
         int[][] answerArrays = {                        // Sorted answers arrays
                 { 10, 25, 3, 6, 8 },
                 { 15, 11, 13, 14, 20 },
                 { 2, 6, 8, 10, 1, 3, 5, 7, 9, 11 },
                 { 30, 18, 6, 12, 9, 22, 39, 45 },
                 { 4, 8, 1, 2, 3, 5, 6, 7, 9 }
         };
 
         for (int i = 0; i < inputArrays.length; i++) {
 
             ps.mergeSortDivisibleByKFirst(inputArrays[i], testKs[i]);
             if ( ! mergeSortDivisibleByKFirstFailure &&
                  ! Arrays.equals(inputArrays[i], answerArrays[i]) )
             {
                 mergeSortDivisibleByKFirstFailure = true;
                 System.out.println("MergeSort Div by k Error, returned array was: "
                                   + Arrays.toString(inputArrays[i]) + " expect was: "
                                   + Arrays.toString(answerArrays[i]));
             }
         }
 
 
         /***************************************************************
          *
          *  Testing the destroying asteroid method
          *
          ***************************************************************/
 
         // Should return true
         int mass = 10;
         int[] asteroids = new int[] {3,9,19,5,21};
         if ( ! ps.asteroidsDestroyed(mass, asteroids)) {
             destoryAsteroidsFail = true;
             System.out.println("Destroy Asteroid Error 1");
         }
 
         // Should return false
         mass = 5;
         asteroids = new int[] {4,9,23,4};
         if ( ! destoryAsteroidsFail && ps.asteroidsDestroyed(mass, asteroids)) {
             destoryAsteroidsFail = true;
             System.out.println("Destroy Asteroid Error 2");
         }
 
         // Should return true
         mass = 3;
         asteroids = new int[] {0};
         if ( ! destoryAsteroidsFail && ! ps.asteroidsDestroyed(mass, asteroids)) {
             destoryAsteroidsFail = true;
             System.out.println("Destroy Asteroid Error 3");
         }
 
         // Should return true
         mass = 0;
         asteroids = new int[] {};
         if ( ! destoryAsteroidsFail && ! ps.asteroidsDestroyed(mass, asteroids)) {
             destoryAsteroidsFail = true;
             System.out.println("Destroy Asteroid Error 4");
         }
 
         // Should return true
         mass = 30;
         asteroids = new int[] {4,9,23,4};
         if ( ! destoryAsteroidsFail && ! ps.asteroidsDestroyed(mass, asteroids)) {
             destoryAsteroidsFail = true;
             System.out.println("Destroy Asteroid Error 5");
         }
 
         // Should return false
         mass = 30;
         asteroids = new int[] {4,9,23,74};
         if ( ! destoryAsteroidsFail && ps.asteroidsDestroyed(mass, asteroids)) {
             destoryAsteroidsFail = true;
             System.out.println("Destroy Asteroid Error 6");
         }
 
 
         /***************************************************************
          *
          *  Testing the number of rescue sleds method
          *
          ***************************************************************/
 
         int people1[] = {1,2};
         int people2[] = {3,2,2,1};
         int people3[] = {3,5,3,4};
         int people4[] = {};
 
         if ( ! numRescueSledsFail && ps.numRescueSleds(people1, 3) != 1 ) {
             numRescueSledsFail = true;
             System.out.println("Number rescue sleds error 1, returned: "
                               + ps.numRescueSleds(people1, 2));
         }
 
         if ( ! numRescueSledsFail && ps.numRescueSleds(people2, 3) != 3 ) {
             numRescueSledsFail = true;
             System.out.println("Number rescue sleds error 2, returned: "
                     + ps.numRescueSleds(people2, 3));
         }
 
         if ( ! numRescueSledsFail && ps.numRescueSleds(people3, 5) != 4 ) {
             numRescueSledsFail = true;
             System.out.println("Number rescue sleds error 3, returned: "
                     + ps.numRescueSleds(people3, 5));
         }
 
         if ( ! numRescueSledsFail && ps.numRescueSleds(people4, 0) != 0 ) {
             numRescueSledsFail = true;
             System.out.println("Number rescue sleds error 4, returned: "
                     + ps.numRescueSleds(people4, 0));
         }
 
         /***************************************************************
          *
          *  Calculate the final score
          *
          ***************************************************************/
 
         if ( ! selectionSortFailure ) {
             score += 27;
             System.out.println("Selection Sort          - PASSED");
         } else {
             System.out.println("Selection Sort          - *** FAILED ***");
         }
 
         if ( ! mergeSortDivisibleByKFirstFailure ) {
             score += 19;
             System.out.println("MergeSort, DivByK First - PASSED");
         } else {
             System.out.println("MergeSort, DivByK First - *** FAILED ***");
         }
 
         if ( ! destoryAsteroidsFail ) {
             score += 27;
             System.out.println("Destroy Asteroids       - PASSED");
         } else {
             System.out.println("Destroy Asteroids       - *** FAILED ***");
         }
 
         if ( ! numRescueSledsFail ) {
             score += 27;
             System.out.println("Number of rescue sleds  - PASSED");
         } else {
             System.out.println("Number of rescue sleds  - *** FAILED ***");
         }
 
         System.out.println("\nTotal Score is: " + score);
     }
 }