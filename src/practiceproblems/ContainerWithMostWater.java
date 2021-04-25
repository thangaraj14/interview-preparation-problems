package practiceproblems;

/**
 * Given n non-negative integers a1, a2, ..., an , 
 * where each represents a point at coordinate (i, ai).
 *  n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 *  Find two lines, which together with x-axis forms a container, such that the container contains the most water.

   Note: You may not slant the container and n is at least 2.
   Input: [1,8,6,2,5,4,8,3,7]
   Output: 49
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        if(height==null || height.length==0) return 0;
        int i=0;
        int j= height.length-1;
        int result=0;
        
        while(i<j){
            
            int waterVol= (j-i)* Math.min(height[i],height[j]); // width * height
            result=Math.max(waterVol, result);
            
            if(height[i]>height[j]){ // the reason we are moving lesser side is
                                    // j-i is going to be decreasing so we need to 
                                    // maintain higher side to have max value
                j--;
            }else{
                i++;
            }
        }
      return result;
    }
}
