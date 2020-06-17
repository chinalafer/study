package com.lafer.leetcode.array;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * 378. 有序矩阵中第K小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 *
 *
 * 示例：
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n2 。
 *
 * 思考：
 * 1、使用大根堆
 * 2、二分查找
 *   使用值作为二分查找的上下界，使用矩阵左上角的值作为最小值left（左边界），使用矩阵右下角的值作为最大值right（右边界）
 *   每次统计数组中小于等于mid的数的数量count，如果count < k, 说明所求值肯定比mid大，left = mid + 1， 否则right = mid，所求职可能就等于mid
 *   如果left == right, 说明即为所求值。
 *
 */

public class LeetCode378 {

    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int[] num : matrix) {
            for (int i : num) {
                queue.add(i);
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }
        return queue.peek();
    }

    public int kthSmallest1(int[][] matrix, int k) {
        int m = matrix.length - 1, n = matrix[0].length - 1;
        int left = matrix[0][0], right = matrix[m][n];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int count = 0;
            for (int i = 0; i <= m; i++) {
                int l = n;
                while (l >= 0 && matrix[i][l] > mid) {
                    l--;
                }
                count += (l + 1);
            }
            if (count < k) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }

}
