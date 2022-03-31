package date0324;

import java.util.*;
import java.io.*;

public class BOJ17140 {
	static int r, c, k;
	static int map[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());
		map = new int[100][100];
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

			}
		}

		int answer = -1;
		int time = 0;
		while (time < 100) {
			if (map[r][c] == k) {
				answer = time;
				break;
			}
			time++;

			int[] arr = getrowcolmax();
			if (arr[0] >= arr[1]) { // 행개수 >= 열개수
				// R 연산: 배열 A의 모든 행에 대해서 정렬을 수행한다.
				for (int i = 0; i < 100; i++) {
					int temp[] = new int[100];
					int[] count = new int[101];
					// 행의 각 숫자 개수 세기
					for (int j = 0; j < 100; j++) {
						if (map[i][j] != 0) {
							count[map[i][j]]++;
						}
					}
					PriorityQueue<Integer> q = new PriorityQueue<>();
					Queue<Integer> newq = new LinkedList<>();
					for (int j = 1; j <= 100; j++) {
						if (count[j] != 0)
							q.add(count[j]);
					}
					while (!q.isEmpty()) {
						int t = q.poll();
						for (int k = 1; k <= 100; k++) {
							if (count[k] == t) {
								count[k] = 0;
								newq.add(k);
								newq.add(t);
								break;
							}
						}
					}

					cleanrow(i);
					int idx = 0;
					while (!newq.isEmpty()) {
						int t = newq.poll();
						map[i][idx++] = t;
						if (idx == 99)
							break;
					}

				}

			} else {
				// C 연산: 배열 A의 모든 열에 대해서 정렬을 수행한다.
				for (int i = 0; i < 100; i++) {
					int temp[] = new int[100];
					int[] count = new int[101];
					// 행의 각 숫자 개수 세기
					for (int j = 0; j < 100; j++) {
						if (map[j][i] != 0) {
							count[map[j][i]]++;
						}
					}
					PriorityQueue<Integer> q = new PriorityQueue<>();
					Queue<Integer> newq = new LinkedList<>();
					for (int j = 1; j <= 100; j++) {
						if (count[j] != 0)
							q.add(count[j]);
					}
					while (!q.isEmpty()) {
						int t = q.poll();
						for (int k = 1; k <= 100; k++) {
							if (count[k] == t) {
								count[k] = 0;
								newq.add(k);
								newq.add(t);
								break;
							}
						}
					}

					cleancol(i);
					int idx = 0;
					while (!newq.isEmpty()) {
						map[idx++][i] = newq.poll();
						if (idx == 99)
							break;
					}

				}
			}

		}
		System.out.println(answer);
	}

	public static boolean allzero(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0)
				return false;
		}
		return true;
	}

	public static int[] getrowcolmax() {
		int[] arr = new int[2];
		int rmax = 0;
		int cmax = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] != 0) {
					rmax = Math.max(rmax, i);
					cmax = Math.max(cmax, j);
				}
			}
		}
		arr[0] = rmax;
		arr[1] = cmax;
		return arr;
	}
	public static void cleancol(int num) {
		for(int i=0;i<100;i++) {
			map[i][num] =0;
		}
	}
	public static void cleanrow(int num) {
		for(int i=0;i<100;i++) {
			map[num][i] =0;
		}
	}
	public static void printmap() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if(map[i][j] == 0)
					break;
				System.out.print(map[i][j]);
			}
			if(map[i][0] == 0)
				break;
			System.out.println();
		}
		System.out.println();
	}

}
