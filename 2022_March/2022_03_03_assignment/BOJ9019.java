package date0310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ9019 {
	static int tc;
	static int a, b;
	static String resultarr[];
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			resultarr = new String[10001];
			visited = new boolean[10001];

			StringTokenizer st = new StringTokenizer(br.readLine());

			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());

			bfs(a);

			sb.append(resultarr[b]);
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static void bfs(int num) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(num);
		visited[num] = true;
		resultarr[num] = "";

		while (!q.isEmpty()) {

			int temp = q.poll();

			int d = (temp * 2) % 10000;
			if (!visited[d]) {
				resultarr[d] = resultarr[temp] + "D";
				if (d == b)
					return;
				visited[d] = true;
				q.offer(d);
			}

			
			int s =temp == 0 ? 9999 : temp-1;
			if (!visited[s]) {
				resultarr[s] = resultarr[temp] + "S";
				if (s == b)
					return;
				visited[s] = true;
				q.offer(s);
			}

			int l =((temp * 10) % 10000) +  temp / 1000;
			if (!visited[l]) {
				resultarr[l] = resultarr[temp] + "L";
				if (l == b)
					return;
				visited[l] = true;
				q.offer(l);
			}
			
			int r = temp % 10 * 1000 + temp / 10;
			if (!visited[r]) {
				resultarr[r] = resultarr[temp] + "R";
				if (r == b)
					return;
				visited[r] = true;
				q.offer(r);
			}
		}

	}


}