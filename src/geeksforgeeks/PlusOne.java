package geeksforgeeks;

class PlusOne {
    public static int[] plusOne(int[] digits) { 
        if(digits==null || digits.length==0) return digits;
        int temp=digits[digits.length-1]+1;
        
        digits[digits.length-1]= temp%10;
        int rem=temp/10;
        for(int i=digits.length-2;i>=0;i--){
            int sum=digits[i]+rem;
           digits[i]=(sum)%10;
             rem=(sum)/10;  
        }
        
        if(rem>0){
          int[] result= new int[digits.length+1];
            result[0]=1;
            return result;
        } 
      
        
        return  digits;
    }
    
}