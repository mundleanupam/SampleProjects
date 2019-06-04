package CrackingCode;

import java.util.Arrays;

public class ArraysStrings {

    public static void main(String args[]) {
        isUniqueChars("ANUPAM");
        System.out.println(returnNumbersToString("This  is a simple sentence."));
        oneEditAway("pale", "ple");
        compress("aabbbccdee");
        compress("aabbccddddeeaa");
        String str = "Mr John Smith";
        char[] charArr = str.toCharArray();
        replaceSpaces(charArr, 13);
        isPermutationOfPalindrome("Tact Coao");
        int a1[][] = {{1,2,3},{4,5,6},{7,8,9}};
        matrixRotate1(a1);
        for (int i =0; i<a1.length; i++){
            System.out.print("[");
            for (int j=0; j<a1.length; j++){
                System.out.print("  "+a1[i][j]+"  ");
            }
            System.out.println("]");
        }
    }

    //Is Unique: Implement an algorithm to determine if a string has all unique characters.
    // What if you cannot use additional data structures?

    public static boolean isUniqueChars(String str) {
        if (str.length() > 128) return false;
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {       //Already found this char in string
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isUniqueChars1(String str) {
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    // Check Permutation: Given two strings, write a method to decide if one is a permutation of the other ---------

    public static String sort(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);

    }

    public static boolean permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sort(s).equals(sort(t));
    }

// ------------------------------------------------------------------------------------------------------------------

    public static boolean permutation1(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] letters = new int[128];
        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }
        for (int i = 0; i < t.length(); i++) {
            int c = (int) t.charAt(i);
            letters[c]--;
            if (letters[c] < 0) {
                return false;
            }
        }
        return true;
    }

    // URLify: Write a method to replace all spaces in a string with '%20'.
    // You may assume that the string has sufficient space at the end to hold the additional characters, and that you are given the "true" length of the string.
    // (Note: If implementing in Java, please use a character array so that you can perform this operation in place.)
    // Input: "Mr John Smith ", 13
    // Output: "Mr%20John%20Smith"

    public static void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = trueLength + spaceCount * 2;
        str = Arrays.copyOf(str, index);
        if (trueLength < str.length) str[trueLength] = '\0';
        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    public static String returnNumbersToString(String str){
        if (str.length()==0 || str==null) return "";
        int charCount = 0;
        int len = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<len; i++){
            if(str.charAt(i)==' '){
                if(str.charAt(i-1)==' ') continue;
                sb.append(charCount);
                sb.append(' ');
                charCount = 0;
            }
            else{
                charCount++;
            }
        }
        sb.append(charCount);
        return String.valueOf(sb);
    }


    // Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palindrome.
    // A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement
    // of letters. The palindrome does not need to be limited to just dictionary words.
    // Input: Tact Coa
    // Output: True (permutations: "taco cat", "atco eta", etc.)

    public static boolean isPermutationOfPalindrome(String phrase) {
        int[] table = buildCharFrequencyTable(phrase);
        return checkMaxOneOdd(table);
    }

    public static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            if (count % 2 == 1) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }
        return true;
    }

    public static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    public static int[] buildCharFrequencyTable(String phrase) {
        int[] table = new int[Character.getNumericValue('z') -
                Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
            }
        }
        return table;
    }

// ------------------------------------------------------------------------------------------------------------------

    // tact coa
    //
    boolean isPermutationOfPalindrome1(String phrase) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') -
                Character.getNumericValue('a') + 1];
        for (char c : phrase.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;

    }

    // One Away: There are three types of edits that can be performed on strings: insert a character,
    // remove a character, or replace a character.
    // Given two strings, write a function to check if they are one edit (or zero edits) away.
    // EXAMPLE -
    // pale, ple -> true
    // pales, pale -> true
    // pale, bale -> true
    // pale, bake -> false

    public static boolean oneEditAway(String first, String second) {
        if (first.length() == second.length()) {
            return oneEditReplace(first, second);
        } else if (first.length() + 1 == second.length()) {
            return oneEditInsert(first, second);
        } else if (first.length() - 1 == second.length()) {
            return oneEditInsert(second, first);
        }
        return false;
    }

    public static boolean oneEditReplace(String s1, String s2) {
        boolean foundDifference = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;
            }
        }
        return true;
    }

    // pale, ple -> true
    public static boolean oneEditInsert(String s1, String s2) {
        int index1 = 0;
        int index2 = 0;
        while (index2 < s2.length() && index1 < s1.length()) {
            if (s1.charAt(index1) != s2.charAt(index2)) {
                if (index1 != index2) {
                    return false;
                }
                index2++;
            } else {
                index1++;
                index2++;
            }
        }
        return true;
    }

    // String Compression: Implement a method to perform basic string compression using the counts of repeated characters.
    // For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not become smaller than the original string, your method should return the original string.
    // You can assume the string has only uppercase and lowercase letters (a - z).

    public static String compress(String str) {
        StringBuilder compressed = new StringBuilder();
        int countConsecutive = 0;
        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            /* If next character is different than current, append this char to result.*/
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }
// ------------------------------------------------------------------------------------------------------------------
    // Rotate Matrix: Given an image represented by an NxN matrix,
    // where each pixel in the image is 4 bytes, write a method to rotate the image by 90 degrees.
    // Can you do this in place?

    public static boolean rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length != matrix[0].length) return false;
        int n = matrix.length;
        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];
                matrix[first][i] = matrix[last - offset][first];
                matrix[last - offset][first] = matrix[last][last - offset];
                matrix[last][last - offset] = matrix[i][last];
                matrix[i][last] = top;
            }
        }
        return true;
    }

    public static int[][] matrixRotate1(int[][] matrix) {
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

}


