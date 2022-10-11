package com.acmipc.oct.week1;

import java.io.*;
import java.util.*;


public class BOJ17822 {
	
	static int N, M, T;
	static int[][] circle;
	
	private static void counterClock(int idx, int k) {
		int s = circle[idx][0];
		if(s + k > M) {
			s = s + k - M;
		} else {
			s = s + k;
		}
		circle[idx][0] = s;
	}
	
	private static void clock(int idx, int k) {
		int s = circle[idx][0];
		if(s - k < 1) {
			s = s - k + M;
		} else {
			s = s - k;
		}
		circle[idx][0] = s;
	}
	
	private static void printMap(int[][] tmp) {
		System.out.println("시작점");
		for(int i = 1; i < N+1; i++) { // 원판 
			System.out.printf("%2d", tmp[i][0]);
		}		
		System.out.println("\n원판");
		for(int i = 1; i < N+1; i++) { // 원판 
			for(int j = 1; j < M+1; j++) {
				System.out.printf("%2d", tmp[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		circle = new int[N+1][M+1];
		
		for(int i = 1; i < N+1; i++) { // 원판 
			st = new StringTokenizer(br.readLine());
			circle[i][0] = 1;
			for(int j = 1; j < M+1; j++) {
				circle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int cs = 1; cs < T+1; cs++) { // 회전
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			// 1. 회전
			for(int idx = x; idx < N+1; idx=idx + x) {
				if(d==0) {
					clock(idx, k);
				} else {
					counterClock(idx, k);
				}
			}
//			System.out.println("회전후");
//			printMap(circle);
			
			// 2. 인접한 수 찾기
			int[][] next = new int[N+1][M+1];
			int total = 0; int cnt = 0; boolean flag = false;
			for(int i = 1; i < N+1; i++) { // 첫번째와 마지막 원판 제외
				int s = circle[i][0];	next[i][0] = s;
				for(int idx = 0; idx < M; idx++) {
					int j = s + idx > M ? s + idx-M : s + idx;
//					System.out.printf("%d %d %d\n", j, s, idx);
					int val = circle[i][j];
					next[i][j] = circle[i][j];
					if(val == -1) {
						continue;
					}
					total += val; cnt++;

					// 상
					if(i != N) {
						int us = circle[i+1][0];
						int uj = us + idx > M ? us + idx-M : us + idx;
						int uval = circle[i+1][uj];
						if(uval == val) {
							flag = true;
							next[i][j] = -1; next[i+1][uj] = -1;
						}
					}
					
					// 하
					if(i != 1) {
						int ds = circle[i-1][0];
						int dj = ds + idx > M ? ds + idx-M : ds + idx;
						int dval = circle[i-1][dj];
						if(dval == val) {
							flag = true;
							next[i][j] = -1; next[i-1][dj] = -1;
						}
					}
					
					
					// 좌
					int lj = (j == 1) ? M : j -1;
					int lval = circle[i][lj];
					if(lval == val) {
						flag = true;
						next[i][j] = -1; next[i][lj] = -1;
					}
					
					
					// 우
					int rj = (j == M) ? 1 : j +1;
					int rval = circle[i][rj];
					if(rval == val) {
						flag = true;
						next[i][j] = -1; next[i][rj] = -1;
					}
				}
			}
			
//			printMap(next);
//			System.out.println("지우기 "+flag);
			if(flag) {
				
			} else {
				double avg = (double) total / (double) cnt;
//				System.out.println("평균"+avg);
				for(int i = 1; i < N+1; i++) { // 첫번째와 마지막 원판 제외
					for(int j = 1; j < M+1; j++) {
						if(next[i][j] != -1) {
							if(total < next[i][j] * cnt) {
								next[i][j]--;
							} else if(total > next[i][j] * cnt) {
								next[i][j]++;							
							}
						}
					}
				}
			}
			
//			System.out.println("2번 완료 후");
//			printMap(next);
			circle = next;
			
		}
		int sum = 0;
		for(int i = 1; i < N+1; i++) { // 첫번째와 마지막 원판 제외
			for(int j = 1; j < M+1; j++) {
				if(circle[i][j]!=-1) {
					sum += circle[i][j];
				}
			}
		}
		bw.write(sum+"\n");
		bw.close();
		br.close();	
		
	}

}
