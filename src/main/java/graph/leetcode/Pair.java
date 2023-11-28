package graph.leetcode;

import lombok.Getter;
import lombok.Setter;

/**
 * There are N network nodes, labelled 1 to N.
 * <p>
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 * <p>
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 */

@Getter
@Setter
public class Pair<T, U> {
    public T key;
    public U value;

    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }
}
