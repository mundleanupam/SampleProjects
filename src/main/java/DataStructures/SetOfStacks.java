// Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
// Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold.
// Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks,
// and should create a new stack once the previous one exceeds capacity.
// SetOfStacks.push() and SetOfStacks.pop() should behave identically to a single stack (that is, pop() should return
// the same values as it would if there were just a single stack).

// FOLLOW UP

// Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
package DataStructures;

import java.util.ArrayList;
import java.util.Stack;

// This problem is solved using java.util.Stack class. This is similar implementation of MySetOfStacks


public class SetOfStacks{

    private static int capacity = 0;
    private static final int DEFAULT_CAPACITY = 5;
    private ArrayList<Stack<Integer>> plates = new ArrayList<Stack<Integer>>();

    public SetOfStacks(){
        capacity = DEFAULT_CAPACITY;
        Stack<Integer>  s = new Stack<Integer>();
        plates.add(s);
    }

    public SetOfStacks(int capacity){
        this.capacity = capacity;
        Stack<Integer>  s = new Stack<Integer>();
        plates.add(s);
    }

    public boolean isEmpty(){
        return plates.size()==0||plates.get(0).isEmpty();
    }

    public void push(int value){
        if(plates.size()==0){
            plates.add(new Stack<Integer>());
        }
        Stack<Integer> s = plates.get(plates.size()-1);
        // last stack is at full capacity.
        if(s.size()>=capacity){
            Stack<Integer> newStack = new Stack<Integer>();
            plates.add(newStack);
            newStack.push(value);
        }else{
            s.push(value);
        }
    }

    public int pop(){
        Stack<Integer> last = plates.get(plates.size()-1);
        int value = last.pop();
        if(last.size()==0){
            plates.remove(plates.size()-1);
        }
        return value;
    }

    public int peek(){
        Stack<Integer> last = plates.get(plates.size()-1);
        int value = last.peek();
        return value;
    }

    public int size(){
        return plates.size();
    }

    public int popAt(int index){
        if(plates.size()-1<index){
            System.out.println("Wrong index.");
            return -1;
        }
        Stack<Integer> s = plates.get(index);
        int value = s.pop();

        balance(index, index+1);

        return value;

    }

    private void balance(int first, int second){
        if(first>plates.size()-1||second>plates.size()-1){
            return;
        }
        Stack<Integer> s1 = plates.get(first);
        Stack<Integer> s2 = plates.get(second);
        s1.push(s2.remove(0));
        balance(second, second+1);
    }

    public void inspect(){
        StringBuffer sb = new StringBuffer();
        sb.append("===Insepecting stack of plates===\n");
        for(int i = 0; i<plates.size(); i++){
            sb.append("Stack "+i+" elements: ");
            Stack<Integer> s = plates.get(i);
            for(int value:s ){
                sb.append(value+",");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        SetOfStacks ins = new SetOfStacks(10);

        for(int i = 0; i<50; i++){
            ins.push(i);
        }
        ins.inspect();
        ins.popAt(3);
        ins.inspect();
        ins.popAt(1);
        ins.inspect();
//		ins.plates.get(2).pop();
//		ins.inspect();
//		System.out.println(ins.plates.get(2).get(0));
    }
}