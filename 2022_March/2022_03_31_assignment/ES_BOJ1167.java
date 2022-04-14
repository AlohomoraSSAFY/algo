package net.acmicpc.april.week2;

import java.io.*;
import java.util.*;


public class BOJ1167 {
	
	static int V;
	static long r;
	static ArrayList<long[]>[] adj;
	static int node = 1;
	
	private static void dfs(int pos, long sum, boolean[] visited) {
		int cnt = 0;
		for(long[] data : adj[pos]) {
			int next = (int) data[0];
			if(visited[next]) continue;
			visited[next] = true;
			cnt++;
			dfs(next, sum + data[1], visited);
			visited[next] = false;
		}		
		
		if(cnt==0) { // 리프노드
			if( r < sum) {
				r = sum;
				node = pos;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		V = Integer.parseInt(br.readLine());
		adj = new ArrayList[V+1];
		
		for(int i = 0; i < V; i++) {
			String[] line = br.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			adj[a] = new ArrayList<>();
			for(int j = 1; j < line.length-1; j+=2) {
				int b = Integer.parseInt(line[j]);
				long w = Long.parseLong(line[j+1]);
				adj[a].add(new long[] {b, w});	
			}
			
		}
		
		boolean[] visited = new boolean[V+1];
		visited[1] = true;
		dfs(1, 0, visited);
		
		visited = new boolean[V+1];
		visited[node] = true;
		dfs(node, 0, visited);
		bw.write(r+"\n");
		bw.close();
		br.close();	
		
	}

}
