package Other;
import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Frameworks {
    public static void main(String[] args) {
        boolean find = findPattern("hello world", "hlo");
        System.out.println(findPattern("hello world", "he"));
        System.out.println(findPattern("hello world", "hel"));
        System.out.println(findPattern("hello world", "hlo"));
        System.out.println(findPattern2("hello world", "he"));
        int [] arr1 = {5,4,6,2,8};
        int count = 0;
        for (int i=0; i< arr1.length; i++) {
            boolean isPresent = binarySearch(arr1, arr1[i]);
            if (isPresent){
                count++;
            }
        }
        //Solution solution = new Solution();
        int [] a = {1,5,10,15,20,25,30,35};
//        System.out.println(doesExist(a, 15));
//        System.out.println(doesExist(a, 11));
//        System.out.println(binarySearch(a, 0, a.length-1, 15));
//        System.out.println(binarySearch(a, 0, a.length-1, 11));
//        System.out.println(binarySearch(a, 0, a.length-1, 30));
//        System.out.println(binarySearch(a, 0, a.length-1, 50));
//        System.out.println("Non Recursive - " + binarySearch(a,  15));
//        System.out.println("Using List - "+ doesExistList(a, 15));
//        //System.out.println("Using Hash - "+ doesExistHash(a, 15));
        int arr[] = oddNumbers1(2, 5);
        for(int i=0; i<arr.length; i++){
            System.out.print(" "+arr[i]);
        }

    }

  /*
  [1,5, 10, 15, 20, 25, 30, 35] => 15 => True
  11 => false
  */

    static void maxDifference(int[] a) {


    }
    // hello world
    // hlo - false
    // he - true
    static boolean findPattern(String str, String pattern){
        boolean found = false;
        char[] ch = pattern.toCharArray();
        for(int i=1; i<ch.length; i++){
            found=false;
            for(int j=0; j<str.length(); j++){
                if(found==false) {
                    if (str.charAt(j) == ch[i]) {
                        found = true;
                    }
                }
                else if(found==true) {
                    if (ch[i - 1] == str.charAt(j)) {
                        found = false;
                        return false;
                    }
                }

            }
        }
    return true;
    }

    static boolean findPattern2(String str, String pattern){
        char[] ch = pattern.toCharArray();
        for (int i=1; i < ch.length-1; i++){
            if(str.lastIndexOf(ch[i]) > str.lastIndexOf(ch[i-1])){
                return false;
            }
        }
        return true;
    }

    static int[] oddNumbers(int l, int r) {
        /*
         * Write your code here.
         */
        int len = ((r-l)/2)+1;
        int res[]= new int[len];
        int k=0;
        if(l<=0 || r<=0) return res;
        for(int i=l; i<=r; i++){
            if(i%2==0) continue;
            else res[k++]=i;
        }
        return res;
    }

    static int[] oddNumbers1(int l, int r) {
        /*
         * Write your code here.
         */
        int len = ((r-l)/2)+1;
        int res[]= new int[len];
        int k=0;
        int start;
        if(l<=0 || r<=0) return res;
        if(l%2==0) start=l+1;
        else start=l;
        while(start<=r){
            res[k++]=start;
            start+=2;
        }
        return res;
    }

    public static boolean doesExist(int[] array, int number) {
        int len = array.length;
        for(int i=0; i<len; i++){
            if (array[i]==number) {
                return true;
            }
        }
        return false;
    }

    public static int binarySearch(int[] array, int l, int r, int number){
        int[] a = array;
        int find = number;

        if(r>=l){
            int mid = l+ (r-l)/2;

            if(a[mid]==find){
                return mid;
            }
            if(a[mid]>find){
                return binarySearch(a, l, mid-1, find);
            }
            else {
                return binarySearch(a, mid+1, r, find);
            }
        }
        return -1;
    }

    public static boolean binarySearch(int arr[], int x)
    {
        int l = 0, r = arr.length - 1, count=0;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == x){
                //count++;
                System.out.println("Found element in Binary Search- " + arr[m]);
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
        System.out.println("Cound not find element in Binary Search- " + x);
        return false;
    }


    public static boolean doesExistList(int[] array, int number) {
        ArrayList<Integer> list = new ArrayList<Integer>(array.length);
        for (int i = 0; i < array.length; i++) {
            list.add(Integer.valueOf(array[i]));
        }
        if(list.contains(number)){
            return true;
        }
        return false;
    }


//    public static boolean doesExistHash(int[] array, int number){
//        Integer[] a = new Integer[];
//        Set<Integer> targetSet = new HashSet<Integer>(Arrays.asList(a));
//        if(targetSet.contains(number)) return true;
//        return false;
//    }

}




