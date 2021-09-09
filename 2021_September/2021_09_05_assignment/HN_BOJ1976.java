package com.baekjoon.problem12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1976 {
	static int N, M;
	static int[] route;
	static boolean[] visited;
	static boolean[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		map = new boolean[N + 1][N + 1];
		StringTokenizer st;
		for (int c = 1; c <= N; c++) {
			st = new StringTokenizer(br.readLine());
			for (int r = 1; r <= N; r++) {
				int tmp = Integer.parseInt(st.nextToken());
				if(tmp != 1) continue;
				map[c][r] = true;
				map[r][c] = true;
			}	
		}
		
		route = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) {
			route[m] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[N + 1];
		bfs(route[0]);
		
		boolean pass = true;
		for (int m = 0; m < M; m++) {
			if(!visited[route[m]]) {
				pass = false;
				break;
			}
		}
		
		System.out.println(pass? "YES" : "NO");
	}
	private static void bfs(int idx) {
		visited[idx] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(idx);
		
		while(!q.isEmpty()) {
			int s = q.size();
			
			for (int size = 0; size < s; size++) {
				int p = q.poll();
				
				for (int i = 1; i <= N; i++) {
					if(map[p][i] && !visited[i]) {
						visited[i] = true;
						q.add(i);
					}
				}
			}
		}
	}

}
