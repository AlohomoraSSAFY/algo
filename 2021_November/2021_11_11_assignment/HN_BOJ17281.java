package com.baekjoon.problem32;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HN_BOJ17281 {
	static final int NINE = 9, FOUR = 4;
	static int N, answer;
	static int score[][];
	static int selected[] = new int[NINE];
	static boolean check[] = new boolean[NINE];
	static boolean exist[] = new boolean[FOUR];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N][NINE];
		StringTokenizer st;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < NINE; w++) {
				score[n][w] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		
		// 주자 순서를 정한다.
		Permu(0);
		
		System.out.println(answer);
	}
	private static void Permu(int cnt) {
		if(cnt == NINE) {
			playGame();
			return;
		}
		
		// 4번 타자는 첫번째 선수(0)임
		if(cnt == FOUR-1) {
			Permu(cnt+1);
			return;
		}
		
		for (int i = 1; i < NINE; i++) {
			if(check[i]) continue;
			check[i] = true;
			selected[cnt] = i;
			Permu(cnt+1);
			check[i] = false;
		}
		
	}
	
	private static void playGame() {
		int round = 0, cur = 0, result = 0, out = 0;
		
		while(round < N) {
			int now = selected[cur];
			if(score[round][now] == 0) {
				// out
				if(++out == 3) {
					out = 0;
					round++;
					Arrays.fill(exist, false);
				}
			}else {
				// 타자가 진출해야하므로.
				exist[0] = true;
				for (int e = FOUR-1; e >= 0; e--) {
					int next = e + score[round][now];
					if(exist[e]) {
						exist[e] = false;
						if(next >= 4) {
							result++;
						}else {
							exist[next] = true;
						}
					}
				}
			}
			
			if(++cur == NINE)
				cur = 0;
		}
		
		answer = Math.max(answer, result);
	}

}
