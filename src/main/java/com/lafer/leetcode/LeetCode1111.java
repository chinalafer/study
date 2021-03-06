package com.lafer.leetcode;

/**
 *
 * 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
 *
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 *
 * 有效括号字符串类型与对应的嵌套深度计算方法如下图所示：
 *
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
 *
 * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
 * A 或 B 中的元素在原字符串中可以不连续。
 * A.length + B.length = seq.length
 * 深度最小：max(depth(A), depth(B)) 的可能取值最小。 
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
 *
 * answer[i] = 0，seq[i] 分给 A 。
 * answer[i] = 1，seq[i] 分给 B 。
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
 *
 *
 * 示例 1：
 *
 * 输入：seq = "(()())"
 * 输出：[0,1,1,1,1,0]
 * 示例 2：
 *
 * 输入：seq = "()(())()"
 * 输出：[0,0,0,1,1,0,1,1]
 * 解释：本示例答案不唯一。
 * 按此输出 A = "()()", B = "()()", max(depth(A), depth(B)) = 1，它们的深度最小。
 * 像 [1,1,1,0,0,1,1,1]，也是正确结果，其中 A = "()()()", B = "()", max(depth(A), depth(B)) = 1 。
 *  
 *
 * 提示：
 *
 * 1 < seq.size <= 10000
 *  
 *
 * 有效括号字符串：
 *
 * 仅由 "(" 和 ")" 构成的字符串，对于每个左括号，都能找到与之对应的右括号，反之亦然。
 * 下述几种情况同样属于有效括号字符串：
 *
 *   1. 空字符串
 *   2. 连接，可以记作 AB（A 与 B 连接），其中 A 和 B 都是有效括号字符串
 *   3. 嵌套，可以记作 (A)，其中 A 是有效括号字符串
 * 嵌套深度：
 *
 * 类似地，我们可以定义任意有效括号字符串 s 的 嵌套深度 depth(S)：
 *
 *   1. s 为空时，depth("") = 0
 *   2. s 为 A 与 B 连接时，depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是有效括号字符串
 *   3. s 为嵌套情况，depth("(" + A + ")") = 1 + depth(A)，其中 A 是有效括号字符串
 *
 * 例如：""，"()()"，和 "()(()())" 都是有效括号字符串，嵌套深度分别为 0，1，2，而 ")(" 和 "(()" 都不是有效括号字符串。
 *
 * 思考：如果直接求depth(A)，可以直接使用栈的思路进行求解，将“（”入栈，遇到“）”出栈，记录栈的最大深度就是depth（A）的结果
 * 题目是要我分成两个字符串然后是这两个字符串A，B的depth最小。上面的思路直接排除。对于所有“（”要么属于A要么属于B，直接使用“（”、“）”的数量的奇偶性来做筛选即可。
 *
 * 题解给出一个很好理解的思路：
 * 要求划分出使得最大嵌套深度最小的分组，我们首先得知道如何计算嵌套深度。我们可以通过栈实现括号匹配来计算：
 *
 * 维护一个栈 s，从左至右遍历括号字符串中的每一个字符：
 *
 * 如果当前字符是 (，就把 ( 压入栈中，此时这个 ( 的嵌套深度为栈的高度；
 *
 * 如果当前字符是 )，此时这个 ) 的嵌套深度为栈的高度，随后再从栈中弹出一个 (。
 *
 * 下面给出了括号序列 (()(())()) 在每一个字符处的嵌套深度：
 *
 * 括号序列   ( ( ) ( ( ) ) ( ) )
 * 下标编号   0 1 2 3 4 5 6 7 8 9
 * 嵌套深度   1 2 2 2 3 3 2 2 2 1
 * 知道如何计算嵌套深度，问题就很简单了：只要在遍历过程中，我们保证栈内一半的括号属于序列 A，
 * 一半的括号属于序列 B，那么就能保证拆分后最大的嵌套深度最小，是当前最大嵌套深度的一半。
 * 要实现这样的对半分配，我们只需要把奇数层的 ( 分配给 A，偶数层的 ( 分配给 B 即可。
 * 对于上面的例子，我们将嵌套深度为 1 和 3 的所有括号 (()) 分配给 A，嵌套深度为 2 的所有括号 ()()() 分配给 B。
 *
 *
 */

public class LeetCode1111 {

    public int[] maxDepthAfterSplit(String seq) {
        char[] chars = seq.toCharArray();
        int[] ans = new int[seq.length()];
        int countz = 0, county = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                ans[i] = 1 & countz++;
            } else {
                ans[i] = 1 & county++;
            }
        }
        return ans;
    }

    public int[] maxDepthAfterSplit2(String seq) {
        char[] chars = seq.toCharArray();
        int[] ans = new int[seq.length()];
        int depth = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                depth++;
                ans[i] = depth & 1;
            } else {
                ans[i] = depth & 1;
                depth--;
            }
        }
        return ans;
    }

}
