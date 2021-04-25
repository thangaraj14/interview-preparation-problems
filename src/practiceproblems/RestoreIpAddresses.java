package practiceproblems;

import java.util.*;

/**
 *Given a string s containing only digits,
 * return all possible valid IP addresses that can be obtained from s.
 * You can return them in any order.
 *
 * A valid IP address consists of exactly four integers,
 * each integer is between 0 and 255, separated by single dots and cannot have leading zeros.
 * For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245",
 * "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
 */

public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        if (s == null)
            return Collections.emptyList();
        List<String> result = new ArrayList<>();

        for (int i = 1; i < 4 && i < s.length(); i++) {
            String first = s.substring(0, i);
            if (isValid(first)) {
                for (int j = 1; j < 4 && j + i < s.length(); j++) {
                    String second = s.substring(i, i + j);
                    if (isValid(second)) {
                        for (int k = 1; i + j + k < s.length() && k < 4; k++) {
                            String third = s.substring(i + j, i + j + k);
                            String fourth = s.substring(i + j + k);
                            if (isValid(third) && isValid(fourth)) {
                                result.add(first + "." + second + "." + third + "." + fourth);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public boolean isValid(String part) {
        if (part == null || part.length() > 3)
            return false;

        if (part.charAt(0) == '0' && part.length() > 1)
            return false;

        int address = Integer.parseInt(part);

        return address >= 0 && address <= 255;
    }
}