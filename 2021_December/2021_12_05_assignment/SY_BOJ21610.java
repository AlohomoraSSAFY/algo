package study1209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ21610 {
	
	static int N;
	static int M;
	static int[][] array;
	static Queue<int[]> cloud;
	static boolean[][] check;
	static int[][] dir = {{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
	static int[][] dir2 = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cloud = new LinkedList<>();
		cloud.offer(new int[] {N-1, 0});
		cloud.offer(new int[] {N-1, 1});
		cloud.offer(new int[] {N-2, 0});
		cloud.offer(new int[] {N-2, 1});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			//구름 이동
			check = new boolean[N][N];
			moveCloud(d, s);
			
			//대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수만큼 바구니의 물의 양이 증가
			for (int[] c : cloud) {
				int x = c[0];
				int y = c[1];
				
				int cnt = 0;
				for (int j = 0; j < 4; j++) {
					int nx = x + dir2[j][0];
					int ny = y + dir2[j][1];
					
					if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
					if (array[nx][ny] >= 1) cnt++;
				}
				
				array[x][y] += cnt;
			}
			
			//구름이 모두 사라짐
			cloud.clear();
			
			//구름이 사라진 칸이 아닌 곳에서 구름이 생기고, 물의 양이 2 줄어듦
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (check[j][k] || array[j][k] < 2) continue;
					
					cloud.offer(new int[] {j, k});
					array[j][k] -= 2;
				}
			}
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += array[i][j];
			}
		}
		System.out.println(result);
	}
	
	//구름 이동
	public static void moveCloud(int d, int s) {
		int size = cloud.size();
		for (int i = 0; i < size; i++) {
			int[] cur = cloud.poll();
			int x = cur[0];
			int y = cur[1];
			
			int nx = x + dir[d][0] * s;
			int ny = y + dir[d][1] * s;
			
			while (nx >= N) {
				nx -= N;
			}
			while (ny >= N) {
				ny -= N;
			}
			while (nx < 0) {
				nx += N;
			}
			while (ny < 0) {
				ny += N;
			}
			
			//구름에서 비가 내려 바구니 물의 양 1 증가
			array[nx][ny] += 1;
			check[nx][ny] = true;
			cloud.offer(new int[] {nx, ny});
		}
	}

}
