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

// It is possible to implement this problem using java.util.Stack package but instead used own MyStack implementation

public class MySetOfStacks{

    //ArraysStrings aS = new ArraysStrings();
    private static int capacity = 0;
    private static final int DEFAULT_CAPACITY = 5;
    private ArrayList<MyStack> plates = new ArrayList<MyStack>();

    public MySetOfStacks(){
        capacity = DEFAULT_CAPACITY;
        MyStack  s = new MyStack(capacity);
        plates.add(s);
    }

    public MySetOfStacks(int capacity){
        this.capacity = capacity;
        MyStack  s = new MyStack(capacity);
        plates.add(s);
    }

//    public boolean isEmpty(){
//        return plates.size()==0||plates.get(0).isEmpty();
//    }

    public void push(int value){
        if(plates.size()==0){
            plates.add(new MyStack(capacity));
        }
        MyStack s = plates.get(plates.size()-1);
        // last stack is at full capacity.
        if(s.getLength()>=capacity){
            MyStack newStack = new MyStack(capacity);
            plates.add(newStack);
            newStack.push(value);
        }else{
            s.push(value);
        }
    }

    public int pop(){
        MyStack last = plates.get(plates.size()-1);
        int value = last.pop();
        if(last.getLength()==0){
            plates.remove(plates.size()-1);
        }
        return value;
    }

    public int peek(){
        MyStack last = plates.get(plates.size()-1);
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
        MyStack s = plates.get(index);
        int value = s.pop();

        balance(index, index+1);

        return value;

    }

    private void balance(int first, int second){
        if(first>plates.size()-1||second>plates.size()-1){
            return;
        }
        MyStack s1 = plates.get(first);
        MyStack s2 = plates.get(second);
        s1.push(s2.pop());
        balance(second, second+1);
    }

    public void inspect(){
        StringBuffer sb = new StringBuffer();
        sb.append("===Insepecting stack of plates===\n");
        for(int i = 0; i<plates.size(); i++){
            sb.append("Stack "+i+" elements: ");
            MyStack s = plates.get(i);
            s.display(s);
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        MySetOfStacks ins = new MySetOfStacks(10);

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