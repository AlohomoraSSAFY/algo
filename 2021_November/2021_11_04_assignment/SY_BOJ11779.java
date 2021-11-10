package study1107;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SY_BOJ11779 {
	
	static int n;
	static int m;
	static int start;
	static int end;
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> list;
	static int[][] distance;
	static Stack<Integer> stack;
	
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < n+1; i++) {
			list.add(new ArrayList<>());
		}
		
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list.get(from).add(new Edge(to, weight));
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		distance = new int[2][n+1];
		Arrays.fill(distance[0], INF);
		stack = new Stack<>();
		
		dijkstra();
		
		sb.append(distance[0][end] + "\n");
		sb.append(stack.size() + "\n");
		while (!stack.isEmpty()) {
			sb.append(stack.pop() + " ");
		}
		System.out.println(sb);
	}
	
	public static void dijkstra() {
		PriorityQueue<Edge> q = new PriorityQueue<>();
		boolean[] visited = new boolean[n+1];
		q.offer(new Edge(start, 0));
		distance[0][start] = 0;
		distance[1][start] = -1;
		
		while (!q.isEmpty()) {
			Edge cur = q.poll();
			
			if (visited[cur.to]) continue;
			visited[cur.to] = true;
			
			if (cur.to == end) break;
			
			for (int i = 0; i < list.get(cur.to).size(); i++) {
				Edge temp = list.get(cur.to).get(i);
				if (distance[0][temp.to] > distance[0][cur.to] + temp.weight) {
					distance[0][temp.to] = distance[0][cur.to] + temp.weight;
					distance[1][temp.to] = cur.to;
					q.offer(new Edge(temp.to, distance[0][temp.to]));
				}
			}
		}
		
		int temp = end;
		while (temp != -1) {
			stack.push(temp);
			temp = distance[1][temp];
		}
	}

}
