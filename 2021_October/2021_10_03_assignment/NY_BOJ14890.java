package date1007THU;

import java.util.*;
import java.io.*;

public class BOJ14890 {
	static int n, l, count;
	static int map[][];
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken()); // 경사로 길이, 높이는 1
		map = new int[n][n];
		count = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//
		for (int i = 0; i < n; i++) { // 가로줄 체크
			visited = new boolean[n];
			boolean flag = true;
			for (int j = 0; j < n - 1; j++) {
				if (map[i][j] == map[i][j + 1]) { // 차이가 없으면

				} else if (map[i][j] - map[i][j + 1] == 1) { // 앞이 더 높으면
					int low = map[i][j + 1];
					if (j + l < n) {// 경사로가 범위 안이고
						for (int k = j + 1; k <= j + l; k++) { // 경사로를 놓을 수 있는 범위만큼 체크
							if (map[i][k] != low || visited[k]) {// 연속된 블럭의 높이가 모두 동일하지 않거나 방문했으면
								flag = false; // 실패
								if (i == 0)
									break;
							}
						}
						if (!flag) {

							break;
						} else { // 경사로를 놓을 수 있음
							for (int k = j + 1; k <= j + l; k++)
								visited[k] = true;
						}

					} else {
						flag = false;
						break;
					}

				} else if (map[i][j] - map[i][j + 1] == -1) { // 뒤가 더 높으면
					int low = map[i][j];
					if (j + 1 - l >= 0) {// 경사로가 범위 안이고
						for (int k = j; k > j - l; k--) { // 경사로를 놓을 수 있는 범위만큼 체크
							if (map[i][k] != low || visited[k]) {// 연속된 블럭의 높이가 모두 동일하지
								flag = false; // 실패
								break;
							}
						}
						if (!flag)
							break;
						else { // 경사로를 놓을 수 있음
							for (int k = j; k > j - l; k--)
								visited[k] = true;

						}
					} else {
						flag = false;
						break;
					}
				} else { // 차이가 2 이상이면 못 지나감
					flag = false;
					break;
				}
			}
			if (flag) {
				count++;
			}
		}

		for (int i = 0; i < n; i++) { // 세로줄 체크
			visited = new boolean[n];
			boolean flag = true;
			for (int j = 0; j < n - 1; j++) {

				if (map[j][i] == map[j + 1][i]) { // 차이가 없으면

				} else if (map[j][i] - map[j + 1][i] == 1) { // 앞이 더 높으면
					int low = map[j + 1][i];
					if (j + l < n) {// 경사로가 범위 안이고
						for (int k = j + 1; k <= j + l; k++) { // 경사로를 놓을 수 있는 범위만큼 체크
							if (map[k][i] != low || visited[k]) {// 연속된 블럭의 높이가 모두 동일하지 않거나 방문했으면
								flag = false; // 실패
								if (i == 0)
								break;
							}
						}
						if (!flag)
							break;
						else { // 경사로를 놓을 수 있음
							for (int k = j + 1; k <= j + l; k++)
								visited[k] = true;
						}
					} else {
						flag = false;
						break;
					}

				} else if (map[j][i] - map[j + 1][i] == -1) { // 뒤가 더 높으면
					int low = map[j][i];
					if (j - l + 1 >= 0) {// 경사로가 범위 안이고
						for (int k = j; k > j - l; k--) { // 경사로를 놓을 수 있는 범위만큼 체크
							if (map[k][i] != low || visited[k]) { // 않으면
								flag = false; // 실패
								break;
							}
						}
						if (!flag)
							break;
						else { // 경사로를 놓을 수 있음
							for (int k = j; k > j - l; k--)
								visited[k] = true;
						}
					} else {
						flag = false;
						break;
					}
				} else { // 차이가 2 이상이면 못 지나감
					flag = false;
					break;
				}
			}
			if (flag) {
				count++;
			}
		}
		System.out.println(count);
	}

}
