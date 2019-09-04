package DataStructures;
import java.util.Stack;

// Implement a queue (First In - First Out) using Stack (First In Last Out)
// Use functions inbuilt stack functions push and pop only

public class StackQueue {

    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();


    public void enQueue(int x){
        System.out.println("Adding an element to Stack Queue " + x);
        while(!s1.empty()){
            s2.push(s1.pop());
        }
        s1.push(x);
        while(!s2.empty()){
            s1.push(s2.pop());
        }
    }

    public int deQueue(){
        if(s1.isEmpty()){
            System.out.println("Queue is Empty");
            System.exit(0);
        }
        int x = s1.pop();
        System.out.println("Removing an element from Stack Queue " + x);
        return x;
    }

    public static void main(String args[]){

        StackQueue stckQueue = new StackQueue();

        stckQueue.enQueue(1);
        stckQueue.enQueue(2);
        stckQueue.enQueue(3);


        stckQueue.deQueue();
        stckQueue.deQueue();
        stckQueue.deQueue();

        System.out.println(stckQueue.deQueue());
    }


}
