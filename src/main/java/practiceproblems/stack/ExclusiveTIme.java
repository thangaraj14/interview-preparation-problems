package practiceproblems.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * tricky revise TODO
 * https://leetcode.com/problems/exclusive-time-of-functions/
 */
public class ExclusiveTIme {

    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Log> stack = new ArrayDeque<>();
        int[] result = new int[n];
        for (String content : logs) {
            Log log = new Log(content);
            if (log.isStart) {
                stack.push(log);
            } else {
                Log top = stack.pop();
                result[top.id] += (log.time - top.time + 1 - top.subDuration);
                if (!stack.isEmpty()) {
                    stack.peek().subDuration += (log.time - top.time + 1);
                }
            }
        }

        return result;
    }

    public static class Log {
        public int id;
        public boolean isStart;
        public int time;
        public int subDuration;

        public Log(String content) {
            String[] strs = content.split(":");
            id = Integer.parseInt(strs[0]);
            isStart = strs[1].equals("start");
            time = Integer.parseInt(strs[2]);
            subDuration = 0;
        }
    }
}
