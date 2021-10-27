package study1028;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2252 {
	
	static int N;
	static int[] indegree;
	static List<List<Integer>> list;
	static List<Integer> result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		indegree = new int[N+1];
		list = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			list.add(new ArrayList<>());
		}
		result = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			indegree[B]++;
			list.get(A).add(B);
		}
		
		topologicalSort();
		
		for (int i = 0; i < N; i++) {
			sb.append(result.get(i) + " ");
		}
		
		System.out.println(sb);
	}
	
	public static void topologicalSort() {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N+1; i++) {
			if (indegree[i] == 0)
				q.offer(i);
		}
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			result.add(cur);
			
			for (Integer i : list.get(cur)) {
				indegree[i]--;
				if (indegree[i] == 0)
					q.offer(i);
			}
		}
	}

}
