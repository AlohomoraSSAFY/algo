package study0224;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ14442 {
	
	static int N, M, K;
	static int[][] array;
	static int result;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		array = new int[N][M];
		result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				array[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs();
		
		if (result == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}
	
	private static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[3] - o2[3];
			}
		});
		q.offer(new int[] {0, 0, 0, 1});
		boolean[][][] visited = new boolean[N][M][K+1];
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == N - 1 && cur[1] == M - 1) {
				result = cur[3];
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (array[nx][ny] == 1) {
					if (cur[2] + 1 <= K && !visited[nx][ny][cur[2] + 1]) {
						q.offer(new int[] {nx, ny, cur[2] + 1, cur[3] + 1});
						visited[nx][ny][cur[2] + 1] = true;
					}
				} else if (!visited[nx][ny][cur[2]]) {
					q.offer(new int[] {nx, ny, cur[2], cur[3] + 1});
					visited[nx][ny][cur[2]] = true;
				}
			}
		}
	}

}
