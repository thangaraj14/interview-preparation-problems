package geeksforgeeks;

class LongestConsequtiveSequence {
    public int longestConsecutive(int[] nums) {
        if(nums.length==0) return 0;
        int max=1;
        Set<Integer> set= new HashSet<>();
        for(int i: nums){
            set.add(i);
        }
        
        for(Integer i: nums){
             int num=i;
            int count=1;
            // looking left;
            while(set.contains(--num)){
                count++;
                set.remove(num);
            }
            num=i;
            while(set.contains(++num)){
                 count++;
                 set.remove(num);
            }
            
            max=Math.max(max,count);
        }
        
        return max;
    }
}