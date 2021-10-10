package study1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ11657 {

	static int N;
	static int M;
	static Bus[] route;
	static long distance[];
	static final long INF = Long.MAX_VALUE;
	
	static class Bus {
		int from;
		int to;
		int time;
		
		public Bus(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		route = new Bus[M+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			route[i] = new Bus(A, B, C);
		}
		
		distance = new long[N+1];
		Arrays.fill(distance, INF);
		
		if (bellmanford()) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 2; i < N+1; i++) {
				if (distance[i] == INF) {
					sb.append(-1 + "\n");
				} else {
					sb.append(distance[i] + "\n");
				}
			}
			System.out.println(sb);
		}
	}
	
	public static boolean bellmanford() {
		distance[1] = 0;
		
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < M; j++) {
				Bus b = route[j];
				if (distance[b.from] != INF && distance[b.from] + b.time < distance[b.to]) {
					distance[b.to] = distance[b.from] + b.time;
				}
			}
		}
		
		for (int j = 0; j < M; j++) {
			Bus b = route[j];
			if (distance[b.from] != INF && distance[b.from] + b.time < distance[b.to]) {
				return true;
			}
		}
		
		return false;
	}

}
