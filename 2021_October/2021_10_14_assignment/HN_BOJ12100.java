package com.baekjoon.problem24;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ12100 {
	static final int MAX = 5;
	static int N, answer;
	static int board[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		board = new int[N][N];

		for (int h = 0; h < N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				board[h][w] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;

		Permu(0, board);

		System.out.println(answer);
	}

	private static void Permu(int cnt, int[][] board) {
		if (cnt == MAX) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nboard[][] = game(i, board);
			boolean change = check(board, nboard);
			
			if (!change) {
				// 해당 방향으로 더 진행 안해도 괜찮음
				continue;
			}
			Permu(cnt + 1, nboard);
		}
	}

	private static boolean check(int[][] ob, int[][] nb) {
		boolean change = false;
		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (ob[i][j] != nb[i][j])
					change = true;
				answer = Math.max(answer, nb[i][j]);
			}
		}
		
		return change;
	}

	private static int[][] game(int sel, int[][] board) {
		int nboard[][] = new int[N][N];

		switch (sel) {
		case 0: // 보드 위에서부터 아래 탐색
			for (int w = 0; w < N; w++) {
				Queue<Integer> q = new LinkedList<>();
				for (int h = 0; h < N; h++) {
					if (board[h][w] != 0) {
						q.add(board[h][w]);
					}
				}
				int h = 0;
				while (!q.isEmpty()) {
					int data = q.poll();

					if (q.peek() != null && q.peek() == data) {
						data += q.poll();
					}

					nboard[h++][w] = data;
				}
			}
			break;
		case 1: // 보드 오른쪽에서부터 왼쪽 탐색
			for (int h = 0; h < N; h++) {
				Queue<Integer> q = new LinkedList<>();
				for (int w = N - 1; w >= 0; w--) {
					if (board[h][w] != 0) {
						q.add(board[h][w]);
					}
				}
				int w = N - 1;
				while (!q.isEmpty()) {
					int data = q.poll();

					if (q.peek() != null && q.peek() == data) {
						data += q.poll();
					}

					nboard[h][w--] = data;
				}
			}
			break;
		case 2: // 보드 아래에서부터 위쪽 탐색
			for (int w = 0; w < N; w++) {
				Queue<Integer> q = new LinkedList<>();
				for (int h = N - 1; h >= 0; h--) {
					if (board[h][w] != 0) {
						q.add(board[h][w]);
					}
				}
				int h = N - 1;
				while (!q.isEmpty()) {
					int data = q.poll();

					if (q.peek() != null && q.peek() == data) {
						data += q.poll();
					}

					nboard[h--][w] = data;
				}
			}
			break;
		case 3: // 보드 왼쪽에서부터 오른쪽 탐색
			for (int h = 0; h < N; h++) {
				Queue<Integer> q = new LinkedList<>();
				for (int w = 0; w < N; w++) {
					if (board[h][w] != 0) {
						q.add(board[h][w]);
					}
				}
				int w = 0;
				while (!q.isEmpty()) {
					int data = q.poll();

					if (q.peek() != null && q.peek() == data) {
						data += q.poll();
					}

					nboard[h][w++] = data;
				}
			}
			break;
		}// switch

		return nboard;
	}
}