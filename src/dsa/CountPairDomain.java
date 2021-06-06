package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPairDomain {

    public static List<String> subdomainVisits(String[] cpdomains) {

        Map<String, Integer> count = new HashMap<>();
        for (String cd : cpdomains) {
            int i = cd.indexOf(' ');
            int n = Integer.parseInt(cd.substring(0, i));
            String s = cd.substring(i + 1);
            for (i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == '.') {
                    String d = s.substring(i + 1);
                    count.put(d, count.getOrDefault(d, 0) + n);
                }
            }
            count.put(s, count.getOrDefault(s, 0) + n);
        }

        List<String> res = new ArrayList<>();
        for (String d : count.keySet())
            res.add(count.get(d) + " " + d);
        return res;
    }

    public static void main(String[] args) {
        String[] arr = new String[] { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" };
        subdomainVisits(arr).forEach(System.out::println);
    }
}
