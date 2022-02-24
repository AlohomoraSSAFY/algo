package date0224;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ14442 {
static int n,m,k;
static int ex,ey;
static int map[][];
static int dx[] = {0,0,1,-1};
static int dy[] = {1,-1,0,0};
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		ex = n-1;
		ey = m-1;
		
		map = new int[n][m];
		
		for(int i=0;i<n;i++) {
			String str = br.readLine();
			for(int j=0;j<m;j++) {
				map[i][j] = str.charAt(j)-'0';
			}
		}
		
		//
		
		System.out.println(bfs());
		
	}
	
	public static int bfs() {
		boolean visited[][][] = new boolean[k+1][n][m];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0,0,0,1});
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			int kk = temp[2];
			int dist = temp[3];
			
			if(x == ex && y == ey) {
				return dist;
			}
			
			for(int d=0;d<4;d++) {
				int nx = x +  dx[d];
				int ny = y +  dy[d];
				
				if( nx < n && ny < m && nx>=0 && ny>=0) {
					if(map[nx][ny] == 1 && kk < k && !visited[kk+1][nx][ny] ) {
						visited[kk+1][nx][ny] = true;
						q.offer(new int[] {nx, ny, kk+1, dist+1} );
					}
					else if (map[nx][ny] == 0 && !visited[kk][nx][ny]) {
						visited[kk][nx][ny] = true;
						q.offer(new int[] {nx, ny, kk, dist+1} );
					}
				}
			}
		}
		
		
		
		return -1;
	}

}
