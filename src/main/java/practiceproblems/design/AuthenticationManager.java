package practiceproblems.design;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/design-authentication-manager/
 */
public class AuthenticationManager {

    int timeToLive;
    TreeMap<String, Integer> authCache;
    TreeMap<Integer, String> timeCache;

    /**
     * AuthenticationManager(int timeToLive) constructs the AuthenticationManager and sets the timeToLive.
     */
    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        authCache = new TreeMap<>();
        timeCache = new TreeMap<>();

    }

    /**
     * generate(string tokenId, int currentTime) generates a new token with the given tokenId at the given currentTime in seconds.
     */
    public void generate(String tokenId, int currentTime) {
        authCache.put(tokenId, currentTime);
        timeCache.put(currentTime, tokenId);
    }

    /**
     * renews the unexpired token with the given tokenId at the given currentTime in seconds.
     * If there are no unexpired tokens with the given tokenId, the request is ignored, and nothing happens.
     */
    public void renew(String tokenId, int currentTime) {
        if (!authCache.containsKey(tokenId)) return;

        int timeOfToken = authCache.get(tokenId);

        if (timeOfToken + timeToLive > currentTime) {
            timeCache.remove(timeOfToken);
            authCache.put(tokenId, currentTime);
            timeCache.put(currentTime, tokenId);
        }
    }

    /**
     * returns the number of unexpired tokens at the given currentTime.
     */
    public int countUnexpiredTokens(int currentTime) {
        return timeCache.subMap(currentTime - timeToLive + 1, currentTime).size();
    }
}
