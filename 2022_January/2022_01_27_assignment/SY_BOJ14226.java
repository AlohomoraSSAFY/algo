package study0203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SY_BOJ14226 {
	
	static int S;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		bfs();
		System.out.println(result);
	}
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[1001][1001];
		q.offer(new int[] {1, 0, 0}); //화면에 있는 이모티콘 개수, 시간, 클립보드에 있는 이모티콘 개수
		visited[1][0] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			if (cur[0] == S) {
				result = cur[1];
				return;
			}
			
			if (!visited[cur[0]][cur[0]]) {
				visited[cur[0]][cur[0]] = true;
				q.offer(new int[] {cur[0], cur[1] + 1, cur[0]});
			}
			
			if (cur[2] > 0 && cur[0] + cur[2] <= S && !visited[cur[0] + cur[2]][cur[2]]) {
				visited[cur[0] + cur[2]][cur[2]] = true;
				q.offer(new int[] {cur[0] + cur[2], cur[1] + 1, cur[2]});
			}
			
			if (cur[0] >= 2 && !visited[cur[0] - 1][cur[2]]) {
				visited[cur[0] - 1][cur[2]] = true;
				q.offer(new int[] {cur[0] - 1, cur[1] + 1, cur[2]});
			}
		}
	}

}
