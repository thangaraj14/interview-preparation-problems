package geeksforgeeks;
/*
Thoughts:
As hint indicates: use 2 pointers to hold position.
Use hasNext to validate (x,y)  and move x.
Use next() to return (x,y) and move it(regardless of correctness, which is determined by hasNext())
*/
public class Flatten2DVector {
	private int x;
	private int y;
	private List<List<Integer>> list;
    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d == null) {
        	return;
        }
        this.x = 0;
        this.y = 0;
        this.list = vec2d;
    }

    public int next() {
        int rst = list.get(x).get(y);
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
