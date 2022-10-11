import java.io.*;
import java.util.*;

public class Main {

	static int n, m, t;

	static int one[][];
	static boolean visited[][];

	static int dx[] = { 0, 0, 1, -1 };
	static int dy[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub==
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		one = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				one[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			rotate(x, d, k);
			if (!check()) { // 인접하면서 같은 수가 없는 경우
				getavgandplusminus(); // 원판에 적힌 수의 평균을 구하고 평균보다 큰 수에서는 1을 빼고 작은 수에 1 더하기
			}

		}
		System.out.println(getsum());
	}

	public static void print() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				System.out.print(one[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}

	public static int getsum() {
		int sum = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				sum += one[i][j];
		}
		return sum;
	}

	public static void getavgandplusminus() {
		double avg = 0;
		double sum = getsum();
		double cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (one[i][j] != 0)
					cnt++;

			}
		}
		avg = sum / cnt;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (one[i][j] != 0) {
					if (one[i][j] < avg)
						one[i][j]++;
					else if (one[i][j] > avg)
						one[i][j]--;
				}
			}
		}
	}

	public static boolean check() {
		boolean ischanged = false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (one[i][j] != 0) {
					if (bfs(i, j)) {
						ischanged = true;
					}
				}
			}
		}

		return ischanged;
	}

	public static boolean bfs(int x, int y) {
		boolean ischanged = false;
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { x, y });
		int num = one[x][y];
		one[x][y] = 0;
		while (!q.isEmpty()) {
			int temp[] = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];

				if (nx < n && nx >= 0) {
					if (ny >= m || ny < 0) {
						ny = (ny == -1 ? m - 1 : 0);
					}
					if (one[nx][ny] == num) {
						ischanged = true;
						one[nx][ny] = 0;
						q.add(new int[] { nx, ny });
					}
				}
			}

		}
		if (!ischanged)
			one[x][y] = num;
		return ischanged;
	}

	public static void rotate(int x, int d, int k) {
		for (int i = 0; i < n; i++) {
			if ((i + 1) % x != 0) // 번호가 x의 배수인 원판만 회전
				continue;
			for (int j = 0; j < k; j++) { // k칸 회전
				if (d == 0) { // 시계방향인 경우
					int temp = one[i][m - 1];
					for (int l = m - 1; l >= 1; l--) {
						one[i][l] = one[i][l - 1];
					}
					one[i][0] = temp;
				} else { // 반시계방향인 경우
					int temp = one[i][0];
					for (int l = 0; l < m - 1; l++) {
						one[i][l] = one[i][l + 1];
					}
					one[i][m - 1] = temp;
				}
			}
		}
	}
}