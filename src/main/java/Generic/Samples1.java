package Generic;

import java.util.*;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.io.File;


public class Samples1 {

    public static String[] resFiles = new String[100];
    public static int counter = 0;

    public static void main(String a[]) {
        int converted = convertFromBase("10000000000", 2);
        System.out.println(converted);

        toBase(100, 15);

        int sum=0;
        int num = getNumberSum(123);
        int num1 = sumOfDigits(123);

        for (int i = 1; i <= 10; i++) {
            System.out.print(fibo2(i) + " ");
        }
        int n = fibo2(10);
        System.out.println(n);
        fibo3(50);
        fibo(10);
        System.out.println();
        System.out.println(fibo(10));
        fibo1(10);

        System.out.println("");
        System.out.println(fibo4(5));

        sieveOfEratosthenes(30);

        List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <= 10; i++) {
            numbers.add(i);
        }
        numbers.add(10);
        System.out.println("Duplicate Number: " + findDuplicateNumber(numbers));

        printBinaryFormat(25);

        isPerfectNumber(6);

        Map<String, Integer> wordMap = getExceptionCount("/Users/anupammundale/Projects/SampleProjects/src/main/java/Generic/MyTestFile.txt");
        List<Entry<String, Integer>> list = sortByValue(wordMap);
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " ==== " + entry.getValue());
        }

        findDuplicateChars("ANUPAMMUNDALE");

        swap();

        System.out.println(isArmstrongNumber(153));

        printMultiplicationTable(12);//-2,1,-3,4,-1,2,1,-5,4

        //System.out.println(isPalindrome("A Toyota! Race fast... safe car: a Toyota"));

        System.out.println(reverse("abc defgh"));

        System.out.println(reverse("A Toyota! Race fast... safe car: a Toyota"));
        System.out.println(reverse1("A Toyota! Race fast... safe car: a Toyota"));

        int nums[] =  {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSubArraySum(nums));

        //displayDirectoryContents1(new File("/Users/Anupam/Documents/Test"));

        int nums1[] =  {3, 1, 8, 10, 9, 11};
        printTwoMaxNumbers(nums1);

        System.out.println();
        System.out.println();

        String str = "geeksforgeeks";
        System.out.println("index of first non repeating char is - "+ firstNonRepeating(str));

        System.out.println(NumberOf1(100));

        int[] arr6 = {3,1,2,5,6,8,10,9};
        int[] arr7 = findTwoMissingIntegers(arr6);
    }

    public static int findDuplicateNumber(List<Integer> numbers){
        int highestNumber = numbers.size() - 1;
        int total = getSum(numbers);
        System.out.println((highestNumber+1)/2);
        System.out.println((highestNumber*(highestNumber+1)/2));
        int duplicate = total - (highestNumber*(highestNumber+1)/2);
        return duplicate;
    }

    public static int getSum(List<Integer> numbers){
        int sum = 0;
        for(int num:numbers){
        sum += num;
        }
        System.out.println("sum="+sum);
        return sum;
    }

    public static void printBinaryFormat(int number){
        int binary[] = new int[25];
        int index = 0;
        while(number > 0){
            binary[index++] = number%2;
            number = number/2;
        }
        for(int i = index-1;i >= 0;i--){
            System.out.print(binary[i]);
        }
        System.out.println();
    }

    public static boolean isPerfectNumber(int number){
        int temp = 0;
        for(int i=1;i<=number/2;i++){
            if(number%i == 0){
                temp += i;
            }
        }
        if(temp == number){
            System.out.println("It is a perfect number");
            return true;
        } else {
            System.out.println("It is not a perfect number");
            return false;
        }
    }


    // Get the total words in a given file

    public static Map<String, Integer> getWordCount(String fileName){

        FileInputStream fis = null; // to read the file
        DataInputStream dis = null; // to read the input char stream
        BufferedReader br = null; // to buffer the data input char stream
        //All 3 of them work in conjunction

        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            String line = null;
            while((line = br.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line, " ");
                while(st.hasMoreTokens()){
                    String tmp = st.nextToken().toLowerCase();
                    if(wordMap.containsKey(tmp)){
                        wordMap.put(tmp, wordMap.get(tmp)+1);
                    } else {
                        wordMap.put(tmp, 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
    }

    // This problem is asekd in Apple Health repeatedly
    // Given a log file print the number of exceptions for each of the application
    // (This can also be asked to implement on per hour per app basis)
    // abc app has 3 exceptions
    // xyz app has 2 exceptions

    public static Map<String, Integer> getExceptionCount(String fileName){

        FileInputStream fis = null; // to read the file
        DataInputStream dis = null; // to read the input char stream
        BufferedReader br = null; // to buffer the data input char stream
        //All 3 of them work in conjunction

        Map<String, Integer> wordMap = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(fileName);
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            List<String> tokens = new ArrayList<>();
            String line = null;
            while ((line = br.readLine()) != null) {
                tokens.clear();
                StringTokenizer st = new StringTokenizer(line, " ");
                while (st.hasMoreTokens()) {
                    tokens.add(st.nextToken().toLowerCase());
                }
                int len = tokens.size();
                if ((wordMap.containsKey(tokens.get(len - 1))) && "EXCEPTION".equalsIgnoreCase(tokens.get(len - 3))) {
                    wordMap.put(tokens.get(len - 1), wordMap.get(tokens.get(len - 1)) + 1);
                } else if ("EXCEPTION".equalsIgnoreCase(tokens.get(len - 3))) {
                    wordMap.put(tokens.get(len - 1), 1);
                }
            }
        }
         catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{if(br != null) br.close();}catch(Exception ex){}
        }
        return wordMap;
    }

    public static List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap){

        Set<Entry<String, Integer>> set = wordMap.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        return list;
    }

    // Given a string find all the duplicate characters
    // abcdab - a->2 b->2

    public static void findDuplicateChars(String str){

        Map<Character, Integer> dupMap = new HashMap<Character, Integer>();
        char[] chrs = str.toCharArray();
        for(Character ch:chrs){
            if(dupMap.containsKey(ch)){
                dupMap.put(ch, dupMap.get(ch)+1);
            } else {
                dupMap.put(ch, 1);
            }
        }
        Set<Character> keys = dupMap.keySet();
        for(Character ch:keys){
            if(dupMap.get(ch) > 1){
                System.out.print(ch.toString()+""+dupMap.get(ch));
            }
        }
    }

    // Given an array of integers find first and second max number

    public static void printTwoMaxNumbers(int[] nums){
        int maxOne = 0;
        int maxTwo = 0;
        for(int n:nums){
            if(maxOne < n){
                maxTwo = maxOne;
                maxOne = n;
            } else if(maxTwo < n){
                maxTwo = n;
            }
        }
        System.out.println("First Max Number: "+maxOne);
        System.out.println("Second Max Number: "+maxTwo);
    }


    // Fibonacci series different implementations
    // Fibo series - 1,2,3,5,8,13,21
    // 1. Given a number 7 print 21
    // 2. Given a number 7 print all the first 7 sequences 1,2,3,5,8,13,21
    // 3. Given a

    public static int[] fibo(int num){
        int[] fib = new int[num];
        fib[0] = 0;
        fib[1] = 1;
        for(int i=2; i < num; i++){
            fib[i] = fib[i-1] + fib[i-2];
        }
        for(int i=0; i< num; i++){
            System.out.print(fib[i] + " ");
        }
        return fib;
    }

    public static void fibo1(int num) {
        int n1 = 0, n2 = 1, n3=0, i, count = num;
        System.out.print(n1 + " " + n2);//printing 0 and 1

        for (i = 2; i < count; ++i)//loop starts from 2 because 0 and 1 are already printed
        {
            n3 = n1 + n2;
            System.out.print(" " + n3);
            n1 = n2;
            n2 = n3;
        }
        //System.out.println(n2);
        //System.out.println(n3);
    }

    // This is called recursively to get the final number
    public static int fibo2(int n)
    {
        if (n <= 1)
            return n;
        return fibo2(n-1) + fibo2(n-2);
    }

    public static void fibo3(int num) {
        int n1 = 0, n2 = 1, n3=0, i, count = num;
        System.out.print(n1 + " " + n2);//printing 0 and 1

        //for (i = 2; i < count; ++i)//loop starts from 2 because 0 and 1 are already printed
        //{
            while(n3<50) {
                n3 = n1 + n2;
                if (n3>=50) break;
                System.out.print(" " + n3);
                n1 = n2;
                n2 = n3;
            }
        //}
        //System.out.println(n2);
        //System.out.println(n3);
    }

    public static int fibo4(int n)
    {
        return fibonacci(n, new int[n+1]);
    }

    public static int fibonacci(int i, int[] memo){
        if(i == 0 || i == 1) return i;
        if (memo[i] == 0) {
            memo[i] = fibonacci(i-1, memo) + fibonacci(i-2, memo);
        }
        return memo[i];
    }

    // Swap the numbers without using a third variable
    // Used + and - to come up with the swap

    public static void swap(){
        int x = 10;
        int y = 20;
        System.out.println("Before swap:");
        System.out.println("x value: "+x);
        System.out.println("y value: "+y);
        x = x+y;
        y=x-y;
        x=x-y;
        System.out.println("After swap:");
        System.out.println("x value: "+x);
        System.out.println("y value: "+y);
    }

    // Return the sum of the digits of a number
    // 123->6

    public static int getNumberSum(int num){
        int sum = 0;
        while (num > 0) {
            sum = sum + num % 10;
            num = num / 10;
        }
        System.out.println(sum);
        return sum;
    }

    public static int sumOfDigits(int num)
    {
        int sum = 0;

        while (num > 0)
        {
            sum = sum + num % 10;
            num = num / 10;
        }

        sum = (sum <10) ? sum : sumOfDigits(sum);

        return sum;
    }

    // Given a number return true if it is prime
    // Prime number is a number divisible only by 1 and itself
    // To check this divisors can be checked only till n/2 and not till n-1

    public static boolean isPrimeNumber(int number){

        for(int i=2; i<=number/2; i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }


    // Within 1...50 print or return all the prime numbers
    // This is a popular technique of generating a list of prime numbers

    public static List<Integer> sieveOfEratosthenes(int n) {
    boolean prime[] = new boolean[n + 1];
    Arrays.fill(prime, true);
    for (int p = 2; p <= Math.sqrt(n); p++) {
        if (prime[p]) {
            for (int i = p * 2; i <= n; i += p) {
            prime[i] = false;
            }
        }
    }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
            primeNumbers.add(i);
            }
        }
    return primeNumbers;
    }


    // Armstrong number -

    public static boolean isArmstrongNumber(int num){
        int numLength = String.valueOf(num).length();
        int temp;
        int sum =0;
        if (num == 0){
            return false;
        }
        for (int i=0; i<numLength; i++){
            //char c = String.valueOf(num).charAt(i);
            temp = String.valueOf(num).charAt(i)-'0';
            //int temp1 = String.valueOf(num).charAt(i)-'0';
            sum += java.lang.Math.pow(temp, numLength);
        }
        if (num == sum) {
            return true;
        }
        return false;
    }

    public static boolean isArmstrongNumber1(int number){

        int tmp = number;
        int noOfDigits = String.valueOf(number).length();
        int sum = 0;
        int div = 0;
        while(tmp > 0)
        {
            div = tmp % 10;
            int temp = 1;
            for(int i=0;i<noOfDigits;i++){
                temp *= div;
            }
            sum += temp;
            tmp = tmp/10;
        }
        if(number == sum) {
            return true;
        } else {
            return false;
        }
    }

    // Print a table of multiplication given a max number
    // Given number 10, print all the tables of multiplication from 1 to 10

    public static void printMultiplicationTable(int max) {
        int result = 1;
        for(int i=1; i<=max; i++){
            System.out.println();
            for (int j=1; j<=10; j++){
                result = (i * j);
                String len = String.valueOf(result);
                if(len.length()==1) System.out.print("  ");
                else System.out.print(" ");
                System.out.print(result);
            }
        }
    }

    // Reverse a given string
    // abcd -> dcba

    // Used while loop and swapped chars from both the ends of the string
    public static String reverse(String s){
        char[] in = s.toCharArray();
        int begin=0;
        int end=in.length-1;
        char temp;
        while(end>begin){
            temp = in[begin];
            in[begin]=in[end];
            in[end] = temp;
            end--;
            begin++;
        }
        return new String(in);
    }

    // Create a new string and looped from end to the first char and appended to the new string and returned
    public static String reverse1(String s){
        String rev = "";
        for (int i=s.length()-1; i>=0; i--){
            int temp = s.charAt(i);
            char temp1 = s.charAt(i);
            if(s.charAt(i)==' ') continue;
            rev = rev + s.charAt(i);
        }
        return new String(rev);
    }

    // Find out if
    public static boolean isPalindrome(String s){
        if (s.length()<2) return true;
        String revS= reverse(s).trim();
        if (s.equals(revS)) return true;
        return false;
    }

    public static boolean isPalRec(String s)
    {   // if length is 0 or 1 then String is palindrome
        if(s.length() == 0 || s.length() == 1)
            return true;
        if(s.charAt(0) == s.charAt(s.length()-1))
            /* check for first and last char of String:
             * if they are same then do the same thing for a substring
             * with first and last char removed. and carry on this
             * until you string completes or condition fails
             * Function calling itself: Recursion
             */
            return isPalRec(s.substring(1, s.length()-1));

        /* If program control reaches to this statement it means
         * the String is not palindrome hence return false.
         */
        return false;
    }

    // {-2, -3, 4, -1, -2, 1, 5, -3}
    public static int maxSubArraySum(int a[])
    {
        int size = a.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;

        for (int i = 0; i < size; i++)
        {
            max_ending_here = max_ending_here + a[i];
            if (max_so_far < max_ending_here)
                max_so_far = max_ending_here;
            if (max_ending_here < 0)
                max_ending_here = 0;
        }
        return max_so_far;
    }

    // Convert an int value to the base of 2, 15, 8 etc..
    public static void toBase(int number, int base) {
        String binary = "";
        for (int j = 0; j < 16 ; j++) {
            try {
                binary += "" + number % base;
                number /= base;
            } catch (Exception e) {
            }
        }
        for (int j = binary.length() - 1; j >= 0; j--) {
            if((j+1)%4==0) System.out.print(" ");
            System.out.print(binary.charAt(j));
        }
    }

    // Convert a number from its base of 2,8,15 etc..
    // 001 -> 1
    // 010 -> 10
    public static int convertFromBase(String number, int base) {
         if (base < 2 || (base > 10 && base != 16)) return -1;
         int value = 0;
         for (int i= number.length() - 1; i >= 0; i--) {
             int digit= Integer.valueOf(number.charAt(i) - '0');
             if (digit < 0 || digit >= base) {
                 return -1;
                 }
             int exp= number.length() - 1 - i;
             value += digit * Math.pow(base, exp);
             }
         return value;
    }

    /* The method returns index of first non-repeating
    character in a string. If all characters are repeating
    then returns -1 */

    public static int firstNonRepeating(String str)
    {
        int NO_OF_CHARS = 256;
        char count[] = new char[NO_OF_CHARS];
        //getCharCountArray(str, count);
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i)]++;
        }

        int index = -1, i;

        for (i = 0; i < str.length(); i++)
        {
//            System.out.println(str.charAt(i));
//            int in = str.charAt(i);
//            System.out.println(in);
//            char ch = count[str.charAt(i)];
//            System.out.println(ch);
            if (count[str.charAt(i)] == 1)
            {
                index = i;
                break;
            }
        }
        return index;
    }

    public static void getCharCountArray(String str, char[] count)
    {
        for (int i = 0; i < str.length(); i++)
            count[str.charAt(i)]++;
    }

    public static int NumberOf1(int n)
    {
        int count = 0;
        while(n>0)
        {
            int n1 = n & 1;
            if(n1==1) {
                count ++;
            }

            n = n >> 1;
        }

        return count;
    }

    // Display the content of a directory given a File dir
    public static String[] displayDirectoryContents(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    displayDirectoryContents(file);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                    resFiles[counter] = file.getName();
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resFiles;
    }

    public static String[] displayDirectoryContents1(File dir) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                   displayDirectoryContents1(file);
                }
            }
        return resFiles;
    }

    public static int[] findTwoMissingIntegers(int[] arr){
        int len = arr.length;
        int totalLen = arr.length+2;
        boolean[] missing = new boolean[totalLen];
        int[] res = new int[2];
        int j=0;
        for(int i=0; i<len; i++){
            missing[arr[i]-1]=true;
        }
        for(int i=0; i<totalLen-1; i++){
            if(!missing[i]) {
                res[j] = i + 1;
                j++;
            }
        }
        return res;
    }

}
