package date0923THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class BOJ11048 {
	static int n, m;
	static int map[][];
	static int dx[] = { 0, 1, 1 };
	static int dy[] = { 1, 0, 1 };
	static int dp[][];
	static int max;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//

		max = 0;

		dp[0][0] = map[0][0];
		for (int i = 1; i < n; i++) {
			dp[i][0] = map[i][0] + dp[i - 1][0];
		}
		for (int i = 1; i < m; i++) {
			dp[0][i] = map[0][i] + dp[0][i - 1];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				
				int mm  = Math.max(dp[i - 1][j - 1], dp[i][j - 1]);
				mm  = Math.max(dp[i - 1][j], mm);
				dp[i][j] = mm+ map[i][j];
			}
		}
		
		System.out.println(dp[n - 1][m - 1]);
	}


}
