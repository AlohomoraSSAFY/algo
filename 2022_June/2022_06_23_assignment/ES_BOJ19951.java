package net.acmicpc.jun;

import java.io.*;
import java.util.*;


public class BOJ19951 {
	
	static int N, M;
	static int[] h;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		h = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+2];
		for(int i = 1; i < M +1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			dp[a] += k;
			dp[b+1] -=k;
		}
		
		int[] sum = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			sum[i] = sum[i-1] + dp[i];
			bw.write(String.format("%d ", sum[i] + h[i]));
		}
		
//		for(int i = 1; i < N+1; i++) {
//			bw.write(String.format("%d ", sum[i] + h[i]));
//		}
		bw.write("\n");
		
		bw.close();
		br.close();	
		
	}

}
