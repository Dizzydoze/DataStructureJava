package binaryTrees.binarySearchTrees.iterative;

public class BinarySearchTree {

    private class BSTNode{
        private int data;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(int elem){
            this.data = elem;
        }
    }

    private BSTNode root;
    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * Average: O(logN), Worst: O(N) as a LinkedList
     * @param elem
     * @return
     */
    public boolean find(int elem){
        BSTNode curr = this.root;
        while (curr != null){
            if (elem < curr.data){
                curr = curr.left;
            }
            else if (elem > curr.data){
                curr = curr.right;
            }
            else {      // target found
                return true;
            }
        }
        return false;   // not found
    }

    /**
     * Average: O(logN), Worst: O(N) as a LinkedList
     * @param elem
     */
    public void insert(int elem){
        // only diff is how to update the curr node, use prev
        BSTNode prev = null;
        BSTNode curr = this.root;

        // edge case, root is null
        if (root == null){
            root = new BSTNode(elem);
            return;
        }

        while (curr != null){
            if (elem < curr.data){
                prev = curr;
                curr = curr.left;
            }
            else {
                prev = curr;
                curr = curr.right;
            }
        }
        // reach the spot to insert
        if (elem < prev.data){
            prev.left = new BSTNode(elem);
        }
        else {
            prev.right = new BSTNode(elem);
        }
    }

    /**
     * Average: O(logN), Worst: O(N) as a LinkedList
     * @param elem
     */
    public void delete(int elem){
        if (root == null){      // not exist, nothing to delete
            return;
        }

        BSTNode prev = null;
        BSTNode curr = root;

        // find target node
        while (curr != null){
            if (elem < curr.data){
                prev = curr;
                curr = curr.left;
            }
            else if(elem > curr.data){
                prev = curr;
                curr = curr.right;
            }
            else{
                break;          // target found, start to delete
            }
        }

        // target not found, nothing to delete
        if (curr == null){
            return;
        }

        if (curr.left == null){             // NO LEFT, only right side, directly skip it
            if (prev == null){              // delete root
                root = root.right;
            }
            // check to see left or right
            else if (prev.left == curr){
                prev.left = curr.right;
            }
            else{// prev.right = curr
                prev.right = curr.right;
            }

        }
        else if (curr.right == null){       // NO RIGHT, only left side, directly skip it
            if (prev == null){              // delete root
                root = root.left;
            }
            // check to see left or right
            else if (prev.left == curr){
                prev.left = curr.left;      // skip
            }
            else{// prev.right = curr
                prev.right = curr.left;
            }

        }
        else {
            // BOTH LEFT && RIGHT children exist
            // two options: smallest on the right subtree, biggest on the left subtree
            // left tree empty, current node is the smallest on the right subtree
            BSTNode rightRoot = curr.right;
            BSTNode prevBeforeRightRoot = curr;
            // keep going left to find the smallest
            while (rightRoot.left != null){
                prevBeforeRightRoot = rightRoot;
                rightRoot = rightRoot.left;
            }
            // smallest found
            System.out.println("smallest in the right subtree " + rightRoot.data);
            // update value of the remove node
            int smallest = rightRoot.data;
            curr.data = smallest;

            // remove smallest, check LEFT or RIGHT
            if (prevBeforeRightRoot.left == rightRoot){
                prevBeforeRightRoot.left = rightRoot.left;  // skip
            }
            else {
                prevBeforeRightRoot.right = rightRoot.right;
            }
        }
    }

    public void printPreorder(){
        printPreorder(root);
    }

    private void printPreorder(BSTNode node){
        if (node != null){
            System.out.print(" " + node.data + " ");
            printPreorder(node.left);
            printPreorder(node.right);
        }
    }
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(6);
        tree.insert(1);
        tree.insert(10);
        tree.insert(8);
        tree.insert(12);
        tree.insert(16);
        tree.printPreorder();
        System.out.println();
        tree.delete(6);
        tree.printPreorder();
        System.out.println();
    }


}
