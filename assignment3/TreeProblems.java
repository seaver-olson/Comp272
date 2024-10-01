/*
 * *** Seaver Olson / 002 ***
*/
import java.util.*;

public class TreeProblems {

  public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {
  
    Set<Integer> temp = new TreeSet<Integer>();
    temp.addAll(setA);
    setA.retainAll(setB);
    setB.addAll(temp);
    setB.removeAll(setA);
    return setB;
  }

  public static void removeEven(Map<Integer, String> treeMap) {
    treeMap.keySet().removeIf(key -> key % 2 == 0);//ONELINER
  }


  public boolean treesEqual(Map<Integer, String> tree1,Map<Integer, String> tree2 ) {
  
    return (tree1.equals(tree2));//really bummed that this worked because I had such a great method I though of down below
    //return (tree1.size() == (tree1.putAll(tree2)).size());

  }

}
