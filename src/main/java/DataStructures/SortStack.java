package DataStructures;

import java.util.*;

// Problem - Sort a stack using 4 operations.
// 1. Push
// 2. Pop
// 3. peek
// 4. isEmpty
// Used regular java.util.Stack

public class SortStack {

    public static Stack<Integer> sortstack(Stack<Integer>
                                                   input)
    {
        Stack<Integer> tmpStack = new Stack<Integer>();
        while(!input.isEmpty())
        {
            // pop out the first element
            int tmp = input.pop();

            // while temporary stack is not empty and
            // top of stack is greater than temp
            while(!tmpStack.isEmpty() && tmpStack.peek()
                    < tmp)
            {
                // pop from temporary stack and
                // push it to the input stack
                input.push(tmpStack.pop());
            }

            // push temp in tempory of stack
            tmpStack.push(tmp);
        }
        return tmpStack;
    }

    // Driver Code
    public static void main(String args[])
    {
        Stack<Integer> input = new Stack<Integer>();
        input.add(34);
        input.add(3);
        input.add(31);
        input.add(98);
        input.add(92);
        input.add(23);

        // This is the temporary stack
        Stack<Integer> tmpStack=sortstack(input);
        System.out.println("Sorted numbers are:");

        while (!tmpStack.empty())
        {
            System.out.print(tmpStack.pop()+" ");
        }
    }
}
