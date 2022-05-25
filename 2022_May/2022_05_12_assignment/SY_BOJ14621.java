package study0526;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ14621 {
	
	static int N, M;
	static int[] parent;
	static char[] array;
	
	static class Road implements Comparable<Road> {
		int u;
		int v;
		int d;
		
		public Road(int u, int v, int d) {
			this.u = u;
			this.v = v;
			this.d = d;
		}
		
		public int compareTo(Road o) {
			return this.d - o.d;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		array = new char[N+1];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
			array[i] = st.nextToken().charAt(0);
		}
		
		List<Road> list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if (array[u] == array[v]) continue;
			list.add(new Road(u, v, d));
		}
		
		Collections.sort(list);
		
		int cnt = 0;
		int result = 0;
		for (Road road : list) {
			if (union(road.u, road.v)) {
				result += road.d;
				cnt++;
			}
			
			if (cnt == N-1) break;
		}
		
		if (cnt == N-1) System.out.println(result);
		else System.out.println(-1);
	}
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) return false;
		
		if (pa < pb) {
			parent[pb] = pa;
		} else {
			parent[pa] = pb;
		}
		return true;
	}

}
