import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {

        String str1 = "aaaaaab";
        String str2 = "tomtomca";
        String str3 = "tomtomc";
        String str4 = "todoto";

        //String str1 = "cdf";



    /*

    Anagram:

    ram ---> arm, mar, mra, rma ....
    cat ---> act, tac, tca ....


    aaaab ---> abaaa, (aabaa), aaaba


    */

        isAnagramPalindrome(str1);
        isAnagramPalindrome(str2);
        isAnagramPalindrome(str3);
        isAnagramPalindrome(str4);

    }


    public static void isAnagramPalindrome(String str){
        int[] table = buildTable(str);
        boolean isAnagramPal = checkMaxOneOdd(table);
        System.out.println("String " + str + "is AnagramPal -" +isAnagramPal);
    }

    //"tomtomca"
    public static int[] buildTable(String str){
        // int table[] = new int[Character.getNumbericValue('z')-Character.getNumericValue('a')+1];

        int table[] = new int[26];
        for (char c : str.toCharArray()){
            int x = Character.getNumericValue(c);
            if(Character.getNumericValue('a') <= x && Character.getNumericValue('z') >= x){
                table[x - Character.getNumericValue('a')]++;
            }
        }
        return table;
    }

    public static boolean checkMaxOneOdd(int[] table){
        boolean foundOdd = false;
        for(int count : table){
            if(count % 2 == 1) {
                if(foundOdd){
                    return false;
                }
            }
            foundOdd = true;
        }
        return true;
    }

}