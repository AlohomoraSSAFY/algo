package study0324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ20058 {
	
	static int N, Q;
	static int size;
	static int[][] array;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static boolean[][] visited;
	static int total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		size = (int)Math.pow(2, N);
		array = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < size; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			//격자 나누고 돌리기
			array = rotate((int)Math.pow(2, Integer.parseInt(st.nextToken())));
			//얼음 녹이기
			melt();
		}
		
		visited = new boolean[size][size];
		int max = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (visited[i][j] || array[i][j] == 0) continue;
				int cnt = bfs(i, j);
				max = Math.max(max, cnt);
			}
		}
		
		System.out.println(total);
		System.out.println(max);
	}
	
	private static int[][] rotate(int r) {		
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i += r) {
			for (int j = 0; j < size; j += r) {
				for (int l = 0; l < r; l++) {
					for (int k = 0; k < r; k++) {
						temp[i + l][j + k] = array[i + r - k - 1][j + l];
					}
				}
			}
		}
		
		return temp;
	}
	
	private static void melt() {
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				temp[i][j] = array[i][j];
			}
		}
		
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (temp[i][j] == 0) continue;
				
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nx = i + d[k][0];
					int ny = j + d[k][1];
					
					if (nx < 0 || nx >= size || ny < 0 || ny >= size) continue;
					if (temp[nx][ny] > 0) cnt++;
				}
				
				if (cnt < 3) {
					array[i][j]--;
				}
			}
		}
	}
	
	private static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		total += array[x][y];
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= size || ny < 0 || ny >= size || visited[nx][ny]) continue;
				if (array[nx][ny] == 0) continue;
				
				q.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
				total += array[nx][ny];
				cnt++;
			}
		}
		
		return cnt;
	}

}
