/******************************************************************
 *
 *   Seaver Olson / 002
 *
 *   Note, additional comments provided throughout this source code
 *   is for educational purposes
 *
 ********************************************************************/

 import java.util.ArrayList;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import java.lang.Math;
 
 
 /**
  * Cuckoo Hashing Exercise
  *
  * Cuckoo hashing is a scheme for resolving hash collisions of keys in 
  * a hashmap that maintains a worst-case constant lookup time, O(1). 
  * The name derives from the behavior of some species of cuckoo, where 
  * the cuckoo chick pushes the other eggs or young out of the nest when 
  * it hatches in a variation of the behavior referred to as brood 
  * parasitism; analogously, inserting a new key into a cuckoo hashing 
  * table may push an older key to a different location in the table.
  *
  * Constructor:
  *    CuckooHash( size )  - Where size is the initial bucket size 
  *                          of the hashmap
  *
  * Public Methods:
  *    int     size()	   - The number of elements, <key,value> pairs,
  *                           in the hashmap
  *    void    clear()	   - Empty the hashmap.
  *    List<V> values()     - Return a List of all values of type 'V' in 
  *                           the hashmap.
  *    Set<K>  keys() 	   - Return a Set of all keys of type 'K" in
  *                           the hashmap.
  *    void    put(K,V)	   - Insert the <key,value> pair of types K and V.
  *    V       get(K)	   - Return the value of type V for the key
  *                           provided of type K.
  *    boolean remove(K, V) - Remove  <key, value> pair, return true 
  *                           if found and removed, else false.
  *    String printTable()  - Return a String representing a
  *                           concatenation of all <key,value> pairs.
  */
 
 @SuppressWarnings("unchecked")
 public class CuckooHash<K, V> {
   
     private int CAPACITY;  					// Hashmap capacity
     private Bucket<K, V>[] table;			// Hashmap table
     private int a = 37, b = 17;				// Constants used in h2(key)
 
 
     /**
      * Class Bucket
      *
      * Inner bucket class which represents a <key,value> pair 
      * within the hash map.
      *
      * @param <K> - type of key
      * @param <V> - type of value
      */
 
     private class Bucket<K, V> {
         private K bucKey = null;
         private V value = null;
         
         public Bucket(K k, V v) {
             bucKey = k; 
             value = v;
         }
 
         /*
          * Getters and Setters
          */
         private K getBucKey() {
             return bucKey;
         }
         private V getValue()  { return value;  }
 
     }
 
 
     /*
      * Hash functions, hash1 and hash2
      */
     private int hash1(K key) 	{ return Math.abs(key.hashCode()) % CAPACITY; }
     private int hash2(K key) 	{ return (a * b + Math.abs(key.hashCode())) % CAPACITY; }
 
 
     /**
      * Method CuckooHash
      *
      * Constructor that initializes and sets the hashmap. A future 
      * optimization would to pass a load factor limit as a target in
      * maintaining the hashmap before reaching the point where we have
      * a cycle causing occurring loop.
      *
      * @param size user input multimap capacity
      */
 
     public CuckooHash(int size) {
         CAPACITY = size;
         table = new Bucket[CAPACITY];
     }						  
 
 
     /**
      * Method size
      *
      * Get the number of elements in the table; the time complexity is O(n).
      *
      * @return total key-value pairs
      */
 
     public int size() {
         int count = 0;
         for (int i=0; i<CAPACITY; ++i) {
             if (table[i] != null)
                 count++; 	
         }
         return count;
     }
 
 
     /**
      * Method clear
      *
      * Removes all elements in the table, it does not rest the size of 
      * the hashmap. Optionally, we could reset the CAPACITY to its
      * initial value when the object was instantiated.
      */
 
     public void clear() {
         table = new Bucket[CAPACITY]; 
     }
 
     public int mapSize() { return CAPACITY; }    // used in external testing only
 
 
     /**
      * Method values
      *
      * Get a list containing of all values in the table
      *
      * @return the values as a list
      */
 
     public List<V> values() {
         List<V> allValues = new ArrayList<V>(); 
         for (int i=0; i<CAPACITY; ++i) {
             if (table[i] != null) {
                 allValues.add(table[i].getValue());
             }
         }
         return allValues;
     }
 
 
     /**
      * Method keys
      *
      * Get a set containing all the keys in the table
      *
      * @return a set of keys
      */
 
     public Set<K> keys() {
         Set<K> allKeys = new HashSet<K>();
         for (int i=0; i<CAPACITY; ++i) {
             if (table[i] != null) {
                 allKeys.add(table[i].getBucKey());
             }
         }
         return allKeys;
     }
 
 
     /**
      * Method put
      *
      * Adds a key-value pair to the table by means of cuckoo hashing. 
      * Each element can only be inserted into one of two bucket locations,
      * defined by the two separate hash functions, h1(key) or h2(key).
      * Each element's initial location will always be defined
      * by h1(key). If later it is kicked out of that bucket location by 
      * another element insertion, it will move back and forth between those
      *  two hash locations (aka, bucket locations).
      *
      * On its initial invocation, this method places the passed <key,value>
      * element at its h1(key) bucket location. If an element is already located
      * at that bucket location, it will be kicked out and moved to its secondary
      * location in order to make room for this initially inserted element. The
      * secondary location is defined by the kicked out key's alternative hash
      * function (aka, either h1(key) or h2(key), whichever is the one that moves
      * to the alternate location.
      *
      * This process will continue in a loop as it moves kicked out 
      * elements to their alternate location (defined by h1(key) and h2(key))
      * until either:
      *         (1) an empty bucket is found, or
      *         (2) we reach 'n' iterations, where 'n' is the bucket capacity
      *             of the hashmap (see HINT below on this method of cycle
      *             detection, the bucket capacity is held in variable 'CAPACITY').
      *
      * If we reach 'n' shuffles of elements being kicked out and moved to their
      * secondary locations (leading to what appears to be a cycle), we will grow
      * the hashmap and rehash (via method rehash()). After the rehash, we will
      * need to re-invoke this method recursively, as we will have one element that
      * was kicked out after the 'n' iteration that still needs to be inserted. Note,
      * that it is possible when the bucket lists is small, that we may need to rehash
      * twice to break a cycle. Again, this is done automatically when calling this
      * method recursively.
      *
      * MAKE SURE YOU UNDERSTAND THE HINTS:
      *
      * HINT 1: To make sure you pass the provided tests in main, follow this rule:
      *          - Given a <key, value> via method's invocation, the bucket it
      *            determined by hashing the 'key'
      *          - Normally, we would not allow dupe keys, for our purposes here we
      *            WILL allow. What will be unique in this assignment's implementation
      *            is the <key,value> in the table. So when inserting a key that is
      *            already in the table, continue unless a dupe key has the same
      *            value as being inserted.
      *
      *      The above is being done to make testing easy on causing cycles with minimal
      *      insertions into the hash table.
      *
      * HINT 2: For simplicity of this assignment, after shuffling elements between
      * buckets 'n' times (where 'n' is defined by the value of variable 'CAPACITY',
      * you can assume you are in an infinite cycle. This may not be true, but if
      * growing the hash map when not in a cycle, this will not cause data integrity
      * issues. BUT BE CLEAR IN PRACTICE, as we discussed in class, a better way to
      * do this is to build a graph (one edge at a time) for each element shuffled
      * (and edge being defined as with end-points of the two bucket locations for the
      * moved element). This once a cycle is detected in this graph, which is by starting
      * to traverse an existing edge in the graph, we have a cycle. However, we have not
      * discussed graphs yet, they are at the end of the semester :-)
      *
      * @param key the key of the element to add
      * @param value the value of the element to add
      */
 
      public void put(K key, V value) {
        Bucket<K,V> temp = new Bucket<>(key, value);
		int nextidx = hash1(temp.bucKey);
		Bucket<K,V> next = table[nextidx];

		for(int i = 0; i < CAPACITY; i++) {

			if (next != null && next.bucKey.equals(temp.bucKey)) {
                if(next.value.equals(temp.value)) return;
            }
			table[nextidx] = temp;
			if(next == null) return;

			if (nextidx == hash1(next.bucKey)){
                nextidx = hash2(next.bucKey);
            } else {
                nextidx = hash1(next.bucKey);
            }

			temp = next;
			next = table[nextidx];
		}
		rehash();
		put(temp.bucKey, temp.value);
     }
 
 
     /**
      * Method get
      *
      * Retrieve a value in O(1) time based on the key because it can only 
      * be in 1 of 2 locations
      *
      * @param key Key to search for
      * @return the found value or null if it doesn't exist
      */
 
     public V get(K key) {
         int pos1 = hash1(key);
         int pos2 = hash2(key);
         if (table[pos1] != null && table[pos1].getBucKey().equals(key))
             return table[pos1].getValue();
         else if (table[pos2] != null && table[pos2].getBucKey().equals(key))
             return table[pos2].getValue();
         return null;
     }
 
 
     /**
      * Method remove
      *
      * Removes this key value pair from the table. Its time complexity 
      * is O(1) because the key can only be in 1 of 2 locations.
      *
      * @param key the key to remove
      * @param value the value to remove
      * @return successful removal
      */
     public boolean remove(K key, V value) {
         int pos1 = hash1(key);
         int pos2 = hash2(key);
         if (table[pos1] != null && table[pos1].getValue().equals(value)) {
             table[pos1] = null;
             return true;
         }
         else if (table[pos2] != null && table[pos2].getValue().equals(value)) {
             table[pos2] = null;
             return true;
         }
         return false;
     }
 
 
     /**
      * Method printTable
      *
      * The method will prepare a String representation of the table of 
      * the format
      *      [ <k1, v1> <k2. v2> ... <kn, vn> ]
      * where n is the number of <key, value> pairs.
      *
      * @return the table's contents as a String
      */
 
     public String printTable() {
         StringBuilder sb = new StringBuilder();
         sb.append("[ ");
         for (int i=0; i<CAPACITY; ++i) {
             if (table[i] != null) {
                 sb.append("<");
                 sb.append(table[i].getBucKey()); //key
                 sb.append(", ");
                 sb.append(table[i].getValue()); //value
                 sb.append("> ");
             }
         }
         sb.append("]");
         return sb.toString();
     }
 
 
     /**
      * Method rehash
      *
      * This method regrows the hashtable to capacity: 2*old capacity + 1 
      * and reinserts (rehashes) all the <key,value> pairs.
      *
      * This method invokes the 'put' method, so it is possible that 
      * another cycle is found when rehashing the hashmap. If this occurs,
      * this function can be invoked recursively via the 'put' method.
      */
 
     private void rehash() {
         Bucket<K, V>[] tableCopy = table.clone();
         int OLD_CAPACITY = CAPACITY;
         CAPACITY = (CAPACITY * 2) + 1;
         table = new Bucket[CAPACITY];
 
         for (int i=0; i<OLD_CAPACITY; ++i) {
             if (tableCopy[i] != null) {
                 put(tableCopy[i].getBucKey(), tableCopy[i].getValue());
             }
         }
     }
 
 }