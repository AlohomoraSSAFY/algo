package net.acmicpc.march.week1;

import java.io.*;
import java.util.*;


public class BOJ2512 {
	
	static int N, M;
	static long[] arr;
	static long sum;
	static long max = 0L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		arr = new long[N+1];
		sum = 0L;
		
		for(int i = 1 ; i < N+1; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			sum += arr[i];
			max = Math.max(max, arr[i]);
		}
		
		M = Integer.parseInt(br.readLine());
		if(sum <= M) {
			bw.write(max + "\n");
		} else {
			
			long left = 1L;
			long right = max;
			long mid = 0L;
			long ans = 0L;
			while(left < right) {
				mid = (left + right)/2;
				
				long tmp = 0L;
				for(int i = 1; i < N+1; i++) {
					if(arr[i] <= mid) {
						tmp += arr[i];
					}else {
						tmp += mid;
					}
				}
//				System.out.println(left+" "+mid+" "+right);
				if(tmp <= M) {
					left = mid +1;
					ans = Math.max(ans, mid);
				}else {
					right = mid;
				}
			}
			
			bw.write(ans + "\n");
			
		}
		
		
		bw.close();
		br.close();	
		
	}

}


