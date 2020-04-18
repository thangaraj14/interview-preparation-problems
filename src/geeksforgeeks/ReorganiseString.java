package geeksforgeeks;

class ReorganiseString{
    public String reorganizeString(String s) {
        if(s == null || s.length() < 1) return "";
        
        int[] hash = new int[26];
        for(char c : s.toCharArray()) {
            hash[c-'a']++;
        }
        
        int max = 0;
        int letter = 0;
        
        for(int i=0; i<hash.length; i++) {
            if(hash[i] > max) {
                max = hash[i];
                letter = i;
            }            
        }
        
        if(max > (s.length()+1)/2) return "";
        
        char[] res = new char[s.length()];
        
        int k=0;
        while(hash[letter] > 0) {
            res[k] = (char)(letter + 'a');
            k += 2;
            hash[letter]--;
        }
        
        for(int i=0; i<hash.length; i++) {
            while(hash[i] > 0) {
                if(k >= res.length) k=1;
                
                res[k] = (char)(i + 'a');
                k += 2;
                hash[i] --;
            }
        }
        
        return new String(res);
    }

    public static void main(String[] args) {
        new ReorganiseString().reorganizeString("aabccdeeee");
    }
}