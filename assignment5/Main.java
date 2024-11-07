/**********************************************************
 *
 * Homework # 5 (Programming Assignment). This assignment has three parts. The first
 * portion includes solving the problem solutions for the methods isSubset,
 * findKthlargest, and sort2Arrays. These methods should be completed utilizing the
 * Java Collection Framework classes Hashmap and PriorityQueue, as appropriate.
 *
 * The second portion of this assignment is to complete the missing method in the
 * bloom filter class object. Finally, the third portion is to complete the missing
 * method in the Cuckoo Hash Table class object.
 *
 * This main routine is the driver for testing these methods that were developed
 * within the files 'ProblemSolutions.java', 'BloomFilter.java', and
 * 'CuckhooHash.java'. Your work will need to pass all these tests for 100%
 *
 *             *** DO NOT MANIPULATE / CHANGE THIS FILE ***
 *
 *********************************************************/

 import java.security.SecureRandom;
 import java.util.Arrays;
 import java.util.HashSet;
 import java.util.Random;
 import java.util.Set;
 import java.util.List;
 import java.util.ArrayList;
 
 
 public class Main {
     public static void main(String[] args) {
 
         ProblemSolutions p = new ProblemSolutions();
         boolean test1Failure = false;
         boolean test2Failure = false;
         boolean test3Failure = false;
         boolean bloomFilterfailure = false;
         boolean cuckooFailure = false;
         int score = 0;
 
 
         /*
          * Testing for the problem solution using isSubset() method
          */
 
         int list1[] = {10, 50, 35, 82, 13, 25};
         int list2[] = {10, 35, 13};
         int list3[] = {10, 35, 13, 8};
 
         if ( ! p.isSubset(list1, list2) ) {
             System.out.println("Error 1: Test Failure);");
             test1Failure = true;
         }
 
         if ( ! test1Failure && p.isSubset(list1, list3) ) {
             System.out.println("Error 2: Test Failure");
             test1Failure = true;
         }
 
 
         /*
          * Testing for problem solution for findKthLargest() method
          */
 
         int list4[] = {1,7,3,10,34,5,8};
         int list5[] = {4, 5, 20, 100, 54, 30, 44, 90, 31, 3, 1001, 88};
 
         if ( ! test2Failure && p.findKthLargest(list4, 4) != 7 ) {
             System.out.println("Error 3: Test Failure);");
             test2Failure = true;
         }
 
         if ( ! test2Failure && p.findKthLargest(list4, 6) != 3 ) {
             System.out.println("Error 4: Test Failure);");
             test2Failure = true;
         }
 
         if ( ! test2Failure && p.findKthLargest(list5, 6) != 44 ) {
             System.out.println("Error 5: Test Failure);");
             test2Failure = true;
         }
 
         /*
          * Testing for problem solution for sort2Arrays() method
          */
 
         int list6[] = {4,1,5};
         int list7[] = {3,2};;
         int sorted[];
 
         sorted = p.sort2Arrays(list6, list7);
 
         int answer1[] = { 1, 2, 3, 4, 5 };
         if ( ! Arrays.equals(sorted, answer1) ) {
             System.out.println("Error 6: Test Failure);");
             test3Failure = true;
         }
 
         sorted = p.sort2Arrays(list5, list2);
 
         int answer2[] = { 3, 4, 5, 10, 13, 20, 30, 31, 35, 44, 54, 88, 90, 100, 1001 };
         if ( ! test3Failure && ! Arrays.equals(sorted, answer2) ) {
             System.out.println("Error 7: Test Failure);");
             test3Failure = true;
         }
 
 
         /***************************************************************
          *
          * BLOOM FILTER TESTING FOLLOWS
          *
          ***************************************************************/
 
         BloomFilter bm = new BloomFilter(16, 3);
 
         /*
          * A simple tests follow, which adds elements to the bloom filter,
          * then check their presence in the set. We are not putting in
          * enough elements in the bloom filter hash map on this simple test
          * to expect and false positives
          */
 
         bm.add("String 1");
         bm.add("String 2");
         bm.add("String 3");
         bm.add("String 12");
 
         if ( ! bm.contains("String 1") ) {
             System.out.println("Error 8: Test Failure);");
             bloomFilterfailure = true;
         }
 
         if ( ! bloomFilterfailure && ! bm.contains("String 2") ) {
             System.out.println("Error 9: Test Failure);");
             bloomFilterfailure = true;
         }
 
         if (! bloomFilterfailure && bm.contains("String 5") ) {
             System.out.println("Error 10: Test Failure);");
             bloomFilterfailure = true;
         }
 
         if ( ! bloomFilterfailure && ! bm.contains("String 12") ) {
             System.out.println("Error 11: Test Failure);");
             bloomFilterfailure = true;
         }
 
         if ( ! bloomFilterfailure && bm.contains("STRING 12") ) {
             System.out.println("Error 12: Test Failure);");
             bloomFilterfailure = true;
         }
 
         BloomFilter bf1 = new BloomFilter(17, 3);
         HashSet<String> hashset1 = new HashSet<String>();
         Random random = new SecureRandom();
 
         // Populate the bloom filter and corresponding hashset
         // with 16,000 randomly generated strings
 
         for (int i = 0 ; i < 16000 ; i++ ) {
             String string = bf1.randomString(random);
             bf1.add(string);
             hashset1.add(string);
         }
 
 
         // Randomly generate strings and probe the bloom filter. If the
         // bloom filter returns false, then the string should not be in
         // the corresponding hashset. If the bloom filter returns true,
         // then it may or may not be in the hashset (can be false positive).
 
         for (int i = 0 ; i < 64000 ; i++ ) {
             String string = bf1.randomString(random);
             boolean found = bf1.contains(string);
 
             if ( ! found && hashset1.contains(string) ) {
                 // String was in the hashset, so this is an error case.
                 System.out.println("Error 15: Test Failure);");
                 bloomFilterfailure = true;
                 break; // simple break from loop, test failed.
             }
         }
 
 
         /*
          * False positivity tests compare various combinations of:
          *      1) number of bits (size of hash map)
          *      2) number of hash functions use per hash map
          *
          * The test will loop through hash map sizes between 2^14 through 2^23,
          * and for each hash map size, will run through tests using 1 to 8 hash
          * codes per instantiation of the bloom filter. Each instantiation will
          * be populated with 16,384 random strings, and the test will probe the
          * bloom filter instantiation (a.k.a., invoke methods contains() using
          * random strings) 2,500,000 times. The false positive rate will be
          * displayed for each bloom filter instantiation.
          */
 
         ///////////////////////////////////////////////////////////////
         //  UNCOMMENT THE FOLLOWING CODE SEGMENT  IF WANTING TO      //
         //  SEE THE FALSE POSITIVE RATIO FOR DIFFERENT COMBINATIONS  //
         //  OF HASH MAPS AND NUMBER OF HASH FUNCTIONS. THE NUMBER OF //
         //  ELEMENTS IN THE HASH TABLE STAY CONSTANT                 //
         ///////////////////////////////////////////////////////////////
 
         /*
         final int NO_FALSE_POSITIVE_TESTS = 2500000; // 2,500,000 probes
         Random r = new SecureRandom();
         final int noItems = 1 << 14;
 
         System.out.println("\nFalse Positivity test ...");
         System.out.println("\tTest loops through bitmap sizes for the Bloom filter from "
                 + (int) Math.pow(2,14) + " to " + (int) Math.pow(2,23) + " bits.");
         System.out.println("\tFor each bitmap size, tests are conducted using from 1 to 8 hash codes per hashmap.");
         System.out.println("\tAnd, each test combination probes the bloom filter "
                 + NO_FALSE_POSITIVE_TESTS + " times!\n");
 
 
         for (int log2bits = 14; log2bits <= 23; log2bits++) {
             for (int noHashes = 1; noHashes <= 8; noHashes++) {
                 double noFalsePositives = 0;
                 int noNotIn = 0;
 
                 BloomFilter bf = new BloomFilter(log2bits, noHashes);
                 Set already = new HashSet(noItems);
                 // Add items fo Bloom filter
                 for (int itemNo = 0; itemNo < noItems; itemNo++) {
                     String s = bm.randomString(r);
                     already.add(s);
                     bf.add(s);
                 }
                 // Now test for false positives
                 for (int n = 0; n < NO_FALSE_POSITIVE_TESTS; n++) {
                     String s = bm.randomString(r);
                     if (!already.contains(s)) {
                         noNotIn++;
                         if (bf.contains(s)) noFalsePositives++;
                     }
                 }
                 double falsePositiveRate = noNotIn == 0 ? 0d :
                         noFalsePositives / noNotIn;
 
                 System.out.println("\nBloom filter populated with "
                         + noItems + " elements with map size of "
                         + (int) Math.pow(2,log2bits) + " and "
                         + noHashes + " hash codes");
                 System.out.print("\tCombination's false positive ratio is: "
                         + falsePositiveRate);
                 if ( falsePositiveRate == 0 )
                     System.out.print(" - no false positives on this combination!");
                 System.out.println();
             }
         }
         */
 
 
 
         /***************************************************************
          *
          * CUCKHOO HASH TABLE TESTING FOLLOWS
          *
          ***************************************************************/
 
         // Initially 10 buckets
         CuckooHash<String, String> table = new CuckooHash<String, String>(10);
 
         // The following quick test is inserting <key, value> pairs, such
         // that the code is exercising hash collisions and the resultant
         // reshuffling of <key, value> pairs in the hashmap. Additionally,
         // based on the current hash function formulas and the hashmap
         // growth function for rehashing, several of these insertions will
         // also cause rehashing / hashmap growth.
         
         // NOTE: The implementation is allowing duplicate 'keys', but
         // not allowing dupe combinations of <key,value> pairs. This is
         // done for simplicity of testing and exercising the collision
         // code with only a few insertions. The 'key' portion still
         // drives the bucket location and. Normally, we would want the
         // the 'key' portion to be unique, and if a unique key is inserted,
         // it would update the value portion. It is not as straightforward
         // to exercise the collision code for purposes of our very
         // simple automated testing here :-)
 
         table.put("A", "AA");
         table.put("A", "LL");
         table.put("B", "BB");
         table.put("C", "CC");
 
         // No cycle was caused by the above insertions. There should not
         // have been a cycle causing a rehashing and growth of the map.
         // We instantiated the map object above with 10, it should still
         // be that size.
 
         if ( table.mapSize() != 10 ) {
             cuckooFailure = true;
             System.out.println("Error 16: Test Failure);");
         }
 
 
         // The following insert will result in a cycle and rehashing
         // The new resized map based in implementation should then be 43
 
         table.put("C", "HH");
         table.put("S", "XX");
 
         if ( ! cuckooFailure && table.mapSize() != 43 ) {
             cuckooFailure = true;
             System.out.println("Error 17: Test Failure);");
         }
 
         // The following insert will result in a another cycle and rehashing
         // The new resized map size based in implementation should then be 87
 
         table.put("S", "SS");
         table.put("B", "KK");
 
         if ( ! cuckooFailure && table.mapSize() != 87 ) {
             cuckooFailure = true;
             System.out.println("Error 18: Test Failure);");
         }
 
         // Based on the above put operations, the table should contain
         // eight (8) <key,value> pairs. The contents should be:
         //  [ <C, HH> <S, XX> <A, AA> <B, KK> <C, CC> <S, SS> <A, LL> <B, BB> ]
         //
         // The key sets and value lists are:
         //  KEYS: [A, B, C, S]
         //  VALUES: [HH, XX, AA, KK, CC, SS, LL, BB]
         //
         // The following code verifies the above contents. .
 
         if ( ! cuckooFailure && table.size() != 8) {
             cuckooFailure = true;
             System.out.println("Error 19: Test Failure);");
         }
 
         Set<String> keys = new HashSet<>();
         keys.add("A");
         keys.add("B");
         keys.add("C");
         keys.add("S");
         if ( ! cuckooFailure && ! keys.equals( table.keys() ) ) {
             cuckooFailure = true;
             System.out.println("Error 20: Test Failure);");
         }
 
         List<String> values = new ArrayList<>();
         values.add("HH");
         values.add("XX");
         values.add("AA");
         values.add("KK");
         values.add("CC");
         values.add("SS");
         values.add("LL");
         values.add("BB");
         if ( ! cuckooFailure && ! values.equals( table.values() ) ) {
             cuckooFailure = true;
             System.out.println("Error 21: Test Failure);");
         }
 
         // Now, remove two <key, value> pairs from the hash table. We should
         // now have 6 items in teh table - lets verify.
         table.remove("A", "AA");
         table.remove("B", "KK");
 
         if ( ! cuckooFailure && table.size() != 6) {
             cuckooFailure = true;
             System.out.println("Error 22: Test Failure);");
         }
 
         table.clear();
 
 
         // Populate the Cuckoo Hash table and corresponding hashset
         // with 16,000 randomly generated strings.
 
         CuckooHash<String, String> table2 = new CuckooHash<String, String>(10);
         hashset1.clear(); // reuse the hashset from earlier.
 
 
         for (int i = 0 ; i < 32000 ; i++ ) {
             String string = bf1.randomString(random);
             table2.put(string, string);
             hashset1.add(string);
         }
 
 
         // Randomly generate strings and probe the Cuckoo Hash table. If the
         // hash table returns null (not found), then the string should not be in
         // the corresponding hashset. If the cuckoo hash table locates the string,
         // it should also be in the hashset.
 
         for (int i = 0 ; i < 128000 ; i++ ) {
             String string = bf1.randomString(random);
             String cuckoofound = table2.get(string);
             boolean hashsetfound = hashset1.contains(string);
 
             if ( cuckoofound == null && hashsetfound ) {
                 // String not found in cuckoo table, but found in hashset, error case.
                 cuckooFailure = true;
                 System.out.println("Error 23: Test Failure);");
                 break; // simple break from loop, test failed.
             }
 
             if ( cuckoofound != null && ! hashsetfound ) {
                 // If string found in cuckoo table, but not found in hashset, error case.
                 cuckooFailure = true;
                 System.out.println("Error 24: Test Failure);");
                 break; // simple break from loop, test failed.
             }
         }
 
 
 
         /*
          * Accumulate total homework assignment 5 score
          */
 
         if ( ! test1Failure ) {
             score += 21;
             System.out.println("Problem Solution, Hash         - PASSED");
         } else {
             System.out.println("Problem Solution, Hash         - *** FAILED ***");
         }
 
         if ( ! test2Failure ) {
             score += 21;
             System.out.println("Problem Solution, PQ 1         - PASSED");
         } else {
             System.out.println("Problem Solution, PQ 1         - *** FAILED ***");
         }
 
         if ( ! test3Failure ) {
             score += 21;
             System.out.println("Problem Solution, PQ 2         - PASSED");
         } else {
             System.out.println("Problem Solution, PQ 2         - *** FAILED ***");
         }
 
         if ( ! bloomFilterfailure ) {
             score += 21;
             System.out.println("Problem Solution, Bloom Filter - PASSED");
         } else {
             System.out.println("Problem Solution, Bloom Filter - *** FAILED ***");
         }
 
         if ( ! cuckooFailure ) {
             score += 16;
             System.out.println("Problem Solution, Cuckhoo Hash - PASSED");
         } else {
             System.out.println("Problem Solution, Cuckhoo Hash - *** FAILED ***");
         }
 
         System.out.println("\nTotal Score is: " + score);
     }
 }