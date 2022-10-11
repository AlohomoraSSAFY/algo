package com.acmipc.oct.week1;

import java.io.*;
import java.util.*;


public class BOJ15683 {
	
	static int R, C, answer;
	static int[][] map;
	static List<int[]> cctv;
	static int[] dr = {-1, 0, 1, 0, -1, 0, 1, 0, -1, 0, 1, 0};
	static int[] dc = {0, -1, 0, 1, 0, -1, 0, 1, 0, -1, 0, 1};
	
	private static boolean[][] copy(boolean[][] visited){
		boolean[][] ret = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j =0; j < C; j++) {
				ret[i][j] = visited[i][j];
			}
		}
		return ret;
	}
	
	private static void dfs(int cnt, boolean[][] visited) {
		if(cnt == cctv.size()) {
			counting(visited);
			return;
		}
		int[] pos = cctv.get(cnt);
		int r = pos[0]; int c = pos[1];
		switch (map[r][c]) {
			case 1:		
				for(int i = 0; i < 4; i++) { // 4방향 선택
					boolean[][] tmp = copy(visited);
					marking(r, c, i, tmp);
					dfs(cnt+1, tmp);
				}
				break;
			case 2:
				for(int i = 0; i < 2; i++) { // 회전으로 방향 가지기
					boolean[][] tmp = copy(visited);
					marking(r, c, i, tmp);
					marking(r, c, i+2, tmp);
					dfs(cnt+1, tmp);
				}
				break;
			case 3:
				for(int i = 0; i < 4; i++) { // 회전으로 방향 가지기
					boolean[][] tmp = copy(visited);
					marking(r, c, i, tmp);
					marking(r, c, i+1, tmp);
					dfs(cnt+1, tmp);
				}
				break;
			case 4:
				for(int i = 0; i < 4; i++) { // 회전으로 방향 가지기
					boolean[][] tmp = copy(visited);
					marking(r, c, i, tmp);
					marking(r, c, i+2, tmp);
					marking(r, c, i+3, tmp);
					dfs(cnt+1, tmp);
				}
				break;
			case 5:
				boolean[][] tmp = copy(visited);
				for(int i = 0; i < 4; i++) { // 4방향
					marking(r, c, i, tmp);
				}
				dfs(cnt+1, tmp);
				break;
		}
		
	}
	
	
	
	private static void marking(int r, int c, int d, boolean[][] visited) {
		visited[r][c] = true;
		while(true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr < 0 || nr > R-1 || nc <0 || nc > C-1) break;
			if(map[nr][nc] != 6) { // 벽이 아닌 경우
				visited[nr][nc] = true;
				r = nr; c = nc;
			} else { // 벽인 경우
				break;
			}
		}
	}
	
	private static void counting(boolean[][] visited) {
		int cnt = 0;
		for(int i = 0; i < R; i++) {
			for(int j =0; j < C; j++) {
				if(!visited[i][j]) cnt++;
			}
		}
//		printMap(visited);
		answer = Math.min(answer, cnt);
	}
	
	private static void printMap(boolean[][] visited) {
		System.out.println("맵 상태");
		for(int i = 0; i < R; i++) {
			for(int j =0; j < C; j++) {
				System.out.printf("%2d ", visited[i][j]? 1 : 0);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		cctv = new ArrayList<>();
		answer = R*C;
		int wall = 0;
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(0< map[i][j] && map[i][j] < 6) {
					cctv.add(new int[] {i, j});
				} else if(map[i][j]==6) {
					wall++;
				}
			}
		}
		
		boolean[][] visited = new boolean[R][C];
		dfs(0, visited);
		
		bw.write((answer-wall)+"\n");
		
		bw.close();
		br.close();	
		
	}

}
