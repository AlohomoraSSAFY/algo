package date0630;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NY_BOJ2665 {

	static int n;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		int answer = 0;
		for (int i = 0; i < n * n; i++) {
			if (bfs(i)) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);

	}

	public static boolean bfs(int num) {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][][] = new boolean[num+1][n][n];

		q.add(new int[] {0,0,0}); //x 좌표, y 좌표, 바꾼 방 개수
		
		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];
			int count = temp[2];
			if (x== n - 1 && y == n - 1) { // 끝 방에 도착
				return true;
			}
			
			for(int d=0;d<4;d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if(nx < n && ny < n && nx >=0 && ny >=0) {
					if(map[nx][ny] == 1 && !visited[count][nx][ny]) {
						visited[count][nx][ny] = true;
						q.add(new int[] {nx, ny, count});
					}else {
						if(count<num && !visited[count+1][nx][ny]) {
							visited[count+1][nx][ny] = true;
							q.add(new int[] {nx, ny, count +1});
						}
					}
				}
			}
			

			
		}

		return false;
	}

}
