// This leetcode file is from May 2019

package Generic;

import java.util.*;

public class LeetCode {

    public static void main (String[] args){
        String reverse0 = reverseWords("The sky is blue");
        String reverse1 = reverseWords("The sky is blue");
        String reverse2 = reverseWords("a good   example");
        String fractionToDecimal = fractionToDecimal(4, 345);
        int[] ratings = {0,1,2,3,4,1,2,0};
        candy(ratings);
        String sub = lrs("geeksforgeeks");
        String sub1 = lrs("bbbbb");
        String sub2 = lrs("pwwkew");
        ConvertSectoDay(3661);
        ConvertSectoDay(3660);
        ConvertSectoDay(2077112);
        ConvertSectoDay(10);
        checkInclusion("bcda", "ssssabcdsdbcatcbad");
        checkInclusion("ba", "ssssssab");
        checkInclusion("ba", "sscdab");
        checkInclusion("ba", "sscdabse");
        checkInclusion("ba", "cdbeab");
        int b = checkInclusion1("bcda", "ssssabcdsdbcatcbad");
        int a = checkInclusion1("ab","ssabbass");
    }

    public static String reverseWords(String s) {
        String s1 = s.trim();
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        StringTokenizer st = new StringTokenizer(s1, " ");
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens())
        {
            tokens.add(st.nextToken());
        }
        int tokenLen = tokens.size();
        for(int j=tokenLen-1; j>=0; j--){
            sb.append(tokens.get(j));
            if(j==0) break;
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String reverseWords1(String s) {
        String s1 = s.trim();
        int len = s1.length();
        char[] strArr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int i = len-1;
        int end = len-1;
        while(i>=0){
            end = i;
            if(strArr[i] ==' '){
                index = i+1;
                for(int j=index; j<=end; j++){
                    sb.append(strArr[i]);
                }
            }
            else i--;
        }
        return sb.toString();
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // "+" or "-"
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // integral part
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // fractional part
        res.append(".");
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        map.put(num, res.length());
        while (num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if (map.containsKey(num)) {
                int index = map.get(num);
                res.insert(index, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length());
            }
        }
        return res.toString();
    }

    static String longestRepeatedSubstring(String str) {
        int n = str.length();
        int LCSRe[][] = new int[n + 1][n + 1];

        String res = ""; // To store result
        int res_length = 0; // To store length of result

        // building table in bottom-up manner
        int i, index = 0;
        for (i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // (j-i) > LCSRe[i-1][j-1] to remove
                // overlapping
                if (str.charAt(i - 1) == str.charAt(j - 1)
                        && LCSRe[i - 1][j - 1] < (j - i)) {
                    LCSRe[i][j] = LCSRe[i - 1][j - 1] + 1;

                    // updating maximum length of the
                    // substring and updating the finishing
                    // index of the suffix
                    if (LCSRe[i][j] > res_length) {
                        res_length = LCSRe[i][j];
                        index = Math.max(i, index);
                    }
                } else {
                    LCSRe[i][j] = 0;
                }
            }
        }
        // If we have non-empty result, then insert all
        // characters from first character to last
        // character of String
        if (res_length > 0) {
            for (i = index - res_length + 1; i <= index; i++) {
                res += str.charAt(i - 1);
            }
        }

        return res;
    }

    public static int candy(int[] ratings) {
        int candies[] = new int[ratings.length];
        Arrays.fill(candies, 1);// Give each child 1 candy

        for (int i = 1; i < candies.length; i++){// Scan from left to right, to make sure right higher rated child gets 1 more candy than left lower rated child
            if (ratings[i] > ratings[i - 1]) candies[i] = (candies[i - 1] + 1);
        }

        for (int i = candies.length - 2; i >= 0; i--) {// Scan from right to left, to make sure left higher rated child gets 1 more candy than right lower rated child
            if (ratings[i] > ratings[i + 1]) candies[i] = Math.max(candies[i], (candies[i + 1] + 1));
        }

        int sum = 0;
        for (int candy : candies)
            sum += candy;
        return sum;
    }


    public static String lcp(String s, String t) {
        int n = Math.min(s.length(), t.length());
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i))
                return s.substring(0, i);
        }
        return s.substring(0, n);
    }


    // return the longest repeated string in s
    public static String lrs(String s) {

        // form the N suffixes
        int n  = s.length();
        String[] suffixes = new String[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = s.substring(i, n);
        }

        // sort them
        Arrays.sort(suffixes);

        // find longest repeated substring by comparing adjacent sorted suffixes
        String lrs = "";
        for (int i = 0; i < n-1; i++) {
            String x = lcp(suffixes[i], suffixes[i+1]);
            if (x.length() > lrs.length())
                lrs = x;
        }
        return lrs;
    }

    // This is asked in Apple

    // day = sec / (24*3600)
    // hour = (sec / (24*3600)) / 3600
    static void ConvertSectoDay(int n)
    {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        int day = n / (24 * 3600);
        if(day!=0) sb1.append(day + "Days, ");

        n = n % (24 * 3600);
        int hour = n / 3600;
        if(hour!=0) sb1.append(hour + " Hours, ");

        n = n % 3600;
        int minutes = n / 60 ;
        if(minutes!=0) sb1.append(minutes + " Minutes, ");

        n = n % 60;
        int seconds = n;
        if(seconds!=0) sb1.append(seconds + " Seconds ");

        if(day==0 && hour==0 && minutes==0){
            sb.append(seconds + " second(s)");
        }
        else if(day==0 && hour==0) {
            sb.append(minutes +" minute(s) " + seconds + " second(s) ");
        }
        else if(day==0 ) {
            sb.append(hour+ " hour(s) " + minutes +" minute(s) " + seconds + " second(s)");
        }
        else {
            sb.append(day+ " day(s) " + hour+ " hour(s) " + minutes +" minute(s) " + seconds + " second(s)");
        }

        System.out.println( day + " " + "days " + hour
                + " " + "hours " + minutes + " "
                + "minutes " + seconds + " "
                + "seconds ");
        System.out.println(sb.toString());
        System.out.println(sb1.toString().substring(0, sb1.length()-2));

    }


    // Problem is to find the permutations of string s1 in s2 e.g. abcd and dcda are permutations
    // Sliding window protocol is used. In my implementation one extra array is used.
    // checkInclusion("bcda", "ssssabcsdsdbcatcbad");

    public static boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        if (len1 > len2) return false;

        int[] count = new int[26];
        for (int i = 0; i < len1; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        if (allZero(count)) return true;

        for (int i = len1; i < len2; i++) {
            count[s2.charAt(i) - 'a']--;
            count[s2.charAt(i - len1) - 'a']++;
            if (allZero(count)) return true;
        }

        return false;
    }

    private static boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return false;
        }
        return true;
    }

    public static int checkInclusion1 (String s1, String s2){
        int[] countPattern = new int[26];
        int[] countString = new int[26];
        int result = 0;
        for(int i=0; i<s1.length(); i++){
            countPattern[s1.charAt(i) - 'a']++;
        }
        for(int j=0; j<s2.length()-s1.length()+1; j++){
            for(int k=j; k<s1.length()+j; k++){
                countString[s2.charAt(k) - 'a']++;
            }
            if(isArrEqual(countPattern, countString)) result++;
            else{
                Arrays.fill(countString, 0);
            }
        }
        return result;
    }

    public static boolean isArrEqual(int[] a1, int[] a2){
        for(int i=0; i<26; i++){
            if(a1[i]!=a2[i]) return false;
        }
        return true;
    }



}
