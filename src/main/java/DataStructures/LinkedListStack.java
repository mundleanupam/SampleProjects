package DataStructures;

import java.util.Stack;

public class LinkedListStack {

    public static void main(String[] args){

        LinkedListStack lStack = new LinkedListStack();
        lStack.push(1);
        lStack.push(2);
        lStack.push(3);
        lStack.push(4);
        lStack.push(5);
        lStack.display();
        System.out.println("Peek -> "+ lStack.peek());
        System.out.println("is Empty? -> "+ lStack.isEmpty());
        lStack.pop();
        lStack.display();
    }

    private StackNode top;
    private static int size;

    LinkedListStack(){
        size = 0;
        top = null;
    }

    public int getSize(){
        return size;
    }

    public int pop() {
        if (top == null) System.out.println("Stack Underflow");
        int item = top.data;
        top = top.next;
        size--;
        return item;
    }

    public void push(int item) {
        StackNode t = new StackNode(item);
        t.next = top;
        top = t;
        size++;
    }

    public int peek() {
        if (top == null) System.out.println("Stack Underflow");
        return top.data;
    }

    public void display() {
        if(top == null) System.out.println("Stack Underflow");
        StackNode current = top;
        while(current!=null){
            System.out.print(current.data + "-->");
            current = current.next;
        }
        System.out.print("null");
        System.out.println();
    }


    public boolean isEmpty() {
        return top== null;
    }

    // StackNode class

    private static class StackNode {
        private int data;
        private StackNode next;

        public StackNode(int data) {
            this.data = data;
        }
    }



}


