package com.leetcode.UnionFind;

import java.util.*;

class AccountUnion {
    int[] parents;
    int[] size;

    public AccountUnion(int size) {
        this.parents = new int[size];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        this.size = new int[size];
        Arrays.fill(this.size, 1);
    }

    public void union(int user1, int user2) {
        int root1 = getRoot(user1);
        int root2 = getRoot(user2);
        if (size[root1] < size[root2]) {
            parents[root1] = root2;
            size[root2] += size[root1];
        } else {
            parents[root2] = root1;
            size[root1] += size[root2];
        }
    }

    public boolean find(int user1, int user2) {
        return getRoot(user1) == getRoot(user2);
    }

    public int getRoot(int user) {
        int temp = user;
        while (parents[temp] != temp) {
            parents[temp] = parents[parents[temp]];
            temp = parents[temp];
        }
        parents[user] = temp;
        return temp;
    }
}

class testAccountsMerge {
    public static void main(String[] args) {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("David", "David0@m.co", "David1@m.co"),
                Arrays.asList("David", "David3@m.co", "David4@m.co"),
                Arrays.asList("David", "David4@m.co", "David5@m.co"),
                Arrays.asList("David", "David2@m.co", "David3@m.co"),
                Arrays.asList("David", "David1@m.co", "David2@m.co")
        );
        LC0721_AccountsMerge accountsMerge = new LC0721_AccountsMerge();
        List<List<String>> ret = accountsMerge.accountsMerge(accounts);
        for (List<String> ele : ret) {
            for (String e : ele) {
                System.out.print(e + " ");
            }
            System.out.println();
            System.out.println();
        }
    }
}

public class LC0721_AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ret = new ArrayList<>();
        AccountUnion accountUnion = new AccountUnion(accounts.size());
        Map<String, Integer> accountToUser = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                if (accountToUser.get(email) == null) {
                    accountToUser.put(email, i);
                } else {
                    if (!accountUnion.find(i, accountToUser.get(email))) {
                        System.out.println(email + " " + i);
                        accountUnion.union(i, accountToUser.get(email));
                    }
                }
            }
        }
        int[] parent = accountUnion.parents;
        Map<Integer, TreeSet<String>> map = new TreeMap<>();
        for (int i = 0; i < parent.length; i++) {
            int p = accountUnion.getRoot(parent[i]);
            map.computeIfAbsent(p, k -> new TreeSet<>());
            for (int k = 1; k < accounts.get(i).size(); k++) {
                map.get(p).add(accounts.get(i).get(k));
            }
        }
        for (Map.Entry<Integer, TreeSet<String>> entry : map.entrySet()) {
            List<String> list = new ArrayList<>();
            int index = entry.getKey();
            String user = accounts.get(index).get(0);
            list.add(user);
            list.addAll(entry.getValue());
            ret.add(list);
        }
        return ret;
    }
}
