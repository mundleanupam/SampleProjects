package Other;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class JavaHashMapPrograms
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("ONE", 1);
        map.put("TWO", 2);
        map.put("THREE", 3);
        map.put("FOUR", 4);
        map.put("FIVE", 5);
        //map.putIfAbsent("ONE", 123);

        Set<Entry<String, Integer>> entrySet = map.entrySet();

        for (Entry<String, Integer> entry : entrySet)
        {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        System.out.println("---------------------------------------------------------");

        HashMap<String, Integer> anotherMap = new HashMap<String, Integer>();
        anotherMap.put("SIX", 6);
        anotherMap.put("SEVEN", 7);
        anotherMap.putAll(map);

        entrySet = anotherMap.entrySet();

        for (Entry<String, Integer> entry : entrySet)
        {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }

        System.out.println("---------------------------------------------------------");

        int value = map.get("TWO");
        System.out.println("get value - "+ value);
        System.out.println(map.containsKey("ONE"));
        System.out.println(map.size());
        //map.clear();
        Set<String> keySet = map.keySet();
        Collection<Integer> valueSet = map.values();
        for (String key : keySet)
        {
            System.out.println(key);
        }

        for (Integer values : valueSet)
        {
            System.out.println(values);
        }

    }
}