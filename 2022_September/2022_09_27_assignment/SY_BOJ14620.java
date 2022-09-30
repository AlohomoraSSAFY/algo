package study1004;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14620 {
	
	static int N;
	static int[][] array;
	static boolean[][] visited;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		visited = new boolean[N][N];
		result = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recursion(0, 0);
		System.out.println(result);
	}
	
	private static void recursion(int cnt, int cost) {
		if (cnt == 3) {
			result = Math.min(result, cost);
			return;
		}
		
		for (int i = 1; i < N-1; i++) {
			for (int j = 1; j < N-1; j++) {
				if (visited[i][j] || visited[i-1][j] || visited[i+1][j] || visited[i][j-1] || visited[i][j+1]) continue;
				
				visited[i][j] = true;
				visited[i-1][j] = true;
				visited[i+1][j] = true;
				visited[i][j-1] = true;
				visited[i][j+1] = true;
				recursion(cnt + 1, cost + array[i][j] + array[i-1][j] + array[i+1][j] + array[i][j-1] + array[i][j+1]);
				visited[i][j] = false;
				visited[i-1][j] = false;
				visited[i+1][j] = false;
				visited[i][j-1] = false;
				visited[i][j+1] = false;
			}
		}
	}

}
