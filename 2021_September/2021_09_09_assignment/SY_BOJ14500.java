package study0912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14500 {
	
	static int N;
	static int M;
	static int[][] array;
	static boolean[][] visited;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;

	public static void main(String[] args) throws IOException {
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
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				recursion(i, j, 1, array[i][j]);
				visited[i][j] = false;
				
				if (i-1 >= 0 && j-1 >= 0 && j+1 < M) {
					result = Math.max(result, array[i][j] + array[i-1][j] + array[i][j-1] + array[i][j+1]);
				}
				
				if (i-1 >= 0 && i+1 < N && j+1 < M) {
					result = Math.max(result, array[i][j] + array[i-1][j] + array[i+1][j] + array[i][j+1]);
				}
				
				if (i+1 < N && j-1 >= 0 && j+1 < M) {
					result = Math.max(result, array[i][j] + array[i][j-1] + array[i+1][j] + array[i][j+1]);
				}
				
				if (i-1 >= 0 && i+1 < N && j-1 >= 0) {
					result = Math.max(result, array[i][j] + array[i-1][j] + array[i][j-1] + array[i+1][j]);
				}
 			}
		}
		
		System.out.println(result);
	}
	
	public static void recursion(int x, int y, int cnt, int sum) {
		if (cnt == 4) {
			result = Math.max(result, sum);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + d[i][0];
			int ny = y + d[i][1];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M  && !visited[nx][ny]) {
				visited[nx][ny] = true;
				recursion(nx, ny, cnt + 1, sum + array[nx][ny]);
				visited[nx][ny] = false;
			}
		}
	}

}
