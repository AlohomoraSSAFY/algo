package com.baekjoon.problem55;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_BOJ17140 {
	static final int LIMIT = 100;
	static int R, C, K, Rcnt, Ccnt, answer;
	static int map[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		Rcnt = Ccnt = 3;
		map = new int[LIMIT][LIMIT];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (answer <= LIMIT) {
			if (map[R - 1][C - 1] == K)
				break;
			answer++;
			int cmap[][] = new int[LIMIT][LIMIT];
			if (Rcnt >= Ccnt) {
				// R 연산
				Ccnt = 0;
				for (int r = 0; r < Rcnt; r++) {
					Map<Integer, Integer> hashMap = new HashMap<>();
					for (int c = 0; c < LIMIT; c++) {
						if (map[r][c] == 0) {
							continue;
						}
						int cnt = hashMap.getOrDefault(map[r][c], 0);
						hashMap.put(map[r][c], cnt + 1);
					}
					//
					PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
						if (i1[1] == i2[1]) {
							return Integer.compare(i1[0], i2[0]);
						}
						return Integer.compare(i1[1], i2[1]);
					});
					for (int i : hashMap.keySet()) {
						pq.add(new int[] { i, hashMap.get(i) });
					}
					int k = 0;
					while (!pq.isEmpty() && k < LIMIT) {
						int[] temp = pq.poll();
						cmap[r][k++] = temp[0];
						cmap[r][k++] = temp[1];
					}
					Ccnt = Math.max(Ccnt, k);
				}
			} else {
				// C 연산
				Rcnt = 0;
				for (int r = 0; r < LIMIT; r++) {
					Map<Integer, Integer> hashMap = new HashMap<>();
					for (int c = 0; c < Ccnt; c++) {
						if (map[c][r] == 0) {
							continue;
						}
						int cnt = hashMap.getOrDefault(map[c][r], 0);
						hashMap.put(map[c][r], cnt + 1);
					}
					//
					PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
						if (i1[1] == i2[1]) {
							return Integer.compare(i1[0], i2[0]);
						}
						return Integer.compare(i1[1], i2[1]);
					});

					for (int i : hashMap.keySet()) {
						pq.add(new int[] { i, hashMap.get(i) });
					}

					int k = 0;
					while (!pq.isEmpty() && k < LIMIT) {
						int[] temp = pq.poll();
						cmap[k++][r] = temp[0];
						cmap[k++][r] = temp[1];
					}
					Rcnt = Math.max(Rcnt, k);
				}
			} // end if

			// cmap을 map으로 이동
			for (int i = 0; i < LIMIT; i++) {
				map[i] = cmap[i].clone();
			}

		}
		System.out.println(answer > LIMIT ? -1 : answer);
	}

}
