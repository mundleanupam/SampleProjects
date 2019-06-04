package DataStructures;

import java.util.Stack;

// This program is written using the java.util.Stack package's in built Stack data structure.
// End to end implementation is written in OriginalMaxStack

public class MaxStack {

    // objective here is to keep track of maximum value in a stack of integers
    // create another another Stack which will keep track of maximum
    Stack<Integer> main = new Stack<>();
    Stack<Integer> track = new Stack<>();

    public void push(int x) {
        if (main.isEmpty()) { // if stack is empty, insert the number in both
            // stacks
            main.add(x);
            track.add(x);
        } else {
            // check if number in Stack(track) is bigger than x
            // which ever is bigger, insert it into Stack

            int a = track.peek();
            track.add(Math.max(a, x));
            main.add(x); // insert it into main stack.
        }
    }

    public int pop() {
        if (!main.isEmpty()) { // pop the top elements
            track.pop();
            return main.pop();
        }
        return 0;
    }

    public int getMax() {
        return track.peek();
    }

    public static void main(String[] args) {
        MaxStack i = new MaxStack();
        i.push(4);
        i.push(2);
        i.push(14);
        i.push(1);
        i.push(18);
        System.out.println("Max Element is " + i.getMax());
        System.out.println("Removing Element " + i.pop());
        System.out.println("Max Element is " + i.getMax());
    }

}
