package graph.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * tricky union find
 *
 * https://www.youtube.com/watch?v=QHniHFvxAl8&ab_channel=ShiranAfergan
 * https://leetcode.com/problems/accounts-merge/
 * Here, we use disjoint set union data structure to keep track of same user accounts.
 * We use a hash map to map emails to account's indices (index of the account in accounts list).
 * Note that different accounts (equivalently account ids) may belong to the same user.
 * We perform union operation on account ids. This might reduce the number of union operations, compared to when we perform union on all emails.
 * We start by iterating all email ids linked to all the accounts.
 * If we observe that an email has been observed before, we know that both email ids must belong to different accounts which belong to the same user.
 * Thus, we fetch the account ids corresponding to this email id, one is the current account's index, and the other is the account index from the map.
 * We then perform union on the account indices.
 * 
 * In the end, all account ids which belong to the same user get grouped together and return the same account id on calling findSet.
 * For each email, we check which account id it belongs to, get that account's parent's id from the union structure, and add the email to that id in a new map.
 * 
 * a b c // now b, c have parent a
 * d e f // now e, f have parent d
 * g a d // now abc, def all merged to group g
 * 
 * parents populated after parsing 1st account: a b c
 * a->a
 * b->a
 * c->a
 * 
 * parents populated after parsing 2nd account: d e f
 * d->d
 * e->d
 * f->d
 * 
 * parents populated after parsing 3rd account: g a d
 * g->g
 * a->g
 * d->g
 */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        UnionFind uf = new UnionFind(accounts.size());

        Map<String, Integer> emailsToIdMapper = new TreeMap<>();


        for (int i = 0; i < accounts.size(); i++) {

            List<String> emails = accounts.get(i);
            // Step 1: traverse all emails except names, if we have not seen an email before, put it with its index into map.
            // Otherwise, union the email to its parent index.
            for(int j=1;j<emails.size();j++){
                String email = emails.get(j);
                if(emailsToIdMapper.containsKey(email)){
                    uf.union(emailsToIdMapper.get(email),i);
                }else{
                    emailsToIdMapper.put(email,i);
                }
            }
        }


        Map<Integer, List<String>> result = new HashMap<>();

        for (String email : emailsToIdMapper.keySet()) {

            int id = emailsToIdMapper.get(email);

            int parentId = uf.find(id);
            if (!result.containsKey(parentId)) {
                result.putIfAbsent(parentId, new ArrayList<>());
                result.get(parentId).add(accounts.get(parentId).get(0));
            }

            result.get(parentId).add(email);
        }

        return new ArrayList<>(result.values());
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];

            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public boolean union(int x, int y) {

            int parentX = find(x);
            int parentY = find(y);

            if (parentX == parentY) return false;

            if (rank[parentX] > rank[parentY]) {
                parent[parentY] = parentX;
            } else if (rank[parentY] > rank[parentX]) {
                parent[parentX] = parentY;
            } else {
                parent[parentY] = parentX;
                rank[parentX]++;
            }

            return true;
        }

        public int find(int x) {
            if (parent[x] == x) return x;

            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean isSameComponent(int p, int q) {
            return find(p) == find(q);
        }
    }
}