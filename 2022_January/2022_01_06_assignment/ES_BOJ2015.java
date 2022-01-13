package net.acmicpc.jan.week2;

import java.io.*;
import java.util.*;


public class BOJ2015 {
	
	static int N;
	static long K;
	static int[] A;
	static long[] sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		A = new int[N+1];
		sum = new long[N+1];
		
		long ans = 0;
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1 ; i < N+1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-1] + A[i];
			if(sum[i] == K) ans++;
		}
		
		HashMap<Long, Long> map = new HashMap<>();
		// 1 2 3 4 
		// 2 -2 2 -2 
		// 2 0 2 0 // -> 1 ~ idx
		// i ~ j
		
		// 1 2 3 4 5 6 -> K = 5 (2,3) (5, 5) (5, 6)
		// 1 2 3 4 5 0 
		// 1 3 6 10 15 15 -> K= 5; 1 ~ idx
		// 1 - () = 5 -4 map(1, 1)
		// 3 - (-2) map(3, 1) 
		// 6 - (1) ans += map(1,1)
		// map(6, 1)
		// 10 - (5) = 5
		
		for(int i = 1; i < N+1; i++) {
			long val = sum[i];
			if(map.containsKey(val - K)) {
				ans+=(map.get(val-K));
			}
			map.put(val, map.getOrDefault(val, 0L) +1L);			
		}
		

		bw.write(ans+"\n");
		
		bw.close();
		br.close();	
		
	}

}
