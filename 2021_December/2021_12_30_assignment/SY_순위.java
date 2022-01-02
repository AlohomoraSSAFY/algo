package study0102;

public class SY_순위 {
    public int solution(int n, int[][] results) {
        boolean[][] check = new boolean[n+1][n+1];
        for (int i = 0; i < results.length; i++) {
            int A = results[i][0];
            int B = results[i][1];
            check[A][B] = true;
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (i == k) continue;
                for (int j = 1; j <= n; j++) {
                    if (i == j || j == k) continue;
                    if (check[i][k] && check[k][j]) check[i][j] = true;
                }
            }
        }
        
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            for (int j = 1; j <= n; j++) {
                if (check[i][j] || check[j][i]) count++;
            }
            
            if (count == n - 1) answer++;
        }
        
        return answer;
    }
}
