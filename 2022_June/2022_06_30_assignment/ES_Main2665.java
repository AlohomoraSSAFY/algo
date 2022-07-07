package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2665 {
	
	static int n;
	static int[][] map;
	static int[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0, 0});
		visited[0][0] = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0]; int c = cur[1];
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nc <0 || nr > n-1 || nc > n-1) continue;
				if(visited[nr][nc] <= visited[r][c]) continue;
				
				if(map[nr][nc] == 1) {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = visited[r][c];
				}else {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = visited[r][c] +1;
				}
			}
		}
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		visited = new int[n][n];
		for(int i =0; i < n; i++) {
			String[] line = br.readLine().split("");
			for(int j =0; j < n; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				visited[i][j] = n*n+2;
			}
		}
		
		bfs();
		
		bw.write(visited[n-1][n-1]+"\n");
		
		bw.close();
		br.close();
	}

}