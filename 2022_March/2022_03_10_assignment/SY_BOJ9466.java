package study0317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ9466 {
	
	static int n;
	static int[] array;
	static boolean[] visited;
	static boolean[] finished;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			n = Integer.parseInt(br.readLine());
			array = new int[n+1];
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			cnt = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				array[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= n; i++) {
				if (visited[i]) continue;
				dfs(i);
			}
			
			sb.append((n - cnt) + "\n");
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int num) {
		visited[num] = true;
		
		if (!visited[array[num]]) {
			dfs(array[num]);
		} else {
			if (!finished[array[num]]) {
				int next = array[num];
				cnt++;
				while (next != num) {
					cnt++;
					next = array[next];
				}
			}
		}
		
		finished[num] = true;
	}

}
