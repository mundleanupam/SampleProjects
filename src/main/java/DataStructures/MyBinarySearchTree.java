package DataStructures;

import java.util.LinkedList;
import java.util.Queue;

public class MyBinarySearchTree {

    public static TreeNode root;

    public MyBinarySearchTree(){
        this.root = null;
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

    public void maxAtDepth()
    {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int depth = 0;
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

    public static void main(String arg[]){
        MyBinarySearchTree b = new MyBinarySearchTree();
        b.insert(20);b.insert(15);
        b.insert(25);b.insert(10);b.insert(18);b.insert(16);b.insert(19);
        //b.insert(9);b.insert(20);b.insert(25);b.insert(15);b.insert(16);
        System.out.println("Original Tree : ");
        b.display(b.root);
        System.out.println("");
        System.out.println("BFS Display");
        b.BFS();
        System.out.println("");
        System.out.println("Check whether TreeNode with value 4 exists : " + b.find(4));
        System.out.println("Delete TreeNode with no children (2) : " + b.delete(19));
        b.display(root);
        System.out.println("\n Delete TreeNode with one child (4) : " + b.delete(4));
        b.display(root);
        System.out.println("\n Delete TreeNode with Two children (10) : " + b.delete(10));
        b.display(root);
        System.out.println("*************");
        printTree(root);
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
