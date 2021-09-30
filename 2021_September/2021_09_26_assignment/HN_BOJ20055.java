package com.baekjoon.problem18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class HN_BOJ20055 {
	static class Robot {
		int location;

		public Robot(int location) {
			this.location = location;
		}
	}

	static int[] Conveyer;
	static boolean[] Robot;
	// static List<Robot> r = new LinkedList<>();
	static int N, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Conveyer = new int[2 * N + 1];
		Robot = new boolean[2 * N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N * 2; i++) {
			Conveyer[i] = Integer.parseInt(st.nextToken());
		}

		int idxN = N, idx1 = 1, idx2N = 2 * N;
		int level = 0;

		boolean cp = check();
		if (!cp) {
			System.out.println(1);
		} else {
			while (check()) {
				level++;

				// 1. Conveyer 회전
				if (--idxN == 0) {
					idxN = idx2N;
				}
				if (--idx1 == 0) {
					idx1 = idx2N;
				}
				// 2. 로봇 내리기
				if (Robot[idxN]) {
					Robot[idxN] = false;
				}

				// 3. 로봇만 따로 옮기기
				int idx;
				for (int i = 1; i < 2*N; i++) {
					if (idxN - i <= 0) {
						idx = idx2N + idxN - i;
					} else {
						idx = idxN - i;
					}

					int next = idx + 1;
					if (next > idx2N) {
						next = 1;
					}

					if (Robot[idx] && !Robot[next] && Conveyer[next] > 0) {
						Robot[idx] = false;
						Conveyer[next]--;
						if (next != idxN) {
							Robot[next] = true;
						}
					}
				}

				// 4. 로봇 올리기
				if (!Robot[idx1] && Conveyer[idx1] > 0) {
					Robot[idx1] = true;
					Conveyer[idx1]--;
				}
			}

			System.out.println(level);
		}
	}

	private static boolean check() {
		// K개 이상이면 확인하고 넘김
		int cnt = 0;
		for (int i = 1; i <= 2 * N; i++) {
			if (Conveyer[i] <= 0) {
				cnt++;
				if (cnt == K) {
					return false;
				}
			}
		}
		return true;
	}

}
