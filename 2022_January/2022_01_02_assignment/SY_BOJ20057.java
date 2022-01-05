package study0106;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ20057 {
	
	static int N;
	static int[][] array;
	static int[][] d = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[] percent = { 1, 1, 2, 2, 5, 7, 7, 10, 10};
	static int[][] moveX = {{-1, 1, -2, 2, 0, -1, 1, -1, 1},
							{-1, -1, 0, 0, 2, 0, 0, 1, 1},
							{-1, 1, -2, 2, 0, -1, 1, -1, 1},
							{1, 1, 0, 0, -2, 0, 0, -1, -1}};
	static int[][] moveY = {{1, 1, 0, 0, -2, 0, 0, -1, -1},
							{-1, 1, -2, 2, 0, -1, 1, -1, 1},
							{-1, -1, 0, 0, 2, 0, 0, 1, 1},
							{-1, 1, -2, 2, 0, -1, 1, -1, 1}};
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		tornado();
		System.out.println(result);
	}
	
	public static void tornado() {
		int x = N/2;
		int y = N/2;
		int dir = 0;
		int move = 1;
		int cnt = 0;
		
		while (true) {
			for (int t = 0; t < move; t++) {
				x += d[dir][0];
				y += d[dir][1];
				
				int total = array[x][y];
				for (int i = 0; i < 9; i++) {
					int nx = x + moveX[dir][i];
					int ny = y + moveY[dir][i];
					int amount = array[x][y] * percent[i] / 100;
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
						result += amount;
					} else {
						array[nx][ny] += amount;
					}
					
					total -= amount;
				}
				
				int nx = x + d[dir][0];
				int ny = y + d[dir][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					result += total;
				} else {
					array[nx][ny] += total;
				}
				
				array[x][y] = 0;
			}
			
			dir = (++dir) % 4;
			if (++cnt == 2) {
				cnt = 0;
				if (x > 0) move++;
			}
			
			if (x == 0 && y == 0) break;
		}
	}

}
