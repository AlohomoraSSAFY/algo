package study1226;

public class SY_등굣길 {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m+1][n+1];
        dp[1][1] = 1;
        
        for (int i = 0; i < puddles.length; i++) {
            int r = puddles[i][0];
            int c = puddles[i][1];
            dp[r][c] = -1;
        }
        
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (i == 1 && j == 1) continue;
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                
                dp[i][j] = (dp[i][j-1] + dp[i-1][j]) % 1000000007;
            }
        }
        
        return dp[m][n];
    }
}
