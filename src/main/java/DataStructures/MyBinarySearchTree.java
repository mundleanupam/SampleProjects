package DataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MyBinarySearchTree {

    public static TreeNode root;

    public MyBinarySearchTree(){
        this.root = null;
    }

    // Insert an element in a BST
    public void insert(int id){
        TreeNode newTreeNode = new TreeNode(id);
        if(root==null){
            root = newTreeNode;
            return;
        }
        TreeNode current = root;
        TreeNode parent = null;
        while(true){
            parent = current;
            if(id<current.data){ //current.data>id
                current = current.left;
                if(current==null){
                    parent.left = newTreeNode;
                    return;
                }
            }else{
                current = current.right;
                if(current==null){
                    parent.right = newTreeNode;
                    return;
                }
            }
        }
    }

    public boolean find(int id){
        TreeNode current = root;
        while(current!=null){
            if(current.data==id){
                return true;
            }else if(current.data>id){
                current = current.left;
            }else{
                current = current.right;
            }
        }
        return false;
    }

    public boolean delete(int id){
        TreeNode parent = root;
        TreeNode current = root;
        boolean isLeftChild = false;
        while(current.data!=id){
            parent = current;
            if(current.data>id){
                isLeftChild = true;
                current = current.left;
            }else{
                isLeftChild = false;
                current = current.right;
            }
            if(current ==null){
                return false;
            }
        }
        //if i am here that means we have found the TreeNode
        //Case 1: if TreeNode to be deleted has no children
        if(current.left==null && current.right==null){
            if(current==root){
                root = null;
            }
            if(isLeftChild ==true){
                parent.left = null;
            }else{
                parent.right = null;
            }
        }
        //Case 2 : if TreeNode to be deleted has only one child
        else if(current.right==null){
            if(current==root){
                root = current.left;
            }else if(isLeftChild){
                parent.left = current.left;
            }else{
                parent.right = current.left;
            }
        }
        else if(current.left==null){
            if(current==root){
                root = current.right;
            }else if(isLeftChild){
                parent.left = current.right;
            }else{
                parent.right = current.right;
            }
        }else if(current.left!=null && current.right!=null){

            //now we have found the minimum element in the right sub tree
            TreeNode successor	 = getSuccessor(current);
            if(current==root){
                root = successor;
            }else if(isLeftChild){
                parent.left = successor;
            }else{
                parent.right = successor;
            }
            successor.left = current.left;
        }
        return true;
    }

    public TreeNode getSuccessor(TreeNode deleleTreeNode){
        TreeNode successsor =null;
        TreeNode successsorParent =null;
        TreeNode current = deleleTreeNode.right;
        while(current!=null){
            successsorParent = successsor;
            successsor = current;
            current = current.left;
        }
        //check if successor has the right child, it cannot have left child for sure
        // if it does have the right child, add it to the left of successorParent.
//		successsorParent
        if(successsor!=deleleTreeNode.right){
            successsorParent.left = successsor.right;
            successsor.right = deleleTreeNode.right;
        }
        return successsor;
    }

    public void BFS()
    {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            /* poll() removes the present head.
            For more information on poll() visit
            http://www.tutorialspoint.com/java/util/linkedlist_poll.htm */
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.data + " ");

            /*Enqueue left child */
            if (tempNode.left != null) {
                queue.add(tempNode.left);
            }
            /*Enqueue right child */
            if (tempNode.right != null) {
                queue.add(tempNode.right);
            }
        }
    }

    // In-Order traversal to print the elements from smallest to largest
    void inOrderTraversal(TreeNode node) {
        if (node != null) {
        inOrderTraversal(node.left);
        System.out.println(node.data);
        inOrderTraversal(node.right);
        }
    }
    // 7,4,2 - print 2
    // 3 - print 3
    //

    // Pre-Order traversal to print
    void preOrderTraversal(TreeNode node) {
        if (node != null) {
            System.out.println(node.data);
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // Post-Order traversal to print
    void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.println(node.data);
        }
    }

    // In order Display of tree
    public void display(TreeNode root){
        if(root!=null){
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }


    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty");
            return ;
        }
        Queue queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            System.out.printf(" %d",node.data);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        System.out.println("");
        return;
    }

    static void printLevelOrder(TreeNode root) {
        // Base Case
        if (root == null)
            return;
        // Create an empty queue for level order tarversal
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        // Enqueue Root and initialize height
        q.add(root);
        while (true) {
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;
            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0) {
                TreeNode node = q.peek();
                System.out.print(node.data + " ");
                q.remove();
                if (node.left != null)
                    q.add(node.left);
                if (node.right != null)
                    q.add(node.right);
                nodeCount--;
            }
            System.out.println();
        }
    }

    // maxAtLevel determines the maximum value element at each level of the BST or BT
    // Essentially it is the right most element at each level

    static void maxAtLevel(TreeNode root) {
        // Base Case
        int max = root.data;
        int level = 0;
        if (root == null)
            return;
        // Create an empty queue for level order tarversal
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        // Enqueue Root and initialize height
        q.add(root);
        while (true) {
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;
            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level
            while (nodeCount > 0) {
                TreeNode node = q.peek();
                q.remove();
                if (node.left != null)
                    q.add(node.left);
                    if (max<node.data) max = node.data;
                if (node.right != null)
                    q.add(node.right);
                    if (max<node.data) max = node.data;
                nodeCount--;
            }
            System.out.println("Max at level " + level + " is " + max);
            System.out.println();
            level++;
        }
    }

    static void createLevelLinkedList(TreeNode root) {
        // Base Case
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<LinkedList<TreeNode>>();
        int level = 0;
        if (root == null)
            return;
        // Create an empty queue for level order tarversal
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        // Enqueue Root and initialize height
        q.add(root);
        LinkedList<TreeNode> rootNode = new LinkedList<>();
        rootNode.add(root);
        lists.add(rootNode);
        while (true) {
            LinkedList<TreeNode> list = new LinkedList<>();
            // nodeCount (queue size) indicates number of nodes
            // at current level.
            int nodeCount = q.size();
            if (nodeCount == 0)
                break;
            // Dequeue all nodes of current level and Enqueue all
            // nodes of next level

            while (nodeCount > 0) {
                TreeNode node = q.peek();
                q.remove();
                if (node.left != null) {
                    q.add(node.left);
                    list.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                    list.add(node.right);
                }
                nodeCount--;
            }
            lists.add(list);
            System.out.println();
            level++;
        }
    }

    public static void main(String args[]){
        MyBinarySearchTree b = new MyBinarySearchTree();
//        b.insert(20);b.insert(15);
//        b.insert(25);b.insert(10);b.insert(18);b.insert(16);b.insert(19);

//        b.insert(50);b.insert(17);
//        b.insert(76);b.insert(9);b.insert(23);b.insert(54);b.insert(14);b.insert(19);b.insert(72);
//        b.insert(12);b.insert(67);
        //b.insert(9);b.insert(20);b.insert(25);b.insert(15);b.insert(16);

        // Completely balanced tree
        b.insert(7);b.insert(4);
        b.insert(2);b.insert(3);b.insert(6);b.insert(5);b.insert(12);
        b.insert(9);b.insert(8);b.insert(11);b.insert(19);b.insert(15);b.insert(20);

        System.out.println("Original Tree : ");
        printLevelOrder(root);
        maxAtLevel(root);
        createLevelLinkedList(root);
        b.inOrderTraversal(root);
        //printTree(root);
        //b.display(b.root);
        System.out.println("");
        System.out.println("BFS Display");
        b.BFS();
        System.out.println("");
        System.out.println("Check whether TreeNode with value 4 exists : " + b.find(4));
        System.out.println("Delete TreeNode with no children (2) : " + b.delete(19));
        b.display(root);
        System.out.println("\n Delete TreeNode with one child (4) : " + b.delete(4));
        printLevelOrder(root);
        //b.display(root);
        System.out.println("\n Delete TreeNode with Two children (10) : " + b.delete(10));
        printLevelOrder(root);
        //b.display(root);
        System.out.println("*************");
        printLevelOrder(root);
        //printTree(root);
        System.out.println("In-Order Traversal");
        b.inOrderTraversal(root);
        System.out.println("Pre-Order Traversal");
        b.preOrderTraversal(root);
        System.out.println("Post-Order Traversal");
        b.postOrderTraversal(root);

    }
}

class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    public TreeNode(int data){
        this.data = data;
        left = null;
        right = null;
    }
}
