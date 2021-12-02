package study1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1912 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] array = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int result = array[0];
		int[] dp = new int[n];
		dp[0] = array[0];
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(0, dp[i-1]) + array[i];
			result = Math.max(result, dp[i]);
		}
		
		System.out.println(result);
	}

}
