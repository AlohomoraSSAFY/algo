package com.baekjoon.problem18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
// 나중에 다시 풀어보기
// 기저조건 오류로 일부 테스트케이스 실패... 반례 찾아봄

public class HN_BOJ5557_Success {
	static long answer;
	static long N, result, max;
	static long zCnt;
	static long[] input;
	static long[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		input = new long[(int) (N + 1)];

		for (int i = 1; i <= N - 1; i++) {
			long tmp = Long.parseLong(st.nextToken());
			input[(int) (i)] = tmp;
		}
		result = Integer.parseInt(st.nextToken());

		max = N - 1;
		dp = new long[(int) (max + 1)][21];
		for (int i = 0; i <= max; i++) {
			Arrays.fill(dp[i], -1);
		}

		subset2(max, result);

		answer = dp[(int) max][(int) result];
		System.out.println(answer);
	}

	private static long subset2(long cnt, long sum) {
		dp[(int) cnt][(int) sum] = 0;

		if (cnt == 1 && sum == input[(int) cnt]) {
			return 1;
		}
		if(cnt == 1 && sum != input[(int) cnt]) {
			return 0;
		}

		long sum1 = sum + input[(int) cnt];
		if (sum1 <= 20 && sum1 >= 0) {
			if (dp[(int) (cnt - 1)][(int) sum1] == -1) {
				dp[(int) (cnt - 1)][(int) sum1] = subset2(cnt - 1, sum1);
			}
			dp[(int) cnt][(int) sum] += dp[(int) (cnt - 1)][(int) sum1];
		}
		long sum2 = sum - input[(int) cnt];
		if (sum2 >= 0 && sum2 <= 20) {
			if (dp[(int) (cnt - 1)][(int) sum2] == -1) {
				dp[(int) (cnt - 1)][(int) sum2] = subset2(cnt - 1, sum2);
			}
			dp[(int) cnt][(int) sum] += dp[(int) (cnt - 1)][(int) sum2];
		}

		return dp[(int) cnt][(int) sum];
	}
}
