package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ1967 {

	static ArrayList<Pair>[] map = null;
	static boolean[] check = null;
	static int[] dist = null;

	/* 참고 : https://codeung.tistory.com/187 */
	static class Pair {
		int node;
		int dist;

		public Pair(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}

	public static void bfs(int start) {
		Queue<Integer> que = new ArrayDeque<>();
		check[start] = true;
		que.add(start);

		while (!que.isEmpty()) {
			int now = que.poll();
			for (Pair p : map[now]) {
				int next = p.node;
				int len = p.dist;
				if (!check[next]) {
					check[next] = true;
					dist[next] = dist[now] + len;
					que.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());

		map = new ArrayList[n + 1];
		check = new boolean[n + 1];
		dist = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			map[parent].add(new Pair(child, dist));
			map[child].add(new Pair(parent, dist));
		}

		bfs(1); // 임의의 점에서 가장 먼 노드를 찾는다
		int node = 1;
		for (int i = 2; i <= n; i++) {
			if (dist[node] < dist[i]) {
				node = i;
			}
		}

		// 초기화 후 BFS 다시 진행
		check = new boolean[n + 1];
		dist = new int[n + 1]; // 임의의 점에서 가장 먼 노드를 시작점으로 한 길이 배열
		bfs(node); // 위에서 찾은 노드에서 가장 먼 노드를 찾는다
		int diameter = Arrays.stream(dist).max().getAsInt();
		System.out.println(diameter);
	}
}