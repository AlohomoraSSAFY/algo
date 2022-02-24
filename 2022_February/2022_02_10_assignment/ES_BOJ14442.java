package net.acmicpc.fet.week4;

import java.io.*;
import java.util.*;


public class BOJ14442 {
	
	static int N, M, K;
	static int[][] map;
	static int[][][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int ans = -1;
	
	static class POS {
		int r, c, cnt;
		int time;

		public POS(int r, int c, int cnt, int time) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.time = time;
		}
		
	}
	
	private static void bfs(int sr, int sc) {
		Queue<POS> queue = new LinkedList<>();
		queue.add(new POS(sr, sc, 0, 1));
		visited[sr][sc][0] = 1;
		
		while(!queue.isEmpty()) {
			POS cur = queue.poll();
			int r = cur.r; int c= cur.c; int cnt = cur.cnt;
			int time = cur.time;
			for(int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if(nr < 1 || nr > N || nc < 1 || nc > M ) continue;
				if(nr == N && nc == M) {
					ans = time+1;
					return;
				}
				
				if(map[nr][nc] == 0 && visited[nr][nc][cnt] == 0) {
					visited[nr][nc][cnt] = 1;
					queue.add(new POS(nr, nc, cnt, time+1));
					continue;
				}
				
				if (map[nr][nc]==1){
					if(cnt == K) continue;
					if(visited[nr][nc][cnt+1] > 0) continue;
					visited[nr][nc][cnt+1] = 1;
					queue.add(new POS(nr, nc, cnt+1, time+1));
				}
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		visited = new int[N+1][M+1][K+1];
		for(int i = 1; i < N+1; i++) {
			String[] tmp = br.readLine().split("");
			for(int j = 1; j < M+1; j++) {
				map[i][j] = Integer.parseInt(tmp[j-1]);
			}
		}
		
		if(N==1 && M==1) {
			ans = 1;
		}else {
			bfs(1, 1);			
		}
		
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}
