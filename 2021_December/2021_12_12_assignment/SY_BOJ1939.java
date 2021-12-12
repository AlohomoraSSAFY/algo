package study1216;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ1939 {
	
	static int N;
	static int M;
	static List<Node>[] list;
	static int[] factory;
	static int result;
	
	static class Node {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
		}
		
		factory = new int[2];
		st = new StringTokenizer(br.readLine(), " ");
		factory[0] = Integer.parseInt(st.nextToken());
		factory[1] = Integer.parseInt(st.nextToken());
		
		int left = 1;
		int right = 1000000000;
		while (left <= right) {
			int mid = (left + right) / 2;
			boolean flag = bfs(mid);
			if (flag) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean bfs(int mid) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(factory[0]);
		boolean[] visited = new boolean[N+1];
		visited[factory[0]] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == factory[1]) return true;
			
			for (int i = 0; i < list[cur].size(); i++) {
				Node temp = list[cur].get(i);
				int to = temp.to;
				int weight = temp.weight;
				
				if (visited[to]) continue;
				if (weight < mid) continue;
				
				visited[to] = true;
				q.offer(to);
			}
		}
		
		return false;
	}

}
