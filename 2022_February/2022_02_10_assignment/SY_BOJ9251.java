package study0224;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ9251 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] array1 = br.readLine().toCharArray();
		char[] array2 = br.readLine().toCharArray();
		int[][] dp = new int[array1.length + 1][array2.length + 1];
		for (int i = 0; i < array1.length; i++) {
			for (int j = 0; j < array2.length; j++) {
				if (array1[i] == array2[j]) {
					dp[i+1][j+1] = dp[i][j] + 1;
				} else {
					dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
				}
			}
		}
		
		System.out.println(dp[array1.length][array2.length]);
	}

}
