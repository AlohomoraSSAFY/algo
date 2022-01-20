package date0120THU;

import java.io.*;
import java.util.*;

public class BOJ13023 {
	static int n, m;
	static boolean visited[], graph[][];
	static ArrayList<Integer> list[] ;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		list = new ArrayList[n];

		for (int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}
		for (int i = 0; i < n; i++) {
			visited = new boolean[n];

			visited[i] = true;
			dfs(1, i);
		}

		System.out.println(0);
	}

	public static void dfs(int count, int a) {
		if (count == 5) {
			System.out.println(1);
			System.exit(0);
		}

		for (int i : list[a]) {
			if (!visited[i]) {
				visited[i] = true;
				dfs(count + 1, i);
				visited[i] = false;
			}
		}
	}

}
