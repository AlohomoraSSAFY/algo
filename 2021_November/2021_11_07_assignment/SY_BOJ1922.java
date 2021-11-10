package study1111;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ1922 {
	
	static int N;
	static int M;
	static List<List<Node>> list;
	static final int INF = Integer.MAX_VALUE;
	
	static class Node {
		int to;
		int cost;
		
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i < N+1; i++) {
			list.add(new ArrayList<>());
		}
		
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}
		
		boolean[] visited = new boolean[N+1];
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[1] = 0;
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			int min = Integer.MAX_VALUE;
			int index = 0;
			for (int j = 1; j < N+1; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					index = j;
				}
			}
			
			visited[index] = true;
			result += min;
			
			for (int j = 0; j < list.get(index).size(); j++) {
				Node temp = list.get(index).get(j);
				if (!visited[temp.to] && distance[temp.to] > temp.cost) {
					distance[temp.to] = temp.cost;
				}
			}
		}
		
		System.out.println(result);
	}

}
