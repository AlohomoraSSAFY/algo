package study0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1189 {
	
	static int R;
	static int C;
	static int K;
	static char[][] array;
	static boolean[][] visited;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new char[R][C];
		visited = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			array[i] = br.readLine().toCharArray();
		}
		
		visited[R-1][0] = true;
		recursion(R-1, 0, 1);
		System.out.println(count);
	}
	
	public static void recursion(int x, int y, int cnt) {
		if (cnt == K) {
			if (x == 0 && y == C - 1) count++;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + d[i][0];
			int ny = y + d[i][1];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C
					&& visited[nx][ny] == false && array[nx][ny] == '.') {
				visited[nx][ny] = true;
				recursion(nx, ny, cnt+1);
				visited[nx][ny] = false;
			}
		}
	}

}
