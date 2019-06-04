package DataStructures;

public class OriginalMaxStack {

    private static int stck[];
    private static int track[];
    private static int tos;

    // allocate and initialize stack

    OriginalMaxStack(int size) {
        System.out.println("This is a regular stack");
        stck = new int[size];
        track = new int[size];
        tos = -1;
        //tosTrack = -1;
    }

    public void push(int item) {
        if (tos == stck.length - 1) {
            System.out.println("Stack is full");
        }
        else if (tos == -1){
            stck[++tos] = item;
            track[tos] = item;
        }
        else {
            stck[++tos] = item;
            track[tos] = Math.max(item, track[tos-1]);
        }
    }

    public int pop() {
        if (tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        } else
            track[tos--] = 0;
            return stck[tos];
    }

    public static int getMax() {
        return track[tos];
    }

    public static void display(OriginalMaxStack s) {
        for (int i = s.tos; i >= 0; i--) {
            System.out.println(s.stck[i]);
        }
    }

    public static void main(String args[]) {
        OriginalMaxStack stck = new OriginalMaxStack(12);
        stck.push(1);
        stck.push(3);
        stck.push(26);
        stck.push(11);
        stck.push(70);
        stck.display(stck);
        stck.pop();
        System.out.println("After Pop");
        stck.display(stck);
        System.out.println("Max item value - " + getMax());
    }
}

