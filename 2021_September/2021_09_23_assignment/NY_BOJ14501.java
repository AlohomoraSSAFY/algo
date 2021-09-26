package date0926SUN;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ14501 {
	static int n;
	static int schedule[][];

	static int dp[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		schedule = new int[n + 2][2];
		dp = new int[n + 2];
		int answer = 0;
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}

		//dp[i] : i번째 일까지 일할 수 있다고 했을 때 얻을 수 있는 최대값
		for (int i = 1; i<=n+1; i++) { // i일에 //4
			for (int j = 1; j < i; j++) { // j번째 스케줄 확인
				if (schedule[j][0] + j <= i) { // 시작날짜 +소요기간 합쳐서 현재 일 안이면 선택 가능
					dp[i] = Math.max(dp[i], dp[j] + schedule[j][1]);
				}
			}
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);

	}

}
