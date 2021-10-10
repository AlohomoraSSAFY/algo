package date1010SUN;

import java.io.*;
import java.util.*;

public class BOJ2234 {
	static int n, m;
	static int map[][];
	static int visited[][];
	static int answer3;
	static int area;
	static int dx[] = { 0, -1, 0, 1 }; // 서 북 동 남
	static int dy[] = { -1, 0, 1, 0 };
	static ArrayList<Integer> arealist;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new int[n][m];

		answer3 = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//
		int count = 0;
		int maxarea = 0;
		int maxindex = 0;
		arealist = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j] == 0) {
					count++;
					bfs(i, j, count);
					arealist.add(area);
					if (area > maxarea) {
						maxarea = area;
						maxindex = count;
					}
				}
			}
		}

		//

		System.out.println(count);
		System.out.println(maxarea);
		System.out.println(search());
	}

	public static void bfs(int x, int y, int count) {
		Queue<int[]> q = new LinkedList<>();
		area = 0;
		visited[x][y] = count;
		q.add(new int[] { x, y });

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			area++;
			int tx = temp[0];
			int ty = temp[1];
			for (int i = 0; i < 4; i++) {
				String binary = Integer.toBinaryString(map[tx][ty]);

				for (int d = 0; d < 4; d++) {
					if (((1 << d) & map[tx][ty]) != (1 << d)) {
						int nx = tx + dx[d];
						int ny = ty + dy[d];

						if (nx >= 0 && ny >= 0 && nx < n && ny < m && visited[nx][ny] == 0) {
							visited[nx][ny] = count;
							q.offer(new int[] { nx, ny, });
						}
					}
				}
			}
		}

	}

	public static int search() {
		int max  =0;
		//가로
		for(int i=0;i<n;i++) {
			for(int j=0;j<m-1;j++) {
				if(visited[i][j] != visited[i][j+1]) {
					max = Integer.max(max, arealist.get(visited[i][j]-1) + arealist.get(visited[i][j+1]-1));
				}
			}
		}
		//세로
		for(int i=0;i<m;i++) {
			for(int j=0;j<n-1;j++) {
				if(visited[j][i] != visited[j+1][i]) {
					max = Integer.max(max, arealist.get(visited[j][i]-1) + arealist.get(visited[j+1][i]-1));
				}
			}
		}
		
		return max;
	}

}