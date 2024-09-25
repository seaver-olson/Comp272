
/*
 * *** YOUR NAME GOES HERE / YOUR SECTION NUMBER ***
 *
 * This java file is a Java object implementing simple AVL Tree.
 * You are to complete the deleteElement method.
 *
 */

import java.lang.Math;


/**
 *  Class: Node
 *
 *  This class represents an inner node of the AVL Tree. Additional candidate  
 *  attributes can be considered, but if added, they must be maintained during 
 *  tree modifications, including rotations.
 * 
 *  The values currently maintained in each node include:
 *    - value : This is the value of the node. 
 *    - height: Height of the node in the tree. A node's height is the maximum 
 *              number of edges to its deepest leaf of its two subtrees.
 * 
 *  Additional candidate values that can be considered (but currently not 
 *  implemented):
 *    - size: # of nodes in a node's subtrees
 *    - bf  : the nodes's balance factor, e.g., {-1, 0, 1}
 *
 */

class Node {
        int value;                      // the node's value
        int height;                     // height of node based on its [sub]trees
        Node leftChild, rightChild;     // left and right subtrees

        public Node(int data) {         // parameterized constructor
            value = data;
            height = 0;
            leftChild = rightChild = null;
        }
}


/**
 *  Class 'LUC_AVLTree'
 *
 *  LUC_AVLTree class definition for constructing / accessing / modifying the 
 *  AVL tree. In order to provide a general purpose AVL tree, better error 
 *  handling should to be provided.
 *
 *  Notes:
 *   1. This AVL tree does not allow duplicates, if one is attempted to be 
 *      inserted, no action is taken by the method (though an error should be 
 *      thrown).
 *   2. If a deletion is attempted for a non-existent value, no action is taken
 *      (again, returning an error indicator and/or throwing an error would be 
 *      appropriate.
 *
 *  Public methods:
 *   void    removeAll()         - Remove all nodes of the tree (empties tree)
 *   boolean checkEmpty()        - Returns boolean value if tree is empty or not
 *   void    insert(int value)   - inserts 'value' into the tree
 *   void    delete(int value)   - removes 'value' from the tree
 *   String  preorderTraversal() - returns a preorder traversal of tree in a String
 *
 */

class LUC_AVLTree {
    private Node rootNode;           // The root node of the AVL Tree

    public LUC_AVLTree()              { rootNode = null; }       // Constructor
    public void removeAll()           { rootNode = null; }       // Make tree empty
    public boolean checkEmpty()       { if (rootNode == null) return true; else return false; }
    public void insert(int value)     { rootNode = insertElement(value, rootNode); }
    public void delete(int value)     { rootNode = deleteElement(value, rootNode); }
    public String preorderTraversal() { return preorderTraversal(rootNode); }

    private boolean isTreeBalanced()   { return isTreeBalanced(rootNode); }
    private boolean isBST()            { return isBST(rootNode); }
    private int getHeight(Node node)  { return node == null ? -1 : node.height; }
    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight) {
        return leftNodeHeight > rightNodeHeight ? leftNodeHeight : rightNodeHeight;
    }


    /**
     * Method isTreeBalanced
     *
     * isTreeBalanced() is private method which uses recursion based on 
     * preorder traversal to determine if an AVL [sub]tree is balanced. For 
     * an AVL tree to be balanced, every node must have a |bf| <= 1
     *
     * @param node - the top of the [sub]tree to check
     *
     * @return boolean [true if balanced, else false]
     */

    private boolean isTreeBalanced(Node node) {
        if (node == null)
            return true;

        int leftSubTree  = getHeight(node.leftChild)  + 1;
        int rightSubTree = getHeight(node.rightChild) + 1;

        // Calculate the bf
        if (Math.abs(leftSubTree - rightSubTree) > 1)
            return false;

        return isTreeBalanced(node.leftChild) && isTreeBalanced(node.rightChild);
    }

    /**
     *  Method isBST 
     *
     *  The method determines if a supplied tree node meets the requirements of
     *  of a Binary Search Tree (BST)
     *
     *  Return true for meeting BST requirements, else false
     */

    private boolean isBST( Node node) {

        if (node == null) 
            return true;

        // false if the max value of the left subtree is > than us
        if ( (node.leftChild != null) && (maxValue(node.leftChild) > node.value) )     
            return false;

        // false if the min value of the right subtree is <= than us
        if ( (node.rightChild != null) && (minValue(node.rightChild) < node.value) )
            return false;

        // false if, recursively, the left or right is not a BST
        if ( ( ! isBST(node.leftChild) ) || ( ! isBST(node.rightChild) ) )                     
            return false;

        return true;
    }


    /**
     *  Return max value of subtree below node (uses recursion).
     */

    private int maxValue(Node node) {
      
        if (node == null) 
            return  Integer.MIN_VALUE;

        int value    = node.value;
        int leftMax  = maxValue(node.leftChild);
        int rightMax = maxValue(node.rightChild);

        return Math.max(value, Math.max(leftMax, rightMax));
    }

    /**
     *  Return min value of subtree below node (uses recursion).
     */

    private int minValue(Node node) {
      
        if (node == null) 
            return Integer.MAX_VALUE;

        int value    = node.value;
        int leftMin  = minValue(node.leftChild);
        int rightMin = minValue(node.rightChild);

        return Math.max(value, Math.max(leftMin, rightMin));
    }



    /**
     *  Method preorderTraversal
     *
     *  prorderTraversal() is a private method that uses recursion. It prints the
     *  [sub]tree using an preorder traversal method. For each node printed, it
     *  prints the pair "[X, Y]" where X represents the value of the node, and Y
     *  represents the node's height in the tree. Think of the node's height as the
     *  number of edges to the deepest leaf of that node.
     *
     *  @param node - the [sub]tree to start the preorder traversal
     *
     *  @return void
     */

    private String preorderTraversal(Node node) {
        if (node == null)
            return "";

        return node.value + " " + preorderTraversal(node.leftChild)
                + preorderTraversal(node.rightChild);
    }


    /**
     *  Method: InsertElement
     *
     *  insertElement() is private method that uses recursion. It will insert a
     *  new element in the tree as a leaf. And, as this function returns through
     *  recursion, each ancestor's node's balance factor (bf) is re-checked. If
     *  at each ancestor node, the |bf| > 1, the appropriate rotation (LL, LR, 
     *  RR, or RL) is performed in order to bring that node's  |bf| <= 1. 
     *  
     *  The only nodes that need to have their bf re-checked are the direct 
     *  ancestor nodes of the newly inserted node, all the way back to the root
     *  node. Any of these nodes may now be out of balanced due to this newly 
     *  inserted node causing a rotation to be required.
     *
     *  Additionally, as this method returns through recursion, it
     *   1. re-adjusts the 'height' variable in each ancestor's node. And,
     *   2. returns the current node. If a rotation is done, then this top node
     *      will change. This allows, through recursion, the invoker to readjust 
     *      the parent's child pointer to this returned node.
     *
     *  @param value - the value to insert below node
     *  @param node  - the top node where to insert the value at.
     *
     *  @return node - the new top of subtree; possibly changed due to a rotation
     */

    private Node insertElement(int value, Node node) {

        // Method is called recursively, if null, then create a
        // new node as a leaf and return this newly created node.
        if (node == null) {
            node = new Node(value);
            return node;
        }

        /*
         * If the inserting 'value' is less than the current node's value, then 
         * we are inserting to the LEFT of this node, else we are inserting to 
         * the RIGHT of it.
         *
         * This AVL tree does not allow duplicates, so do nothing if one is 
         * found. Normally, we should throw an error indicating the application 
         * tried to insert a duplicate.
         *
         * Last, note that If a rotation occurred  during an insert in this 
         * node's subtree, then we need to adjust this node's pointer to the 
         * new top of that subtree.
         */
        if (value < node.value) {
            node.leftChild = insertElement(value, node.leftChild);
            int bf = getBalanceFactor(node);

            // Check bf of node to determine if a LL or LR Rotation is needed
            if (Math.abs(bf) > 1) {

                // We need to re-balance, check if the 'value' was inserted to the
                // left or right of this node's left child. This determines if 
                // LL or LR is needed.
                if (value < node.leftChild.value)
                    node = LLRotation(node);
                else
                    node = LRRotation(node);
            }
        } else if (value > node.value ) {

            // Inserting to the right...
            node.rightChild = insertElement(value, node.rightChild);
            int bf = getBalanceFactor(node);

            // Check bf and if a RR or RL Rotation is needed
            if (Math.abs(bf) > 1 ) {
                // Re-balance needed, check if inserted to the right or 
                // left of child.
                if (value > node.rightChild.value)
                    node = RRRotation(node);
                else
                    node = RLRotation(node);
            }
        } else
            ; // value is duplicate, do nothing.

        /*
         * Re-adjust current node's height, this will also be done for each 
         * ancestor node (if one exists) as we return through recursion. If 
         * one or more rotations occurred, then the node's height would have 
         * changed. Doing so, will re-adjust the height of each ancestor node 
         * through to the root.
         */
      
        node.height = (getMaxHeight( getHeight(node.leftChild), getHeight(node.rightChild))) + 1;

        return node;
    }


    /**
     *  Method deleteElement
     *
     *  deleteElement() is private method that uses recursion. It will delete 
     *  an element within the tree. It considers several scenarios on where the 
     *  element to delete is in the tree.
     *
     *  This includes the following scenarios of where the node to delete is 
     *  in the tree:
     *    1. leaf node - simpliest case, just return null (which removes node)
     *    2. interior node with only left subtree below it (node gets replaced 
     *       with left subtree)
     *    3. interior node with only right subtree below it (node gets replaced
     *       with right subtree)
     *    4. interior node with both a left and right subtree below it. In this
     *       scenario, it gets the inorder successor node (aka, the smallest 
     *       value node in the right subtree, this is accomplished by using 
     *       method minValueNode()). Once found, it then copies the inorder
     *       successor node's value to this node, which for all purposes deletes 
     *       the node. Finally, it needs to delete that inorder successor node 
     *       from the right subtree.
     *
     *  When deleting a node, the heights need to be recalculated, including on 
     *  all ancestor nodes to the root. And for each node, if the bf is > 1, a 
     *  rotation is needed.
     *
     *  So, scenario 1 from above, we simply remove the leaf node and 
     *  recalculates the heights of each of its ancestors back to the root. 
     * 
     *  On scenarios 2 and 3, we simply pull up the appropriate subtree to 
     *  replace the deleted node and recalculate the height for this node and 
     *  each of its ancestors back to the root.
     *
     *  In scenario 4, the routine pulls up (and deletes) the "Inorder 
     *  Successor" node on its right subtree. It will be a leaf node. 
     * 
     *  This node (and its ancestors nodes) may need re-balancing; so the bf 
     *  is checked at the end of this routine and if needing re-balancing, it 
     *  invokes the proper rotation (LL, LR, RR, RL).
     *
     *  Again, the heights for each of its ancestor nodes are re-recalculate 
     *  to the root.
     *
     *  @param value - the value to delete from the tree
     *  @param node  - the top node of the [sub]tree where the delete will occur
     *
     *  @return node - new top of subtree, it possibly changed due to a rotation
     */

    private Node deleteElement(int value, Node node) {

        /*
         * ADD CODE HERE
         * 
         * NOTE, that you should use the existing coded private methods
         * in this file, which include:
         *      - minValueNode,
         *      - getMaxHeight,
         *      - getHeight,
         *      - getBalanceFactor,
         *      - LLRotation
         *      - RRRotation,
         *      - LRRotation,
         *      - RLRotation.
         *
         * To understand what each of these methods do, see the method prologues and
         * code for each. You can also look at the method InsertElement, as it has do
         * do many of the same things as this method.
         */

        return node;
    }


    /**
     *  Method getBalance
     *
     *  getBalance() is a private method that returns the balance factor (bf) 
     *  of a node. If the return value is within the set {1, 0, -1} then it is 
     *  balanced. If it is out of the range, then the node is unbalanced.
     *
     *  @param: node - the node to check its bf
     *
     *  @return: bf - an integer indicating the node's bf
     */

    private int getBalanceFactor(Node node) {
        if (node == null) return 0;

        int leftSubTreeHeight  = getHeight(node.leftChild)  + 1;
        int rightSubTreeHeight = getHeight(node.rightChild) + 1;

        return leftSubTreeHeight - rightSubTreeHeight;
    }


    /**
     *  Method minValueNode
     *
     *  minValueNode() is a private method that given a node, it will return 
     *  a pointer to the node that represents the minimum value on its left 
     *  subtree. By definition of a AVL tree, it will be a leaf node.
     *
     *  @param: node -  node to search for the min value under its left subtree.
     *
     *  @return: current - the pointer to the node containing the minimal value.
     */

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.leftChild != null)
            current = current.leftChild;
        return current;
    }


    /**
     *  Method: LLRotation
     *
     *  LLRotation() is a private method that performs a rotation where node X 
     *  is the node out-of-balance due to Left Left children. After the rotation,
     *  its left sub-child, Y, becomes the new top of this [sub]tree. And, node 
     *  Y's right sub-child, yr, becomes the left sub-child of X. Node Y is 
     *  returned as the new top to the [sub]tree. The height values of nodes X 
     *  and Y are re-adjusted. Z's height does not change.
     *
     *               X
     *             /   \
     *            Y     xr                     Y
     *          /   \          ==>          /     \
     *         Z     yr                    Z       X
     *        / \                        /   \    /  \
     *       zl  zr                     zr   zl  yr  xr
     *
     *  @param X - node that is out of balance
     *
     *  @return Y - new top node of [sub]tree after rotation
     */

    private Node LLRotation( Node x) {
        Node y = x.leftChild;
        x.leftChild = y.rightChild;
        y.rightChild = x;

        // Re-calculate the node heights
        x.height = getMaxHeight( getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        y.height = getMaxHeight( getHeight(y.leftChild), getHeight(y.rightChild)) + 1;

        return y;
    }


    /**
     *  Method: LRRotation
     *
     *  LRRotation() is a private method that performs a rotation where node X 
     *  is the node out-of-balance due to Left Right children. After the 
     *  rotation, node X's left Right grand-child, Z, becomes the new top of the 
     *  [sub]tree. And, Z's left sub-child, zl, becomes the right sub-child of 
     *  Y, and right sub-child, zr, becomes the left sub-child of X. Node Z is 
     *  returned as the new top to the [sub]tree. The height values of nodes X, Y,
     *  and Z are adjusted, each changes in this rotation.
     *
     *          X
     *        /   \
     *        Y     xr                     Z
     *      /   \          ==>           /   \
     *     yl     Z                    Y       X
     *           / \                 /   \    /  \
     *          zl  zr              yl   zl  zr  xr
     *
     *  @param X - node that is out of balance
     *
     *  @return Z - new top of the [sub]tree after rotation
     */

    private Node LRRotation( Node x) {
        Node y = x.leftChild;
        Node z = x.leftChild.rightChild;
        y.rightChild = z.leftChild;
        x.leftChild  = z.rightChild;
        z.leftChild  = y;
        z.rightChild = x;

        // Re-calculate the node heights
        x.height = getMaxHeight(getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        y.height = getMaxHeight(getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        z.height = getMaxHeight(getHeight(z.leftChild), getHeight(z.rightChild)) + 1;

        return z;
    }


    /**
     *  Method: RRRotation
     *
     *  RRRotation() is a private method that performs a rotation where node X 
     *  is the node out-of-balance due to Right Right children. After the 
     *  rotation, its right sub-child, Y, becomes the new top of this
     *  [sub]tree. And, node Y's left sub-child, yl, becomes the right sub-child 
     *  of X. Node Y is returned as the new top to the [sub]tree. The height 
     *  values of nodes X and Y are re-adjusted. Z's height does not change.
     *
     *               X
     *             /   \
     *            xl     Y                          Y
     *                 /   \        ==>           /    \
     *               yl     Z                    X       Z
     *                     / \                 /   \    /  \
     *                    zl  zr              xl   yl  zl  zr
     *
     *  @param X - node that is out of balance
     *
     *  @return Y - new top node of [sub]tree after rotation
     */

    private Node RRRotation( Node x) {
        Node y = x.rightChild;
        x.rightChild = y.leftChild;
        y.leftChild = x;

        // Re-calculate the node heights
        x.height = getMaxHeight( getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        y.height = getMaxHeight( getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        return y;
    }


    /**
     *  Method: RLRotation
     *
     *  RLRotation performs a rotation where node X is the node out-of-balance
     *  due to Right Left children. After the rotation, node X's right left 
     *  grandchild, Z, becomes the new top of the [sub]tree. And, Z's left 
     *  subchild, zl, becomes the right subchild of X, and right subchild, zr, 
     *  becomes the left subchild of Y. Node Z is returned as the new top to the 
     *  [sub]tree. The height values of nodes X, Y, and Z are adjusted, each 
     *  changes in this rotation.
     *
     *          X
     *        /   \
     *       xl     Y                           Z
     *            /   \         ==>           /   \
     *           Z     yr                   X       Y
     *          / \                       /   \    /  \
     *         zl  zr                    xl   zl  zr  yr
     *
     *  @param X - node that is out of balance
     *
     *  @return Z - new top of the [sub]tree after rotation
     */

    private Node RLRotation( Node x) {
        Node y = x.rightChild;
        Node z = x.rightChild.leftChild;
        y.leftChild  = z.rightChild;
        x.rightChild = z.leftChild;
        z.rightChild = y;
        z.leftChild  = x;

        // Re-calculate the node heights
        x.height = getMaxHeight( getHeight(x.leftChild), getHeight(x.rightChild)) + 1;
        y.height = getMaxHeight( getHeight(y.leftChild), getHeight(y.rightChild)) + 1;
        z.height = getMaxHeight( getHeight(z.leftChild), getHeight(z.rightChild)) + 1;
        return z;
    }
}
