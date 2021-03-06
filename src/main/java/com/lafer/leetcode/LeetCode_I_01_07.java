package com.lafer.leetcode;

/**
 *
 * 面试题 01.07. 旋转矩阵
 *
 * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
 *
 * 不占用额外内存空间能否做到？
 *
 * 思考：
 *  不使用额外的内存空间，旋转N*N的矩阵，只需要对每个元素的位置进行变化即可，注意变化的规则以及记忆被覆盖的那个数
 *  对每个元素（i，j），它都将被（n - j - 1, i）的元素所替换，那么针对一个元素，只要是他被替换即可，但是如果按照
 *  顺序进行替换，肯定会发出错乱，我们要保证替换真确性，只需要针对矩阵的1/4个角落进行替换三次即可。
 *
 *  题解中提到的翻转代替旋转的方法：
 *
 *  我们还可以另辟蹊径，用翻转操作代替旋转操作。我们还是以题目中的示例二
 *
 *  5  1  9 11
 *  2  4  8 10
 * 13  3  6  7
 * 15 14 12 16
 * 作为例子，先将其通过水平轴翻转得到：
 *
 *  5  1  9 11                 15 14 12 16
 *  2  4  8 10                 13  3  6  7
 * ------------   =水平翻转=>   ------------
 * 13  3  6  7                  2  4  8 10
 * 15 14 12 16                  5  1  9 11
 * 再根据主对角线 \backslash\ 翻转得到：
 *
 * 15 14 12 16                   15 13  2  5
 * 13  3  6  7   =主对角线翻转=>   14  3  4  1
 *  2  4  8 10                   12  6  8  9
 *  5  1  9 11                   16  7 10 11
 * 就得到了答案。这是为什么呢？对于水平轴翻转而言，我们只需要枚举矩阵上半部分的元素，和下半部分的元素进行交换，即
 *
 * matrix[row][col] = matrix[n−row−1][col] = matrix[col][n−row−1]
 *
 * 其实推出来的公式是一样的
 *
 */

public class LeetCode_I_01_07 {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n >> 1; i++) {
            for (int j = 0; j < (n  + 1) >> 1; j++) {
                int temp = matrix[i][j];
//                matrix[i][j] = matrix[n - j - 1][i];
//                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
//                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
//                matrix[j][n - i - 1] = temp;
                int c = 3, x = i, y = j;
                while (c-- > 0) {
                    matrix[x][j] = matrix[n - y - 1][x];
                    x = n - y - 1;
                    y = x;
                }
                matrix[x][y] = temp;
            }
        }
    }

}
