package thread.pluralsight.third;

import javafx.beans.binding.MapExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * @author i312458
 */
public class TestThis {

    public static void main(String[] args) {
 /*     // String s = new String(new char[] { 'a', 'b', 'c' }); // "abc" will not be added to String constants pool.
        System.out.println(System.identityHashCode(s));
        s = s.intern();// add s to String constants pool
        System.out.println(System.identityHashCode(s));*/

        String str1 = new String("hello");
        //  String str2 = "hello";
        String str3 = str1.intern();
        System.out.println(System.identityHashCode(str1));
        System.out.println(System.identityHashCode("hello"));
        //   System.out.println(System.identityHashCode(str3));

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        ListIterator<Integer> integerListIterator = list.listIterator();
        while (integerListIterator.hasNext()) {
            if (integerListIterator.next().equals(1)) {
                integerListIterator.remove();
            }
        }

        EnumSet<test> enumSet = EnumSet.allOf(test.class);
        System.out.println("Enum test" + enumSet.contains(test.Two));

        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,1);

        Collections.synchronizedMap(map);

        ArrayList entryList = new ArrayList(map.entrySet());
        Object o = entryList.get(0);
    }

    enum test {
        One, Two;
    }

    class NameComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return 0;
        }
    }

}
