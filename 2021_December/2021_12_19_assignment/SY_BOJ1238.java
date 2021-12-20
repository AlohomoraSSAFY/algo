package study1223;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ1238 {
	
	static int N, M, K;
	
	static class Path implements Comparable<Path> {
		int to;
		int time;
		
		public Path(int to, int time) {
			this.to = to;
			this.time = time;
		}
		
		public int compareTo(Path o) {
			return Integer.compare(this.time, o.time);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		List<Path>[] list = new ArrayList[N+1];
		List<Path>[] rList = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			list[i] = new ArrayList<>();
			rList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
			list[from].add(new Path(to, time));
			rList[to].add(new Path(from, time));
		}
		
		int[] distance = new int[N+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[K] = 0;
		int[] rDistance = new int[N+1];
		Arrays.fill(rDistance, Integer.MAX_VALUE);
		rDistance[K] = 0;
		
		dijkstra(list, distance);
		dijkstra(rList, rDistance);
		
		int result = 0;
		for (int i = 1; i < N+1; i++) {
			if (distance[i] + rDistance[i] > result)
				result = distance[i] + rDistance[i];
		}
		System.out.println(result);
	}
	
	public static void dijkstra(List<Path>[] list, int[] distance) {
		PriorityQueue<Path> pq = new PriorityQueue<>();
		pq.offer(new Path(K, 0));
		boolean[] visited = new boolean[N+1];
		
		while (!pq.isEmpty()) {
			Path cur = pq.poll();
			
			if (visited[cur.to]) continue;
			visited[cur.to] = true;
			
			for (int i = 0; i < list[cur.to].size(); i++) {
				Path temp = list[cur.to].get(i);
				if (distance[temp.to] > distance[cur.to] + temp.time) {
					distance[temp.to] = distance[cur.to] + temp.time;
					pq.offer(new Path(temp.to, distance[temp.to]));
				}
			}
		}
	}

}
