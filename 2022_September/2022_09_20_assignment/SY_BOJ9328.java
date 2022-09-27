package study0927;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ9328 {
	
	static int h, w;
	static char[][] array;
	static List<int[]> eList;
	static boolean[] key;
	static boolean[][] visited;
	static List<Pos>[] dList;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;
	
	static class Pos {
		int x;
		int y;
		
		public Pos (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			array = new char[h][w];
			eList = new ArrayList<>();
			key = new boolean[26];
			visited = new boolean[h][w];
			dList = new ArrayList[26];
			for (int i = 0; i < 26; i++) {
				dList[i] = new ArrayList<>();
			}
			result = 0;
			for (int i = 0; i < h; i++) {
				array[i] = br.readLine().toCharArray();
				for (int j = 0; j < w; j++) {
					if (i == 0 || i == h-1 || j == 0 || j == w-1) {
						if (array[i][j] != '*') {
							if (array[i][j] == '$') {
								result++;
								array[i][j] = '.';
							} else if (array[i][j] >= 'a' && array[i][j] <= 'z') {
								key[array[i][j] - 'a'] = true;
							}
							eList.add(new int[] {i, j});
						}
					}
				}
			}
			
			char[] kList = br.readLine().toCharArray();
			for (int i = 0; i < kList.length; i++) {
				if (kList[i] == '0') break;
				key[kList[i] - 'a'] = true;
			}
			
			for (int i = 0; i < eList.size(); i++) {
				int x = eList.get(i)[0];
				int y = eList.get(i)[1];
				
				if (array[x][y] >= 'A' && array[x][y] <= 'Z') {
					if (key[array[x][y] - 'A']) {
						bfs(x, y);
					} else {
						dList[array[x][y] - 'A'].add(new Pos(x, y));
					}
				} else {
					bfs(x, y);
				}
			}
			
			System.out.println(result);
		}
	}
	
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + d[i][0];
				int ny = cur[1] + d[i][1];
				
				if (nx < 0 || nx >= h || ny < 0 || ny >= w || visited[nx][ny] || array[nx][ny] == '*') continue;
				if (array[nx][ny] == '.') {
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				} else if (array[nx][ny] == '$') {
					result++;
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				} else if (array[nx][ny] >= 'a' && array[nx][ny] <= 'z') {
					key[array[nx][ny] - 'a'] = true;
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
					
					int temp = array[nx][ny] - 'a';
					for (int j = 0; j < dList[temp].size(); j++) {
						Pos door = dList[temp].get(j);
						q.offer(new int[]{door.x, door.y});
						visited[door.x][door.y] = true;
					}
					dList[temp] = new ArrayList<>();
				} else {
					if (key[array[nx][ny] - 'A']) {
						q.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
					} else {
						dList[array[nx][ny] - 'A'].add(new Pos(nx, ny));
					}
				}
			}
		}
	}

}
