package date0916THU;

import java.io.*;
import java.util.*;

public class BOJ9205 {
	static int tc, n;
	static int[][] xy;
	static boolean[] visited;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			n = Integer.parseInt(br.readLine());

			xy = new int[n + 2][2];
			visited = new boolean[n + 2];

			for (int i = 0; i < n + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				xy[i][0] = Integer.parseInt(st.nextToken());
				xy[i][1] = Integer.parseInt(st.nextToken());
			}

			//

			if (bfs())
				sb.append("happy\n");
			else
				sb.append("sad\n");

		} // eot

		System.out.println(sb.toString());
	}

	public static boolean bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(xy[0]);
		visited[0] = true;

		int dx = xy[n + 1][0];
		int dy = xy[n + 1][1];

		while (!q.isEmpty()) {
			int[] temp = q.poll();

			int tx = temp[0];
			int ty = temp[1];

			if (tx == dx && ty == dy) {
				return true;
			}

			for (int i = 1; i < n + 2; i++) {
				if (!visited[i] && (Math.abs(tx - xy[i][0]) + Math.abs(ty - xy[i][1])) <= 1000) {
					q.add(xy[i]);
					visited[i] = true;
				}
			}
		}
		return false;
	}

}
