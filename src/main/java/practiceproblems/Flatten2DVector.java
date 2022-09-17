package practiceproblems;

import java.util.List;

/*
Thoughts:
As hint indicates: use 2 pointers to hold position.
Use hasNext to validate (x,y)  and move x.
Use next() to return (x,y) and move it(regardless of correctness, which is determined by hasNext())

Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, 
the order of elements returned by next should be: [1,2,3,4,5,6].

Understand the problem:
The question itself is very easy to solve. Just several corner cases need to think of:
  -- What if the 2d vector contains empty arrays, e.g. [ ], [ ], 1 2 3 ? In this case, the next() should not output anything, but the return type is int. There the hasNext() should be more complicated in which it handles this situation.
  -- What if the 2d vector itself is empty? Again, handle it in hasNext()
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
        while (x < list.size() && list.get(x).isEmpty()) {
            x++;
            y = 0;
        }
        if (x >= list.size()) {
            return false;
        }
        return y < list.get(x).size();
    }
}
