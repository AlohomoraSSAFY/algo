package com.acmicpc.samsung;

import java.io.*;
import java.util.*;


public class BOJ17837 {
	
	static int N, K;
	static Deque<Integer>[][] map;
	static int[] dr = {0, 0, 0, -1, 1};
	static int[] dc = {0, 1, -1, 0, 0};
	static ArrayList<int[]> horses = new ArrayList<>();
	static int[][] color;
	
	public static void printHorse() {
		System.out.println("-------");
		for(int i = 0 ; i < K; i++) {
			System.out.printf("%d %d %d\n", horses.get(i)[0], horses.get(i)[1], horses.get(i)[2]);
		}
		System.out.println("-------");
	}
	
	public static void printMap() {
		System.out.println("-------");
		for(int i = 0 ; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].size() > 0) {
					System.out.printf("(%d, %d) : ", i, j);
					for(Iterator<Integer> iter = map[i][j].iterator(); iter.hasNext();) {
						System.out.printf("%d ", iter.next());						
					}
					System.out.println();
				}
			}
		}
		System.out.println("-------");
	}
	
	public static boolean WhiteBlock(int cur, int r, int c, int nr, int nc) { // blue도 포함
//		System.out.println("W "+map[r][c].size()+" "+r+" "+c);
		if(map[r][c].size() > 0) {
			Deque<Integer> move = new ArrayDeque<>();
			while(true) {
				int e = map[r][c].pollLast();
				int[] horse = horses.get(e);
				horse[0] = nr; horse[1] = nc;
				move.push(e);
				if(e==cur) break;
			}
//			for(Iterator<Integer> iter = map[r][c].descendingIterator(); iter.hasNext();) {
//				int e = iter.next();
//				move.push(e);
//				if(e==cur) {
//					break;
//				}
//			}
			map[nr][nc].addAll(move);// 끝에 추가
			if(map[nr][nc].size() > 3) return true;
			
		}
		return false;
	}
	
	public static boolean RedBlock(int cur, int r, int c, int nr, int nc) {
//		System.out.println("RED "+map[r][c].size()+" "+r+" "+c);
		if(map[r][c].size() > 0) {
			Deque<Integer> move = new ArrayDeque<>();
//			for(Iterator<Integer> iter = map[r][c].descendingIterator(); iter.hasNext();) {
//				int e = iter.next();
//				move.add(e);
//				if(e==cur) {
//					break;
//				}
//			}
			while(true) {
				int e = map[r][c].pollLast();
				int[] horse = horses.get(e);
				horse[0] = nr; horse[1] = nc;
				move.add(e);
				if(e==cur) break;
			}
			map[nr][nc].addAll(move);// 끝에 추가		
			if(map[nr][nc].size() > 3) return true;
		}
		return false;
	}
	
	public static boolean move() {
		boolean flag = false;
		int idx = 0;
		LOOP:
		for(Iterator<int[]> iter = horses.iterator();iter.hasNext(); idx++) {
			int[] cur = iter.next();
			int r = cur[0]; int c = cur[1]; int dir = cur[2];
			int nr = r + dr[dir]; int nc = c + dc[dir];
			
			if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || color[nr][nc]==2) {
				cur[2] = (dir%2==1) ? dir+1 : dir-1;
				nr = r + dr[cur[2]]; nc = c + dc[cur[2]];
				if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1 || color[nr][nc]==2) {
					continue;
				}else {
					if(color[nr][nc]==0) {// 흰색인 경우
						flag = WhiteBlock(idx, r, c, nr, nc);
					}else if(color[nr][nc]==1) { // 빨강인 경우 
						flag = RedBlock(idx, r, c, nr, nc);
					}
					if(flag) break;  // 조건문 제대로 하기
				}
				continue;
			}
			
			if(color[nr][nc]==0) {// 흰색인 경우
				flag = WhiteBlock(idx, r, c, nr, nc);
			}else if(color[nr][nc]==1) { // 빨강인 경우 
				flag = RedBlock(idx, r, c, nr, nc);
			}
			
			if(flag) break;
			
		}
		
		
		
		return flag;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		color = new int[N][N];
		map = new ArrayDeque[N][N];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				color[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = new ArrayDeque<>();
			}
		}
		
		
		for(int i = 0 ; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) -1;
			int c = Integer.parseInt(st.nextToken()) -1;
			int dir = Integer.parseInt(st.nextToken());
			map[r][c].add(i); // 맵에 방향 넣기
			horses.add(new int[] {r, c, dir}); // 말 위치와 방향 넣기
		}
		int turn = 0;
		while(true) {

//			System.out.println("Before");
//			printMap();
//			printHorse();
			boolean flag = move();
//			System.out.println("AFTER");
//			printMap();
//			printHorse();
			turn++;
			if(flag) {
				break;
			}
			if(turn > 1000) {
				turn = -1;
				break;
			}
			
		}
		
		bw.write(turn+"\n");
		bw.close();
		br.close();	
		
	}

}
