package com.baekjoon.problem50;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HN_BOJ2668 {
	static int N, answer;
	static int input[], exist[];
	static boolean visited[], notExist[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1];
		exist = new int[N + 1];
		notExist = new boolean[N + 1];
		visited = new boolean[N + 1];
		for (int n = 1; n <= N; n++) {
			input[n] = Integer.parseInt(br.readLine());
			exist[input[n]]++;
		}
		int oldCnt = N;
		int cnt = 0;
		while (true) {
			for (int n = 1; n <= N; n++) {
				if (exist[n] != 0)
					continue;
				// 카운트가 0이면 해당 n은 존재하지 않는다.
				notExist[n] = true;
			}

			cnt = 0;
			for (int n = 1; n <= N; n++) {
				if (!notExist[n] || visited[n])
					continue;
				// 방문하지 않고 n이 존재하지 않는다면, 카운트 감소
				cnt++;
				visited[n] = true;
				exist[input[n]]--;
			}

			if (cnt == 0)
				break;
			// 최초 N개에서 삭제되는 n의 값을 빼준다.
			oldCnt -= cnt;
		}
		StringBuilder sb = new StringBuilder();
		sb.append(oldCnt).append("\n");
		for (int n = 1; n <= N; n++) {
			if (!notExist[n])
				sb.append(n).append("\n");
		}
		System.out.println(sb);
	}

}
