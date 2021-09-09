package com.baekjoon.problem12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// bfs 말고 dp로 풀고싶은데 dp를 쓰면 테케에서 틀렸다고 나오네...
public class HN_BOJ1189 {
	static int answer;
	static int R, C, K;
	static boolean visited[][];
	static char map[][];
	static int dp[][];
	static int[] dy = {-1, 0, 1, 0}; // 상, 우, 좌, 하
	static int[] dx = {0 , 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		visited = new boolean[R][C];
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		// 시작위치, 길이
		dijkstra(R-1, 0, 1);
		//dp = new int[R][C];
		//answer = dijkstra2(R-1, 0, 1);
		
		System.out.println(answer);
	}
	private static void dijkstra(int cr, int cc, int length) {
		if(length > K)
			return;
		
		if(cr == 0 && cc == C-1) {
			if(length == K) {
				answer++;
				return;
			}
		}
		
		visited[cr][cc] = true;
		
		for (int i = 0; i < dx.length; i++) {
			int nr = cr + dy[i];
			int nc = cc + dx[i];
			
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'T' || visited[nr][nc]) {
				continue;
			}
			visited[nr][nc] = true;
			dijkstra(nr,nc, length+1);
			visited[nr][nc] = false;
		}
		
	}
	
	private static int dijkstra2(int cr, int cc, int length) {
		if(length > K)
			return 0;
		
		if(cr == 0 && cc == C-1) {
			if(length == K) {
				return 1;
			}
		}
		
		visited[cr][cc] = true;
		
		for (int i = 0; i < dx.length; i++) {
			int nr = cr + dy[i];
			int nc = cc + dx[i];
			
			if(nr < 0 || nr >= R || nc < 0 || nc >= C || map[nr][nc] == 'T' || visited[nr][nc]) {
				continue;
			}
			
			if(dp[nr][nc] == 0) {
				visited[nr][nc] = true;
				dp[nr][nc] = dijkstra2(nr,nc, length+1);
				visited[nr][nc] = false;
				dp[cr][cc] += dp[nr][nc];
			}else {
				dp[cr][cc] += dp[nr][nc];
			}
			
		}
		
		return dp[cr][cc];
	}
}
