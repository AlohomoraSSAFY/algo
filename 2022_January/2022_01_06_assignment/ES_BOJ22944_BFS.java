package net.acmicpc.jan.week2;

import java.io.*;
import java.util.*;


public class BOJ22944_BFS {
	
	static int N, D, H; // NxN크기, 내구도 D, 체력 H
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static char[][] map;
	static int[] start = new int[2];
	static int[] end = new int[2];
	static int ans = -1;
	static ArrayList<UMB> list = new ArrayList<>();
	
	static class UMB{
		int r, c;

		public UMB(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UMB other = (UMB) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
		
	}
	
	static class POS{
		int r, c, idx, umb, h;
		public POS(int r, int c, int idx, int umb, int h) {
			this.r = r;
			this.c = c;
			this.idx = idx;
			this.umb = umb;
			this.h = h;
		}
	}
	
	private static void bfs(int sr, int sc) {
		POS p = new POS(sr, sc, 0, 0, H);
		boolean[][][] visited = new boolean[1 << 10][N][N];
		
		Queue<POS> queue = new LinkedList<>();
		queue.offer(p);
		
		int time = 1;
		visited[0][sr][sc] = true;

		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0; s < size; s++) {
				POS cur = queue.poll();
				int r = cur.r; int c = cur.c;
				for(int d = 0 ; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
					if(visited[cur.idx][nr][nc]) continue;
					if(map[nr][nc]=='E') {
						ans = time;
						return;
					}else if(map[nr][nc]=='U') {
						int idx = list.indexOf(new UMB(nr, nc));
						queue.offer(new POS(nr, nc, (cur.idx | idx), D-1, cur.h));
						visited[(cur.idx | idx)][nr][nc] = true;
					}else {
						if(cur.umb > 0) { // 우산이 있는 경우
							queue.offer(new POS(nr, nc,  cur.idx, cur.umb-1, cur.h));
							visited[cur.idx][nr][nc] = true;
						}else { //우산이 없는 경우
							if(cur.h == 1) continue; // 죽는 경우
							queue.offer(new POS(nr, nc, cur.idx, 0, cur.h-1));
							visited[cur.idx][nr][nc] = true;
						}						
					}
				}
			}
			time++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new char[N][N];
		for(int i = 0 ; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				if(map[i][j]=='S') {
					start[0] = i; start[1] = j;
				}else if(map[i][j]=='E') {
					end[0] = i; end[1] = j;
				}else if(map[i][j]=='U') {
					list.add(new UMB(i, j));
				}
			}
		}
		
		bfs(start[0], start[1]);
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}
