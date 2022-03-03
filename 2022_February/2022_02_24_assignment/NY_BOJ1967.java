package date0303;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

class Node {
	int num;
	int dist;

	public Node(int num, int dist) {
		this.num = num;
		this.dist = dist;
	}

}

public class BOJ1967 {
	static int n;
	static ArrayList<Node> list[];
	static int answer;
	static int maxnum, max;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		boolean parent[] = new boolean[n + 1];

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
			parent[a] = true;
		}

		answer = 0;
		maxnum = 1;
		max = 0;

		bfs(1);

		max = 0;

		bfs(maxnum);
		System.out.println(answer);

	}

	public static void bfs(int num) {
		boolean visited[] = new boolean[n + 1];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { num, 0 });
		visited[num] = true;

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			int cur = temp[0];
			int distsum = temp[1];
			if (distsum > max) {
				max = distsum;
				maxnum = cur;
			}
			answer = Math.max(distsum, answer);

			for (Node next : list[cur]) {
				if (!visited[next.num]) {
					visited[next.num] = true;
					q.add(new int[] { next.num, next.dist + distsum });
				}
			}
		}
	}
}
