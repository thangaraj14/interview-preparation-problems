package geeksforgeeks;

class ShuffleArray {

    int[] value;
    int[] originalVal;
    public Solution(int[] nums) {
       value= nums; 
       originalVal=nums.clone();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return originalVal;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
       // int index=value.length-1;
        for(int i=value.length-1;i>0;i--){
            int randIndex= (int)Math.floor(new Double(i*Math.random()));
            int temp=value[i];
            value[i]=value[randIndex];
            value[randIndex]=temp;
          //  index--;
        }
        
        return value;
    }
}
