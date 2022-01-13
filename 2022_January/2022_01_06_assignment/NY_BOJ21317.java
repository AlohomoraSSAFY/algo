package date0113THU;

import java.io.*;
import java.util.*;

public class BOJ21317 {
	static int n, k;
	static int jump[][];
	static int cost[];

	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		jump = new int[n + 2][2];
		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			jump[i][0] = Integer.parseInt(st.nextToken());
			jump[i][1] = Integer.parseInt(st.nextToken());

		}
		k = Integer.parseInt(br.readLine());

		//
		cost = new int[n + 3];

		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.MAX_VALUE;
		}
		bfs();
		System.out.println(cost[n - 1]);
	}

	public static void bfs() {

		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { 1, jump[0][0], 0 });
		q.add(new int[] { 2, jump[0][1], 0 });
		q.add(new int[] { 3, k, 1 });

		while (!q.isEmpty()) {
			int temp[] = q.poll();
			int cur = temp[0];
			int energy = temp[1];

			if (cur >= n || energy > cost[n - 1])
				continue;
			cost[cur] = Math.min(energy, cost[cur]);
			q.add(new int[] { cur + 1, energy + jump[cur][0], temp[2] });
			q.add(new int[] { cur + 2, energy + jump[cur][1], temp[2] });
			if (temp[2] == 0) { // 큰점프 가능
				q.add(new int[] { cur + 3, energy + k, 1 });
			}

		}

	}

}
