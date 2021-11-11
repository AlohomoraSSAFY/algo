package com.acmicpc.samsung;

import java.io.*;
import java.util.*;


public class BOJ23290 {
	
	static int M, S;
	static int time;
	static int[][] smell = new int[4][4];
	static ArrayList<Integer>[][] map = new ArrayList[4][4]; // 물고기 방향을 가진 배열 저장
	static ArrayList<Integer>[][] cur; // 이동한 물고기 방향을 가진 배열 저장
	static int[] fr = {0, -1, -1, -1, 0, 1, 1, 1}; // 물고기 이동
	static int[] fc = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dr = { 0, 1, 0, -1}; // 상어 이동
	static int[] dc = { 1, 0, -1, 0}; // 상 좌 하  우 > 우 하 좌 상 >=
	static int sr, sc;
	
	public static ArrayList<Integer>[][] init() {
		ArrayList<Integer>[][] ret = new ArrayList[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				cur[i][j] = new ArrayList<>();
			}
		}
		return ret;
	}
	
	public static void move() { // 2번 물고기 한칸 이동
//		ArrayList<Integer>[][] cur = new ArrayList[4][4];
		cur = new ArrayList[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				cur[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				for(int dir : map[i][j]) { // 격자별 물고기 탐색
					int r = i; int c = j;// 이동하려는 칸
					boolean flag = false;
					for(int d  = dir ; d > -1; d--) { // 
						int nr = r + fr[d];
						int nc = c + fc[d];
						if(nr < 0 || nc <0 || nr > 3 || nc >3 || (nr == sr && nc == sc)) { // 격자 밖 + 상어랑 겹치거나
							continue;
						}
						if( smell[nr][nc] >0 && (time - smell[nr][nc]  == 1 || time - smell[nr][nc]  == 2)) { // 직전에 냄새가 남음
							continue;
						}
						// 격자 안 상어랑 냄새도 없으면
						cur[nr][nc].add(d);
						flag = true;
						break;
					}
					if(flag) continue;
					for(int d= 7; d > dir; d--) { // 
						int nr = r + fr[d];
						int nc = c + fc[d];
						if(nr < 0 || nc <0 || nr > 3 || nc >3 || (nr == sr && nc == sc)) { // 격자 밖 + 상어랑 겹치거나
							continue;
						}
						if(  smell[nr][nc] >0 && (time - smell[nr][nc]  == 1 || time - smell[nr][nc]  == 2)) { // 직전에 냄새가 남음
							continue;
						}
						// 격자 안 상어랑 냄새도 없으면
						cur[nr][nc].add(d);
						flag = true;
						break;
					}
					if(flag) continue;
					cur[r][c].add(dir);// 
					
					
				}
			}
		}
	}
	
	public static void eating() { // 상어가 가장 많이 먹을 수 있는 거 확인 작업
		int mcnt = 0, my = 0, mx = 0;
		int acnt = 0, bcnt = 0, ccnt = 0;
		int AC = 0, BC = 0, CC = 0;
		int na = 0, nb = 0, nc = 0;
		for(int a = 0 ; a < 4; a++) { // 0 0 0 우우우 -> 0 0 0  상상상
			int sy = sr + dr[a];
			int sx = sc + dc[a];
			if(sy < 0 || sx <0 || sy > 3 || sx >3) { // 격자 밖은 못간다
				continue;
			}
			acnt =  cur[sy][sx].size();
			for(int b = 0; b < 4; b++) {
				int bsy = sy + dr[b];
				int bsx = sx + dc[b];
				if(bsy < 0 || bsx <0 || bsy > 3 || bsx >3) { // 격자 밖은 못간다
					continue;
				}
				
				bcnt = cur[bsy][bsx].size();
				
				for(int c = 0 ; c < 4; c++) {
					int csy = bsy + dr[c];
					int csx = bsx + dc[c];
					if(csy < 0 || csx < 0 || csy > 3 || csx >3) { // 격자 밖은 못간다
						continue;
					}
					// 갈 수 있다.
					if(sy==csy && sx==csx) ccnt = 0;
					else if(bsy==csy && bsx==csx) ccnt = 0;
					else ccnt = cur[csy][csx].size();

					if(acnt + bcnt + ccnt >= mcnt) { // 큰 경우만 업데이트 같으면 사전순 앞
//						System.out.println(a+" "+b+" "+c +" "+mcnt+" "+(acnt+" " + bcnt+" " + ccnt));
						mcnt = acnt + bcnt + ccnt;
						my = csy; mx = csx;
						na = a; nb = b; nc = c; // 이동 경로
						AC = acnt; BC = bcnt; CC = ccnt; 
					}
				}
			}
		}
		
		// 다음 상어 위치 결정 + 냄새 찍기
//		System.out.println(na+" "+nb+" "+nc);
		int sy = sr + dr[na];
		int sx = sc + dc[na];
		if(AC > 0) {
//			System.out.println("SS"+sy+" "+sx);
			smell[sy][sx] = time;
			cur[sy][sx] = new ArrayList<>();
		}
		sy = sy + dr[nb];
		sx = sx + dc[nb];
		if(BC > 0 ) {
			 smell[sy][sx] = time;
			 cur[sy][sx] = new ArrayList<>();
		}
		sy = sy + dr[nc];
		sx = sx + dc[nc];
		if(CC > 0 ) {
			smell[sy][sx] = time;
			cur[sy][sx] = new ArrayList<>();
		}
		sr = my; sc = mx;
	}
	
	public static void duplicate() { // 복제 마법
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j].addAll(cur[i][j]);
			}
		}
	}
	
	
	public static void printSmell() { // 복제 마법
		System.out.println("----SMELL--------");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.printf("%2d", smell[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printMap() { // 복제 마법
		System.out.println("----MAP-------");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.printf("%2d", map[i][j].size());
			}
			System.out.println();
		}
	}
	
	public static void printMapDIR() { // 복제 마법
		System.out.println("----direct-------");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.print("[ ");
				for(int d : map[i][j]) {
					System.out.printf("%2d", d);
				}
				System.out.print(" ]");
			}
			System.out.println();
		}
	}
	
	public static void printCur() { // 복제 마법
		System.out.println("----CUR-------");
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				System.out.printf("%2d", cur[i][j].size());
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		time = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken())-1;
			map[r][c].add(dir); // 격자 칸에 물고기 추가
		}
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;
		time = 1;
		while(true) {
//			System.out.println(time);
			move();
//			printCur();
			eating();
			duplicate();	
//			printMap();
//			printMapDIR();
//			printSmell();			
//			System.out.println("상어위치 "+sr+" "+sc);
			if(time==S) {
				break;
			}
			time++;
		}
		
		int answer = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				answer += map[i][j].size();
			}
		}
		
		bw.write(answer+"\n");
		bw.close();
		br.close();	
		
	}

}
