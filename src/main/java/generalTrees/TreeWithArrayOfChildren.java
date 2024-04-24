package generalTrees;


import binaryTrees.BinaryTree;
import com.sun.source.tree.Tree;

public class TreeWithArrayOfChildren {

    public static int MAX_NUM_CHILDREN = 5;

    private TreeNode root;

    private class TreeNode{
        private Object data;
        private TreeNode[] children;

        public TreeNode(Object data){
            this.data = data;
            this.children = new TreeNode[MAX_NUM_CHILDREN];
        }
    }

    public void printPreorder(){
        printPreorder(root);
    }

    private void printPreorder(TreeNode node){
        // dfs recursive
        if (node == null){
            return;
        }
        System.out.print(" " + node.data + " ");
        // iterate all children for current node
        for (int i = 0; i < MAX_NUM_CHILDREN; i++) {
            printPreorder(node.children[i]);
        }
    }

    public int countNodes(){
        return countNodes(root);
    }

    private int countNodes(TreeNode node){
        if (node == null){
            return 0;
        }
        // count children num of current node
        int count = 0;
        // all children will be null for leave node
        for (int i = 0; i < MAX_NUM_CHILDREN; i++) {
            count += countNodes(node.children[i]);
        }
        return 1 + count;       // current node +1
    }

    public int height(){
        return height(root);
    }

    private int height(TreeNode node){
        if (node == null){
            return 0;
        }
        int maxHeight = Integer.MIN_VALUE;
        for (int i = 0; i < MAX_NUM_CHILDREN; i++) {
            int childHeight = height(node.children[i]);
            maxHeight = Math.max(maxHeight, childHeight);
        }
        return 1 + maxHeight;
    }

    public int countLeaves(){
        return countLeaves(root);
    }

    private int countLeaves(TreeNode node){
        if (node == null){
            return 0;
        }
        if (isLeaf(node)){
            return 1;
        }
        int count = 0;
        for (int i = 0; i < MAX_NUM_CHILDREN; i++) {
            count += countLeaves(node.children[i]);
        }
        return count;
    }

    private boolean isLeaf(TreeNode node){
        if (node == null){
            return false;
        }
        for (int i = 0; i < MAX_NUM_CHILDREN; i++) {
            if (node.children[i] != null){
                return false;
            }
        }
        return true;
    }

    public String serialize(){
        return serialize(root);
    }

    private String serialize(TreeNode node){
        if (node == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();

        sb.append(" " + node.data + " ");
        for (int i = 0; i < MAX_NUM_CHILDREN; i++) {
            sb.append(serialize(node.children[i]));
        }
        // current node completed
        sb.append(") ");
        return sb.toString();
    }


    public void createSimpleTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        node1.children[0] = node11;
        node1.children[1] = node12;
        node1.children[2] = node13;

        TreeNode node111 = new TreeNode(111);
        TreeNode node112 = new TreeNode(112);
        node11.children[0]= node111;
        node11.children[1] = node112;

        TreeNode node131 = new TreeNode(131);
        TreeNode node132 = new TreeNode(132);
        TreeNode node133 = new TreeNode(133);
        node13.children[0] = node131;
        node13.children[1] = node132;
        node13.children[2] = node133;

        this.root = node1;
    }

    public static void main(String[] args) {
        TreeWithArrayOfChildren tree = new TreeWithArrayOfChildren();
        tree.createSimpleTree();
        tree.printPreorder();
        System.out.println();
        System.out.println("countNodes: " + tree.countNodes());
        System.out.println("Height: " + tree.height());
        System.out.println("countLeaves: " + tree.countLeaves());
        System.out.println(tree.serialize());
    }
}
