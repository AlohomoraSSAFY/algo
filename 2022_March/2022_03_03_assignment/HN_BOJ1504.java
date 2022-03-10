package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HN_BOJ1504 {
	static int INF = 87654321;
	static int N, E, V1, V2, answer;
	static int dist[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		dist = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		int f, t, c;
		for (int e = 0; e < E; e++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			dist[f][t] = dist[t][f] = c;
		}
		st = new StringTokenizer(br.readLine());
		V1 = Integer.parseInt(st.nextToken());
		V2 = Integer.parseInt(st.nextToken());

		// 경우지
		for (int i = 1; i <= N; i++) {
			// 출발지
			for (int j = 1; j <= N; j++) {
				// 도착지
				if (i == j || dist[i][j] == INF)
					continue;
				for (int k = 1; k <= N; k++) {
					if (i == k || j == k || dist[j][i] == INF || dist[i][k] == INF)
						continue;
					dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
				}
			}
		}

		answer = Math.min(dist[1][V1] + dist[V1][V2] + dist[V2][N], dist[1][V2] + dist[V2][V1] + dist[V1][N]);
		if (answer >= INF)
			answer = -1;
		System.out.println(answer);
	}
}
