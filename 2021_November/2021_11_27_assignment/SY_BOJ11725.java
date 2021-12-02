package study1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ11725 {
	
	static int N;
	static ArrayList<Integer>[] list;
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		parent = new int[N+1];
		parent[1] = -1;
		
		StringTokenizer st;
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		bfs();
		
		for (int i = 2; i < N+1; i++) {
			sb.append(parent[i] + "\n");
		}
		System.out.println(sb);
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < list[cur].size(); i++) {
				if (parent[list[cur].get(i)] == 0) {
					parent[list[cur].get(i)] = cur;
					q.add(list[cur].get(i));
				}
			}
		}
	}

}
