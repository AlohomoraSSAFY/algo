package net.acmicpc.august.week4;

import java.io.*;
import java.util.*;


public class BOJ2075 {
	
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j =0; j < N; j++) {
				int e = Integer.parseInt(st.nextToken());
				pq.add(-e);
			}
		}
		
		int ans = 0;
		while(N-- > 0) {
			ans = pq.poll();
		}
		
		bw.write((-ans)+"\n");
		bw.close();
		br.close();	
		
	}

}
