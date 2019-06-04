package DataStructures;

import java.util.Hashtable;

public class DoubleLinkedList {

    private int counter;
    private DoubleNode head;
    private DoubleNode tail;

    public static void main(String[] args) {

        // Default constructor - let's put "0" into head element.
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add("1");
        doubleLinkedList.add("2");
        doubleLinkedList.add("3");
        doubleLinkedList.add("5");
        doubleLinkedList.add("1");
        doubleLinkedList.add("6");
        doubleLinkedList.add("7");
        doubleLinkedList.add("4", 3);
        doubleLinkedList.reverseInPlace();
        doubleLinkedList.reverse();

        /*
         * Please note that primitive values can not be added into LinkedList directly. They must be converted to their
         * corresponding wrapper class.
         */

        System.out.println("Print: crunchifyList: \t" + doubleLinkedList);
        System.out.println(".size(): \t" + doubleLinkedList.size());
        System.out.println(".get(0): \t" + doubleLinkedList.get(0) + " (get element at index:0 - list starts from 0)");
        System.out.println(".get(6): \t" + doubleLinkedList.get(6) + " (get element at index:6 - list starts from 0)");
        System.out.println(".remove(2): \t" + doubleLinkedList.remove(2) + " (element removed)");
        System.out.println(".size(): \t" + doubleLinkedList.size());
        System.out.println("Print again: crunchifyList: \t" + doubleLinkedList);
        doubleLinkedList.add("3", 2);
        System.out.println("Print after adding element at index(2): crunchifyList: \t" + doubleLinkedList);
        doubleLinkedList.removeHead();
        System.out.println("Print again: crunchifyList: \t" + doubleLinkedList);
        doubleLinkedList.removeTail();
        System.out.println("Print again: crunchifyList: \t" + doubleLinkedList);
        doubleLinkedList.removeDuplicates(doubleLinkedList);
        System.out.println("Print after removing duplicates: crunchifyList: \t" + doubleLinkedList);
    }

    // Default constructor
    public DoubleLinkedList() {

    }

    // returns the number of elements in this list.
    public int size() { return getCounter(); }
    //get the size of the list
    private int getCounter() { return counter; }

    //add 1 to the size of the list once element is inserted
    private void incrementCounter() {
        counter++;
    }

    //subtract 1 from the size once the element is removed
    private void decrementCounter() {
        counter--;
    }

    // To add an element at the end of the linked list and point last element to null
    public void add(Object data) {

        // Initialize Node only incase of 1st element
        if (head == null && tail==null) {
            head = new DoubleNode(data);
            tail=head;
            incrementCounter();
            return;
        }
        DoubleNode temp = new DoubleNode(data);

        temp.setPrev(tail);
        tail.setNext(temp);
        tail = temp;

        incrementCounter();
    }

    // inserts the specified element at the specified position in this list
    public void add(Object data, int index) {
        DoubleNode temp = new DoubleNode(data);
        DoubleNode current = head;
        DoubleNode nextofCurrent = head;

        if (current != null) {

            for (int i = 0; i < index-1 && current.getNext() != null; i++) {
                current = current.getNext();
                nextofCurrent = current.getNext();
            }
        }

        temp.setNext(current.getNext());
        temp.setPrev(current);
        current.setNext(temp);
        nextofCurrent.setPrev(temp);

        incrementCounter();
    }

    // returns the element at the specified position in this list.
    public Object get(int index)
    {
        // index must be 1 or higher
        if (index < 0)
            return null;
        DoubleNode current = null;
        if (head != null) {
            current = head;
            for (int i = 0; i < index; i++) {
                if (current.getNext() == null)
                    return null;
                current = current.getNext();
            }
            return current.getData();
        }
        return current;
    }

    public Object getHead(){
        if(head!=null) return head;
    return null;
    }

    public Object getTail(){
        if(tail!=null) return tail;
        return null;
    }

    // removes the element at the specified position in this list.
    public boolean remove(int index) {
        // if the index is out of range, exit
        if (index < 1 || index > size())
            return false;
        DoubleNode current = head;
        if (head != null) {
            for (int i = 0; i < index-1; i++) {
                if (current.getNext() == null)
                    return false;
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            current.getNext().setPrev(current);

            decrementCounter();
            return true;
        }
        return false;
    }

    public boolean removeHead(){
        DoubleNode current = head;
        if (head != null) {
            if (current.getNext() == null) {
                head = null;
                decrementCounter();
            }
            else{
                head = current.getNext();
                head.setPrev(null);
                decrementCounter();
            }
        }
        return false;
    }

    public boolean removeTail(){
        DoubleNode current = tail;
        if (head != null) {
            current.getPrev().setNext(null);
            tail=current.getPrev();
        }
        return false;
    }

    public void reverse() {
     int i = 0;
     int j = counter;
     DoubleNode first = head;
     DoubleNode last = tail;
     Object temp;
     if(head != null) {
         while (i < j) {
             temp = first.getData();
             first.setData(last.getData());
             last.setData(temp);
             first = first.getNext();
             last = last.getPrev();
             i++;
             j--;
         }
     }
    }

    public void reverseInPlace(){
        DoubleNode current = head;
        DoubleNode prev = null;
        DoubleNode next;
        tail = current;
        while (current!= null){
            next = current.getNext();
            current.setNext(prev);
            current.setPrev(next);
            prev = current;
            current = next;
        }
        head = prev;
    }

    //1,2,3,2,3,4,5
    public void removeDuplicates(DoubleLinkedList crunchifyList) {
        Hashtable hashtable = new Hashtable();
        if (head==null)
            return;
        DoubleNode current = head;
        DoubleNode previous = current;
        if (head != null) {
            int test = crunchifyList.size();
            for (int i = 0; i < crunchifyList.size(); i++) {
                while (current != null){
                    if (hashtable.containsKey(current.getData())) {
                        previous.setNext(current.getNext());
                        current = previous.getNext();
                        decrementCounter();
                        continue;
                    } else {
                        hashtable.put(current.data, true);
                    }
                previous = current;
                current = current.getNext();
                }
            }
        }
    }

    public String toString() {
        String output = "";

        if (head != null) {
            DoubleNode current = head;
            while (current != null) {
                output += "[" + current.getData().toString() + "]";
                current = current.getNext();
            }
        }
        return output;
    }

}

class DoubleNode {
    // reference to the next node in the chain, or null if there isn't one.
    DoubleNode next;
    DoubleNode prev;
    Object data;

    // Node constructor
    public DoubleNode(Object dataValue) {
        next = null;
        prev = null;
        data = dataValue;
    }

    // another Node constructor if we want to specify the node to point to.
    @SuppressWarnings("unused")
    public DoubleNode(Object dataValue, DoubleNode nextValue, DoubleNode prevValue) {
        next = nextValue;
        prev = prevValue;
        data = dataValue;
    }

    // these methods should be self-explanatory
    public Object getData() {
        return data;
    }

    @SuppressWarnings("unused")
    public void setData(Object dataValue) {
        data = dataValue;
    }

    public DoubleNode getNext() {
        return next;
    }

    public void setNext(DoubleNode nextValue) {
        next = nextValue;
    }

    public DoubleNode getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode prevValue) {
        prev = prevValue;
    }

}