package com.lafer.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode637 {

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        Queue<TreeNode> tempQueue1 = new LinkedList<>();
        Queue<TreeNode> tempQueue2 = new LinkedList<>();
        Queue<TreeNode> temp = null;
        if (root == null) {
            return result;
        }
        tempQueue1.add(root);
        while (!tempQueue1.isEmpty()) {
            Double tempResult = Double.valueOf(0);
            int n = 0;
            while (!tempQueue1.isEmpty()) {
                TreeNode node = tempQueue1.poll();
                if (node.left != null) {
                    tempQueue2.add(node.left);
                }
                if (node.right != null) {
                    tempQueue2.add(node.right);
                }
                tempResult += node.val;
                n++;
            }
            result.add(tempResult / n);
            //交换tempQueue1 与 tempQueue2
            temp = tempQueue1;
            tempQueue1 = tempQueue2;
            tempQueue2 = temp;
        }
        return result;
    }

}
