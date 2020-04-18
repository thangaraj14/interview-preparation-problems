package geeksforgeeks;

class CountAndSay{
    public String countAndSay(int n) {
        String result="";
        if(n<=0) return result;
        
        result="1";
        for(int i=1;i<n;i++){
          result= populateString(result);  
        }
       return result;
        
    }
    
    public String populateString(String result){
        StringBuilder sb= new StringBuilder();
        int i=0;
       for( ;i<result.length();i++){
           char temp=result.charAt(i);
           int count=1;
           while(i+1<result.length() && result.charAt(i+1)==temp){
               count++;
               i++;
           }
           sb.append(""+count);
           sb.append(""+temp);
       }
        
        return sb.toString();
    }
}