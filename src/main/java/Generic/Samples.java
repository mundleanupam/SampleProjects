package Generic;

import java.util.*;

public class Samples {

    public static void main(String args[]) throws Exception {

        callMethods();
    }

    public static void callMethods() {
        char arr[][] = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println("Is Valid Sudoku? - "+isValidSudoku(arr));

        printSquare(12);

        int x = 123;
        System.out.println();
        System.out.println("Reverse of the number "+ x + " is "+reverse1(x));
        System.out.println("Reverse of the number (LeeetCode)"+ x + " is "+reverse(x));

        String s = longestPalindrome("forgeeksskeegfor");
        System.out.println(s);

        int A[] = {0,1,2,10,15,60};
        int B[] = {61, 65, 70, 73, 74, 75};
//      int B[] = {10, 15, 20, 45, 50, 61};
//      0,1,2,10,10,15,15,20,45,50,60,61
        System.out.println("Median = "+findMedianSortedArrays(A, B));  //Need to revisit
        findMedianSortedArraysMyVersion(A, B);
        System.out.println();

        System.out.println(zigzag1("APPLEISHIRING", 3));
        System.out.println(zigzag1("MYNAMEISANUPAM", 3));

        System.out.println(factorialRec(3));

        String a = "  79 U 0 T7165  0128862 089 39 5";
        System.out.println(atoi(a));

        System.out.println(isPalindrome1(-121));
        System.out.println(isPalindrome(121));

        System.out.println(isMatch("aaa", "aa."));

        int area[]  = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(area));

        System.out.println(intToRoman1(1287));
        System.out.println(romanToInt1("MCMLXXXVII"));

        System.out.println(longestCommonPrefix(new String[]{"abcd", "abc", "abcde"}));

        int[] n = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(n));

        System.out.println(letterCombinations("234"));

        int F[] = {-1, 0, 3, 4, 0, -3, 10, 11, 20};
        int F1[] = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(findMaxSumOfSubArray(F));
        System.out.println(maxSubArray(F));

        System.out.println(isParenthesisValid("{[()()()]}"));
        System.out.println(isParenthesisValid("ABC{[()()()]}"));
        System.out.println(isParenthesisValid("(apple)(test)abc(())"));
        System.out.println(generateParenthesis(3));

        countAndSay(5);

        int[] n1 = {-2, -1, 0, 4, 3, 1};
        int[] n2 = {1001, 1002, 1004, 1005};
        System.out.println(firstMissingPositive(n1));
        System.out.println(firstMissingPositive(n2));

        int C[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(C));

        System.out.println(multiply("210", "100000"));

        int a1[][] = {{1,2,3},{4,5,6},{7,8,9}};
        int b1[][] = matrixRotate((a1));
        for (int i =0; i<a1.length; i++){
            System.out.print("[");
            for (int j=0; j<a1.length; j++){
                System.out.print("  "+a1[i][j]+"  ");
            }
            System.out.println("]");
        }

        System.out.println(multiply(2 ,3));
        System.out.println(multiply(2 ,-3));
        System.out.println(multiply(-2 ,-3));
        System.out.println(multiply(-2 ,3));

    }

    // Reverse a integer
    // 123 -> 321

    public static int reverse(int x) {
        boolean flag = false;
        if (x < 0) {
            x = 0 - x;
            flag = true;
        }
        int res = 0;
        int p = x;

        while (p > 0) {
            int mod = p % 10;
            p = p / 10;
            res = res * 10 + mod;
        }
        if (flag) {
            res = 0 - res;
        }

        return res;
    }

    // Similar to above problem
    // Here integer is converted to string for reversal and put it back to integer before return

    public static int reverse1(int x){
        StringBuilder s1 = new StringBuilder();
        String s = String.valueOf(x);
        char[] a = s.toCharArray();
        if(x>0) {
            for (int i = a.length - 1; i >= 0; i--)
                s1.append(a[i]);
            return Integer.parseInt(s1.toString());
        }
        else if (x<0){
            for(int i=a.length-1; i>0; i--)
                s1.append(a[i]);
            int val = Integer.parseInt(s1.toString());
            return -val;
        }
        return 0;
    }

    public static String longestPalindrome(String s) {
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    //median of sorted array, here arrays provided are sorted
    //median is calculated as follows -
    // i) for even number of 2 arrays (array1.length + array2.length + 1)/2
    // ii) for odd number of 2 arrays it can be also calculated as ((array1.length + array2.length +1)/2)

    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    //This program works only for 2 completely sorted arrays
    //Sorting problem is pending at this time

    public static float findMedianSortedArraysMyVersion(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        int[] c = new int[m+n];
        int count = 0;
        for(int i=0; i<m; i++){
            c[count] = A[i];
            count++;
        }
        for(int j=0; j<n; j++){
            c[count] = B[j];
            count++;
        }
        if(c.length % 2 ==0) {
            int mid = c.length / 2;
            float value1 = (float)(c[mid - 1] + c[mid])/2;
            System.out.println(c[mid - 1]);
            System.out.println(c[mid]);
            System.out.printf("%f", value1);
            return value1;
        }
        else{
            double value2 = (c[(c.length/2)]);
            System.out.println(value2);
            return (float)(c[(c.length/2)])/2;
        }
    }

    // Print a string in a zig zag fashion based on no. of rows
    // "MYNAMEISANUPAM" string with 3 rows would return "MMAAYAESNPMNIU"
    // Results are return row wise

    public static String zigzag(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) { // vertically down
                sb[idx].append(c[i]);
                i++;
            }
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) { // obliquely up
                sb[idx].append(c[i]);
                i++;
            }
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }

    public static String zigzag1(String s, int nRows){
        int a = 0;
        //int b = ++a; // b = 1; a = 1
        //System.out.println("a =" + a + "b = "+b);
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for(int i=0; i<sb.length; i++) {
            sb[i] = new StringBuffer();
        }
        int i = 0;
        while(i<len) {
            for (int idx = 0;  idx<nRows && i<len; idx++){
                sb[idx].append(c[i]);
                i++;
            }
            for (int idx = nRows-2; idx>=1 && i<len; idx--){
                sb[idx].append(c[i]);
                i++;
            }
        }
        for(int idx=1; idx<sb.length; ++idx){
           sb[0].append(sb[idx]);
        }
        return sb[0].toString();
    }

    // Factorial of a number using recursion
    // 3! = 3*2*1

    public static int factorialRec(int n){
        int result;
        if (n==1) return 1;
        result = factorialRec(n-1)*n;
        return result;
    }

    // Given a String convert it's Ascii values to to integer. If the string is not a number value then just return null
    // Or if the string is number and alphabets then just return numbers

    public static int atoi(String a){
        if (a.length()==0 || a==null)
            return 0;
        a = a.trim();
        int i = 0;
        double result = 0;
        boolean flag = false;
        if(a.charAt(0)=='+'){
            i++;
        }
        if(a.charAt(0)=='-'){
            flag = true;
            i++;
        }
        //char temp = a.charAt(1);
        //int temp = a.charAt(1);
        while(i<a.length() && a.charAt(i) >= '0' && a.charAt(i) <= '9'){
            result = result * 10 + (a.charAt(i) - '0');
            System.out.println(result);
            i++;
        }
        if(flag==true)
            result = -result;
        if(result>Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        if(result<Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        return (int)result;

    }

    // Is number a palindrome?
    // 121 -> true
    // 123 -> false
    // 1221 -> true

    public static boolean isPalindrome(int x) {
        //if (x<0 || (x!=0 && x== 0)) return false;
        int rev = 0;
        while (x>rev){
            rev = rev*10 + x;
            x = x/10;
        }
        //return x;
        return (x==rev || x==rev/10);
    }

    public static boolean isPalindrome1(int x){
        boolean flag = false;
        boolean resFlag = false;
        if(x<0){
            x = 0 - x;
            flag=true;
        }
        int res = 0;
        int p =x;
        while(p>0){
            int mod = p % 10;
            p = p / 10;
            res = res * 10 + mod;
        }
        if (flag) {
            res = 0 - res;
            x = -x;
        }
        if(res==x)
            resFlag = true;

        return resFlag;
    }

    // Given a text/String and a pattern, find out if the string is matching the regex pattern
    // abc, ab. -> true
    // abc, * -> true

    public static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    // Find the maximum area to hold the water between the bar graph
    // Here the height of the bar(Y Axis) and distance between the bars(X Axis) matters

    public static int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxarea;
    }



    // Covert a number to Roman
    //Roman/Numeric - I-1, V-5, X-10, L-50, C-100, D-500, M-1000
    // 1287 - MCCLXXXVII
    // 1000 - M
    // 200 - CC
    // 80 - LXXX
    // 7 - VII
    // Total = 1287


    public static String intToRoman1(int num) {
        final int MIN_VALUE = 1;
        final int MAX_VALUE = 3999;
        final String[] RN_M = {"", "M", "MM", "MMM"};
        final String[] RN_C = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        final String[] RN_X = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        final String[] RN_I = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        if (num < MIN_VALUE || num > MAX_VALUE) {
            throw new IllegalArgumentException(
                    String.format(
                            "The number must be in the range [%d, %d]",
                            MIN_VALUE,
                            MAX_VALUE
                    )
            );
        }
        return new StringBuilder()
                .append(RN_M[num / 1000])
                .append(RN_C[num % 1000 / 100])
                .append(RN_X[num % 100 / 10])
                .append(RN_I[num % 10])
                .toString();
    }

    // Convert number given in Roman String to integer. Mzx roman number supported in both cases is 3999
    // switch is used to first add the integers to the int array
    // once all the array is filled up it is traversed to get the sum
    //   if num[i]<num[i+1]
    //        then sum= num[i+1]-num[i];
    //   else sum = num[i]+num[i+1]

    // MCMLXXXVII - 1987
    // num[0] M - 1000
    // num[1] C - 100
    // num[2] M - 1000
    // num[3] L - 50
    // num[4] X - 10
    // num[5] X - 10
    // num[6] X - 10
    // num[7] V - 5
    // num[8] I - 1
    // num[9] I - 1

    public static int romanToInt1(String s){
        int nums[]= new int[s.length()];
        int sum=0;
        for(int i=0; i<s.length(); i++){
            switch(s.charAt(i)){
                case 'I':
                    nums[i]=1;
                    break;
                case 'V':
                    nums[i]=5;
                    break;
                case 'X':
                    nums[i]=10;
                    break;
                case 'L':
                    nums[i]=50;
                    break;
                case 'C':
                    nums[i]=100;
                    break;
                case 'D':
                    nums[i]=500;
                    break;
                case 'M':
                    nums[i]=1000;
                    break;
            }
        }
        //System.out.println(nums.toString());
        for(int i=0; i<nums.length-1; i++){
            if(nums[i]<nums[i+1]){
                sum=sum-nums[i];
            }
            else{
                sum=sum+nums[i];
            }
        }
        return sum+nums[nums.length-1];
    }

    // Consider a String array of {abcd, abc, abcde, abcdef}
    // Longest common prefix would be "abc" as "abc" prefix is shared by all the strings in an array

    public static String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        int len = strs.length;
        for (int i=0; i<strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                flag =false;
                if (i<=strs[j].length()-1 && strs[0].charAt(i) == strs[j].charAt(i)){
                    flag = true;
                }
                else return sb.toString();
            }
            if (flag) System.out.println(sb.append(strs[0].charAt(i)));

        }
        return sb.toString();
    }

    // Return the lists of 3 integers with sum = 0
    // int array is sorted so
    // {-1, 0, 1, 2, -1, -4} becomes {-4, -1, -1, 0, 1, 2}
    // Traverse the sorted array from left to right if sum of 3 values is less than 0
    // Traverse the sorted array from right to left if sum of 3 values is greater than 0

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        if(nums.length < 3) return result;
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 2) {
            if(nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0) result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                if(sum <= 0) while(nums[j] == nums[++j] && j < k);
                if(sum >= 0) while(nums[k--] == nums[k] && j < k);
            }
            while(nums[i] == nums[++i] && i < nums.length - 2);
        }
        return result;
    }


    // Given a string
    public static List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                //System.out.println(ans.peek().length());
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }

    // Find out if string/sentence is a valid parenthesis
    // {[()()()]} is a valid parenthesis string
    // ABC()(){}[] is a valid parenthesis string
    // {{}[] is not a valid parenthesis string

    public static boolean isParenthesisValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            // for || - both the conditions needs to be true
            // for && - any one of the condition needs to be true
            if (c!='(' && c!='{' && c!='[' && c!='}' && c!=']' && c!=')')
                continue;
            else if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }

    // This needs to be solved again. Could not get the solution

    public static List<String> generateParenthesis(int n)
    {
        List<List<String>> lists = new ArrayList();
        lists.add(Collections.singletonList(""));
        for (int i = 1; i <= n; ++i)
        {
            final List<String> list = new ArrayList();

            for (int j = 0; j < i; ++j)
            {
                for (final String first : lists.get(j))
                {
                    for (final String second : lists.get(i - 1 - j))
                    {
                        list.add("(" + first + ")" + second);
                    }
                }
            }
            lists.add(list);
        }
        return lists.get(lists.size() - 1);
    }

    // Find out if the sudoku matrix is valid or not
    // Used following string representations to store the values in hashset and if the similar value is observed then sudoku is not valid
    //row = (4)1 -> Value-4 Row-1
    //column = 1(4) -> Value-4 Col-1
    //Grid = 1(4)1 -> Value-4 Top left block of 9 squares

    public static boolean isValidSudoku(char[][] board) {
        Set seen = new HashSet();
        for (int i=0; i<9; ++i) {
            for (int j=0; j<9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i/3 + b + j/3))
                        return false;
                }
            }
        }
        return true;
    }

    // Given a number print following. Repeat prints for n times
    // n=5
    // 1
    // 11
    // 21
    // 1211
    // 112121

    public static String countAndSay(int n) {
        String ans = "1";
        System.out.println(ans);
        for(int i=2;i<=n;i++)
        {
            ans = GetSay(ans);
            System.out.println(ans);
        }
        return ans;
    }

    public static String GetSay(String inp){
        int len = inp.length();
        String ans = "";
        char last = inp.charAt(0);
        int count = 1;

        for(int i=1;i<len;i++)
        {
            char ch = inp.charAt(i);
            if(ch == last)
            {
                count++;
             //   ans+=count+""+ch;
            }
            else
            {
                ans+=count+""+last;
                count = 1;
                last = ch;
            }
        }
        ans+=count+""+last;
        return ans;
    }

    // Find a first missing positive in an array of integers
    // Replaced integers with their respective index
    // {-2, -1, 0, 4, 3, 1} -> {1,-1,3,4,-1,-2}

    public static int firstMissingPositive(int[] A) {
        int i = 0;
        while(i < A.length){
            if(A[i] == i+1 || A[i] <= 0 || A[i] > A.length) i++;
            else if(A[A[i]-1] != A[i]) swap(A, i, A[i]-1);
            else i++;
        }
        i = 0;
        while(i < A.length && A[i] == i+1) i++;
        return i+1;
    }

    private static void swap(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    //need to take a look again
    public static int trap(int[] A){
        int a=0;
        int b=A.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,A[a]);
            rightmax=Math.max(rightmax,A[b]);
            if(leftmax<rightmax){
                max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            }
            else{
                max+=(rightmax-A[b]);
                b--;
            }
        }
        return max;
    }

    // This problem is asked to Pratik

    public static String multiply(String num1, String num2) {
        int multiplicationNo  = 0;
        int a,b;
        String c;
        int multiplies[] = {0, 10, 100, 1000, 10000, 100000, 1000000};
        if (num1.length()>num2.length()) {
            a = num1.length();
            b = myStringToInteger(num2);
            c= num1;
        }
        else {
            a=num2.length();
            b = myStringToInteger(num1);
            c= num2;
        }
        int ans[] = new int[a];

        for (int i=0; i<=a+1; i++){
            int trailingZero = a-1;
            if (trailingZero*10!=0) {
                ans[i] = (b * (c.charAt(i) - '0')) * (multiplies[trailingZero]);
                a--;
            }
            else{
                ans[i] = (b * (c.charAt(i) - '0'));
                a--;
            }
        }
        for (int i : ans)
            multiplicationNo += i;

        return "" + multiplicationNo;
    }

    // Convert a given string to a number

    public static int myStringToInteger(String str) {
        int answer = 0, factor = 1;
        for (int i = str.length()-1; i >= 0; i--) {
            answer += (str.charAt(i) - '0') * factor;
            factor *= 10;
        }
        return answer;
    }

    // Rotate a matrix by 90 degrees
    // Step 1 - Transpose the matrix
    // Step 2 - Swap the elements row wise till it reaches middle element
    // Transpose the matrix by swapping

    public static int[][] matrixRotate(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                //System.out.println(matrix.length/2);
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
        return matrix;
    }

    //int F[] = {-2,1,-3,4,-1,2,1,-5,4};
    public static int findMaxSumOfSubArray(int[] numbers){
        if(numbers.length == 0 ) return 0;
        if(numbers.length == 1) return numbers[0];
        int[] subseqSums = new int[numbers.length];
        for(int cnt=0;cnt < numbers.length;cnt++){
            subseqSums[cnt] = Integer.MIN_VALUE;
        }
        subseqSums[0] = numbers[0];
        //initialize 0th position in the array
        for(int idx=1;idx < numbers.length;idx++){
            int localSum = subseqSums[idx-1] + numbers[idx];
            subseqSums[idx] = Math.max(localSum,numbers[idx]);
        }
        int results = Integer.MIN_VALUE;
        for(int idx=0;idx<subseqSums.length;idx++){
            if(subseqSums[idx] != Integer.MIN_VALUE){
                if (results == Integer.MIN_VALUE || results < subseqSums[idx] ){
                    results = subseqSums[idx];
                }
            }
        }
        return results;
    }

    //int F[] = {-1, 0, 3, 4, 0, -3, 10, 11, 20};
    // -2,1,-3,4,-1,2,1,-5,4

    public static int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];

        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    // This problem was asked in Siri QA position
    // Given a number print hollow square of a same size
    // For size = 4

    /*
    ****
    *  *
    *  *
    ****
     */

    public static void printSquare(int size){
        for(int i=0; i<size; i++){
            System.out.println();
            for(int j=0; j<size; j++){
                if(i==0 || i==size-1){
                    System.out.print("*");
                }
                else if((i!=0 || i!=size-1) && (j==0 || j==size-1)){
                        System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
        }
    }

    // This problem was asked in Couchbase on-site interview
    // Given an integer value, find out if the number is power of 2
    // If it is power of 2 then return the int power

    public static int isPowerOfTwo(int n)
    {
        int power = 0;
        if (n == 0)
            return -1;

        while (n != 1)
        {
            if (n % 2 != 0)
                return -1;
            power++;
            n = n / 2;
        }
        return power;
    }

    public int findPeakElement(int[] nums) {
        int before = -1, current = 0, after = current+1;
        if(nums.length == 1) {
            return 0;
        }

        for(int i = 0; i < nums.length; i++) {
            if(current == 0) {
                if(nums[after] < nums[current]) {
                    return current;
                }
            }
            else if(current == nums.length - 1) {
                if(nums[before] < nums[current]) {
                    return current;
                }
            } else if (nums[before] < nums[current] && nums[current] > nums[after]) {
                return current;
            }

            before++;
            current++;
            after++;
        }
        return 0;
    }

    public static int multiply(int a, int b){
        if(a==0 || b==0) return 0;
        int c = 0;
        int d = 0;
        if(b>0){
            for(int i=0; i<b; i++){
                c+=a;
            }
            return c;
        }
        if(b<0){
            for(int j=b; j<0; j++){
                c+=a;
                d = -c;
            }
            return d;
        }
        return -1;
    }

}
