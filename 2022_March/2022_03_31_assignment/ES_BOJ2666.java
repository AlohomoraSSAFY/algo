package net.acmicpc.april.week2;

import java.io.*;
import java.util.*;


public class BOJ2666 {
	
	static int N, M;
	static int fd, sd;
	static int[] pos;
	
	private static int dfs(int cnt, int first, int second) {
//		System.out.println("cnt "+cnt+" "+first+" "+second);
		if(cnt == M) {
			return 0;
		}
		
		int one = Math.abs(first - pos[cnt]);
		int two = Math.abs(second - pos[cnt]);
		
		return Math.min( one + dfs(cnt+1, pos[cnt], second), two + dfs(cnt+1, first, pos[cnt]));		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		fd = Integer.parseInt(st.nextToken());
		sd = Integer.parseInt(st.nextToken());
		
		M = Integer.parseInt(br.readLine());
		pos = new int[M];
		for(int i = 0 ; i < M; i++) {
			pos[i] = Integer.parseInt(br.readLine());
		}
		
		bw.write(dfs(0, fd, sd)+"\n");
		
		bw.close();
		br.close();	
		
	}

}
