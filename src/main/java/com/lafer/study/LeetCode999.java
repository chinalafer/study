package com.lafer.study;

public class LeetCode999 {

    public int numRookCaptures(char[][] board) {
        char R = 'R', D = '.', B = 'B', p = 'p';
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (R == board[i][j]) {
                    int h = i, l = j;
                    for (int ii = h + 1; ii < board[h].length; ii++) {
                        if (board[ii][l] == p) {
                            count++;
                            break;
                        }
                        if (board[ii][l] == B) {
                            break;
                        }
                    }
                    for (int ii = h - 1; ii >= 0; ii--) {
                        if (board[ii][l] == p) {
                            count++;
                            break;
                        }
                        if (board[ii][l] == B) {
                            break;
                        }
                    }
                    for (int jj = l + 1; jj < board.length; jj++) {
                        if (board[h][jj] == p) {
                            count++;
                            break;
                        }
                        if (board[h][jj] == B) {
                            break;
                        }
                    }
                    for (int jj = l - 1; jj >= 0; jj--) {
                        if (board[h][jj] == p) {
                            count++;
                            break;
                        }
                        if (board[h][jj] == B) {
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

}
