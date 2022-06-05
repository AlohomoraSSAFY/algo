package study0609;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ18430 {
	
	static int N, M;
	static int[][] array;
	static boolean[][] visited;
	static int[][] d = {{0, -1, 1, 0}, {0, -1, -1, 0}, {-1, 0, 0, 1}, {1, 0, 0, 1}};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recursion(0, 0);
		System.out.println(result);
	}
	
	private static void recursion(int idx, int sum) {
		if (idx == N * M) {
			result = Math.max(result, sum);
			return;
		}
		
		int x = idx / M;
		int y = idx % M;
		
		if (!visited[x][y]) {
			for (int i = 0; i < 4; i++) {
				int nx1 = x + d[i][0];
				int ny1 = y + d[i][1];
				int nx2 = x + d[i][2];
				int ny2 = y + d[i][3];
				
				if (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M
						|| nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) continue;
				if (visited[nx1][ny1] || visited[nx2][ny2]) continue;
				
				visited[x][y] = true;
				visited[nx1][ny1] = true;
				visited[nx2][ny2] = true;
				recursion(idx + 1, sum + (array[x][y] * 2) + array[nx1][ny1] + array[nx2][ny2]);
				visited[x][y] = false;
				visited[nx1][ny1] = false;
				visited[nx2][ny2] = false;
			}
		}
		
		recursion(idx + 1, sum);
	}

}
