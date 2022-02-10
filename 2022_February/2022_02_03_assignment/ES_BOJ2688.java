package net.acmicpc.feb.week2;

import java.io.*;
import java.util.*;


public class BOJ2688 {
	
	static int N;
	static int[] arr;
	static int[] visited;
	static ArrayList<Integer> answer = new ArrayList<>();
	
	private static void dfs(int cur) {
//		System.out.println(cur+" "+visited[cur]);
		if(visited[cur]==2 ) {
			return;
		}
				
		visited[cur]++;
		int next = arr[cur];
//		visited[next]++;
		dfs(next);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		for(int i = 1 ; i < N+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		visited = new int[N+1];
		for(int i = 1; i < N+1; i++) {
			if(visited[i]==0) {
//				visited[i]++;
				dfs(i);				
			}
			for(int j = 1; j < N+1; j++) {
//				System.out.println("viste: "+j+" "+visited[j]);
				if(visited[j]!=2) visited[j] = 0;
			}
		}
		
		for(int i = 1; i < N+1; i++) {
			if(visited[i]==2) {
				answer.add(i);
			}
		}
		
		bw.write(answer.size()+"\n");
		for(Integer e : answer) {
			bw.write(e+"\n");
		}
		bw.close();
		br.close();	
		
	}

}
