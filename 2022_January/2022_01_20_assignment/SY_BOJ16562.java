package study0127;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ16562 {
	
	static int N, M, K;
	static int[] cost;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cost = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < N+1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			union(a, b);
		}
		
		boolean[] check = new boolean[N+1];
		for (int i = 1; i < N+1; i++) {
			check[find(i)] = true;
		}
		
		int result = 0;
		for (int i = 1; i < N+1; i++) {
			if (check[i]) result += cost[i];
		}
		
		if (result <= K) {
			System.out.println(result);
		} else {
			System.out.println("Oh no");
		}
	}
	
	private static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	private static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		
		if (ra == rb) return false;
		
		if (cost[ra] < cost[rb]) {
			parent[rb] = ra;
		} else {
			parent[ra] = rb;
		}
		
		return true;
	}

}
