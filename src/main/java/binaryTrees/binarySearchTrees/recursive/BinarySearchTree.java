package binaryTrees.binarySearchTrees.recursive;

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

    public void insert(int elem){
        root = insert(root, elem);
    }

    /**
     * Insert elem into the tree with the given root
     * Average: O(logN), Worst: O(N) as it could be LinkedList
     * @param node root of a tree
     * @param elem element to insert
     * @return the root of a tree that contains the new node
     */
    private BSTNode insert(BSTNode node, int elem){
        if (node == null){
            return new BSTNode(elem);
        }
        if (elem < node.data){
            node.left = insert(node.left, elem);
        }
        else{
            node.right = insert(node.right, elem);
        }
        return node;        // return the new subtree for reconnection
    }

    public void delete(int elem){
        root = delete(root, elem);      // update the new root for the tree
    }

    /**
     * Average: O(logN), Worst: O(N) as it could be LinkedList
     * @param node
     * @param elem
     * @return
     */
    private BSTNode delete(BSTNode node, int elem){
        if (node == null){          // target node not found
            return null;
        }
        if (elem < node.data){
            node.left = delete(node.left, elem);
        }
        else if(elem > node.data){
            node.right = delete(node.right, elem);
        }
        else {                      // target elem found
            if (node.left == null){ // directly return right subtree, the only child
                return node.right;
            }
            else if(node.right == null){
                return node.left;
            }
            else{
                // both children exist
                // two ways
                // get the smallest element on the right subtree(I use this here)
                // OR get the biggest element on the left subtree
                if (node.right.left == null){
                    // if left child is null, current node is the smallest node in the BST tree
                    return node.right;
                }
                else {  // left child exists, get and remove the smallest node from right subtree
                    node.data = removeSmallest(node.right);
                    return node;
                }
            }
        }
        return node;
    }

    private int removeSmallest(BSTNode node){
        if (node.left.left == null){    // node.left if the smallest node
            int smallest = node.left.data;
            node.left = node.left.right;    // delete node.left, replace with its right subtree
            return smallest;
        }
        return removeSmallest(node.left);  // keep going left
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
