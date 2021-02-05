package geeksforgeeks;

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
public class Flatten2DVector {

    private int x;
    private int y;
    private List<List<Integer>> list;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        if (vec2d == null) {
            return;
        }
        this.x = 0;
        this.y = 0;
        this.list = vec2d;
    }

    public int next() {
        int rst = list.get(x).get(y);
        // when y(column) reaches end increment row(x) and reset y
        if (y + 1 >= list.get(x).size()) {
            y = 0;
            x++;
        } else {
            y++;
        }
        return rst;
    }

    public boolean hasNext() {
        if (list == null) {
            return false;
        }
        // this condition is to check for empty rows
        while (x < list.size() && list.get(x).size() == 0) {
            x++;
            y = 0;
        }
        if (x >= list.size()) {
            return false;
        }
        if (y >= list.get(x).size()) {
            return false;
        }
        return true;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */

