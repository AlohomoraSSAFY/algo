package study0303;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ1774 {
	
	static int N;
	static int M;
	static int[][] pos;
	static int[] parent;
	static List<Edge> list;
	
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		double distance;
		
		public Edge(int from, int to, double distance) {
			this.from = from;
			this.to = to;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.distance, o.distance);
		}
	}
	
	static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) {
			return false;
		}
		
		parent[pa] = pb;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pos = new int[N+1][2];
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (union(a, b)) cnt++;
		}
		
		list = new ArrayList<>();
		for (int i = 1; i < N+1; i++) {
			for (int j = i+1; j < N+1; j++) {
				double d = Math.sqrt(Math.pow(pos[i][0] - pos[j][0], 2) + Math.pow(pos[i][1] - pos[j][1], 2));
				list.add(new Edge(i, j, d));
			}
		}
		
		Collections.sort(list);
		
		double result = 0;
		for (Edge edge : list) {
			if (!union(edge.from, edge.to)) continue;
			result += edge.distance;
			if (++cnt == N-1) break;
		}
		System.out.printf("%.2f", result);
	}

}
