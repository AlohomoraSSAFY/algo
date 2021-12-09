package com.acmicpc.samsung;

import java.io.*;
import java.util.*;


public class BOJ21610 {
	
	static int N, M;
	static int[][] cmd;
	static ArrayList<CLOUD> clouds = new ArrayList<>();
	static ArrayList<CLOUD> after;
	static int d = -1, s = -1;
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // 이동 방향 체크
	static int[] dc = {0, -1,  -1, 0, 1, 1, 1, 0, -1};
	static int[][] A;
	static boolean[][] visited;
	static int[] dy = {-1, -1, 1, 1}; // 대각선체크
	static int[] dx = {-1, 1, 1, -1};
	
	private static class CLOUD{
		int r; int c;

		public CLOUD(int r, int c) {
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
			CLOUD other = (CLOUD) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}	
		
		
	}
	
	public static void moveCloud() {
		after = new ArrayList<>(); // 구름이 이동하는 곳
		visited = new boolean[N][N]; // 구름이 방문한 곳 마킹
		for(Iterator<CLOUD> iter = clouds.iterator(); iter.hasNext(); ) { // 1번 작업
			CLOUD cloud = iter.next();
			int nr = cloud.r + dr[d]*( s % N );
			int nc = cloud.c + dc[d]*( s % N);
			
			if(nr < 0) {
				nr = N +nr;
			}else if(nr > N-1) {
				nr = nr - N;
			}
			
			if(nc <0) {
				nc = N+nc;
			}else if(nc > N-1) {
				nc = nc - N;
			}
			
			visited[nr][nc] = true;			
			after.add(new CLOUD(nr, nc));
			A[nr][nc]++; // 2번 작업
		}
	}
	
	public static int[][] copyMap() {
		int[][] next = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				next[i][j] = A[i][j];
			}
		}
		return next;
	}
	
	public static void copyCloud() { // 4번 물복사 버그 시전
		int[][] next = copyMap();
		for(Iterator<CLOUD> iter = after.iterator(); iter.hasNext(); ) { // 3번 작업
			CLOUD cloud = iter.next();
			int sum = 0;
			for(int dir = 0; dir < 4; dir++) { // 거리가 1인 대각선 방향
				int nr = cloud.r + dy[dir];
				int nc = cloud.c + dx[dir];
				if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
//				sum += A[nr][nc];
				if(A[nr][nc]>0) sum++;
			}
			next[cloud.r][cloud.c] += sum;
		}
		A = next;
	}
	
	public static void makeCloud() { // 5. 구름 만들기
		clouds = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j] && A[i][j] > 1) {
					clouds.add(new CLOUD(i, j));
					A[i][j] -= 2;
				}
			}
		}
		
	}
	
	public static void printClouds() {
		System.out.println("--------- "+d+" "+s);
		for(Iterator<CLOUD> iter = clouds.iterator(); iter.hasNext(); ) { 
			CLOUD cloud = iter.next();
			System.out.println(cloud.r+" "+cloud.c);
		}
		System.out.println("-----------");
	}
	
	public static void printAfter() {
		System.out.println("AFTER--------- "+d+" "+s);
		for(Iterator<CLOUD> iter = after.iterator(); iter.hasNext(); ) { 
			CLOUD cloud = iter.next();
			System.out.println(cloud.r+" "+cloud.c);
		}
		System.out.println("-----------");
	}
	
	public static void printMap() {
		System.out.println("--------- "+d+" "+s);
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < N; j++) {
				System.out.printf("%5d", A[i][j]);
			}
			System.out.println();
		}
		System.out.println("-----------");
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		A= new int[N][N];
		cmd = new int[M][2];
		
		for(int i = 0; i < N; i++) { // 물바구니 양 체크
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < M; i++) { // 명령어
			st = new StringTokenizer(br.readLine());
			cmd[i][0] = Integer.parseInt(st.nextToken());
			cmd[i][1] = Integer.parseInt(st.nextToken());
		}
		
//		cloud = new int[N][N];
//		cloud[N-1][0] = 1;// 처음 시전했을때 구름 위치들
//		cloud[N-1][1] = 1;
//		cloud[N-2][0] = 1;
//		cloud[N-2][1] = 1;
		clouds.add(new CLOUD(N-1, 0));
		clouds.add(new CLOUD(N-1, 1));
		clouds.add(new CLOUD(N-2, 0));
		clouds.add(new CLOUD(N-2, 1));
		
		int cnt = 0;
		while(true) {
			if(cnt==M) break;
			d = cmd[cnt][0];
			s = cmd[cnt][1];
			moveCloud();
//			printClouds();
//			printMap();
			copyCloud();
//			printAfter();
//			printMap();
			makeCloud();
//			printClouds();
//			printMap();
			cnt++;
			
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				answer += A[i][j];
			}
		}
		
		bw.write(answer+"\n");
		bw.close();
		br.close();	
		
	}

}
