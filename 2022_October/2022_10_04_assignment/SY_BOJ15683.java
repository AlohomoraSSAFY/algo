package study1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ15683 {
	
	static int N, M;
	static int[][] array;
	static List<CCTV> list;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int[][] dir = {{0}, {0}, {0, 2}, {0, 1}, {0, 1, 2}, {0, 1, 2, 3}};
	static int result;
	
	static class CCTV {
		int num;
		int x;
		int y;
		
		public CCTV(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
				if (array[i][j] != 0 && array[i][j] != 6) list.add(new CCTV(array[i][j], i, j));
			}
		}
		
		result = Integer.MAX_VALUE;
		recursion(0, new boolean[N][M]);
		
		System.out.println(result);
	}
	
	private static void recursion(int cnt, boolean[][] check) {
		if (cnt == list.size()) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (array[i][j] == 0 && !check[i][j]) count++;
				}
			}
			
			result = Math.min(result, count);
			return;
		}
		
		int num = list.get(cnt).num;
		int cx = list.get(cnt).x;
		int cy = list.get(cnt).y;
		for (int t = 0; t < 4; t++) {
			boolean[][] possible = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					possible[i][j] = check[i][j];
				}
			}
			
			for (int r = 0; r < dir[num].length; r++) {
				int nx = cx;
				int ny = cy;
				int dx = d[(dir[num][r] + t) % 4][0];
				int dy = d[(dir[num][r] + t) % 4][1];
				
				while (true) {
					nx += dx;
					ny += dy;
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= M || array[nx][ny] == 6) break;
					possible[nx][ny] = true;
				}
			}
			
			recursion(cnt + 1, possible);
		}
	}

}
