package study0905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ2636 {
	
	static int N;
	static int M;
	static int[][] array;
	static int cheese;
	static int time;
	static int result;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] == 1) cheese++;
			}
		}
		
		while(cheese != 0) {
			++time;
			bfs(0, 0);
		}
		
		System.out.println(time);
		System.out.println(result);
	}
	
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		visited[x][y] = true;
		q.offer(new int[]{x, y});
		
		int count = 0;
		while(!q.isEmpty()) {
			int[] qTemp = q.poll();
			int tempX = qTemp[0];
			int tempY = qTemp[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = tempX + d[i][0];
				int ny = tempY + d[i][1];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && array[nx][ny] == 0 && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					q.offer(new int[]{nx, ny});
				} else if (nx >= 0 && nx < N && ny >= 0 && ny < M && array[nx][ny] == 1) {
					visited[nx][ny] = true;
					array[nx][ny] = 0;
					count++;
				}
			}
		}
		
		if(cheese - count == 0) {
			result = count;
		}
		cheese -= count;
	}

}
