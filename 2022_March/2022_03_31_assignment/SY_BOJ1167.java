package study0414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ1167 {

	static int V;
	static List<int[]>[] list;
	static int max;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(br.readLine());
		list = new ArrayList[V+1];
		for (int i = 0; i < V+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			while (a != -1) {
				int b = Integer.parseInt(st.nextToken());
				list[num].add(new int[] {a, b});
				a = Integer.parseInt(st.nextToken());
			}
		}
		
		find(1);
		find(result);
		System.out.println(max);
	}
	
	private static void find(int num) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {num, 0});
		boolean[] visited = new boolean[V+1];
		visited[num] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < list[cur[0]].size(); i++) {
				int[] temp = list[cur[0]].get(i);
				if (visited[temp[0]]) continue;
				
				q.offer(new int[] {temp[0], cur[1] + temp[1]});
				visited[temp[0]] = true;
				
				if (max < cur[1] + temp[1]) {
					max = cur[1] + temp[1];
					result = temp[0];
				}
			}
		}
	}

}
