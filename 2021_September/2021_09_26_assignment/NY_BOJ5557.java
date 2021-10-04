package date0930THU;

import java.io.*;
import java.util.*;

public class BOJ5557 {

	static int n;
	static int arr[];
	static long dp[][];
	static int k;
	static long answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 1; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dp = new long[21][n];
		k = Integer.parseInt(st.nextToken());
		answer = 0;
		// 왼쪽부터 계산할 때, 중간에 나오는 수가 모두 0 이상 20 이하

		dp[arr[1]][1] = 1;
		for (int i = 2; i < n; i++) { // i번째까지 쓸 수 있을 때
			for (int j = 0; j < 21; j++) {
				if (dp[j][i-1] != 0) {
					
					int cp = j + arr[i];
					int cm = j - arr[i];
					
					if (cp < 21)
						dp[cp][i]+=dp[j][i-1];
					if (cm >= 0)
						dp[cm][i]+=dp[j][i-1];
				}
			}
		}
		
		answer = dp[k][n - 1];

		System.out.println(answer);
	}

}
