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

public class Cisco {
    public static void main(String[] args) {

        String[] strArr = {"applepie", "piecrust", "pieisforpieday"};
        String[] strArr1 = {"applepiep", "piecrustp", "pieisforpiedayp"};
        int occurences = find_occurences(strArr1, "pie");
        System.out.println(findPattern("hello world", "he"));
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

    public static int find_occurences(String[] strArr, String pattern){
        if (pattern == null || pattern.length()<1) return 0;
        int len = strArr.length;
        int counter = 0;
        for(int i=0; i<len; i++){
            if (strArr[i] == "" || strArr[i] == null) continue;
            char[] charArr = strArr[i].toCharArray();
            for(int j=0; j<charArr.length-pattern.length()+1; j++){
                if(pattern.charAt(0)==charArr[j]){
                    if(pattern.equalsIgnoreCase(strArr[i].substring(j, j+pattern.length()))) counter++;
                }
            }
        }
        return counter;
    }


























}
