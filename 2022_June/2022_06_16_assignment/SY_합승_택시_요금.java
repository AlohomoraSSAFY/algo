package study0623;

import java.util.*;

public class SY_합승_택시_요금 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] array = new int[n+1][n+1];
        for (int i = 1; i < n+1; i++) {
            Arrays.fill(array[i], Integer.MAX_VALUE);
            array[i][i] = 0;
        }
        
        for (int i = 0; i < fares.length; i++) {
            int c = fares[i][0];
            int d = fares[i][1];
            int f = fares[i][2];
            
            array[c][d] = f;
            array[d][c] = f;
        }
        
        for (int k = 1; k < n+1; k++) {
            for (int i = 1; i < n+1; i++) {
                if (i == k) continue;
                for (int j = 1; j < n+1; j++) {
                    if (i == j || j == k) continue;
                    if (array[i][k] == Integer.MAX_VALUE || array[k][j] == Integer.MAX_VALUE) continue;
                    if (array[i][j] > array[i][k] + array[k][j]) {
                        array[i][j] = array[i][k] + array[k][j];
                    }
                }
            }
        }
        
        int answer = Integer.MAX_VALUE;
        for (int i = 1; i < n+1; i++) {
            int sum = array[s][i] + array[i][a] + array[i][b];
            answer = Math.min(answer, sum);
        }
        return answer;
    }
}
