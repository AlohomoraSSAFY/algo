package com.acmicpc.samsung;

import java.io.*;
import java.util.*;

// 연구소 3
public class BOJ17142 {
	
	static int N, M;
	static int[][] map;
	static int[] X;
	static int[] Y;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static int blank = 0;
	static int wall = 0;
	static ArrayList<int[]> virus = new ArrayList<>();
	static int answer;
		
	public static int spreadVirus() {
		Queue<int[]> queue = new LinkedList<int[]>();
		int[][] time = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			Arrays.fill(time[i], 2*N*N );
		}
		
		
		for(int i = 0; i < M; i++) {
			int y = Y[i]; int x= X[i];
			time[y][x] = 0;
			queue.offer(new int[] {y, x});
		}
		
		int count = 0; int t = 0; 
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int y = cur[0]; int x= cur[1];
			for(int d= 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1 ) continue;
				if(time[ny][nx]== 2*N*N) {
					if(map[ny][nx] == 0) {
						time[ny][nx] = time[y][x]+1;
						queue.offer(new int[] {ny, nx});
						if(time[ny][nx] > t) t = time[ny][nx];
						count++;
					}
					if(map[ny][nx] == 2) {
						time[ny][nx] = time[y][x]+1;
						queue.offer(new int[] {ny, nx});
						
					}
					
				}
				
			}
		}
		
		
//		for(int i = 0 ; i < N; i++) {
//			for(int j = 0 ; j < N; j++) {
//				if(time[i][j]!=2*N*N && map[i][j] ==0 ) {
//					if(time[i][j] > t) t = time[i][j];
//				}
//			}
//		}
		
//		printMap(time);
//		System.out.println("C "+count+" 벽"+wall+" 빈곳"+blank);
		if(count == blank) {
			return t;
		}else {
			return 2*N*N;
		}
		
	}
	
	static int test = 0;
	public static void selectVirus(int cnt, int idx) {
		if(cnt==M) {
			int ret = spreadVirus();
//			System.out.println((test++)+"번 "+ret+"이다. 기존 "+answer);
//			for(int i = 0; i < M; i++) {
//				System.out.print(Y[i]+" "+X[i]+" / ");
//			}
//			System.out.println();
//			System.out.println("----------");
			if( answer > ret) {
				answer = ret;
			}
			return;
		}
		
		for(int i = idx; i < virus.size(); i++) {
			int[] cur = virus.get(i);
			Y[cnt] = cur[0];
			X[cnt] = cur[1];
			selectVirus(cnt+1, i+1);
		}
	}
	
	public static void printMap(int[][] A) {
		System.out.println("----------");
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				System.out.printf("%5d", A[i][j]);
			}
			System.out.println();
		}
		System.out.println("----------");
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0 ; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if(tmp == 0) {
					blank++; // 빈칸
				}else if(tmp==1) {
					wall++; // 벽
				}else { // 바이러스 위치
					virus.add(new int[] {i, j});
				}
			}
		}
		
		answer = 2*N*N;
		X = new int[M];
		Y = new int[M];
		selectVirus(0, 0);
		
		if(answer == 2*N*N) {
			bw.write("-1\n");
		}else {
			bw.write(answer+"\n");
		}
		
		bw.close();
		br.close();	
		
	}

}
