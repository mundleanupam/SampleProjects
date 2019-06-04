package Generic;

import java.lang.reflect.Array;
import java.util.*;


public class ArrayAndStrings {

    public static void main(String args[]) {
        System.out.println(parseFormula("Ch2H2O").toString());
        System.out.println(parseFormula("CO2").toString());
        System.out.println(parseFormula("H2O").toString());
//        parseFormula("ch2");
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> one = Arrays.asList(-1);
        List<Integer> two = Arrays.asList(3,2);
        List<Integer> three = Arrays.asList(-3,1,-1);
        //List<Integer> four = Arrays.asList(4,1,8,3);
        list.add(one);
        list.add(two);
        list.add(three);
        //list.add(four);
        System.out.println("SUM:"+ minimumTotal(list));

        System.out.println(isUniqueChars2("anupam"));
        System.out.println(isUniqueChars2("anupm"));

        char[] str = {'a','a', 'a', 'b','a','c','v','f','d'};
        System.out.println(removeDuplicates(str));
        System.out.println(removeDuplicatesEff(str));

        System.out.println(isAnagram("geeksforgeeks", "forgeeksgeeks"));

        System.out.println(replaceSpace("My name is Anupam"));
        System.out.println(replaceSpace1("My name is Anupam"));

        int coins[] = {1,5,10};
        System.out.println(countWays(coins, 3, 78));

        int arr[] = {1,2,3,0,0,0,6};
        int arr1[] = {0, 0, 0, 1,2,3,0,0,6, 8};
        retIndices(arr);
        retIndices(arr1);

        pushZerosToEnd(arr);
        pushZerosToEnd(arr1);

        int a[][] = {{1, 2, 0}, {4, 0, 0}, {7, 8, 9}};
        printMatrix(a);
        System.out.println();
        matrixRotate(a);
        printMatrix(a);
        System.out.println();

        matrixReplace((a));
        System.out.println();
        printMatrix(a);

        findMistake();

        System.out.println(findFirstDuplicate("aabcdefghijklmnopqrstuvz"));

        genSpiral(3);

        alphabetCount("string", "strong");
        System.out.println();

        System.out.println(common("string", "strong"));
        int arr2[] = { 2, 3, 4, 1, 4, 5 };
        System.out.println(findElement1(arr2, 6));
        System.out.println(findElement(arr2, 6));

        int bin[] = { 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0};
        System.out.println(maxConsecutiveOnes(bin));

        int arr3[] = {4, 5, 6, 7, 8, 9, 12, 15, 16, 17, 18, 20, 22, 23, 24, 27};
        System.out.println(oddevenSort(arr));

        System.out.println(retConsecutiveIndices(arr));

        int arr4[] = {3,4,7,7,6,6};
        int arr5[] = {80,80,1000000,80,80,80,80,80,80,123456};
        solution(arr4);
        solution(arr5);

        int add_one_arr[] = {1,9,9};
        int add_one_arr1[] = {9,9,9};
        add_one(add_one_arr);
        add_one(add_one_arr1);

        printPattern();

        printSequence();

        isVowel('a');

        int matrix[][] = {
                {0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};
        printMaxSubSquare(matrix);

        String str1 = replaceVowels("bcdaei");
        System.out.print(str1);

        int arr6[] = {0, 0, 1, 5, 9, 7, 3};
        int arr7[] = {0, 0, 1, 1, 5, 9, 7, 3};
        int arr8[] = {1, 5, 9, 7, 3};
        System.out.println(isSum2(arr7, 21));
        System.out.println(isSum2(arr6, 19));
        System.out.println(isSum2(arr6, 3));
        System.out.println(isSum2(arr8, 3));
        System.out.println(isSum(arr8, 21));
        System.out.println(isSum(arr6, 21));
        System.out.println(isSum(arr6, 3));

        System.out.println(subArraySum(arr7, 21));
        System.out.println(subArraySum(arr6, 19));
        System.out.println(subArraySum(arr6, 3));
        System.out.println(subArraySum(arr8, 3));
        System.out.println(subArraySum(arr8, 22));


        int arr9[] = {5,6,3,15,11};
        System.out.println("Nth Smallest - "+NthSmallest(arr9, 3));
    }

    public static void printMatrix(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print("[");
            for (int j = 0; j < a.length; j++) {
                System.out.print("  " + a[i][j] + "  ");
            }
            System.out.println("]");
        }
    }

    //    Problem 1
    //    Implement an algorithm to determine if a string has all unique characters. What if you
    //    can not use additional data structures?

    public static boolean isUniqueChars2(String str) {
        boolean[] char_set = new boolean[127];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) return false;
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isUniqueChars1(String str) {
        HashSet<Character> chars = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (!chars.contains(str.charAt(i))) {
                chars.add(str.charAt(i));
            } else {
                return false;
            }
        }
        return true;
    }

    //CO2 - C1 O2
    //H20 - H2 O1
    //Ch2 - Ch2
    //He2O

    public static HashMap<String, Integer> parseFormula(String str){
        HashMap<String, Integer> map = new HashMap<>();
        char[] strArr = str.toCharArray();
        int len= str.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i<len-1){
            if((strArr[i+1]> 'a' && strArr[i+1] < 'z') && (strArr[i]> 'A' && strArr[i] < 'Z')){
                if(strArr[i+2]>'0' && strArr[i+2]<'9' && i<len-2) {
                    map.put(strArr[i]+""+strArr[i+1], Integer.valueOf(strArr[i+2] - '0'));
                    i += 3;
                    continue;
                }
                else {
                    map.put(strArr[i] + "" + strArr[i + 1], 1);
                    i += 2;
                    continue;
                }
            }
            else if(strArr[i]> 'A' && strArr[i] < 'Z'){
                if(strArr[i+1]>'0' && strArr[i+1]<'9') {
                    map.put(strArr[i]+ "", Integer.valueOf(strArr[i+1] - '0'));
                    i += 2;
                    continue;
                }
                else {
                    map.put(strArr[i] + "", 1);
                    i += 1;
                    continue;
                }
            }
        }
        if(strArr[len-1]>'A' && strArr[len-1]<'Z')
        map.put(strArr[i]+"", 1);
        return map;

    }

    //char[] str = {'a', 'a', 'a', 'b', 'a', 'c', 'v', 'f', 'd'};
    public static String removeDuplicates(char[] str) {
        if (str == null) return null;
        int len = str.length;
        if (len < 2) return null;

        int tail = 1;

        for (int i = 1; i < len; ++i) {
            int j;
            for (j = 0; j < tail; ++j) {
                if (str[i] == str[j]) break;
            }
            if (j == tail) {
                str[tail] = str[i];
                ++tail;
            }
        }
        //str[tail] = 0;
        return String.valueOf(str).substring(0, tail);
        //return retStr;
    }

    //{'a','a', 'a', 'b','a','c','v','f','d'};
    public static char[] removeDuplicatesEff(char[] str) {
        if (str == null) return null;
        int len = str.length;
        if (len < 2) return null;
        boolean[] hit = new boolean[256];
        for (int i = 0; i < 256; ++i) {
            hit[i] = false;
        }
        hit[str[0]] = true;
        int tail = 1;
        for (int i = 1; i < len; ++i) {
            if (!hit[str[i]]) {
                str[tail] = str[i];
                ++tail;
                hit[str[i]] = true;
            }
        }
        str[tail] = 0;
        return str;
    }

    //Write a method to decide if two strings are anagrams or not

    public static boolean isAnagram(String str1, String str2) {
        char[] strArray1 = str1.toCharArray();
        char[] strArray2 = str1.toCharArray();
        String returnedStr1 = bubbleSort(strArray1).toString();
        String returnedStr2 = bubbleSort(strArray2).toString();
        System.out.println(returnedStr1);
        System.out.println(returnedStr2);
        if (returnedStr1.equals(returnedStr2)) {
            return true;
        } else {
            return false;
        }
    }

    // In bubble sort everytime the comparison is made between the consecutive elements one less time
    // Every pass guarantees the sorting of 1 element

    public static String bubbleSort(char arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap temp and arr[i]
                    Character temp = arr[j]; // temp = A
                    arr[j] = arr[j + 1];     // A = B
                    arr[j + 1] = temp;       // B = temp
                }

        return String.valueOf(arr);
    }

    //Write a method to replace all spaces in a string with ‘%20’

    public static String replaceSpace(String str) {
        StringBuilder result = new StringBuilder();
        if (str.length() < 1) return "";
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (val == 32) result.append("%20");
            else result.append(str.charAt(i));
        }
        return result.toString();
    }

    public static String replaceSpace1(String str) {
        StringBuilder result = new StringBuilder();
        if (str.length() < 1) return "";
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') result.append("%20");
            else result.append(str.charAt(i));
        }
        return result.toString();
    }

    //Given an image represented by an NxN matrix, where each pixel in the image is 4
    //bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

    public static int[][] matrixRotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix[0].length; j++) {
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        System.out.println("After 1st replace");
        printMatrix(matrix);
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length / 2; j++) {
                //System.out.println(matrix.length/2);
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length - 1 - j];
                matrix[i][matrix.length - 1 - j] = temp;
            }
        }
        return matrix;
    }

    //Write an algorithm such that if an element in an MxN matrix is 0, its entire row and column is set to 0.

    public static int[][] matrixReplace(int[][] matrix) {
        HashMap<Integer, Integer> hm = new HashMap();
        int i, j, k, l;
        for (i = 0; i < matrix[0].length; i++) {
            for (j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    hm.put(i, j);
                }
            }
        }
        for (k = 0; k < matrix[0].length * 3; k++) {
            if (hm.containsKey(k)) {
                int col = hm.get(k);
                for (l = 0; l < matrix.length; l++) {
                    matrix[k][l] = 0;
                    matrix[l][col] = 0;
                }
            } else {
                break;
            }
        }
        return matrix;
    }

    // Easy implemantation of same above problem
    public static void SetZero( int [][] matrix, int m, int n )
    {
        boolean[] row = new boolean[m];
        boolean[] column = new boolean[n];
        for( int i = 0; i < m; i++ )
        {
            for( int j = 0; j < n; j++ )
            {
                if( matrix[i][j] == 0 )
                {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for( int i = 0; i < m; i++ )
        {
            if( row[i] )
            {
                for( int j = 0; j < n; j++ )
                {
                    matrix[i][j] = 0;
                }
            }
        }
        for( int j = 0; j < n; j++ )
        {
            if( column[j] )
            {
                for( int i = 0; i < m; i++ )
                {
                    matrix[i][j] = 0;
                }
            }
        }

    }

    static void pushZerosToEnd(int arr[]) {
        int n = arr.length;
        int count = 0;  // Count of non-zero elements

        // Traverse the array. If element encountered is
        // non-zero, then replace the element at index 'count'
        // with this element
        for (int i = 0; i < n; i++)
            if (arr[i] != 0)
                arr[count++] = arr[i]; // here count is incremented
                // count++;

        // Now all non-zero elements have been shifted to
        // front and 'count' is set as index of first 0.
        // Make all elements 0 from count to end.
        while (count < n)
            arr[count++] = 0;
    }

    //Assume you have a method isSubstring which checks if one word is a substring of
    //another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using
    //only one call to isSubstring (i.e., “waterbottle” is a rotation of “erbottlewat”).

    public static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        /* check that s1 and s2 are equal length and not empty */
        if (len == s2.length() && len > 0) {
            /* concatenate s1 and s1 within new buffer */
            String s1s1 = s1 + s1;
            //return isSubstring(s1s1, s2);
        }
        return false;
    }

    public static void findMistake() {
        int i;
        for (i = 10; i >= 0; --i)
            System.out.println(i);
    }

    //Output the first duplicated char in a string anupam

    public static char findFirstDuplicate(String s) {
        char[] arr = s.toCharArray();
        boolean[] charArr = new boolean[256];
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            if (charArr[arr[i]] == true)
                return arr[i];
            charArr[arr[i]] = true;
        }
        return 0;
    }

    // This problem is similar to above problem

    static char firstRepeating(char str[]) {
        // Creates an empty hashset
        HashSet<Character> h = new HashSet<>();
        // Traverse the input array from left to right
        for (int i = 0; i <= str.length - 1; i++) {
            char c = str[i];
            // If element is already in hash set, update x
            // and then break
            if (h.contains(c))
                return c;
            else // Else add element to hash set
                h.add(c);
        }
        return '\0';
    }

    // This generates a spiral of the number provided in a matrix
    // n = 9 ->
    // 1 2 3
    // 8 9 4
    // 7 6 5

    public static int[][] genSpiral(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("N must be >0");
        }
        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{1, 0, -1, 0};
        int dir = 0, val = 0, r = 0, c = 0, limit = n * n;
        int[][] matrix = new int[n][n];
        while (val++ < limit) {
            matrix[r][c] = val;
            r += dr[dir];
            c += dc[dir];
            if (isInvalid(matrix, r, c)) {
                r -= dr[dir];
                c -= dc[dir];
                dir = (dir + 1) % 4;
                r += dr[dir];
                c += dc[dir];
            }
        }
        return matrix;
    }

    private static boolean isInvalid(int[][] m, int r, int c) {
        System.out.println(m.length);
        return r < 0 || c < 0 || r >= m.length || c >= m.length || m[r][c] != 0;
    }

    static long countWays(int S[], int m, int n) {
        //Time complexity of this function: O(mn)
        //Space Complexity of this function: O(n)

        // table[i] will be storing the number of solutions
        // for value i. We need n+1 rows as the table is
        // constructed in bottom up manner using the base
        // case (n = 0)
        long[] table = new long[n + 1];

        // Initialize all table values as 0
        Arrays.fill(table, 0);   //O(n)

        // Base case (If given value is 0)
        table[0] = 1;

        // Pick all coins one by one and update the table[]
        // values after the index greater than or equal to
        // the value of the picked coin
        for (int i = 0; i < m; i++)
            for (int j = S[i]; j <= n; j++)
                table[j] += table[j - S[i]];

        return table[n];
    }


    // Return the Start and End indices consecutive non-zero elements of an array
    //{0,0,0,1,2,3,0,0,6,8} -> {{3,5}{8,9}}
    //{1,2,3,0,0,6} -> {{0,2}{5,5}}

    public static HashMap<Integer, Integer> retIndices(int a[]) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        int startIndex = 0;
        int endIndex = 0;
        boolean isStartSet = false;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                if (isStartSet) {
                    isStartSet = false;
                }
            } else {
                if (!isStartSet) {
                    startIndex = i;
                    isStartSet = true;
                    endIndex = i;
                } else {
                    endIndex = i;
                }
                hm.put(startIndex, endIndex);
            }
        }
        return hm;
    }

    public static void alphabetCount(String str1, String str2) {
        String str3 = str1.concat(str2);
        char[] c = str3.toCharArray();
        LinkedHashSet<Character> l = new LinkedHashSet<Character>();
        for (char c1 : c) {
            l.add(c1);
        }
        Iterator<Character> l1 = l.iterator();
        while (l1.hasNext()) {
            System.out.print(l1.next());
        }
    }

    // Find the common chars in two strings s1 and s2

    public static String common(String s1, String s2) {
        Character c = null;
        String str3 = "";
        LinkedHashSet<Character> hs = new LinkedHashSet<Character>();
        for (int i = 0; i < s1.length(); i++) {
            hs.add(s1.charAt(i));
        }
        for (int i = 0; i < s2.length(); i++) {
            c = s2.charAt(i);
            if (hs.contains(c))
                str3 = str3 + c;
        }
        return str3;
    }

    // Find the intersection of 2 sorted arrays
    // int a[] = {1, 2, 3, 3, 4, 5, 5, 6};
    // int b[] = {3, 3, 5};
    // --> {3, 3, 5}

    public static void intersection(int a[], int b[], int n, int m)
    {
        int i = 0, j = 0;
        while (i < n && j < m)
        {
            if (a[i] > b[j])
            {
                j++;
            }
            else if (b[j] > a[i])
            {
                i++;
            }
            else
            {
                // when both are equal
                System.out.print(a[i] + " ");
                i++;
                j++;
            }
        }
    }

    // Similar to above problem. This solution works for unsorted array as well with O(n⌃2)
    public static void intersectionUnsorted(int a[], int b[], int n, int m){
    List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i] == b[j]) {
                list.add(a[i]);
            }
        }
    }
        System.out.println(list);
    }


    // Find the total of minimum total of in a triangle.
    // This problem works only with bottom up approach
    // You can get on similar or consecutive index values when row is changed.

    public static int minimumTotal(List<List<Integer>> triangle) {
        int index = 1;
        int sum = 0;
        int len = triangle.size();
        int last;
        for(int i=len-1; i>=0; i--){
            if ( i == len-1 ){
                last = i;
            }
            else {
                last = index + 1;
            }
            index = getMinimum(triangle.get(i), index, last);
            int min = triangle.get(i).get(index);
            sum += min;
        }
        return sum;
    }

    public static int getMinimum(List<Integer> list, int index, int last){
        int min = Integer.MAX_VALUE;
        int start;
        int ind = 0;
        if(list.size()==1){
            return ind;
        }
        if(index <= 0){
            start = 0;
        }
        else {
            start = index - 1;
        }
        for(int i=start; i < list.size() && i<=last; i++){
            if(min>list.get(i)){
                min = list.get(i);
                ind = i;
            }
        }
        return ind;
    }

    // Partitioned element which divides the left and right arrays
    // int arr[] = { 2, 3, 4, 1, 4, 5 }
    // In above example 1 is the partitioning element

    public static int findElement(int arr[], int size)
    {
        int right_sum = 0, left_sum = 0;

        // Computing right_sum
        for (int i = 1; i < size; i++)
            right_sum += arr[i];

        // Checking the point of partition
        // i.e. left_Sum == right_sum
        for (int i = 0, j = 1; j < size; i++, j++) {
            right_sum -= arr[j];
            left_sum += arr[i];

            if (left_sum == right_sum)
                return arr[i + 1];
        }

        return -1;
    }

    public static int findElement1(int arr[], int size)
    {
        int i = 0;
        int j = size-1;
        int leftSum=0, rightSum=0;
        while(i<j){
            if(leftSum<rightSum){
                leftSum += arr[i];
                i++;
                continue;
            }
            if(leftSum>rightSum){
                rightSum += arr[j];
                j--;
                continue;
            }
            if(leftSum==rightSum){
                leftSum += arr[i];
                rightSum += arr[j];
                i++;
                j--;
                continue;
            }

        }
        if(leftSum==rightSum && i==j){
            return arr[i];
        }
        return -1;
    }

    // Find the consecutive one's in an integer array
    // int arr[] = {0,1,0,1,1,0,1,1,1,1,0,0,0,1,1,0,1,1,1}
    // For above problem the answer would be 4

    public static int maxConsecutiveOnes(int arr[]){
        int runningOnes = 0;
        int max = 0;

        for(int i=0; i<arr.length; i++){
            if(arr[i]==1){
                runningOnes=runningOnes+1;
            }
            else{
                if(max<runningOnes) {
                    max = runningOnes;
                    runningOnes=0;
                }
                else{
                    runningOnes=0;
                }
            }
        }
        if(runningOnes<max) return max;
        return runningOnes;
    }


    // Problem is to return the indices of the consecutive values in an integer array
    // int arr[] = {4, 5, 6, 7, 8, 9, 12, 15, 16, 17, 18, 20, 22, 23, 24, 27};
    // For above array returned list will be {{0,5}, {7,10}, {12, 14}}

    public static List<List<Integer>> retConsecutiveIndices(int arr[]){

        int len = arr.length;
        List<List<Integer>> finalIndices = new LinkedList<List<Integer>>();
        int startIndex=0;
        int endIndex=0;
        for (int i=0; i<len-1; i++){
                if (arr[i + 1] == arr[i] + 1) {
                    endIndex = i + 1;
                    continue;
                }
                if (startIndex != endIndex) {
                    List<Integer> indices = new LinkedList<Integer>();
                    indices.add(startIndex);
                    indices.add(endIndex);
                    finalIndices.add(indices);
                }
                startIndex = i + 1;
                endIndex = i + 1;
            }
        return finalIndices;
    }


    /*
    [1, 3]- [10, 17] -[2,7] -[11, 20]
    Sort the intervals by their starting points
    [1, 3]- [2,7] -[10, 17] -[11, 20]
    start - 1
    end - 3
    1<3? = YES. no changes
    2<3? = YES end = 7
    10<7 ? No
    Add start=1 and end=7 to the result list and update start and end to [10, 17]


    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1)
            return intervals;

        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));

        List<Interval> result = new LinkedList<Interval>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;

        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }

        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }*/

    // This problem was asked in Couchbase interview
    // Problem is to sort an integer array in such a way that all the odd elements will be at the left side of an array and even elements will be at the right side
    // {1,4,2,3,6,7,9,8,6,7,3,4,5,6,7,8,10,13,16,13}
    // Odd to the left and even to the right

    public static int[] oddevenSort(int arrOddEven[]) {

        int oddCounter = 0;
        int evenCounter = arrOddEven.length-1;

        while (oddCounter < evenCounter) {
            if ((arrOddEven[oddCounter] % 2 == 0) && (arrOddEven[evenCounter] % 2 != 0)) {
                swap(arrOddEven, oddCounter, evenCounter);
                oddCounter++;
                evenCounter--;
            }
            else if ((arrOddEven[oddCounter] % 2 != 0) && (arrOddEven[evenCounter] % 2 == 0)){
                oddCounter++;
                evenCounter--;
            }
            else if (arrOddEven[oddCounter] % 2 != 0 && arrOddEven[evenCounter] % 2 != 0) {
                oddCounter++;
            }
            else if (arrOddEven[oddCounter] % 2 == 0 && arrOddEven[evenCounter] % 2 == 0) {
                evenCounter--;
            }
        }
        return arrOddEven;
    }

    public static void swap(int arr[], int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    // This is candies problem. This was asked to solve to enroll in mock interviews
    //[3,4,7,7,6,6] -> 3
    //[80,80,1000000,80,80,80,80,80,80,123456] -> 3
    public static int solution(int[] arr){
        Set<Integer> s = new HashSet<>();
        for(int i=0; i<arr.length; i++){
            if(!s.contains(arr[i])){
                s.add(arr[i]);
            }
        }
        int candies = arr.length/2;
        int uniqCandies = s.size();

        if(candies>uniqCandies){
            System.out.println(uniqCandies);
            return uniqCandies;
        }
        else{
            System.out.println(candies);
            return candies;
        }
    }

    // Given an integer array just add "1" to the total number
    // int[] arr = {1,0,3} - > {1,0,4}
    // int[] arr = {1,0,0} - > {1,0,1}
    // int[] arr = {9,9,9} - > {1,0,0,0}

    public static int[] add_one(int[] arr){
        int carry =1;
        int[] result = new int[arr.length];
        for(int i=arr.length-1; i>=0; i-- ){

            int total = arr[i] + carry;
            if (total==10) carry=1;
            else carry=0;
            result[i] = total % 10;
        }
        if (carry==1){
            result = new int[arr.length+1];
            result[0]=1;
        }
    return result;
    }

    public static void printPattern(){

        int i = 1;
        int j = 9;

        while(i<=9){
            System.out.print(i +", " +j+", ");
            i++;
            j--;
        }
    }

    // This was asked in AR/VR position
    // Given a number print the elements in such a form that all the consecutive numbers will be printed in alternate fashion back and forth
    // Given a num = 9
    // It prints ->  1, 9, 2, 8, 3, 7, 4, 6, 5, 5, 6, 4, 7, 3, 8, 2, 9, 1
    public static void printSequence(){
        int[] a = new int[18];

        for(int i=1, j=9; i<=9; i++, j--){
            a[(i-1)*2] = i;
            a[(j*2)-1] = i;
        }
        for(int i=0; i<18; i++) {
            System.out.print(a[i]+", ");
        }

    }

    // Similar question asked in Google on-site

    static List<ArrayList<Integer>> printMaxSubSquare(int M[][])
    {
        List<ArrayList<Integer>> Result = new ArrayList<ArrayList<Integer>>(4);
        int i,j;
        int R = M.length;        //no of rows in M[][]
        int C = M[0].length;     //no of columns in M[][]
        int S[][] = new int[R][C];

        int max_of_s, max_i, max_j;

        /* Set first column of S[][]*/
        for(i = 0; i < R; i++)
            S[i][0] = M[i][0];

        /* Set first row of S[][]*/
        for(j = 0; j < C; j++)
            S[0][j] = M[0][j];

        /* Construct other entries of S[][]*/
        for(i = 1; i < R; i++)
        {
            for(j = 1; j < C; j++)
            {
                if(M[i][j] == 1) {
                    System.out.println(" i is -> " +i+ " j is -> "+j+" j-1 is -> "+(j-1)+"");
                    S[i][j] = Math.min(S[i][j - 1],
                            Math.min(S[i - 1][j], S[i - 1][j - 1])) + 1;
                }
                else {
                    S[i][j] = 0;
                }
            }
        }

        /* Find the maximum entry, and indexes of maximum entry
            in S[][] */
        max_of_s = S[0][0]; max_i = 0; max_j = 0;
        for(i = 0; i < R; i++)
        {
            for(j = 0; j < C; j++)
            {
                if(max_of_s < S[i][j])
                {
                    max_of_s = S[i][j];
                    max_i = i;
                    max_j = j;
                }
            }
        }

        Result.add(new ArrayList<Integer>(Arrays.asList(max_i,max_j)));
        Result.add(new ArrayList<Integer>(Arrays.asList(max_i,max_j-max_of_s+1)));
        Result.add(new ArrayList<Integer>(Arrays.asList(max_i-max_of_s+1,max_j-max_of_s+1)));
        Result.add(new ArrayList<Integer>(Arrays.asList(max_i-max_of_s+1,max_j)));

        System.out.println("Maximum size sub-matrix is: ");

        for(i = max_i; i > max_i - max_of_s; i--)
        {
            for(j = max_j; j > max_j - max_of_s; j--)
            {
                System.out.print(M[i][j] + " ");
            }
            System.out.println();
        }

        return Result;
    }

    // This was asked in google on-site
    // Problem is to replace all the vowels with "-" or"_" anything in such a way that
    // a,e,i,o,u = ""
    // a,e,e,b = b--b
    // bcd = bcd
    // "" = ""
    // a,e,i,b,d = bd_bd
    // a,b,i,c,e,d = b,b,c,c,d,d
    // b,c,d,a,e,i = b,c,d,_,_,_
    // a,b,i,c,e,d = b,b,c,c,d,d

    public static String replaceVowels(String str){
        if(str.length()<1 || str.isEmpty()) return null;
        char[] arr = str.toCharArray();
        int vp = 0;
        int cp = 0;
        // a,b,i,c,e,d = b,b,c,c,d,d
        for(int i =0; i<arr.length; i++){
            if(isVowel(arr[vp] ) && !isVowel(arr[cp]) && vp<cp) {
                insert(arr, vp, cp);
                cp++;
                vp++;
            }
                if(isVowel(arr[i])){
                    cp++;
                }
                else{
                    if(isVowel(arr[vp])) {
                        insert(arr, vp, cp);
                        cp++;
                        vp++;
                    }
                    else{
                        vp++;
                    }
                }
            }
        if(vp<cp){
            for(int j =vp; j<cp; j++){
                if(isVowel(arr[j])) {
                    arr[j] = '_';
                }
            }
        }
        else if(vp==cp){
            for(int k =vp; k<arr.length; k++){
                if(isVowel(arr[k])) {
                    arr[k] = '_';
                }
            }
        }
        return String.valueOf(arr);
    }


    public static void insert(char[] A, int i, int j){
        A[i]= A[j];

    }

    public static boolean isVowel(char c){
        if(c=='a'|| c=='e' || c=='i' || c=='o' || c=='u'){
            return true;
        }
        else return false;
    }

    //    Write code to reverse a C-Style String. (C-String means that “abcd” is represented as
//    five characters, including the null character.)

//    void reverse(char *str) {
//         char * end = str;
//         char tmp;
//         if (str) {
//             while (*end) {
//                 ++end;
//                 }
//             --end;
//             while (str < end) {
//                 tmp = *str;
//                 *str++ = *end;
//                 *end-- = tmp;
//                 }
//             }
//         }

//    Design an algorithm and write code to remove the duplicate characters in a string
//    without using any additional buffer. NOTE: One or two additional variables are fine.
//    An extra copy of the array is not.
    //****** need to revisit

    // Asked for Oculus phone interview

    /**
     * Inputs: Array of integers, target integer
     * Output: Does the array have a continuous subarray that sums to the total? (True/False)
     * Ex:
     * [1, 5, 9, 7, 3]
     * 21 = 5 + 9 + 7  -> return true
     * 13 = 1 + 5 + ... 7? -> return false
     * 3 = 3           -> return true
     */

    // This is brute force with O(n2) time complexity
    // Another solution is with backtracking/DP

    public static int subArraySum(int arr[], int sum)
    {
        int n = arr.length;
        int curr_sum, i, j;

        // Pick a starting point
        for (i = 0; i < n; i++)
        {
            curr_sum = arr[i];

            // try all subarrays starting with 'i'
            for (j = i + 1; j <= n; j++)
            {
                if (curr_sum == sum)
                {
                    int p = j - 1;
                    System.out.println("Sum found between indexes " + i
                            + " and " + p);
                    return 1;
                }
                if (curr_sum > sum || j == n)
                    break;
                curr_sum = curr_sum + arr[j];
            }
        }

        System.out.println("No subarray found");
        return 0;
    }

    public static boolean isSum (int[] arr, int val){
        if(arr == null || arr.length == 0) return false;
            int current_sum = arr[0];
            int start = 0;
            int len = arr.length;
            for(int i=1; i<=len; i++){
                while(current_sum>val && start < i-1){
                    current_sum = current_sum - arr[start];
                    start++;
                }
                if(current_sum == val){
                    return true;
                }
                if(i<len){
                    current_sum = current_sum + arr[i];
                }
            }
            return false;
        }

    public static boolean isSum2 (int[] arr, int val){
        if(arr == null || arr.length == 0) return false;
        HashMap<Integer, Integer> diffMap = new HashMap<Integer, Integer>();
        int sum = 0;
        int end = -1;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
            if (sum == val){
                end = -1;
                break;
            }
            if(diffMap.containsKey(sum - val)){
                 end = i;
            }
            diffMap.put(sum, i);
        }
        if (end == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isSum1 (int[] arr, int val){
        if(arr == null || arr.length == 0) return false;
        HashMap<Integer, Integer> diffMap = new HashMap<Integer, Integer>();
        int sum = 0;
        int start = 0;
        for(int i=0; i<arr.length; i++){
            sum += arr[i];
            if(sum < val){
                diffMap.put(i, val+arr[i]);
                continue;
            }
            else{
                if (diffMap.get(start) == sum){
                    return true;
                }
                else{
                    sum -= arr[start];
                    start++;
                }
            }
            if(sum == val) return true;
        }
        return false;
    }

    // Nth Smallest element of the unsorted array

    public static int NthSmallest(int[] arr, int m){
        int start = 0;
        int end = arr.length-2;
        int index = 0;
        int[] newArray = new int[arr.length-1];
        for(int i=1; i<arr.length; i++){
            if(arr[i]<arr[index]){
                newArray[start] = arr[i];
                start++;
            }
            else{
                newArray[end] = arr[i];
                end--;
            }
        }
        System.out.println(Arrays.toString(newArray));
        if(m>start){
            return NthSmallest(Arrays.copyOfRange(newArray, start+1, newArray.length), m-start);
        }
        else if (m<start){
            return NthSmallest(Arrays.copyOfRange(newArray, 0, start), m);
        }
        else{
            return arr[start];
        }
    }
}