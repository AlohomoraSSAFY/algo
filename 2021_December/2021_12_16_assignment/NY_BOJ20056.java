package date1219SUN;

import java.io.*;
import java.util.*;

public class BOJ20056 {

	static int n, m, k;
	static int dx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int dy[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Queue<int[]> q[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		q = new LinkedList[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				q[i][j] = new LinkedList<>();
			}
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q[r][c].add(new int[] { m, s, d, 0 });
		}

		//
		for (int l = 0; l < k; l++) { // k번 이동
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int size= q[i][j].size();
					for (int h = 0; h < size; h++) {
						int[] temp = q[i][j].poll();
						int m = temp[0];
						int s = temp[1];
						int d = temp[2];
						int cnt = temp[3];
						int nx = ((i + (dx[d] * s) + (n * 1000)) % n);//
						int ny = ((j + (dy[d] * s) + (n * 1000)) % n);//
						if (cnt == l) {
							q[nx][ny].offer(new int[] { m, s, d, cnt + 1 });
						} else {
							q[i][j].offer(new int[] { m, s, d, cnt }); // 이미 이동한거면 다시 그대로 넣어줌
							break;
						}
					}
				}
			}
			// 이동 끝 2개 이상의 파이어볼 있는 칸 확인
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int size = q[i][j].size();
					if (size > 1) {
						int num = 1;
						int cnt=0;
						int msum = 0;
						int ssum = 0;
						boolean evencheck = true;
						boolean oddcheck = true;
						for (int h = 0; h < size; h++) {
							int temp[] = q[i][j].poll();
							msum += temp[0];
							ssum += temp[1];
							cnt  = temp[3];
							if (temp[2] % 2 == 0) // 짝수면
								oddcheck = false;
							else // 홀수면
								evencheck = false;
						}
						// 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
						if (oddcheck || evencheck)
							num = 0;
						if (msum / 5 > 0) { // 질량이 0인 파이어볼은 소멸
							for (int t = num; t < 8; t += 2) {
								q[i][j].add(new int[] { (msum / 5), (ssum / size), t,  cnt});
							}
						}
					}
				}
			}

		} // k번 이동 끝
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int size = q[i][j].size();
				for (int h = 0; h < size; h++) {
					int temp[] = q[i][j].poll();
					answer += temp[0];
				}
			}
		}
		System.out.println(answer);

	}

}
