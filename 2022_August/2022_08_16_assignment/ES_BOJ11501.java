package net.acmicpc.august.week4;

import java.io.*;
import java.util.*;


public class BOJ11501 {
	
	static int T, N;
	static long[] arr;
	static long[] maxi;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int cs = 1; cs < T+1; cs++) {
			N = Integer.parseInt(br.readLine());
			arr = new long[N+1];
			maxi = new long[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i < N+1; i++) {
				arr[i] = Long.parseLong(st.nextToken());
//				for(int j = 1; j < i; j++) {
//					if(arr[j] < arr[i] && maxi[j] < arr[i]) {
//						maxi[j] = arr[i];
//					}
//				}
			}

			long ans = 0L;
//			for(int i = 1; i < N+1; i++) {
//				if(maxi[i] > 0) {
//					ans += (maxi[i] - arr[i]);
//				}
//			}
			
			long max = arr[N];
			for(int i = N; i > 0; i--) {
				if(max < arr[i]) {
					max = arr[i];
				} else {
					ans += (max - arr[i]);
				}
			}
			bw.write(ans+"\n");
			
		}
		
		bw.close();
		br.close();	
		
	}

}
