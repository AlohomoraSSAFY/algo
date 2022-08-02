package date0726;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1757 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];
		for (int i = 0; i < n; i++) {
			arr[i + 1] = Integer.parseInt(br.readLine());
		}

		int dp[][] = new int[n + 1][m + 1];
		//dp[i][j] == i분에 지침지수가 j일 때 갈 수 있는 최대 거리
		for (int i = 1; i <= n; i++) {
			//i번째에 쉰 경우
			dp[i][0] = dp[i - 1][0];		
			
			//i번째에 달린 경우
			for(int j=1 ; j<=m;j++) {
				dp[i][j] = dp[i-1][j-1]+arr[i];
			}
			
			//지침지수가 0으로 끝나는 경우의 최댓값을 저장
			
			for(int j=1; j<=m && i>j;j++) {
				dp[i][0] = Math.max(dp[i][0], dp[i-j][j]);
			}
		}
		
		System.out.println(dp[n][0]);
	}

}
