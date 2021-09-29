package study0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ5557 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		long[][] dp = new long[N-1][21];
		dp[0][array[0]] = 1;
		
		for (int i = 1; i < N-1; i++) {
			for (int j = 0; j < 21; j++) {
				if (dp[i-1][j] > 0) {
					int temp1 = j + array[i];
					if (temp1 <= 20) dp[i][temp1] += dp[i-1][j];
					
					int temp2 = j - array[i];
					if (temp2 >= 0) dp[i][temp2] += dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[N-2][array[N-1]]);
	}

}
