package date0930THU;

import java.io.*;
import java.util.*;

public class BOJ13913 {
	static int n, k;
	static int[] list;
	static boolean[] visited;
	static int check;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		Stack<Integer> s = new Stack<>();
		list = new int[100001];
		visited = new boolean[100001];

		bfs();

		int temp = k;
		int count = 0;
		while (temp != n) {
			s.add(temp);
			temp = list[temp];
			count++;

		}
		s.add(temp);

		sb.append(count + "\n");
		while (!s.isEmpty())
			sb.append(s.pop() + " ");
		System.out.println(sb.toString());
	}

	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		visited[n] = true;
		q.offer(n);
		while (!q.isEmpty()) {
			int temp = q.poll();

			int a[] = new int[3];
			a[0] = temp + 1;
			a[1] = temp - 1;
			a[2] = temp * 2;

			for (int i : a) {
				if (i >= 0 && i < 100001 && !visited[i]) {
					list[i] = temp;
					visited[i] = true;
					q.offer(i);
				}
			}

			if (list[k] != 0)
				return;

		}
	}
}
