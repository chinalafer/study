package com.lafer.leetcode;

/**
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 *
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 *
 * 给出两个矩形，判断它们是否重叠并返回结果。
 *
 * 思考： 区域相交问题转换成x轴、y轴两个维度的线段是否相交问题，两个矩阵相交等价于x轴上的两条线段相交并且y轴上的两条线段相交。
 *
 */
public class LeetCode836 {

    public static void main(String[] args) {

    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        if ( !((rec1[0] >= rec2[2] || rec2[0] >= rec1[2]) || (rec1[1] >= rec2[3] || rec2[1] >= rec1[3]))) {
            return true;
        } else {
            return false;
        }
    }
}
