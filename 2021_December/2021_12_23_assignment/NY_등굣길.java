package date1226SUN;

public class 프로그래머스_등굣길 {

	 public int solution(int m, int n, int[][] puddles) {
	        int answer = 0;
	        int plen = puddles.length;
	        boolean map[][] = new boolean[n+1][m+1];
	        long dp[][] = new long[n+1][m+1];
	        
	        for(int i=0;i<plen;i++){
	            map[puddles[i][1]][puddles[i][0]] = true;
	        }
	        
	        dp[1][1]=1;
	        
	        for(int i=1;i<=n;i++){
	            for(int j=1;j<=m;j++){
	                if(!map[i][j]){ //웅덩이가 아니면
	                    dp[i][j] += (dp[i-1][j] + dp[i][j-1])% 1000000007;
	                }
	            }
	        }
	        
	        
	        return (int)(dp[n][m] % 1000000007);
	    }

}
