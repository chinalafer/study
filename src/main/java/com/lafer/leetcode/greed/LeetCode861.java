package com.lafer.leetcode.greed;

public class LeetCode861 {

    /**
     * 861. 翻转矩阵后的得分
     * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
     *
     * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
     *
     * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
     *
     * 返回尽可能高的分数。
     *
     * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
     * 输出：39
     * 解释：
     * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
     * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
     *
     * 不难发现一点：为了得到最高的分数，矩阵的每一行的最左边的数都必须为 11。为了做到这一点，我们可以翻转那些最左边的数不为 11 的那些行，而其他的行则保持不动。
     *
     * 当将每一行的最左边的数都变为 11 之后，就只能进行列翻转了。为了使得总得分最大，我们要让每个列中 11 的数目尽可能多。因此，我们扫描除了最左边的列以外的每一列，
     * 如果该列 00 的数目多于 11 的数目，就翻转该列，其他的列则保持不变。
     *
     *
     */

    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length;
        int ret = m * (1 << (n - 1));
        for (int j = 1; j < n; j++) {
            int count1 = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 0) {
                    count1 += (1 - A[i][j]);
                } else {
                    count1 += A[i][j];
                }
            }
            count1 = Math.max(count1, m - count1);
            ret += (count1 * (1 << (n - 1 - j)));
        }
        return ret;
    }

}
