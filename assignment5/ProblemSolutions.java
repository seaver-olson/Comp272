/******************************************************************
 *
 *   Seaver Olson / 002
 *
 ********************************************************************/
import java.util.*;
 class ProblemSolutions {
     public boolean isSubset(int list1[], int list2[]) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < list1.length; i++) set.add(list1[i]);
        for (int i = 0; i < list2.length; i++) {
            if (!set.contains(list2[i])) return false;
        }
        return true;
     }
     public int findKthLargest(int[] array, int k) {
        PriorityQueue<Integer> arr = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) arr.add(array[i]);
        for (int i = 0; i < array.length; i++) if (arr.size() > k) arr.poll();
        return arr.poll();
     }
     public int[] sort2Arrays(int[] array1, int[] array2) {
        PriorityQueue<Integer> arr = new PriorityQueue<>();
        for (int i = 0; i < array1.length; i++) arr.add(array1[i]);
        for (int i = 0; i < array2.length; i++) arr.add(array2[i]);
        int[] results = new int[arr.size()];
        for (int i = 0; i < results.length; i++) results[i] = arr.poll();
        return results;
     }
 }