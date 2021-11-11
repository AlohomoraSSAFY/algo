package com.acmicpc.week8;

import java.io.*;
import java.util.*;


public class BOJ2638 {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] checked;
	
	private static void bfs() {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r= cur[0]; int c = cur[1];
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr <0 || nc < 0 || nr > N-1 || nc > M -1) continue;
				if(!visited[nr][nc] && map[nr][nc]==0) { // 공기인 곳 큐에 집어넣어줌
					visited[nr][nc] = true;
					queue.offer(new int[] {nr, nc});	
				}
				
				if( map[nr][nc]==1) { // 치즈인 곳을 카운팅
					checked[nr][nc]++;
				}
			}
		}
	}
	
	private static int counting() { // 녹는 치즈 개수 카운팅 함수
		int count = 0;
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				if(checked[i][j] > 1) { // 2면 이상 접하고 있다면
					map[i][j] = 0; // 0으로 변환
					count++; 
				}
			}
		}
		return count;
	}
	
	public static void printMap(boolean[][] A) {
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				System.out.printf("%3d", A[i][j] == true? 0 : 1  );
			}
			System.out.println();
		}
	}
	
	public static void printCheck(int[][] A) {
		System.out.println("-------------");
		for(int i = 0; i < N; i++) {
			for(int j =0; j < M; j++) {
				System.out.printf("%3d", A[i][j] );
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());				
			}
		}
		
		int time = 0;
		while(true) {
			visited= new boolean[N][M];
			checked = new int[N][M];
			bfs();
//			printMap(visited);
//			printCheck(checked);			
			if(counting()==0) {
				break;
			}
			time++;
		}
		
		bw.write(time+"\n");		
		bw.close();
		br.close();	
		
	}

}

