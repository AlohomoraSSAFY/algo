package net.acmicpc.jan.week3;

import java.io.*;
import java.util.*;


public class BOJ13023 {
	
	static int N, M;
	static ArrayList<Integer>[] con;
	static int ans;
	static boolean[] checked;
	
	private static void dfs(int idx, int depth) {
		if(depth == 4) {
			ans = 1;
			return;
		}
		
		for(int n : con[idx]) {
			if(ans!=1 && !checked[n]) {
				checked[n] = true;
				dfs(n, depth+1);
				checked[n] = false;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		con = new ArrayList[N];
		for(int i = 0 ; i < N; i++) {
			con[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			con[a].add(b);
			con[b].add(a);
		}
		ans = 0;
		for(int i = 0 ; i < N; i++) {
			if(ans==0) {
				checked = new boolean[N];
				checked[i] = true;
				dfs( i, 0);		
			}else if(ans==1) {
				break;
			}
		}
		
		bw.write(ans+"\n");
		bw.close();
		br.close();	
		
	}

}
