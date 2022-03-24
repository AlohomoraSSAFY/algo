package net.acmicpc.march.week4;

import java.io.*;
import java.util.*;


public class BOJ1937 {
	
	static int N;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int[][] map;
	static int[][] dp;
	static int level = 0;
	static int ans;
	
	private static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j <  N; j++) {
				System.out.printf("%3d ", dp[i][j]);
			}
			System.out.println();
		}
	}
	
	private static int dfs(int r, int c) {
//		System.out.println("---------("+r+", "+c+")");
//		printMap();
		int val = 0;
		for(int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
			if(map[r][c] < map[nr][nc] ) {
				if( dp[nr][nc] > 0) {
					val = Math.max(val, dp[nr][nc] );
				}else {
					val = Math.max(val, dfs(nr, nc));					
				}
			}
		}
		dp[r][c] = val+1;
		ans = Math.max(ans, val+1);
		return val+1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0 ; j <  N; j++) {
				if(dp[i][j]==0) {
					dp[i][j] = dfs(i, j);					
				}
			}
		}
		

		
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}
