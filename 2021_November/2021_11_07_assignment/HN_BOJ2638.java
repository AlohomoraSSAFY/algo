package com.baekjoon.problem31;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ2638 {
	static int N, M, answer;
	static int dx[] = {1, 0, -1, 0};
	static int dy[] = {0, -1, 0, 1};
	static int cheese[][];
	static int check[][];
	static boolean visited[][], remainCheese;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cheese = new int[N][M];
		remainCheese = false;
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				cheese[n][m] = Integer.parseInt(st.nextToken());
				if (cheese[n][m] == 1)
					remainCheese = true;
			}
		}

		check = new int[N][M];
		answer = 0;
		while (remainCheese) {
			answer++;
			
			countBfs();

			melting();

			remainCheese = checkRemain();
		}

		System.out.println(answer);
	}

	private static boolean checkRemain() {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (cheese[n][m] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	private static void melting() {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (check[n][m] >= 2) {
					cheese[n][m] = 0;
				}
				check[n][m] = 0;
			}
		}
	}

	private static void countBfs() {
		Queue<int []> q = new LinkedList<>();
		q.add(new int[] {0,0});
		visited = new boolean[N][M];
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int tmp[] = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int ny = tmp[0] + dy[i];
				int nx = tmp[1] + dx[i];
				
				if(check(ny, nx) && !visited[ny][nx]) {
					if(cheese[ny][nx] == 1) {
						check[ny][nx]++;
					}else {
						visited[ny][nx] = true;
						q.add(new int[] {ny, nx});
					}
				}// if
			}//for
		}// while
	}

	private static boolean check(int ny, int nx) {
		if(ny < 0 || ny >= N || nx < 0 || nx >=M)
			return false;
		return true;
	}

}
