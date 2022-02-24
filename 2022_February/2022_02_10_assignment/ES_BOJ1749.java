package net.acmicpc.fet.week4;

import java.io.*;
import java.util.*;


public class BOJ1749 {

	static int N, M;
	static long[][] A;
	static long[][] dp;
	static long ans = Long.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new long[N+1][M+1];
		dp = new long[N+1][M+1];
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < M+1; j++) {
				A[i][j] = Long.parseLong(st.nextToken());
				dp[i][j] = dp[i][j-1] + dp[i-1][j] + A[i][j] - dp[i-1][j-1];
			}
		}
		
		
		for(int i = 1; i < N+1; i++) {
			for(int j = 1; j < M+1; j++) {
				for(int k = i; k < N+1; k++) {
					for(int m = j; m < M+1; m++) {
						long tmp = dp[k][m] - dp[i][m] - dp[k][j] + dp[i][j];
						ans = Math.max(ans, tmp);
					}
				}
				
			}
		}	
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}
