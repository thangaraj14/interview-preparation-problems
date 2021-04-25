package dsa;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class RaceCar {

    public int racecar(int target) {
        Set<String> visited = new HashSet<>();
        Queue<StateNode> queue = new LinkedList<>();
        queue.add(new StateNode(1, 0, 0));
        while (!queue.isEmpty()) {

            StateNode cur = queue.poll();
            if (cur.position == target) {
                return cur.result;
            }
            // if A
            int nextPosition = cur.position + cur.speed;
            int nextSpeed = cur.speed * 2;
            if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                visited.add(nextSpeed + "," + nextPosition);
                queue.offer(new StateNode(nextSpeed, nextPosition, cur.result + 1));
            }
            // if R
            nextPosition = cur.position;
            nextSpeed = cur.speed > 0 ? -1 : 1;
            if (!visited.contains(nextSpeed + "," + nextPosition) && Math.abs(target - nextPosition) < target) {
                visited.add(nextSpeed + "," + nextPosition);
                queue.offer(new StateNode(nextSpeed, nextPosition, cur.result + 1));
            }

        }
        return -1;
    }
}

class StateNode {
    int speed;
    int position;
    int result;

    public StateNode(int speed, int position, int result) {
        this.speed = speed;
        this.position = position;
        this.result = result;
    }
}