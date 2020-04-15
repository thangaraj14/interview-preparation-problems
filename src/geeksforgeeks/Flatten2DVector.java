package geeksforgeeks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*

https://leetcode.com/problems/flatten-2d-vector/

https://www.lintcode.com/problem/flatten-2d-vector/

Thoughts:
As hint indicates: use 2 pointers to hold position.
Use hasNext to validate (x,y)  and move x.
Use next() to return (x,y) and move it(regardless of correctness, which is determined by hasNext())

Input:[[1,2],[3],[4,5,6]]
Output:[1,2,3,4,5,6]

*/
public class Flatten2DVector implements Iterator<Integer> {

    List<List<Integer>> result;
    int index = 0;
    int subIndex = 0;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        result = vec2d;
    }

    @Override
    public Integer next() {
        int temp = index;
        int tempSubIndex = subIndex;
        if (hasNext()) {

            if (index < result.size()) {

                List<Integer> value = result.get(index);

                while (value.size() == 0) {
                    value = result.get(++index);
                    temp = index;
                }

                if (subIndex + 1 < value.size()) {
                    subIndex++;
                } else {
                    index++;
                    subIndex = 0;
                }
            }
            return result.get(temp).get(tempSubIndex);
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        if (index < result.size()) {
            return true;
        }
        return false;
    }

    @Override
    public void remove() {
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

