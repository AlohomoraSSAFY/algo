package date1216THU;

import java.io.*;
import java.util.*;

public class BOJ1939 {

	static int n, m;
	static int e, s;
	static ArrayList<int[]>[] list;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList[n + 1];
		for (int i = 0; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		int max = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new int[] { b, c });
			list[b].add(new int[] { a, c });
			max = Math.max(c, max);
		}

		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());

		//

		int left = 1;
		int right = max;

		while (left <= right) {
			int mid = (left + right) / 2;
			if (bfs(mid)) { // 운송가능
				left = mid + 1;
			} else
				right = mid - 1;
		}

		System.out.println(right);
	}

	public static boolean bfs(int mid) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[n + 1];
		q.add(s);
		visited[s] = true;
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int[] arr : list[temp]) {
				int to = arr[0];
				int weight = arr[1];
				if (weight >= mid) {
					if (to == e)
						return true;
					if (!visited[to]) {  
						q.add(to);
						visited[to] = true;
					}
				}
			}
		}
		return false;
	}
}
