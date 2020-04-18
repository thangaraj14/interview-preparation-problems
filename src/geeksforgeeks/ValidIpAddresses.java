package geeksforgeeks;

import java.util.*;

public class ValidIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        if(s==null) return Collections.emptyList();
        List<String> result= new ArrayList<>();
        
        for(int i=1; i<4 && i<s.length();i++){
            String first= s.substring(0,i);
            if(isValid(first)){
                for(int j=1; j<4 && j+i<s.length(); j++ ){
                    String second= s.substring(i,i+j);
                    if(isValid(second)){
                        for(int k=1; i+j+k<s.length() && k<4 ;k++){
                            String third= s.substring(i+j,i+j+k);
                            String fourth= s.substring(i+j+k);
                            if(isValid(third) && isValid(fourth)){
                                result.add(first+"."+second+"."+third+"."+fourth);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public boolean isValid(String part){
        if(part==null || part.length()>3) return false;
        
        if(part.charAt(0)=='0' && part.length()>1) return false;
        
        int address= Integer.parseInt(part);
        
        return address>=0 && address<=255;
    }
}