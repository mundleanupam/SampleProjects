package DataStructures;

// This also has a divided array into number of sub-arrays given
// To implement this, stack array is created. Top of the stack is calculated based on the no. of divided stacks
// e.g. for noOfArrays 3 and size 36 stack array is divided in 3 sub-arrays with top/tos of 1st array set to -1, 2nd array to 11
// and 3d to 23


class MyStack {
    private int stck[];
    private int stckPointer[];  //used for divided stack only
    private int parts;          //used for divided stack only
    private int tos;
    private int size;

    // allocate and initialize stack

    MyStack(int size) {
        System.out.println("This is a regular stack");
        stck = new int[size];
        tos = -1;
        this.size = size;
    }

    //Divided stack into no of parts
    MyStack (int size, int parts){
        System.out.println("This is a divided stack");
        this.parts=parts;
        stckPointer = new int[parts];
        for (int i=0; i<stckPointer.length; i++){
            stckPointer[i]= (((size*parts)/parts)*i) - 1 ;
        }
        stck = new int [size * parts];
    }

    public int getLength(){
        return tos+1;
    }

    public void push(int item) {
        if(tos==stck.length-1) {
            //if(tos==size-1)
            System.out.println("Stack is full");
        }
        else
            stck[++tos] = item;
    }

    public void push(int item, int stckNo) {
        if(stckPointer[stckNo]==((stck.length/parts)*(stckNo+1)-1)) {
            System.out.println("Stack is full");
        }
        else
            stck[++stckPointer[stckNo]] = item;
    }

    public int pop() {
        if(tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[tos--];
    }

    public int pop(int stckNo) {

        if(stckPointer[stckNo] < ((stck.length/parts)*(stckNo))) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[stckPointer[stckNo]--];
    }

    public int peek() {
        if(tos < 0) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[tos];
    }

    public int peek(int stckNo) {
        if(stckPointer[stckNo] < ((stck.length/parts)*(stckNo))) {
            System.out.println("Stack underflow.");
            return 0;
        }
        else
            return stck[stckPointer[stckNo]];
    }

    public void display(MyStack s){
        for(int i=s.tos; i>=0; i--){
            System.out.println("[ "+stck[i]+" ]");
        }
    }

    public void display(int stckNo){
        for(int i=stckPointer[stckNo]; i>=(stck.length/parts)*(stckNo) ; i--){
            System.out.println("[ "+stck[i]+" ]");
        }
    }

    public static void main (String args[]){
        MyStack stck = new MyStack(5);
        stck.push(1);
        stck.push(2);
        stck.push(3);
        stck.push(4);
        stck.push(5);
        stck.push(6);
        stck.display(stck);
        stck.pop();
        System.out.println("After Pop");
        stck.display(stck);
        System.out.println("After peek()- [ "+ stck.peek() +" ]");
        System.out.println("-----------------------------");

        //Following implementation is for divided stack. Stack is divided in equal sizes and top of each stack is maintained within same stack
        // e.g. stac of size 12 with 3 parts is initialized to size of 36 and then top is maintained in int array {-1, 11, 23}

        MyStack dividedStck = new MyStack(12, 3);
        dividedStck.push(1, 0);
        dividedStck.push(2, 0);
        dividedStck.push(3, 0);
        dividedStck.push(4, 0);
        dividedStck.push(5, 0);
        dividedStck.push(6, 0);
        dividedStck.push(7, 0);

        dividedStck.push(1, 1);
        dividedStck.push(2, 1);
        dividedStck.push(3, 1);

        System.out.println("Stack no 0 - ");
        dividedStck.display( 0);
        System.out.println("Stack no 1 - ");
        dividedStck.display( 1);

        System.out.println("After peek()- [ "+ dividedStck.peek(0) +" ]");
        dividedStck.pop(1);
        System.out.println("After Pop on stckNo 1");
        dividedStck.display( 1);

    }

}
