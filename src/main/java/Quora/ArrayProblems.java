package Quora;

import java.util.Arrays;

public class ArrayProblems {

    public static final int N = 8;

    public static void main(String[] args){

         int[] arr = {1,2,3,4,5};
         findProduct(arr);
        int[] arr1 = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
         printallSubarrays(arr1);
        // int[] arr2 = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
        int[] arr2 = { 2, 0, 2, 1, 4, 3, 1, 0 };
        int i = findLength(arr2, arr2.length);
        int[] A = { 5, 6, -5, 5, 3, 5, 3, -2, 0 };
        maxLengthSubArray(A, 8);
        int[] a = {3,7};
        int[] b = {4, 6};
        simplestFormOutputMultiplication(a, b);



    }

    // {1,2,3,4,5}
    // {120, 60, 40 30,24}
    public static void findProduct(int[] A)
    {
        int n = A.length;

        // use two auxiliary arrays
        int[] left = new int[n];
        int[] right = new int[n];

        // left[i] stores the product of all elements in the sub-array[0..i-1]
        left[0] = 1;
        for (int i = 1; i < n; i++) {
            left[i] = A[i - 1] * left[i - 1];
        }

        // right[i] stores the product of all elements in sub-array[i+1..n-1]
        right[n - 1] = 1;
        for (int j = n - 2; j >= 0; j--) {
            right[j] = A[j + 1] * right[j + 1];
        }

        // replace each element with product of its left and right sub-array
        for (int i = 0; i < n; i++) {
            A[i] = left[i] * right[i];
        }
    }

    public static void printallSubarrays(int[] A)
    {
        // consider all sub-arrays starting from i
        for (int i = 0; i < A.length; i++)
        {
            int sum = 0;

            // consider all sub-arrays ending at j
            for (int j = i; j < A.length; j++)
            {
                // sum of elements so far
                sum += A[j];

                // if sum is seen before, we have found a sub-array with 0 sum
                if (sum == 0) {
                    System.out.println("Subarray [" + i + ".." + j + "]");
                }
            }
        }
    }

    private static boolean isSafe(char mat[][], int r, int c)
    {
        // return false if two queens share the same column
        for (int i = 0; i < r; i++)
            if (mat[i][c] == 'Q')
                return false;

        // return false if two queens share the same \ diagonal
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--)
            if (mat[i][j] == 'Q')
                return false;

        // return false if two queens share the same / diagonal
        for (int i = r, j = c; i >= 0 && j < N; i--, j++)
            if (mat[i][j] == 'Q')
                return false;

        return true;
    }

    private static void nQueen(char mat[][], int r)
    {
        // if N queens are placed successfully, print the solution
        if (r == N)
        {
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < N; j++)
                    System.out.print(mat[i][j] + " ");
                System.out.println();
            }
            System.out.println();

            return;
        }

        // place Queen at every square in current row r
        // and recurse for each valid movement
        for (int i = 0; i < N; i++)
        {
            // if no two queens threaten each other
            if (isSafe(mat, r, i))
            {
                // place queen on current square
                mat[r][i] = 'Q';

                // recurse for next row
                nQueen(mat, r + 1);

                // backtrack and remove queen from current square
                mat[r][i] = '-';
            }
        }
    }

    private static int min(int x, int y)
    {
        return (x < y) ? x : y;
    }

    private static int max(int x, int y)
    {
        return (x > y) ? x : y;
    }

    private static int  findLength(int arr[], int n)
    {
        int max_len = 1;  // Initialize result
//        int start = 0;
//        int end = 0;

        for (int i = 0; i < n - 1; i++)
        {
            // Initialize min and max for all subarrays starting with i
            int mn = arr[i], mx = arr[i];

            // Consider all subarrays starting with i and ending with j
            for (int j = i + 1; j < n; j++)
            {
                // Update min and max in this subarray if needed
                mn = min(mn, arr[j]);
                mx = max(mx, arr[j]);

                // If current subarray has all contiguous elements
                if ((mx - mn) == j - i) {

//                    if (max_len < mx - mn + 1) {
//                        start = i;
//                        end = j;
//                    }

                    max_len = max(max_len, mx - mn + 1);
                }
            }
        }
        return max_len;  // Return result
    }

    public static void maxLengthSubArray(int[] A, int S)
    {
        // len stores the maximum length of sub-array with sum S
        int len = 0;

        // stores ending index of maximum length sub-array having sum S
        int ending_index = -1;

        // consider all sub-arrays starting from i
        for (int i = 0; i < A.length; i++)
        {
            int sum = 0;

            // consider all sub-arrays ending at j
            for (int j = i; j < A.length; j++)
            {
                // sum of elements in current sub-array
                sum += A[j];

                // if we have found a sub-array with sum S
                if (sum == S)
                {
                    // update length & ending index of max length subarray
                    if (len < j - i + 1)
                    {
                        len =  j - i + 1;
                        ending_index = j;
                    }
                }
            }
        }

        // print the sub-array
        System.out.println("[" + (ending_index - len + 1) + ", " + ending_index + "]");
    }

    public static void merge(int[] X, int[] Y)
    {
        int m = X.length;
        int n = Y.length;

        // consider each element X[i] of array X and ignore the element
        // if it is already in correct order else swap it with next smaller
        // element which happens to be first element of Y
        for (int i = 0; i < m; i++)
        {
            // compare current element of X[] with first element of Y[]
            if (X[i] > Y[0])
            {
                // swap (X[i], Y[0])
                int temp = X[i];
                X[i] = Y[0];
                Y[0] = temp;

                int first = Y[0];

                // move Y[0] to its correct position to maintain sorted
                // order of Y[]. Note: Y[1..n-1] is already sorted
                int k;
                for (k = 1; k < n && Y[k] < first; k++) {
                    Y[k - 1] = Y[k];
                }

                Y[k - 1] = first;
            }
        }
    }

    static boolean hasArrayTwoCandidates(int A[], int arr_size, int sum)
    {
        int l, r;

        /* Sort the elements */
        Arrays.sort(A);

        /* Now look for the two candidates
        in the sorted array*/
        l = 0;
        r = arr_size-1;
        while (l < r)
        {
            if(A[l] + A[r] == sum)
                return true;
            else if(A[l] + A[r] < sum)
                l++;
            else // A[i] + A[j] > sum
                r--;
        }
        return false;
    }

    public static void findMaximumProduct(int[] A)
    {
        int max_product = Integer.MIN_VALUE;
        int max_i = -1, max_j = -1;

        // consider every pair of elements
        for (int i = 0; i < A.length - 1; i++)
        {
            for (int j = i + 1; j < A.length; j++)
            {
                // update maximum product if required
                if (max_product < A[i] * A[j])
                {
                    max_product = A[i] * A[j];
                    max_i = i;
                    max_j = j;
                }
            }
        }

        System.out.print("Pair is (" + A[max_i] + ", " + A[max_j] + ")");
    }
// 3,7
// 4,6

    public static int[] simplestFormOutputMultiplication(int[] a, int[] b){

        int[] resArr = new int[2];

        if(a.length<2 && b.length<2){
         return null;
        }

        else if(a.length==2 && b.length==2){
         int crossProductNumerator = ((a[0]*b[1]) + (a[1]*b[0]));
         int crossProductDenominator = (a[1]*b[1]);
         resArr[0] = crossProductNumerator;
         resArr[1] = crossProductDenominator;
         //System.out.println(crossProductNumerator);
         //System.out.println(crossProductDenominator);

         while(resArr[0]%2==0 || resArr[0]%3==0 || resArr[0]%5==0 || resArr[0]%7==0 || resArr[0]%9==0) {
             //System.out.println(resArr[0]%2!=0 || resArr[0]%3!=0);
             //System.out.println(resArr[0]%2!=0 && resArr[0]%3!=0);
             for (int i = 2; i <= 9; i++) {
                 if (crossProductNumerator % i == 0 && crossProductDenominator % i == 0) {
                     resArr[0] = crossProductNumerator / i;
                     resArr[1] = crossProductDenominator / i;
                 }
             }
         }
     }
        return resArr;
    }

    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // Print the Product of N fraction in
// Reduced Form.
    static void productReduce(int n, int num[],
                              int den[])
    {
        int new_num = 1, new_den = 1;

        // finding the product of all N
        // numerators and denominators.
        for (int i = 0; i < n; i++) {
            new_num *= num[i];
            new_den *= den[i];
        }

        // Finding GCD of new numerator and
        // denominator
        int GCD = gcd(new_num, new_den);

        // Converting into reduced form.
        new_num /= GCD;
        new_den /= GCD;

        System.out.println(new_num + "/" +new_den);
    }


}
