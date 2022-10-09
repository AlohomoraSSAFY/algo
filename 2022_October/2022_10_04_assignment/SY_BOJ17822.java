package study1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SY_BOJ17822 {
	
	static int N, M, T;
	static List<Integer>[] list;
	static boolean[][] visited;
	static boolean flag;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int r = 0; r < T; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			rotate(x, d, k);
			
			flag = false;
			visited = new boolean[N+1][M];
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (list[i].get(j) != 0 && !visited[i][j]) {
						find(i, j, list[i].get(j));
					}
				}
			}
			
			if (!flag) {
				int cnt = 0;
				int sum = 0;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (list[i].get(j) != 0) {
							cnt++;
							sum += list[i].get(j);
						}
					}
				}
				
				double avg = (double)sum / cnt;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (list[i].get(j) != 0) {
							if (list[i].get(j) > avg) {
								list[i].set(j, list[i].get(j) - 1);
							} else if (list[i].get(j) < avg) {
								list[i].set(j, list[i].get(j) + 1);
							}
						}
					}
				}
			}
		}
		
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				result += list[i].get(j);
			}
		}
		
		System.out.println(result);
	}
	
	private static void rotate(int x, int d, int k) {
		for (int i = x; i <= N; i += x) {
			for (int j = 0; j < k; j++) {
				if (d == 0) { //시계 방향
					int num = list[i].remove(list[i].size() - 1);
					list[i].add(0, num);
				} else { //반시계 방향
					int num = list[i].remove(0);
					list[i].add(num);
				}
			}
		}
	}
	
	private static void find(int x, int y, int num) {
		for (int i = 0; i < 4; i++) {
			int nx = x + d[i][0];
			int ny = (y + d[i][1] + M) % M;
			
			if (nx < 1 || nx > N) continue;
			if (list[nx].get(ny) != num || visited[nx][ny]) continue;
			
			flag = true;
			list[nx].set(ny, 0);
			visited[nx][ny] = true;
			find(nx, ny, num);
		}
	}

}
