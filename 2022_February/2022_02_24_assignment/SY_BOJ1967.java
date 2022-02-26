package study0303;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ1967 {
	
	static int n;
	static List<Edge>[] list;
	static boolean[] visited;
	static int maxLength;
	static int maxNode;
	
	static class Edge {
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n+1];
		for (int i = 0; i < n+1; i++) {
			list[i] = new ArrayList<>();
		}
		visited = new boolean[n+1];
		
		StringTokenizer st;
		for (int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b, w));
			list[b].add(new Edge(a, w));
		}
		
		visited[1] = true;
		dfs(1, 0);
		
		visited = new boolean[n+1];
		visited[maxNode] = true;
		dfs(maxNode, 0);
		
		System.out.println(maxLength);
	}
	
	private static void dfs(int start, int sum) {
		for (int i = 0; i < list[start].size(); i++) {
			Edge temp = list[start].get(i);
			if (!visited[temp.to]) {
				if (sum + temp.weight > maxLength) {
					maxLength = sum + temp.weight;
					maxNode = temp.to;
				}
				visited[temp.to] = true;
				dfs(temp.to, sum + temp.weight);
			}
		}
	}

}
