package internals;

import java.util.HashSet;
import java.util.Set;

public class QuadTree<P> {

    Point topLeft;
    Point bottomRight;
    Set<QuadNode<P>> nodes;
    // children (this can also be used like Trie, where it is an Array of QuadTree)
    QuadTree<P> topLeftTree;
    QuadTree<P> topRightTree;
    QuadTree<P> bottomLeftTree;
    QuadTree<P> bottomRightTree;
    int maxLen;
    private Set<QuadNode<P>> node;

    public QuadTree(Point topLeft, Point bottomRight, int maxLen) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.maxLen = maxLen;
        nodes = new HashSet<>();
    }

    public Set<QuadNode<P>> search(Point p) {
        QuadTree<P> curr = this;

        while (!curr.isLeaf()) {

            // recurse by checking if it is within the boundary
            if (p.x < (curr.topLeft.x + curr.bottomRight.x) / 2) {
                if (p.y < (curr.topLeft.y + curr.bottomRight.y) / 2) {
                    curr = curr.topLeftTree;
                } else {
                    curr = curr.bottomLeftTree;
                }
            } else {
                if (p.y < (curr.topLeft.y + curr.bottomRight.y) / 2) {
                    curr = curr.topRightTree;
                } else {
                    curr = curr.bottomRightTree;
                }

            }
        }
        return curr.node;
    }

    private boolean isLeaf() {
        return true;
    }


    static class QuadNode<T> {
        T data;
        Point point;

        public QuadNode(Point p, T data) {
            this.data = data;
            this.point = p;
        }

        public QuadNode(int x, int y, T data) {
            this.data = data;
            this.point = new Point(x, y);
        }

        @Override
        public String toString() {
            return "data " + data + " point " + point;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // more like a deep clone
        public Point(Point p) {
            this.x = p.x;
            this.y = p.y;
        }

        @Override
        public String toString() {
            return "x: " + x + " y: " + y;
        }
    }
}
