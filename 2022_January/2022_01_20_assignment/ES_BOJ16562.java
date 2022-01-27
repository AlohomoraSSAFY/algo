package net.acmicpc.jan.week4;

import java.io.*;
import java.util.*;


public class BOJ16562 {
	
	static int N, M;
	static long K;
	static int[] A;
	static int[] parent;
	
	private static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
		
	}
	
	private static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if(pa != pb) {
			if(A[pa] > A[pb]) {
				parent[pa] = pb;
			}else {
				parent[pb] = pa;
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Long.parseLong(st.nextToken());
		
		A = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i < N+1; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		parent = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		for(int i = 1; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		int sum = 0;
		for(int i = 1; i < N+1; i++) {
			if(parent[i]==i) {
				sum += A[i];
			}
		}
		
		if(K - sum < 0) {
			bw.write("Oh no\n");
		} else {
			bw.write(sum+"\n");
		}
		
		bw.close();
		br.close();	
		
	}

}
