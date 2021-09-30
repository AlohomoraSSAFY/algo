package com.baekjoon.problem18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN_BOJ1062 {
	static char word[][];
	static boolean teach[];
	static int N, K, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		word = new char[N][];

		teach = new boolean[26];
		for (int i = 0; i < N; i++) {
			word[i] = br.readLine().toCharArray();
		}

		answer = 0;
		if (K >= 5) {
			teach['a' - 'a'] = teach['c' - 'a'] = teach['t' - 'a'] = teach['n' - 'a'] = teach['i' - 'a'] = true;
			combi(0, 0);
		}

		System.out.println(answer);
	}

	private static void combi(int cnt, int start) {
		// 현재까지 가르친 글자수 + 남은 글자수가 가르쳐야하는 문자수보다 작으면 return
		if (start + (K - 5 - cnt) > 26) {
			return;
		}

		if (cnt == K - 5) {
			// K만큼 다 가르침
			answer = Math.max(count(), answer);
			return;
		}

		for (int i = start; i < 26; i++) {
			if (teach[i])
				continue;
			teach[i] = true;
			combi(cnt + 1, i + 1);
			teach[i] = false;
		}
	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			boolean read = true;
			for (int j = 4; j < word[i].length - 4; j++) {
				int idx = word[i][j] - 'a';
				if (!teach[idx]) {
					read = false;
				}
			}

			if (read) {
				cnt++;
			}
		}
		return cnt;
	}

}
