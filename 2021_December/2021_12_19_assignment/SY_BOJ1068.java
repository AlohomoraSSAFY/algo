package study1223;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ1068 {

	static int N;
	static int[] parent;
	static List<Integer>[] child;
	static boolean[] leaf;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parent = new int[N];
		child = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			child[i] = new ArrayList<>();
		}
		leaf = new boolean[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
			if (parent[i] != -1) child[parent[i]].add(i);
		}
		
		for (int i = 0; i < N; i++) {
			if (child[i].size() == 0) leaf[i] = true;
		}
		
		int removeNum = Integer.parseInt(br.readLine());
		bfs(removeNum);
		
		int p = parent[removeNum];
		if (p != -1 && child[p].size() - 1 == 0) leaf[p] = true;
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			if (leaf[i]) result++;
		}
		System.out.println(result);
	}
	
	public static void bfs(int removeNum) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(removeNum);
		boolean[] visited = new boolean[N];
		visited[removeNum] = true;
		leaf[removeNum] = false;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int i = 0; i < child[cur].size(); i++) {
				int temp = child[cur].get(i);
				if (visited[temp]) continue;
				
				visited[temp] = true;
				leaf[temp] = false;
				q.offer(temp);
			}
		}
	}

}
