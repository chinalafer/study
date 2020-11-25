package com.lafer.leetcode.hashtable;

import java.util.*;

public class LeetCode381 {

    HashMap<Integer, HashSet<Integer>> indexMap;
    List<Integer> valList;

    /** Initialize your data structure here. */
    public LeetCode381() {
        indexMap = new HashMap<>();
        valList = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean addList = valList.add(val);
        if (!addList) {
            return false;
        }
        HashSet<Integer> index = indexMap.get(val);
        boolean ans = false;
        if (index == null) {
            index = new HashSet<>();
            ans = true;
        }
        boolean addSet = index.add(valList.size() - 1);
        if (!addSet) {
            valList.remove(valList.size() - 1);
            return false;
        }
        indexMap.put(val, index);
        return ans;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        HashSet<Integer> index = indexMap.get(val);
        if (index == null) {
            return false;
        }
        Iterator<Integer> iterator = index.iterator();
        if (!iterator.hasNext()) {
            return false;
        }
        Integer next = iterator.next();
        boolean remove = index.remove(next);
        if (!remove) {
            return false;
        }
        valList.set(next, valList.get(valList.size() - 1));
        valList.remove(valList.size() - 1);
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        Random random = new Random();
        int i = random.nextInt(valList.size());
        return valList.get(i);
    }

}
