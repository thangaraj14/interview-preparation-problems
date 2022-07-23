package practiceproblems.design;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * https://leetcode.com/problems/design-a-leaderboard/
 */
public class LeaderBoard {
    Map<Integer, Player> players = new HashMap<>();
    TreeSet<Player> scores = new TreeSet<>((a, b) -> b.score - a.score == 0 ? a.id - b.id : b.score - a.score);

    //Log(N)

    /**
     * addScore(playerId, score): Update the leaderboard by adding score to the given player's score.
     * If there is no player with such id in the leaderboard, add him to the leaderboard with the given score.
     */
    public void addScore(int playerId, int score) {
        Player cur;
        if (players.containsKey(playerId)) {
            cur = players.get(playerId);
            scores.remove(cur);
            cur.score += score;
            scores.add(cur);
        } else {
            cur = new Player(playerId, score);
            players.put(playerId, cur);
            scores.add(cur);
        }
    }

    /**
     * Return the score sum of the top K players.
     */
    public int top(int K) {
        Iterator<Player> iterator = scores.iterator();
        int res = 0;
        while (K-- > 0 && iterator.hasNext()) {
            res += iterator.next().score;
        }
        return res;
    }

    /**
     * Reset the score of the player with the given id to 0 (in other words erase it from the leaderboard).
     * It is guaranteed that the player was added to the leaderboard before calling this function.
     */
    public void reset(int playerId) {
        Player cur = players.get(playerId);
        scores.remove(cur);
        cur.score = 0;
    }

    class Player {
        int id;
        int score;

        public Player(int id, int score) {
            this.id = id;
            this.score = score;
        }
    }
}
