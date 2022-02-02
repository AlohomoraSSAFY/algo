package study0203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ1865 {
	
	static int N, M, W;
	static Road[] route;
	static final int INF = 987654321;
	
	static class Road {
		int from;
		int to;
		int time;
		
		public Road(int from, int to, int time) {
			this.from = from;
			this.to = to;
			this.time = time;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < TC; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			route = new Road[2*M+W];
			
			int idx = 0;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				route[idx++] = new Road(S, E, T);
				route[idx++] = new Road(E, S, T);
			}
			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				route[idx++] = new Road(S, E, T * (-1));
			}
			
			if (bellmanFord()) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		
		System.out.println(sb);
	}
	
	public static boolean bellmanFord() {
		int[] distance = new int[N+1];
		Arrays.fill(distance, INF);
		distance[1] = 0;
		
		for (int i = 0; i < N-1; i++) {
			for (int j = 0; j < 2*M+W; j++) {
				Road road = route[j];
				if (distance[road.from] + road.time < distance[road.to]) {
					distance[road.to] = distance[road.from] + road.time;
				}
			}
		}
		
		for (int j = 0; j < 2*M+W; j++) {
			Road road = route[j];
			if (distance[road.from] + road.time < distance[road.to]) {
				return true;
			}
		}
		
		return false;
	}

}
