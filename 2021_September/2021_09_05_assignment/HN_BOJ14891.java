package com.baekjoon.problem12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 굳이 비트 마스킹이 아니라 boolean으로 푸는게 훨씬 간단했을지도
public class HN_BOJ14891 {
	static final int FIRST = 0, LEFT = 6, RIGHT = 2, LAST = 7;
	static int[] wheel;
	static int K, answer;
	static boolean visited[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		wheel = new int[4];
		
		for (int w = 0; w < 4; w++) {
			char[] tmp = br.readLine().toCharArray();
			for (int t = 0; t < tmp.length; t++) {
				if (tmp[t] == '1') {
					wheel[w] |= (1 << t);
				}
			}
		}

		K = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			visited = new boolean[4];
			rotation(idx - 1, dir);
		}
		
		for (int w = 0; w < 4; w++) {
			if( (wheel[w] & (1<<FIRST)) == 1) 
				answer += 1<<w;
		}
		
		System.out.println(answer);
	}

	private static void rotation(int idx, int dir) {
		if (idx < 0 || idx > 3) {
			return;
		}
		
		visited[idx] = true;
		
		int lv1 = 0, lv2 = 0;
		int rv1 = 0, rv2 = 0;

		if (idx != 0 && !visited[idx-1]) {
			// 왼쪽 확인
			lv1 = (wheel[idx - 1] & (1 << RIGHT)) >> RIGHT;
			lv2 = (wheel[idx] & (1 << LEFT)) >> LEFT;

			if (lv1 != lv2) {
				rotation(idx - 1, dir * -1);
			}
		}

		if (idx != 3 && !visited[idx+1]) {
			// 오른쪽 확인
			rv1 = (wheel[idx] & (1 << RIGHT)) >> RIGHT;
			rv2 = (wheel[idx + 1] & (1 << LEFT)) >> LEFT;

			if (rv1 != rv2) {
				rotation(idx + 1, dir * -1);
			}
		}

		// 내 체인 회전
		if (dir == 1) {
			// 시계방향
			int tmp = (wheel[idx] & (1 << LAST)) >> LAST;
			wheel[idx] = (tmp == 1 ? ((wheel[idx] << 1) | tmp) - (1 << (LAST + 1)) : (wheel[idx] << 1));
		} else {
			// 반시계방향
			int tmp = wheel[idx] & 1<<FIRST;
			wheel[idx] = (tmp == 1 ? ((wheel[idx]>>1) + (1<<LAST )) : (wheel[idx] >> 1));
		}

	}

}
