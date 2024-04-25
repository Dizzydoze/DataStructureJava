package generalTrees;


public class TreeWithLinkedListOfChildren {

    private TreeNode root;

    private class TreeNode{
        private int data;
        private TreeNode leftChild;
        private TreeNode rightSibling;

        public TreeNode(int elem){
            this.data = elem;
        }
    }

    public void printPreorder(){
        printPreorder(root);
    }
    private void printPreorder(TreeNode node){
        if (node == null){
            return;
        }
        TreeNode curr = node;
        while (curr != null){
            // for each right node, we go as deep as possible
            System.out.print(" " + curr.data + " ");
            printPreorder(curr.leftChild);
            // come back from left end, go right
            curr = curr.rightSibling;
        }
    }

    public int countNodes(){
        return countNodes(root);
    }
    private int countNodes(TreeNode node){
        int count = 0;
        TreeNode curr = node.leftChild;
        // explore current level for this leftChild
        while (curr != null){
            count += countNodes(curr);
            curr = curr.rightSibling;
        }
        // the result of current level
        return 1 + count;       // current node +1
    }

    public int height(){
        return height(root);
    }

    private int height(TreeNode node){
        if (node == null){
            return 0;
        }
        TreeNode curr = node.leftChild;
        int maxHeight = 0;
        // explore current level for this child
        while (curr != null){
            int childHeight = height(curr);
            if (childHeight > maxHeight){
                maxHeight = childHeight;
            }
            curr = curr.rightSibling;
        }
        return 1 + maxHeight;       // current node height +1
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
        TreeNode curr = node.leftChild;
        while (curr != null) {
            count += countLeaves(curr);
            curr = curr.rightSibling;
        }
        // finish iteration of current node, return and go right
        // reach this line meaning this node is not leaf node, no need to +1
        return count;
    }

    private boolean isLeaf(TreeNode node){
        if (node == null){
            return false;
        }
        return node.leftChild == null;
    }

    public void serialize(){
        System.out.println(serialize(root));
    }

    private String serialize(TreeNode node){
        StringBuilder sb = new StringBuilder();
        sb.append(" " + node.data + " ");
        TreeNode curr = node.leftChild;
        while (curr != null){
            sb.append(serialize(curr));
            curr = curr.rightSibling;
        }
        // current level completed, add ")"
        sb.append(") ");
        return sb.toString();
    }

    public void createSimpleTree() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node11 = new TreeNode(11);
        TreeNode node12 = new TreeNode(12);
        TreeNode node13 = new TreeNode(13);
        node1.leftChild = node11;
        node11.rightSibling = node12;
        node12.rightSibling = node13;
        this.root = node1;

        TreeNode node111 = new TreeNode(111);
        TreeNode node112 = new TreeNode(112);
        node11.leftChild = node111;
        node111.rightSibling = node112;

        TreeNode node131 = new TreeNode(131);
        TreeNode node132 = new TreeNode(132);
        TreeNode node133 = new TreeNode(133);

        node13.leftChild = node131;
        node131.rightSibling = node132;
        node132.rightSibling = node133;

//        TreeNode node1311 = new TreeNode(1311);
//        node131.leftChild = node1311;

    }



    public static void main(String[] args) {
        TreeWithLinkedListOfChildren tree = new TreeWithLinkedListOfChildren();
        tree.createSimpleTree();
        tree.printPreorder();
        System.out.println();
        System.out.println(tree.countNodes());
        tree.serialize();
        System.out.println(tree.height());
        System.out.println(tree.countLeaves());
    }
}
