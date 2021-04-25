package practiceproblems;

/**
 * A valid IPv4 address is an IP in the form "x1.x2.x3.x4"
 * where 0 <= xi <= 255 and xi cannot contain leading zeros.
 * For example, "192.168.1.1" and "192.168.1.0" are valid IPv4 addresses but "192.168.01.1",
 * while "192.168.1.00" and "192.168@1.1" are invalid IPv4 addresses.
 *
 * A valid IPv6 address is an IP in the form "x1:x2:x3:x4:x5:x6:x7:x8" where:
 *
 * 1 <= xi.length <= 4
 * xi is a hexadecimal string which may contain digits,
 * lower-case English letter ('a' to 'f') and upper-case English letters ('A' to 'F').
 * Leading zeros are allowed in xi.
 * For example, "2001:0db8:85a3:0000:0000:8a2e:0370:7334"
 * and "2001:db8:85a3:0:0:8A2E:0370:7334"
 * are valid IPv6 addresses, while "2001:0db8:85a3::8A2E:037j:7334"
 * and "02001:0db8:85a3:0000:0000:8a2e:0370:7334" are invalid IPv6 addresses.
 *
 * Input: IP = "172.16.254.1"
 * Output: "IPv4"
 * Explanation: This is a valid IPv4 address, return "IPv4".
 *
 * Input: IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * Output: "IPv6"
 * Explanation: This is a valid IPv6 address, return "IPv6".
 */
public class ValidateIpAddresses {
    // the condition for IPv4 is
    // there should be 4 components separated by 3 dots
    // each component should have value between 0-9 (base 10)
    public String validIPAddress(String IP) {
        if(IP==null || IP.length()==0) return "Neither";
        
        if(IP.chars().filter(e->e=='.').count()==3){
            
            for(String s: IP.split("\\.",-1)){ //-1 is for edge case like "1.0.1."
                if(s.length()==0 || s.length()>4) return "Neither";
                if(s.charAt(0)=='0' && s.length()!=1) return "Neither";
                for(char c:s.toCharArray()) if(!Character.isDigit(c)) return "Neither";
                if(Integer.parseInt(s)>255) return "Neither";
            }
            
            return "IPv4";
            
        }
        // the condition for IPv6 is 
        // it should have 8 components, separated by 7 ':'s
        // each component should have hex-value i.e 0-9, a-f or A-F
        else if(IP.chars().filter(e->e==':').count()==7){
            for(String s: IP.split(":",-1)){
                if(s.length()==0 || s.length()>4) return "Neither";
                for(char c: s.toCharArray()){
                    if(!((c>='0' && c<='9') || (c>='a' && c<='f') || (c>='A' && c<='F'))){
                        return "Neither";
                    }
                }
            }
            
            return "IPv6";
        }
        
        return "Neither";
    }
}