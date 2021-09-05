package com.baekjoon.problem11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HN14569_TimeTable {
	static int N, M;
	static int[][] subject; // 과목별 , 과목 인덱스 번호 정보를 넣기위한 배열
	static boolean[][] student; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		subject = new int[N][];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			subject[i] = new int[k];
			
			for (int j = 0; j < k; j++) {
				subject[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		M = Integer.parseInt(br.readLine());
		student = new boolean[M][51];
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < k; j++) {
				int n = Integer.parseInt(st.nextToken());
				student[i][n] = true;
			}
			// 빈시간을 true로 설정
		}
		
		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			int cnt = 0;
			for (int i = 0; i < subject.length; i++) {
				boolean pass = true;
				for (int j = 0; j < subject[i].length; j++) {
					int tmp = subject[i][j];
					// 제시된 수업 시간표 시간이 학생의 시간표와 겹치면 pass = false
					if(!student[m][tmp]) {
						pass = false;
						break;
					}
				}
				if(pass)
					cnt++;
			}
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}

}
