package net.acmicpc.jan.week2;

import java.io.*;
import java.util.*;


public class BOJ21317 {
	
	static int N, K;
	static int[][] energy;
	static int[][] dp; // 0은 매우 큰 점프 X
	static int[] jump = {1, 2, 3};// 작은 점프, 큰 점프, 매우 큰 점프
	
	private static void dfs(int pos) {
		if(pos == N -1) {
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			int next = pos + jump[i];
			if(next > N-1) continue;
			if(i==2) {
				dp[next][1] = Math.min(dp[next][1], dp[pos][0] + K);
			}else {
				dp[next][0] = Math.min(dp[next][0], dp[pos][0] + energy[pos][i]);
				dp[next][1] = Math.min(dp[next][1], dp[pos][1] + energy[pos][i]);
			}
			dfs(next);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		N = Integer.parseInt(br.readLine());
		energy = new int[N][2];
		dp = new int[N][2];
		for(int i = 1 ; i < N; i++) {
			dp[i][0] = dp[i][1] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < N-1; i++) {
			String[] tmp = br.readLine().split(" ");
			int a = Integer.parseInt(tmp[0]);
			int b = Integer.parseInt(tmp[1]);
//			small[i] = a; big[i]= b;
			energy[i][0] = a; energy[i][1]=b;
		}
		K = Integer.parseInt(br.readLine());
		
		dfs(0);
		bw.write((Math.min(dp[N-1][0], dp[N-1][1]))+"\n");
		
		bw.close();
		br.close();	
		
	}

}
