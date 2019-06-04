package DataStructures;

import java.util.Hashtable;


// It is very much required to create new nodes or entirely new list when clone, reverse and clone, partition problems are asked.
// Currently designed list is operating on itself and its static head. All the operations are carried out and once the head pointer is changed
// complete list gets converted

public class MyLinkedList {

    // for single linked list implementation both these variables need to be static to maintain single copy
    private int counter;
    private Node head;

    public static void main(String[] args) {

        MyLinkedList myList = new MyLinkedList();
        MyLinkedList myList1 = new MyLinkedList();
        MyLinkedList myList3 = new MyLinkedList();
        MyLinkedList myList4 = new MyLinkedList();
        MyLinkedList myList5 = new MyLinkedList();

        // create list1
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);
        myList.add(7);

        //create list2
        myList1.add(8);
        myList1.add(9);
        myList1.add(10);
        myList1.add(11);
        myList1.add(12);

        // for palindrome
        myList3.add(0);
        myList3.add(1);
        myList3.add(2);
        myList3.add(1);
        myList3.add(0);

        // for partition node
        myList4.add(3);
        myList4.add(5);
        myList4.add(8);
        myList4.add(5);
        myList4.add(10);
        myList4.add(2);
        myList4.add(1);

        // Find beginning of the loop
        // for partition node
        myList5.add(1);
        myList5.add(2);
        myList5.add(3);

        //Print lists
        System.out.println("Print myList: \t" + myList);
        System.out.println("Print myList1: \t" + myList1);

        // get size
        System.out.println("myList.size(): \t" + myList.size());

        // get element using index
        System.out.println("myList.get(0): \t" + myList.get(0) + " (Get element at index:0 - list starts from 0)");

        // add element using index
        myList.add(10, 2);
        System.out.println("Print again myList: \t" + myList);

        // remove element using index
        System.out.println("myList.remove(2): \t" + myList.remove(2) + " (Element removed)");
        System.out.println("myList.get(1): \t" + myList.get(1) + " (Get element at index:1 - list starts from 0)");
        System.out.println("myList.size(): \t" + myList.size());
        System.out.println("Print again myList: \t" + myList);

        // remove duplicate elements from list
        myList.removeDuplicates();
        System.out.println("Print after removing duplicates: myList: \t" + myList);


        // Show detectLoop function
        Node last = myList.add(3, "");
        last.next = myList.head;
        System.out.println("Is myList a loop -> " + myList.detectLoop());
        last.next = null;
        System.out.println("myList.remove(2): \t" + myList.remove(7) + " (Element removed)");
        System.out.println("Print after removing loop myList: \t" + myList);

        // Show detectLoop findBeginning function
        Node beginning = myList5.add(4, "");
        myList5.add(5);
        myList5.add(6);
        myList5.add(7);
        myList5.add(8);
        myList5.add(9);
        myList5.add(10);
        Node last1 = myList5.add(11, "");
        last1.next = beginning;
        Node temp = myList5.FindBeginning(myList5.head);
        System.out.println("Print myList5 beginning of loop: \t" + temp.data);
        last1.next = null;
        System.out.println("Print after removing loop myList: \t" + myList5);


        // Reverse a list
        myList.reverse();
        System.out.println("Print: myList Reversed : \t" + myList);

        //Nth from last
        System.out.println("Nth(2) from last: \t" + myList.printNthFromLast(2));
        System.out.println("Nth(4) from last: \t" + myList.printNthFromLast(4));

        // isPalindrome? 0->1->2->1->0
        System.out.println("myList1 isPalindrome: \t"+ myList1.isPalindrome());
        System.out.println("myList3 isPalindrome: \t"+ myList3.isPalindrome());

        // partition
        Node start = myList4.partition(myList4.head, 5);
        System.out.println("Print myList4: \t" + myList4);

    }

    // Default constructor
    public MyLinkedList() {
        head = null;
        counter = 0;
    }

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

    // returns the number of elements in this list.
    public int size() {
        return getCounter();
    }

    //get the size of the list
    private int getCounter() {
        return counter;
    }

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

    //this is used only for loop creation
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
    public Object get(int index) {
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
            for (int i = 0; i < index - 1; i++) {
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

    public void reverse() {

        Node current = head;
        Node prev = null;
        Node next;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
    }

    boolean isPalindrome() {
        Node reversed = reverseAndClone(head);
        return isEqual(head, reversed);
        }

    public static Node reverseAndClone(Node node) {
        Node current = null;
        while (node != null) {
            Node n = new Node(node.data);
            n.next = current;
            current = n;
            node = node.next;
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
        return one == null && two == null;
    }


    public void removeDuplicates() {
        Hashtable hashtable = new Hashtable();
        System.out.println();
        if (head == null)
            return;
        Node current = head;
        Node previous = current;
        if (head != null) {
            //int test = crunchifyList.size();
            for (int i = 0; i < getCounter(); i++) {
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

    public Object printNthFromLast(int n) {
        Node main_ptr = head;
        Node ref_ptr = head;

        int count = 0;
        if (head != null) {
            while (count < n) {
                if (ref_ptr == null) {
                    //System.out.println(n + " is greater than the no " +
                    //        " of nodes in the list");
                    return null;
                }
                ref_ptr = ref_ptr.next;
                count++;
            }
            while (ref_ptr != null) {
                main_ptr = main_ptr.next;
                ref_ptr = ref_ptr.next;
            }
            //System.out.println("Node no. " + n + " from last is " +
            //        main_ptr.data);
            return main_ptr.data;
        }
        return main_ptr.data;
    }

    public boolean detectLoop() {
        Node slow_p = head;
        Node fast_p = head;
        while (slow_p != null && fast_p != null && fast_p.next != null) {
            slow_p = slow_p.next;
            fast_p = fast_p.next.next;
            if (slow_p == fast_p) {
                return true;
            }
        }
        return false;
    }

    /* Pass in the head of the linked list and the value to partition around */
    public static Node partition(Node node, int x) {
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        /* Partition list */
        while (node != null) {
            Node next = node.next;
            node.next = null;
            if ((Integer)node.data < x) {
                /* Insert node into end of before list */
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = node;
                }
            } else {
                /* Insert node into end of after list */
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = node;
                    afterEnd = node;
                }
            }
            node = next;
        }

        /* Merge before list and after list */
        if (beforeStart == null) {
            return afterStart;
        }

        beforeEnd.next = afterStart;
        return beforeStart;
    }

    public Node FindBeginning(Node node) {
         Node slow = head;
         Node fast = head;

         /* Find meeting point. This will be LOOP_SIZE - k steps into the linked list. */
         while (fast != null && fast.next != null) {
             slow = slow.next;
             fast = fast.next.next;
             if (slow == fast) {    //Collision
                 break;
                 }
             }

         /* Error check - no meeting point, and therefore no loop*/
         if (fast == null || fast.next == null) {
             return null;
             }

         /* Move slow to Head. Keep fast at Meeting Point. Each are k steps from the
            20 * Loop Start. If they move at the same pace, they must meet at Loop Start. */

         slow = head;

         while (slow!= fast) {
             slow = slow.next;
             fast = fast.next;
             }

         /* Both now point to the start of the loop. */
         return fast;
    }

        public void partitionNode(int x) {
        Node dummyNode1 = new Node(-1);
        Node dummyNode2 = new Node(-1);
        Node p1Last = dummyNode1;
        Node p2Last = dummyNode2;
        Node current = head;

        while (current != null) {
            if ((int) current.getData() < x) {
                p1Last.next = current;
                p1Last = p1Last.next;
            } else {
                p2Last.next = current;
                p2Last = p2Last.next;
            }
            current = current.next;
        }
        p1Last.next = dummyNode2.next;
        p2Last.next = null;
    }

    //This function is used for variations of 2 single linked lists such as add/subtract etc..

    public static MyLinkedList addList(MyLinkedList l1, MyLinkedList l2) {
        MyLinkedList result = new MyLinkedList();
        if (l1.head == null && l2.head == null) {
            return null;
        }
        Node l1Current = l1.head;
        Node l2Current = l2.head;
        while (l1Current != null && l2Current != null) {
            int n1 = Integer.parseInt((String) l1Current.getData());
            int n2 = Integer.parseInt((String) l2Current.getData());
            result.add(n1 + n2);
            l1Current = l1Current.getNext();
            l2Current = l2Current.getNext();
        }
        return result;
    }
}


