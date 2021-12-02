package date1202THU;

import java.io.*;
import java.util.*;

public class BOJ1912 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[n];
		int dp[] = new int[n];

		int answer = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//

		dp[0] = arr[0];
		
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
		}

		for (int i = 0; i < n; i++) {
			answer = Math.max(answer, dp[i]);
		}


		System.out.println(answer);
	}

}
