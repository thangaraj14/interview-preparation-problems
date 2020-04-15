package geeksforgeeks;

import java.util.*;

/*https://leetcode.com/problems/design-twitter/discuss/82825/Java-OO-Design-with-most-efficient-function-getNewsFeed*/
public class Twitter {
    private static int timeStamp = 0;

    private Map<Integer, User> userMap;

    // Tweet link to next Tweet so that we can save a lot of time
    // when we execute getNewsFeed(userId)
    private class Tweet {
        public int id;
        public int time;
        public Tweet next;

        public Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }

    public class User {
        public int id;
        public Set<Integer> following;
        public Tweet tweet_head;

        public User(int id) {
            this.id = id;
            following = new HashSet<>();
            follow(id); // first follow itself
            tweet_head = null;
        }

        public void follow(int id) {
            following.add(id);
        }

        public void unfollow(int id) {
            following.remove(id);
        }

        // everytime user post a new tweet, add it to the head of tweet list.
        public void post(int id) {
            Tweet t = new Tweet(id);
            t.next = tweet_head;
            tweet_head = t;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        userMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User u = new User(userId);
            userMap.put(userId, u);
        }
        userMap.get(userId).post(tweetId);

    }

    // Best part of this.
    // first get all tweets lists from one user including itself and all people it followed.
    // Second add all heads into a max heap. Every time we poll a tweet with
    // largest time stamp from the heap, then we add its next tweet into the heap.
    // So after adding all heads we only need to add 9 tweets at most into this
    // heap before we get the 10 most recent tweet.
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new LinkedList<>();

        if (!userMap.containsKey(userId)) {
            return result;
        }

        Set<Integer> users = userMap.get(userId).following;
        PriorityQueue<Tweet> q = new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));
        for (int user : users) {
            Tweet t = userMap.get(user).tweet_head;
            // very important! If we add null to the head we are screwed.
            if (t != null) {
                q.add(t);
            }
        }
        int n = 0;
        while (!q.isEmpty() && n < 10) {
            Tweet t = q.poll();
            result.add(t.id);
            n++;
            if (t.next != null) {
                q.add(t.next);
            }
        }
        return result;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        if (!userMap.containsKey(followeeId)) {
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId) {
            return;
        }
        userMap.get(followerId).unfollow(followeeId);
    }

    public static void main(String[] args) {
        Twitter obj = new Twitter();
        obj.postTweet(1, 5);
        List<Integer> param_2 = obj.getNewsFeed(1);
        obj.follow(1, 2);
        obj.unfollow(1, 2);
    }
}