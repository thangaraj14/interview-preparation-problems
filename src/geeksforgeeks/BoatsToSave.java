package geeksforgeeks;

import java.util.Arrays;

class BoatsToSave {
    public int numRescueBoats(int[] people, int limit) {
        if(people.length==0 || limit==0) return 0;
        
        Arrays.sort(people);
        
        int i=0;
        int j=people.length-1;
        int res=0;
        while(i<=j){
            if(people[i]+people[j]<=limit){
              res++;
                i++;
                j--;
            }else{
                res++;
                j--;// neglecting people with more weight 
            }
            
        }
        
        return res;
    }
}