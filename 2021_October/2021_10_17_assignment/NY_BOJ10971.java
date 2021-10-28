package date1021THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10971 { //외판원 순회 2

	static int n;
	static int graph[][];
	static boolean visited[];
	static int min;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		min = Integer.MAX_VALUE;
		dfs(0, visited, 0,0);
		System.out.println(min);
		
	}

	public static void dfs(int cur, boolean[] visited, int cost, int count) {
		visited[cur] = true;
		if(cost>=min)
			return;
		
		if (count==n-1) {
			if (graph[cur][0] != 0) {
				min = Math.min(min, cost + graph[cur][0]);
			}
			return;
		}
		
		for (int i = 1; i < n; i++) {
			if (graph[cur][i] != 0 && !visited[i]) {
				visited[i] = true;
				dfs(i, visited, cost+ graph[cur][i],count+1);
				visited[i] = false;
			}
		}

	}

}
