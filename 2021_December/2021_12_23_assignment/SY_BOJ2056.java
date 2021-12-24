package study1226;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2056 {

	static int N;
	static int[] timeArr;
	static List<Integer>[] graph;
	static int[] indegree;
	static int time;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		timeArr = new int[N+1];
		graph = new ArrayList[N+1];
		for (int i = 0; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		indegree = new int[N+1];
		
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			timeArr[i] = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int before = Integer.parseInt(st.nextToken());
				graph[before].add(i);
				indegree[i]++;
			}
		}
		
		topologicalSort();
		System.out.println(time);
	}
	
	public static void topologicalSort() {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 1; i < N+1; i++) {
			if (indegree[i] == 0) {
				q.offer(new int[] {i, 1});
			}
		}
		
		while (!q.isEmpty()) {
			time++;
			
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				int cNum = cur[0];
				int cTime = cur[1];
				
				if (cTime == timeArr[cNum]) {
					for (int temp : graph[cNum]) {
						indegree[temp]--;
						if (indegree[temp] == 0) {
							q.offer(new int[] {temp, 1});
						}
					}
				} else {
					q.offer(new int[] {cNum, cTime + 1});
				}
			}
		}
	}

}
