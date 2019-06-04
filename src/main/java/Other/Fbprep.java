package Other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import Other.Person;


public class Fbprep {

    public static void main(String[] args){
//        int a[][] = genSpiral(3);
//        for (int i =0; i<a.length; i++) {
//            System.out.println("");
//            for (int j = 0; j < a.length; j++) {
//                System.out.print("  " + a[i][j] + "  ");
//            }
//        }
//        HashMap<Integer, Integer> person = new HashMap<>();
//        person.put(2000, 2010);
//        person.put(1975, 2005);
//        person.put(1975, 2003);
//        person.put(1803, 1809);
//        person.put(1750, 1869);
//        person.put(1840, 1935);
//        person.put(1803, 1921);
//        person.put(1894, 1921);


//        int minBirthYear = getMinBirthYear(person);
//        int maxBirthYear = getMaxBirthYear(person);
//        System.out.println(minBirthYear);
//        System.out.println(maxBirthYear);
//        getPopulation(person, minBirthYear, maxBirthYear);

        ArrayList<Person> people = new ArrayList<Person>();
        Person p1 = new Person(2000, 2010);
        Person p2 = new Person(1975, 2005);
        Person p3 = new Person(1975, 2003);
        Person p4 = new Person(1803, 1809);
        Person p5 = new Person(1750, 1869);
        Person p6 = new Person(1840, 1935);
        Person p7 = new Person(1803, 1921);
        Person p8 = new Person(1894, 1921);
        people.add(p1);people.add(p2);people.add(p3);people.add(p4);people.add(p5);people.add(p6);people.add(p7);people.add(p8);

}

    public static int getMinBirthYear(HashMap<Integer, Integer> person){
        int minBirthYear = Integer.MAX_VALUE;
        int birthYear = 0;
        Set<Map.Entry<Integer, Integer>> entrySet = person.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet)
        {
            birthYear = entry.getKey();
            if(birthYear<minBirthYear){
                minBirthYear=birthYear;
            }
        }
        return minBirthYear;
    }

    public static int getMaxBirthYear(HashMap<Integer, Integer> person){
        int maxBirthYear = 0;
        int birthYear = 0;
        Set<Map.Entry<Integer, Integer>> entrySet = person.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet)
        {
            birthYear = entry.getKey();
            if(birthYear>maxBirthYear){
                maxBirthYear=birthYear;
            }
        }
        return maxBirthYear;
    }


    public static HashMap getPopulation(HashMap<Integer, Integer> person, int minBirthYear, int maxBirthYear){
        //int[] population = new int[maxBirthYear - minBirthYear];
        HashMap<Integer, Integer> frqs= new HashMap<>();
        Set<Map.Entry<Integer, Integer>> entrySet = person.entrySet();
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            if(!frqs.containsKey(entry.getKey())){
                frqs.put(entry.getKey(), 1);
            }
            else {
                frqs.put(entry.getKey(), frqs.get(entry.getKey()) + 1);
            }
            if(!frqs.containsKey(entry.getValue())){
                frqs.put(entry.getValue(), -1);
            }
            else{
                frqs.put(entry.getValue(), frqs.get(entry.getValue()) - 1);
            }
        }

        return frqs;
    }




}
