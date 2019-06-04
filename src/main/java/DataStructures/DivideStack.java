package DataStructures;

public class DivideStack {
    private int stck[];
    private int next[];
    private int top[];
    int n, k = 0;
    int free;

    DivideStack(int size, int noOfArrays) {
        int k = noOfArrays;
        int n = size;
        stck = new int[n];
        next = new int[n];
        top = new int[k];
        free = 0;
        for (int i = 0; i < n - 1; i++)
            next[i] = i + 1;
        next[n - 1] = -1;
        for (int j = 0; j < k; j++) {
            top[j] = -1;
        }
    }

    boolean isFull() {
        return (free == -1);
    }

    boolean isEmpty(int sn)
    {
        return (top[sn] == -1);
    }

    void push(int item, int sn) {
        // Overflow check
        if (isFull()) {
            System.out.println("Stack Overflow");
            return;
        }

        int i = free; // Store index of first free slot

        // Update index of free slot to index of next slot in free list
        free = next[i];

        // Update next of top and then top for stack number 'sn'
        next[i] = top[sn];
        top[sn] = i;

        // Put the item in array
        stck[i] = item;
    }

    int pop(int sn)
    {
        // Underflow check
        if (isEmpty(sn))
        {
            System.out.println("Stack Underflow");
            return Integer.MAX_VALUE;
        }

        // Find index of top item in stack number 'sn'
        int i = top[sn];

        top[sn] = next[i]; // Change top to store next of previous top

        // Attach the previous top to the beginning of free list
        next[i] = free;
        free = i;

        // Return the previous top item
        return stck[i];
    }

        public void display(DivideStack s, int no){
        for(int i=top[no]; i>=0; i--){
            System.out.println(s.stck[i]);
        }
    }

    public static void main(String[] args) {
        // Let us create 3 stacks in an array of size 10
        int k = 3, n = 10;

        DivideStack ks = new DivideStack(n, k);

        ks.push(17, 0);
        ks.push(49, 0);
        ks.push(39, 0);
        ks.pop(0);
        ks.push(39, 0);

        ks.push(32, 0);
        ks.push(30, 0);
        ks.push(10, 0);
        System.out.println("After all the push operations on 0");
        ks.display(ks, 0);
        ks.pop(0);
        ks.pop(0);
        System.out.println("After all the pop operations on 0");
        ks.display(ks, 0);

        ks.push(41, 1);
        ks.push(42, 1);
        ks.push(43, 1);
        System.out.println("After all the push operations on 1");
        ks.display(ks, 1);
        System.out.println("After all the pop operations on 1");
        ks.pop(1);
        ks.display(ks, 1);

        ks.push(44, 2);
        ks.push(45, 2);
        System.out.println("After all the push operations on 2");
        ks.display(ks, 2);
        System.out.println("After all the pop operations on 2");
        ks.pop(2);
        ks.display(ks, 2);

        System.out.println("After all the operations - 0");
        ks.display(ks, 0);
        System.out.println("After all the operations - 1");
        ks.display(ks, 1);
        System.out.println("After all the operations - 2");
        ks.display(ks, 2);
    }

}
