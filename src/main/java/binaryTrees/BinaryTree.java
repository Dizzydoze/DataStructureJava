package binaryTrees;

import queues.ArrayQueue;
import queues.Queue;
import stacks.ArrayStack;
import stacks.Stack;

public class BinaryTree {

    private class BinaryTreeNode{
        private int data;
        private BinaryTreeNode left;
        private BinaryTreeNode right;

        public BinaryTreeNode(int elem){
            this.data = elem;
        }
    }

    private BinaryTreeNode root;

    public void printPreorder(){
        printPreorderRecursive(root);
    }

    private void printPreorderRecursive(BinaryTreeNode node){
        if (node != null){
            System.out.print(" " + node.data + " ");
            printPreorderRecursive(node.left);
            printPreorderRecursive(node.right);
        }
    }

    public void printPreorderIterative(){
        if (root == null){
            return;
        }
        BinaryTreeNode curr = root;
        Stack stack = new ArrayStack();
        stack.push(curr);
        // right, left <-- <--
        while (!stack.isEmpty()){
            curr = (BinaryTreeNode) stack.pop();
            System.out.print(" " + curr.data + " ");
            if (curr.right != null){
                stack.push(curr.right);
            }
            if (curr.left != null){
                stack.push(curr.left);
            }
        }
    }

    public void printInorderRecursive(){
        printInorderRecursive(root);

    }
    private void printInorderRecursive(BinaryTreeNode node){
        if (node == null){
            return;
        }
        printInorderRecursive(node.left);
        System.out.print(" " + node.data + " ");
        printInorderRecursive(node.right);
    }

    public void printInorderIterative(){
        if (root == null){
            return;
        }
        BinaryTreeNode curr = root;
        Stack stack = new ArrayStack();

        while (!stack.isEmpty() || curr != null){
            if (curr != null){      // keep going left, push to stack
                stack.push(curr);
                curr = curr.left;
            }
            else{                   // reach the end of current left, pop and go right
                curr = (BinaryTreeNode) stack.pop();
                System.out.print(" " + curr.data + " ");
                curr = curr.right;
            }
        }
    }

    public void printPostorderRecursive(){
        printPostorderRecursive(root);
    }

    private void printPostorderRecursive(BinaryTreeNode node){
        if (node == null){
            return;
        }
        printPostorderRecursive(node.left);
        printPostorderRecursive(node.right);
        System.out.print(" " + node.data + " ");
    }

    public void printPostorderIterative(){
        if (root == null){
            return;
        }
        // double stack
        Stack s1 = new ArrayStack();
        Stack s2 = new ArrayStack();
        BinaryTreeNode curr = root;
        s1.push(curr);

        while (!s1.isEmpty()){
            // pop s1 and save it to s2
            curr = (BinaryTreeNode) s1.pop();
            s2.push(curr);
            // add children to s1
            if (curr.left != null){
                s1.push(curr.left);
            }
            if (curr.right != null){
                s1.push(curr.right);
            }
        }
        // reverse s2 is post order traversal
        while (!s2.isEmpty()){
            curr = (BinaryTreeNode) s2.pop();
            System.out.print(" " + curr.data + " ");
        }
    }


    public void printNodesByLevel(){
        if (root == null){
            return;
        }
        Queue queue = new ArrayQueue(20);
        queue.enqueue(root);

        int size = 1;       // num of elements in the queue, use for level traversal
        while (!queue.empty()){
            int levelSize = size;
            for (int i = 0; i < levelSize; i++) {
                BinaryTreeNode curr = (BinaryTreeNode) queue.dequeue();
                size--;
                System.out.print(" " + curr.data + " ");
                // normal order for queue
                if (curr.left != null){
                    queue.enqueue(curr.left);
                    size++;
                }
                if (curr.right != null){
                    queue.enqueue(curr.right);
                    size++;
                }
            }
            System.out.println();
        }
    }

    public String serializeUsingPreorder(){
        return serializeUsingPreorder(root);
    }

    private String serializeUsingPreorder(BinaryTreeNode node){
        StringBuilder sb = new StringBuilder();
        if (node == null){
            sb.append("/");
            return sb.toString();
        }
        // recursive steps
        sb.append(" " + node.data + " ");
        sb.append(serializeUsingPreorder(node.left));
        sb.append(serializeUsingPreorder(node.right));
        return sb.toString();
    }


    public void createSampleTree() {
        // Note: this is not a binary search tree, just a binary tree
        root = new BinaryTreeNode(5);
        BinaryTreeNode node1 = new BinaryTreeNode(10);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(35);
        BinaryTreeNode node4 = new BinaryTreeNode(3);
        BinaryTreeNode node5 = new BinaryTreeNode(18);
        BinaryTreeNode node6 = new BinaryTreeNode(29);
        BinaryTreeNode node7 = new BinaryTreeNode(6);
        BinaryTreeNode node8 = new BinaryTreeNode(5);
        BinaryTreeNode node9 = new BinaryTreeNode(1);
        BinaryTreeNode node10 = new BinaryTreeNode(9);

        root.right =  node1;
        root.left = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node4.right = node8;
        node5.right = node9;
        node9.left = node10;
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.createSampleTree();

        System.out.println("Recursive Preorder");
        tree.printPreorder();
        System.out.println();

        System.out.println("Iterative Preorder");
        tree.printPreorderIterative();
        System.out.println();

        System.out.println("Serialize Preorder");
        System.out.println(tree.serializeUsingPreorder());
        System.out.println();

        System.out.println("Level Traversal");
        tree.printNodesByLevel();
        System.out.println();

        System.out.println("Recursive Inorder");
        tree.printInorderRecursive();
        System.out.println();

        System.out.println("Iterative Inorder");
        tree.printInorderIterative();
        System.out.println();

        System.out.println("Recursive Postorder");
        tree.printPostorderRecursive();
        System.out.println();

        System.out.println("Iterative Postorder");
        tree.printPostorderRecursive();
        System.out.println();
    }
}
