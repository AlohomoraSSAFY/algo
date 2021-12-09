package date1205SUN;

import java.util.*;
import java.io.*;

public class BOJ16973 {

	static int n, m;
	static int h, w;
	static int sr, sc;
	static int fr, fc;
	static int answer;
	static int min;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 }; //오왼위아래
	static int dy[] = { 1, -1, 0, 0 };
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		sr = Integer.parseInt(st.nextToken())-1;
		sc = Integer.parseInt(st.nextToken())-1;

		fr = Integer.parseInt(st.nextToken())-1;
		fc = Integer.parseInt(st.nextToken())-1;

		//

		answer = -1;
		bfs();
		System.out.println(answer);

	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sr, sc, 0 });
		visited[sr][sc] = true;

		while (!q.isEmpty()) {
			int temp[] = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				int cnt = temp[2]+1;

				if( nx == fr && ny == fc) {
					answer= cnt;
					return;
				}
				
				if (nx+h <= n && nx >= 0 && ny+w <= m && ny >= 0 && map[nx][ny]==0 && !visited[nx][ny]) {
					if(wall(nx,ny)) { // 직사각형이 벽에 닿지 않는지 체크
						q.offer(new int[] {nx, ny, cnt});
//						System.out.println("nx: "+nx + " ny: "+ ny+" cnt: "+cnt+" d: "+d);
						visited[nx][ny] = true;
					}
				}	
			}
		}

	}

	private static boolean wall(int nx, int ny) {
		//nx ny 부터 직사각형 h,w 안의 범위 지도가 다 0이고 경우 이동 가능하므로 true 리턴
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(map[i+nx][j+ny] == 1) 
					return false;
			}
		}		
		return true;
	}

}
