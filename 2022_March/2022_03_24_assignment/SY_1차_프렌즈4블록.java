package study0331;

import java.util.*;

public class SY_1차_프렌즈4블록 {
    
    char[][] array;
    int answer;
    
    public int solution(int m, int n, String[] board) {
        array = new char[board.length][board[0].length()];
        for (int i = 0; i < board.length; i++) {
            String str = board[i];
            for (int j = 0; j < str.length(); j++) {
                array[i][j] = str.charAt(j);
            }
        }
        
        boolean flag = true;
        while (flag) {
            flag = remove();
        }
        
        return answer;
    }
    
    private boolean remove() {
        boolean flag = false;
        boolean[][] check = new boolean[array.length][array[0].length];
        
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array[i].length - 1; j++) {
                char temp = array[i][j];
                if (temp == '0') continue;
                if (temp == array[i+1][j] && temp == array[i][j+1] && temp == array[i+1][j+1]) {
                    flag = true;
                    check[i][j] = true;
                    check[i+1][j] = true;
                    check[i][j+1] = true;
                    check[i+1][j+1] = true;
                }
            }
        }
        
        for (int i = 0; i < array[0].length; i++) {
            Queue<Character> q = new LinkedList<>();
            for (int j = array.length - 1; j >= 0; j--) {
                if (!check[j][i]) {
                    q.offer(array[j][i]);
                } else {
                    answer++;
                }
            }
            
            for (int j = array.length - 1; j >= 0; j--) {
                if (!q.isEmpty()) {
                    array[j][i] = q.poll();
                } else {
                    array[j][i] = '0';
                }
            }
        }
        
        return flag;
    }
}
