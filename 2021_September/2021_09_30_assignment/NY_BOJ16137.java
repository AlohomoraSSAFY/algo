package date1003SUN;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

public class BOJ16137 {
	static int n, m;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };

	static boolean visited[][][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		visited = new boolean[2][n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//
		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0, 0, 0, 0 }); // 견우 초기위치 //네번째 원소는 오작교를 연속해서 건너지 않게 체크 (0이면 직전에 안 건넘 1이면 건넘)
		visited[0][0][0] = true;

		// 다섯번째는 오작교 한 번 만들었는지 체크
		while (!q.isEmpty()) {
			int[] temp = q.poll();

			int x = temp[0];
			int y = temp[1];

			//
		
			
			//
			int ns = temp[2] + 1;

			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (nx == n - 1 && ny == n - 1) {
					return temp[2] + 1;
				}

				if (nx < n && ny < n && nx >= 0 && ny >= 0 && !visited[temp[4]][nx][ny]) {
					if (map[nx][ny] == 1) { // 이동할 수 있는 일반적인 땅
						q.offer(new int[] { nx, ny, ns, 0, temp[4] });
						visited[temp[4]][nx][ny] = true;
					} else if (map[nx][ny] > 1 && temp[3] == 0) {// 오작교가 있고 직전에 오작교를 건너지 않으면
						if (ns % map[nx][ny] == 0) { // 다음 시간의 배수면 건널 수 있으므로 큐에 넣음
							q.offer(new int[] { nx, ny, ns, 1, temp[4] });
							visited[temp[4]][nx][ny] = true;
						}
					} else if (map[nx][ny] == 0 && temp[3] == 0 && temp[4] == 0) { // 오작교 놓을 수 있으면 놓기
						if (!crosscheck(nx, ny)) { // 교차 안 하는지 확인
							if (ns % m == 0) { // 다음 시간이 m의 배수면
								q.offer(new int[] { nx, ny, ns, 1, 1 });
								visited[1][nx][ny] = true;
							}
						}
					}
				}
			}
			if (map[x][y] == 1) //일반 땅이면 제자리에서 기다리는것도 가능
				q.offer(new int[] { x, y, ns, 0, temp[4] }); 
		}
		return 0;
	}

	public static boolean crosscheck(int x, int y) {
		boolean w = false;
		boolean h = false;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < n && ny < n && nx >= 0 && ny >= 0 && map[nx][ny] == 0) {
				if (i == 0 || i == 1) {
					h = true;
				} else if (i == 2 || i == 3)
					w = true;
			}
		}

		if (h && w)
			return true;
		else
			return false;
	}

}
