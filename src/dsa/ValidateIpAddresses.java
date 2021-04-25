package dsa;

/**
 * https://leetcode.com/problems/validate-ip-address/
 */
public class ValidateIpAddresses {
    // the condition for IPv4 is
    // there should be 4 components separated by 3 dots
    // each component should have value between 0-9 (base 10)
    //
    public static String validIPAddress(String IP) {

        if (IP == null || IP.length() == 0) {
            return "Neither";
        }

        if (IP.chars().filter(e -> e == '.').count() == 3) {
            // -1 will help us to split (".") -> 1.1. => [1][1][""]
            for (String s : IP.split("\\.", -1)) {
                if (s.length() == 0 || s.length() > 4) {
                    return "Neither";
                }
                if (s.charAt(0) == '0' && s.length() != 1) {
                    return "Neither";
                }
                for (char c : s.toCharArray())
                    if (!Character.isDigit(c)) {
                        return "Neither";
                    }
                if (Integer.parseInt(s) > 255) {
                    return "Neither";
                }
            }
            return "IPv4";
        }
        // the condition for IPv6 is
        // it should have 8 components, separated by 7 ':'s
        // each component should have hex-value i.e 0-9, a-f or A-F
        else if (IP.chars().filter(e -> e == ':').count() == 7) {
            for (String s : IP.split(":", -1)) {
                if (s.length() == 0 || s.length() > 4) {
                    return "Neither";
                }
                for (char c : s.toCharArray()) {
                    if (!((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                        return "Neither";
                    }
                }
            }
            return "IPv6";
        }
        return "Neither";
    }

    public static void main(String[] args) {
        validIPAddress("172.16.254.1");
    }
}