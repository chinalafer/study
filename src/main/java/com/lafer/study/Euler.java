package com.lafer.study;

public class Euler {

    /**
     * 与n互质的质因子的个数（1~n-1） 直接求法
     *
     * @param n
     * @return
     */
    public static int euler(int n) {
        int res = n, a = n;
        for (int i = 2; i * i <= a; i++) {
            if (a % i == 0) {
                res = res / i * (i - 1);
                while (a % i == 0) {
                    a /= i;
                }
            }
        }
        if (a > 1) {
            res = res / a * (a - 1);
        }
        return res;
    }


    public static int[] p;

    /**
     * 与n互质的质因子的个数（1~n-1） 打表法
     * @param maxn
     */
    public static void phi(int maxn) {
        p[1] = 1;
        for (int i = 2; i < maxn; i++) {
            if (p[i] != 0) {
                for (int j = i; j <= maxn; j += i) {
                    if (p[j] != 0) {
                        p[j] = j;
                    }
                    p[j] = p[j] / i * (i - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            System.out.println("φ(" + i + ") = " + euler(i));
        }
    }

}
