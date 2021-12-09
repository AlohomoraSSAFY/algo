package com.baekjoon.problem37;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ21610 {
	static int N, M, answer;
	static int map[][];
	static int move[][];
	static int dx[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		/*
		 * 구름을 q로 저장한다. 구름을 이동시킨다. -> 경계를 넘어가면 -처리 해주기. 이동 후 물증가/ boolean 체크 여기서 체크한 구역을
		 * 다시 구름 생성 x 물복사 버그 시전. 물이 2개 이상인 모든칸에 구름 생성 단, 불린 체크 확인
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int n1 = 0; n1 < N; n1++) {
				map[n][n1] = Integer.parseInt(st.nextToken());
			}
		}
		q = new LinkedList<>();
		q.add(new int[] { N - 1, 0 });
		q.add(new int[] { N - 1, 1 });
		q.add(new int[] { N - 2, 0 });
		q.add(new int[] { N - 2, 1 });

		move = new int[M][2];
		int d, s;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			move[m][0] = d;
			move[m][1] = s;
		}

		answer = 0;

		magic();

		System.out.println(answer);
	}

	private static void magic() {
		int round = 0;
		int d, s, size;
		boolean cloud[][];
		Queue<int[]> cq = new LinkedList<>();
		while (round < M) {
			cloud = new boolean[N][N];
			d = move[round][0];
			s = move[round][1];
			while (!q.isEmpty()) {
				int tmp[] = q.poll();
				int ny = tmp[0] + dy[d] * s;
				int nx = tmp[1] + dx[d] * s;

				while(ny >= N) {
					ny -= N;
				}
				while(ny < 0) {
					ny += N;
				}
				while(nx >= N) {
					nx -= N;
				}
				while(nx < 0) {
					nx += N;
				}
				map[ny][nx]++;
				cloud[ny][nx] = true;
				cq.add(new int[] { ny, nx });
			}

			// 물복사 버그 시작
			size = cq.size();
			while (size-- > 0) {
				int tmp[] = cq.poll();
				int cnt = 0;
				for (int i = 2; i <= 8; i = i + 2) {
					int ny = tmp[0] + dy[i];
					int nx = tmp[1] + dx[i];

					if (bound(ny, nx) && map[ny][nx] != 0) {
						cnt++;
					}

				}
				if (cnt != 0)
					cq.add(new int[] { tmp[0], tmp[1], cnt });
			}

			size = cq.size();
			while (size-- > 0) {
				int tmp[] = cq.poll();
				map[tmp[0]][tmp[1]] += tmp[2];
			}

			// 모든칸에 구름 생성
			for (int n = 0; n < N; n++) {
				for (int n1 = 0; n1 < N; n1++) {
					if (map[n][n1] >= 2 && !cloud[n][n1]) {
						map[n][n1] -= 2;
						q.add(new int[] { n, n1 });
					}
				}
			}

			round++;
		}

		for (int n = 0; n < N; n++) {
			for (int n1 = 0; n1 < N; n1++) {
				answer += map[n][n1];
			}
		}
	}

	private static boolean bound(int ny, int nx) {
		if (ny >= N || nx >= N || ny < 0 || nx < 0)
			return false;
		return true;
	}

}
