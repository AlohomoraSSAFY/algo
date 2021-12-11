package study1212;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ1647 {
	
	static int N;
	static int M;
	static int[] parent;
	static List<Node> list;
	
	static class Node implements Comparable<Node> {
		int start;
		int end;
		int weight;
		
		public Node(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static void make() {
		parent = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		
		if (pa == pb) return false;
		parent[pb] = pa;
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list.add(new Node(A, B, C));
		}
		
		Collections.sort(list);
		make();
		
		int cnt = 0;
		int result = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < M; i++) {
			Node temp = list.get(i);
			int A = temp.start;
			int B = temp.end;
			int C = temp.weight;
			
			if (union(A, B)) {
				result += C;
				max = Math.max(max, C);
				if (++cnt == N-1) break;
			}
		}
		
		System.out.println(result - max);
	}

}
