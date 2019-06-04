package Dynamic;

import java.util.*;

public class Dynamic {

    public static void main (String[] args){

        String lcs = lcs ("GGCACCACG", "ACGGCGGATACG");
        System.out.println(lcs);

        System.out.println(countPathsRecursive(3,3));
        System.out.println(countPathsRecursive(4,4));
        System.out.println(countPathsRecursive(3,4));

        int size = 5;
        boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 90);
        boolean[][] maze1 = {{true,true,false,false},{false, true, true, false},{false, false, true, true},{false, false, false, true}};
        AssortedMethods.printMatrix(maze);
        AssortedMethods.printMatrix(maze1);
        ArrayList<Point> path = getPath(maze1);
        if (path != null) {
            System.out.println(path.toString());
        } else {
            System.out.println("No path found.");
        }

        int arr[] = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12,13};
        magicSlow(arr);
        magicFast(arr);

        permute("AABC".toCharArray());

        int arr3[] = { 10, 22, 9, 33, 21, 50, 41, 60 };
        int n = arr3.length;
        System.out.println("Length of lis is " + lis( arr3, n ) + "\n" );


        ArrayList<String> list = generateParens(2);
        ArrayList<String> list1 = generateParens(3);
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(list.size());

        int N = 3;
        int usedL = 0;
        int usedR = 0;
        String current = "";
        List<String> result = new ArrayList<String>();
        int depth = 0;

        generate(N, usedL, usedR, current, result, depth);

        for (String s : result) {
            System.out.printf("%s\n", s);
        }

        int[] arr4 = {6,5,3,2,2,8,10,9};
        sortNearlySortedArray(arr4, 3);

        String start = "hit";
        String end = "cog";
        Set dict = new HashSet();
        dict.add("hot");dict.add("dot");dict.add("dog");dict.add("lot");dict.add("log");
        int distance = ladderLength(start,end,dict);
        System.out.println("Distance is - " +distance);


        Set dict1 = new HashSet();
        dict1.add("apple");
        dict1.add("pen");
        wordBreak("applepenapple", dict1);
    }

    // (x- "GGCACCACG", y- "ACGGCGGATACG");
    // GGCAACG

    public static String lcs(String x, String y) {
        int m = x.length(), n = y.length();
        int[][] opt = new int[m+1][n+1];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                System.out.println("x - " + x.charAt(i) + " y - " + y.charAt(j));
                System.out.println("i(row) - " + i + " j(col) - " + j);
                if (x.charAt(i) == y.charAt(j)) {
                    opt[i][j] = opt[i+1][j+1] + 1;
                }
                else {
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                }
            }
        }
        // Recover LCS itself.
        String lcs = "";
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (x.charAt(i) == y.charAt(j)) {
                lcs += x.charAt(i);
                i++;
                j++;
            }
            else if (opt[i+1][j] >= opt[i][j+1]) i++;
            else j++;
        }
        return lcs;
    }


    // This problem is to find the number of possible paths in an N*M array
    // This problem only returns the possible paths integer value and does not track them
    // Also, this does not have obstacles in maze and you can move only in right or down direction

    public static int countPathsRecursive(int n, int m){
        if(n == 1 || m == 1){
            return 1;
        }
        return countPathsRecursive(n-1, m) + countPathsRecursive(n, m-1);
    }

    public static int countPaths(int n,int m){
        int T[][] = new int[n][m];
        for(int i=0; i < n; i++){
            T[i][0] = 1;
        }

        for(int i=0; i < m; i++){
            T[0][i] = 1;
        }
        for(int i=1; i < n; i++){
            for(int j=1; j < m; j++){
                T[i][j] = T[i-1][j] + T[i][j-1];
            }
        }
        return T[n-1][m-1];
    }

    public static ArrayList<Point> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0) return null;
        ArrayList<Point> path = new ArrayList<Point>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
            return path;
        }
        return null;
    }

    public static boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
        // If out of bounds or not available, return.
        if (col < 0 || row < 0 || !maze[row][col]) {
            return false;
        }

        boolean isAtOrigin = (row == 0) && (col == 0);

        // If there's a path from the start to my current location, add my location.
        if (isAtOrigin || getPath(maze, row, col - 1, path) || getPath(maze, row - 1, col, path)) {
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }

        return false;
    }

    // Magic index is the the index of an array where a[i] is i
    // a[4] = 4
    // a[1] = 1
    // a[0] = 0

    public static int magicSlow(int[] arr){
        for (int i=0; i<arr.length; i++){
            if(arr[i] == i){
                return i;
            }
        }
        return -1;
    }

    // This fast binary solution works only for sorted array with distinct elements
    // If the arr[mid]<mid
    // Why couldn't the magic index be on the left side?
    // Observe that when we move from i to i -1, the value at this index must decrease by at least 1,
    // if not more (since the array is sorted and all the elements are distinct).
    // So, if the middle element is already too small to be a magic index,
    // then when we move to the left, subtracting k indexes and (at least) k values, all subsequent elements will also be too small.

    public static int magicFast(int[] array, int start, int end) {
        if (end < start) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] == mid) {
            return mid;
        } else if (array[mid] > mid){
            return magicFast(array, start, mid - 1);
        } else {
            return magicFast(array, mid + 1, end);
        }
    }

    public static int magicFast(int[] array) {
        return magicFast(array, 0, array.length - 1);
    }

    // For reference
    public static boolean binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1, count=0;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x){
                count++;
                System.out.println(arr[m]);
                return true;
            }
            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;
                // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return false;
    }

    public static List<String> permute(char input[]) {
        Map<Character, Integer> countMap = new TreeMap<>();
        for (char ch : input) {
            countMap.compute(ch, (key, val) -> {
                if (val == null) {
                    return 1;
                } else {
                    return val + 1;
                }
            });
        }
        char str[] = new char[countMap.size()];
        int count[] = new int[countMap.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            str[index] = entry.getKey();
            count[index] = entry.getValue();
            index++;
        }
        List<String> resultList = new ArrayList<>();
        char result[] = new char[input.length];
        permuteUtil(str, count, result, 0, resultList);
        return resultList;
    }

    public static void permuteUtil(char str[], int count[], char result[], int level, List<String> resultList) {
        if (level == result.length) {
            resultList.add(new String(result));
            return;
        }

        for(int i = 0; i < str.length; i++) {
            if(count[i] == 0) {
                continue;
            }
            result[level] = str[i];
            count[i]--;
            permuteUtil(str, count, result, level + 1, resultList);
            count[i]++;
        }
    }

    private void printArray(char input[]) {
        for(char ch : input) {
            System.out.print(ch);
        }
        System.out.println();
    }

    static int lis(int arr[],int n)
    {
        int lis[] = new int[n];
        int i,j,max = 0;

        /* Initialize LIS values for all indexes */
        for ( i = 0; i < n; i++ )
            lis[i] = 1;

        /* Compute optimized LIS values in bottom up manner */
        for ( i = 1; i < n; i++ )
            for ( j = 0; j < i; j++ )
                if ( arr[i] > arr[j] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;

        /* Pick maximum of all LIS values */
        for ( i = 0; i < n; i++ )
            if ( max < lis[i] )
                max = lis[i];

        return max;
    }


    public static ArrayList<String> generateParens(int count) {
        char[] str = new char[count*2];
        ArrayList<String> list = new ArrayList<String>();
        addParen(list, count, count, str, 0);
        return list;
    }

    public static void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int index) {
        System.out.println(str);
        if (leftRem < 0 || rightRem < leftRem) return; // invalid state

        if (leftRem == 0 && rightRem == 0) { /* all out of left and right parentheses */
            list.add(String.copyValueOf(str));
        } else {
            str[index] = '('; // Add left and recurse
            addParen(list, leftRem - 1, rightRem, str, index + 1);

            str[index] = ')'; // Add right and recurse
            addParen(list, leftRem, rightRem - 1, str, index + 1);
        }
    }

    public static void generate(int N, int usedL, int usedR, String current, List<String> result, int depth) {

        int count = 0 ;
        System.out.printf("%susedL=%d, usedR=%d, current='%s'\n",
                getIndentation(depth), usedL, usedR, current);

        if (usedL == N && usedR == N) {
            // We've used up all the available parentheses (up to N),
            // so add the current built string to the result.
            result.add(current);
            return;
        }

        if (usedL < N) {
            // Add another left parenthesis "(".
            String newCurrent = current + "(";
            generate(N, usedL + 1, usedR, newCurrent, result, depth+1);
            System.out.println("first loop"  + usedL + usedR);
        }

        if (usedR < N && usedL > usedR) {
            // Add another right parenthesis ")" if there are already
            // used left parentheses.
            String newCurrent = current + ")";
            generate(N, usedL, usedR + 1, newCurrent, result, depth+1);
            System.out.println("second loop"  + usedL + usedR);
        }
    }

    // Utility function used for pretty-printing.
    private static String getIndentation(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }


    public static void sortNearlySortedArray(int[] arr, int k) {

  /*
    Create a min heap. Without a comparator Java (as of Java 10) defaults putting
    the smallest items at the front of the priority queue.
    Not sure if the Java PriorityQueue is implemented underneath with a binary heap...
    but yeah. Remember that a heap is an implementation of the priority queue ADT
    (abstract data type)
  */
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = arr.length;

  /*
    Add the first k elements to the min heap. Guard against the case that
    there are less than k items in the whole array
  */
        for (int i = 0; i < k && i < n; i++) {
            minHeap.add(arr[i]);
        }

  /* {6, 5, 3, 2, 8, 10, 9}
    Add and place...add and place...add and place from the heap. Initially, we
    added items from index 0 to index k - 1. We continue reading from index k
    and begin our minimum element placements at index 0.
    Continue while the read index stays within the index bounds of the array.
    When it runs over we will have no more items to add to the heap for consideration.
  */
        int readIndex = k;
        int placementIndex = 0;
        while (readIndex < n) {
    /*
      Add the next element to be considered in the heap. The heap will
      hold at max k + 1 items.
    */
            minHeap.add(arr[readIndex]);
            readIndex++;

    /*
      Remove the smallest item to place into the array. This item will be
      placed where it belongs by the definition of what k represents.
    */
            arr[placementIndex] = minHeap.poll();
            placementIndex++;
        }

  /*
    Empty out the rest of the heap & do placements.
  */
        while (!minHeap.isEmpty()) {
            arr[placementIndex] = minHeap.poll();
            placementIndex++;
        }

    }

    public static int ladderLength(String beginWord, String endWord, Set<String> wordDict) {
        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));

        wordDict.add(endWord);

        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;

            if(word.equals(endWord)){
                return top.numSteps;
            }

            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    char temp = arr[i];
                    if(arr[i]!=c){
                        arr[i]=c;
                    }

                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, top.numSteps+1));
                        wordDict.remove(newWord);
                    }

                    arr[i]=temp;
                }
            }
        }

        return 0;
    }

    public static boolean wordBreak(String s, Set<String> dict) {

        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;

        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                System.out.println(s.substring(j, i));
                if(f[j] && dict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        return f[s.length()];
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<String>();
        Set<String> resset = new HashSet<String>();
        if(s == null || s.length() <= 10){
            return res;
        }
        Set<String> set = new HashSet<String>();
        int len = s.length();
        for(int i = 0; i <= len - 10; i++){
            String sub = s.substring(i, i + 10);
            if(!set.add(sub)){
                resset.add(sub);
            }
        }
        res.addAll(resset);
        return res;
    }

}

class WordNode{
    String word;
    int numSteps;

    public WordNode(String word, int numSteps){
        this.word = word;
        this.numSteps = numSteps;
    }
}
