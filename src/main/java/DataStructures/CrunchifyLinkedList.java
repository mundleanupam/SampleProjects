package DataStructures;

import java.util.Hashtable;


// It is very much required to create new nodes or entirely new list when clone, reverse and clone, partition problems are asked.
// Currently designed list is operating on itself and its static head. All the operations are carried out and once the head pointer is changed
// complete list gets converted

public class CrunchifyLinkedList {

    // for single linked list implementation both these variables need to be static to maintain single copy
    private static int counter;
    private static Node head;

    public static void main(String[] args) {

        // Default constructor - let's put "0" into head element.
        CrunchifyLinkedList crunchifyList = new CrunchifyLinkedList();
        CrunchifyLinkedList crunchifyList1 = new CrunchifyLinkedList();

        // add more elements to LinkedList

        crunchifyList.add(1);
        crunchifyList.add(2);
        crunchifyList.add(4);
        crunchifyList.add(5);
        crunchifyList.add(3);
        crunchifyList.add(6);
        crunchifyList.add(7);
        crunchifyList.add(7);
        crunchifyList.add(8, 3);

//      crunchifyList1.add(9);
//      crunchifyList1.add(10);
//      crunchifyList1.add(11);
//      crunchifyList1.add(12);
//      crunchifyList1.add(13);

        System.out.println("Print: crunchifyList: \t" + crunchifyList);
        System.out.println("Print: crunchifyList1: \t" + crunchifyList1);


        System.out.println("crunchifyList.size(): \t" + crunchifyList.size());
        System.out.println("crunchifyList.get(0): \t" + crunchifyList.get(0) + " (get element at index:0 - list starts from 0)");
        //add at the index
        //crunchifyList.add(10, 2);
        System.out.println("Print again: crunchifyList: \t" + crunchifyList);
        System.out.println("crunchifyList.remove(3): \t" + crunchifyList.remove(3) + " (element removed)");
        System.out.println("crunchifyList.get(1): \t" + crunchifyList.get(1) + " (get element at index:1 - list starts from 0)");
        System.out.println("crunchifyList.size(): \t" + crunchifyList.size());
        System.out.println("Print again: crunchifyList: \t" + crunchifyList);
        crunchifyList.removeDuplicates();
        System.out.println("Print after removing duplicates: crunchifyList: \t" + crunchifyList);


        //Node last = crunchifyList.add(3, "");
        //last.next = head;
        //System.out.println("Is CruchifyList a loop -> " + crunchifyList.detectLoop());

//      Node temp = crunchifyList.FindBeginning(head);
//      System.out.println(temp.data);

//      Node reversed = crunchifyList.reverseAndClone(head);
//      System.out.println("Print: crunchifyList: \t\t" + crunchifyList);
//      System.out.println("Is Palindrome - > "+ isEqual(head, reversed));

//
//      System.out.println("Print: crunchifyList: \t\t" + crunchifyList);
//      crunchifyList.printNthFromLast(2);

//      crunchifyList.partitionNode(4);
//      System.out.println("Print: crunchifyList: \t\t" + crunchifyList);
//
//      crunchifyList.reverse(head);
//      System.out.println("Print: crunchifyList Reversed : \t\t" + crunchifyList);

//        System.out.println(addList(crunchifyList, crunchifyList1));

        /*
         * Please note that primitive values can not be added into LinkedList direc tly. They must be converted to their
         * corresponding wrapper class.
         */
    }

    // Default constructor
    public CrunchifyLinkedList() {
       head = null;
       counter = 0;
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
        if (head == null) {
            head = new Node(data);
            incrementCounter();
            return;
        }
        Node temp = new Node(data);
        Node current = head;
        // Let's check for NPE before iterate over current
        if (current != null) {
            // starting at the head node, crawl to the end of the list and then add element after last node
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // the last node's "next" reference set to our new node
            current.setNext(temp);
        }
        // increment the number of elements variable
        incrementCounter();
    }

    public Node add(Object data, String fake) {

        // Initialize Node only incase of 1st element
        if (head == null) {
            head = new Node(data);
            incrementCounter();
            return head;
        }
        Node temp = new Node(data);
        Node current = head;
        // Let's check for NPE before iterate over current
        if (current != null) {
            // starting at the head node, crawl to the end of the list and then add element after last node
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // the last node's "next" reference set to our new node
            current.setNext(temp);
        }
        // increment the number of elements variable
        incrementCounter();
        return temp;
    }

    // inserts the specified element at the specified position in this list
    public void add(Object data, int index) {
        if(index<0 || index>getCounter()) return;
        Node temp = new Node(data);
        Node current = head;

        // Let's check for NPE before iterate over current
        if (current != null) {
            // crawl to the requested index or the last element in the list, whichever comes first
            for (int i = 0; i < index - 1 && current.getNext() != null; i++) {
                current = current.getNext();
            }
        }
        // set the new node's next-node reference to this node's next-node reference
        temp.setNext(current.getNext());

        // now set this node's next-node reference to the new node
        current.setNext(temp);

        // increment the number of elements variable
        incrementCounter();
    }

    // returns the element at the specified position in this list.
    public Object get(int index)
    {
        // index must be 1 or higher
        if (index < 0)
            return null;
        Node current = null;
        if (head != null) {
            current = head;//.getNext();
            for (int i = 0; i < index; i++) {
                if (current.getNext() == null)
                    return null;
                current = current.getNext();
            }
            return current.getData();
        }
        return current;
    }


    // removes the element at the specified position in this list.
    public boolean remove(int index) {
        // if the index is out of range, exit
        if (index < 1 || index > size())
            return false;
        Node current = head;
        if (head != null) {
            for (int i = 0; i < index-1; i++) {
                if (current.getNext() == null)
                    return false;
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
            // decrement the number of elements variable
            decrementCounter();
            return true;
        }
        return false;
    }

    public void reverse(Node node){

    Node current = node;
    Node prev = null;
    Node next;

    while (current!= null){
        next = current.getNext();
        current.setNext(prev);
        prev = current;
        current = next;
    }
        node = prev;
        head = node;
        //head = prev;
    }

    public static Node reverseAndClone(Node node) {
        Node current= null;
        while (node != null) {
            Node n = new Node(node.data); // Clone
            n.next = current;
            current = n;
            node= node.next;
        }
        return current;
    }

    public static boolean isEqual(Node one, Node two) {
        while (one != null && two != null) {
            if (one.data != two.data) {
                return false;
            }
            one = one.next;
            two = two.next;
        }
        return one== null && two== null;
    }

    public void reverse(){

        Node current = head;
        Node prev = null;
        Node next;

        while (current!= null){
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
    }


    //1,2,3,2,3,4,5
    // 1-2-3-4-5
    public void removeDuplicates() {
        Hashtable hashtable = new Hashtable();
        System.out.println();
        if (head==null)
            return;
        Node current = head;
        Node previous = current;
        if (head != null) {
            //int test = crunchifyList.size();
            for (int i = 0; i <getCounter() ; i++) {
                if (hashtable.containsKey(current.getData())){
                    previous.setNext(current.getNext());
                    current=previous.getNext();
                    decrementCounter();
                    continue;
                }
                else{
                    hashtable.put(current.data, true);
                }
                previous = current;
                current = current.getNext();
            }
        }
    }

    public void printNthFromLast(int n)
    {
        Node main_ptr = head;
        Node ref_ptr = head;

        int count = 0;
        if (head != null)
        {
            while (count < n)
            {
                if (ref_ptr == null)
                {
                    System.out.println(n+" is greater than the no "+
                            " of nodes in the list");
                    return;
                }
                ref_ptr = ref_ptr.next;
                count++;
            }
            while (ref_ptr != null)
            {
                main_ptr = main_ptr.next;
                ref_ptr = ref_ptr.next;
            }
            System.out.println("Node no. "+n+" from last is "+
                    main_ptr.data);
        }
    }

    public boolean detectLoop()
    {
        Node slow_p = head, fast_p = head;
        while (slow_p != null && fast_p != null && fast_p.next != null) {
            slow_p = slow_p.next;
            fast_p = fast_p.next.next;
            if (slow_p == fast_p) {
                System.out.println("Found loop");
                return true;
            }
        }
        return false;
    }

    public void partitionNode(int x) {
        Node dummyNode1=new Node(-1);
        Node dummyNode2=new Node(-1);
        Node p1Last=dummyNode1;
        Node p2Last=dummyNode2;
        Node current=head;

        while(current!=null){
            if((int)current.getData()<x){
                p1Last.next=current;
                p1Last=p1Last.next;
            }else{
                p2Last.next=current;
                p2Last=p2Last.next;
            }
            current=current.next;
        }
        p1Last.next=dummyNode2.next;
        p2Last.next=null;
    }
//1,2,4,5,3,6,7


    public String toString() {
        StringBuilder output = new StringBuilder();
        if (head != null) {
            Node current = head;
            while (current != null) {
                output.append("[" + current.getData() + "]");
                current = current.getNext();
            }
        }
        return output.toString();
    }

    /*public String toString() {
        String output = "";
        if (head != null) {
            Node current = head;
            while (current != null) {
                output += "[" + current.getData().toString() + "]";
                current = current.getNext();
            }
        }
        return output;
    }*/

    //This function is used for variations of 2 single linked lists such as add/subtract etc..

    public static CrunchifyLinkedList addList(CrunchifyLinkedList l1, CrunchifyLinkedList l2){
        CrunchifyLinkedList result = new CrunchifyLinkedList();
        if (l1.head == null && l2.head == null) {
            return null;
        }
        Node l1Current = l1.head;
        Node l2Current = l2.head;
        while(l1Current!=null && l2Current!=null) {
            int n1 = Integer.parseInt((String) l1Current.getData());
            int n2 = Integer.parseInt((String) l2Current.getData());
            result.add(n1 + n2);
            l1Current = l1Current.getNext();
            l2Current = l2Current.getNext();
        }
        return result;
    }

    //appends the specified element to the end of this list.

    /*public void add(Object data) {

        Node temp = new Node(data);

        // Initialize Node only incase of 1st element
        if (head == null) {
            head = temp;
            current = head;

        } else {
            current = head;
            // Let's check for NPE before iterate over current
            if (current != null) {

                // starting at the head node, crawl to the end of the list and then add element after last node
                while (current.getNext() != null) {
                    current = current.getNext();
                }

                // the last node's "next" reference set to our new node
                current.setNext(temp);
            }
        }

        // increment the number of elements variable
        incrementCounter();
    }*/

}

    class Node {
        // reference to the next node in the chain, or null if there isn't one.
        Node next;
        // data carried by this node. could be of any type you need.
        Object data;

        // Node constructor
        public Node(Object dataValue) {
            next = null;
            data = dataValue;
        }

        // another Node constructor if we want to specify the node to point to.
        @SuppressWarnings("unused")
        public Node(Object dataValue, Node nextValue) {
            next = nextValue;
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

        public Node getNext() {
            return next;
        }

        public void setNext(Node nextValue) {
            next = nextValue;
        }

    }

