package Other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class Regex {
    public static void main(String[] args) {
//        String input = "++++++++";
//        Pattern p = Pattern.compile("\\++");
//        Matcher m = p.matcher(input);
//        System.out.println(m.matches());
//
//        Pattern p2 = Pattern.compile("[+]+");
//        Matcher m2 = p2.matcher(input);
//        System.out.println(m2.matches());

        String str = "abcababcdecdcdcdecdcdabab";
        Pattern p3 = Pattern.compile("(cd)\1");
        Matcher m3 = p3.matcher(str);
        System.out.println(m3.groupCount());
        while(m3.find()){
            String w = m3.group();
            System.out.println(w + " " + m3.start() + " " + m3.end());
        }

        String numPattern = "-?\\d+";
        String numStr = "12arr909oposdf435rr-453";
        Pattern p5 = Pattern.compile(numPattern);
        Matcher m = p5.matcher(numStr);
        List list = new ArrayList();
        while(m.find()){
            list.add(m.group());
        }
        System.out.println(list);

        String input = " This    is    a sentence.    \n" +
                "This sentance     two";
        String output = input.trim().replaceAll(" +", " ");
        System.out.println(output);


        Scanner sc = new Scanner(System.in);
        String input1 = sc.nextLine();
        String[] words = input1.split(" ");
        System.out.println(" Number of words = " + words.length);

    }
}

// ? - Preceding character is optional
// * - Zero or more occurrence of the character or capturing group
// + - One or more occurrence of the character or capturing group

// . - Any character
// d - 0-9
// s - Whitespace character
// w - A word character, ie [A-Za-z_0-9]
// D - Non-digit character
// W - Non-word character
// S - Non-whitespace character

