package com.baekjoon.problem29;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class HN_BOJ23290 {
	static final int moveNum = 3;
	static final int N = 5;
	static int M, S;
	static long answer;
	static int fishMap[][][];
	static boolean dieMap[][][];

	static class Fish {
		int y, x, d, num;

		public Fish(int y, int x, int d, int num) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.num = num;
		}
	}

	static Queue<Fish> copyQ = new LinkedList<>();
	static Queue<Fish> remainQ = new LinkedList<>();
	static int fdy[] = { 0, -1, -1, -1, 0, 1, 1, 1 }; // 물고기 이동 배열
	static int fdx[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int sdy[] = { -1, 0, 1, 0 }; // 상어 이동 배열
	static int sdx[] = { 0, -1, 0, 1 };
	static Fish Shark;
	static Fish[] moveShark;
	static Fish[] tempMoveShark;
	static int eat;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// M, S를 입력받는다.
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		fishMap = new int[S][N][N];
		dieMap = new boolean[S][N][N];
		answer = 0;

		int y, x, d;
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			remainQ.add(new Fish(y, x, d, 1));
		}
		st = new StringTokenizer(br.readLine());
		y = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());

		Shark = new Fish(y, x, 0, 0);
		moveShark = new Fish[3];
		tempMoveShark = new Fish[3];
		int round = 0;
		while (round < S) {
			// 복제 -> 물고기를 CopyQ에 넣는다.
			int size = remainQ.size();
			for (int s = 0; s < size; s++) {
				Fish f = remainQ.poll();
				copyQ.add(f);
				remainQ.add(f);
			}

			// 물고기가 이동한다.
			fishMap[round] = moveFish(round, dieMap, Shark);
			// 상어가 이동한다 => 순열

			eat = -1;
			Shark.d = Integer.MAX_VALUE;
			Permu(Shark.y, Shark.x, 0, 0, 0, round);

			// 상어 위치 갱신
			Shark = moveShark[moveNum - 1];
			for (int i = 0; i < moveNum; i++) {
				if (fishMap[round][moveShark[i].y][moveShark[i].x] != 0)
					dieMap[round][moveShark[i].y][moveShark[i].x] = true;
			}

			// 잡아먹힌 물고기 제거
			size = remainQ.size();
			for (int s = 0; s < size; s++) {
				Fish f = remainQ.poll();

				if (dieMap[round][f.y][f.x]) {
					continue;
				}

				remainQ.add(f);
			}

			// 복제 마법 완료
			size = copyQ.size();
			for (int s = 0; s < size; s++) {
				remainQ.add(copyQ.poll());
			}
			round++;
		}

		while (!remainQ.isEmpty()) {
			answer += remainQ.poll().num;
		}

		System.out.println(answer);
	}

	private static void Permu(int y, int x, int cnt, int dir, int sum, int r) {
		// 만약 이동이 불가능하면 백트래킹
		// 가능한 이동방향 중 물고기 수가 제일 많은 곳으로
		// 순열이 완성되면 물고기 수와 방향 정수 저장하기, 중복되면 방향 정수가 제일 큰 곳으로)
		// map에 물고기를 복제한다.
		dir = dir * 10;
		if (cnt == moveNum) {
			if (sum > eat) {
				eat = sum;
				Shark.d = dir;
				moveShark = tempMoveShark.clone();
			} else if (sum == eat && dir < Shark.d) {
				Shark.d = dir;
				moveShark = tempMoveShark.clone();
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ny = y + sdy[i];
			int nx = x + sdx[i];

			if (!check(ny, nx)) {
				// 해당 방향으로 진행 못함
				continue;
			}
			// visited 로 처리하면 상어가 물고기를 못 잡아먹는 경우, 오류가 난다.. 예제 5번과 6번!
			int temp = fishMap[r][ny][nx];
			fishMap[r][ny][nx] = 0;
			tempMoveShark[cnt] = new Fish(ny, nx, 0, 0);
			Permu(ny, nx, cnt + 1, dir + i + 1, sum + temp, r);
			fishMap[r][ny][nx] = temp;
		}
	}

	private static int[][] moveFish(int round, boolean[][][] notMove, Fish shark) {
		int[][] map = new int[N][N];
		// 만약 이동방향으로 이동시 격자를 벗어나면 이동하지 않고 45도 반시계 회전한다. (d-1)
		// 단 물고기 냄새, 상어가 있는 칸, 격자 범위를 벗어나는 칸으로는 이동할 수 없다.
		// 물고기 냄새를 기록하는 map을 만들어야 한다
		// [round][y][x].
		int size = remainQ.size();
		for (int s = 0; s < size; s++) {
			Fish f = remainQ.poll();

			int dir = f.d - 1 < 0 ? 7 : f.d - 1;
			// fdy 는 0~7까지
			// 물고기 방향은 1~8까지
			int ny = f.y + fdy[dir];
			int nx = f.x + fdx[dir];

			boolean isMove;

			if (!check(ny, nx) || ((ny == shark.y) && (nx == shark.x)) || !(isMove = check2(ny, nx, notMove, round))) {
				// 격자 범위를 초과하거나, 상어와 위치가 같은 경우...
				// 물고기 냄새로인해 이동할 수 없는 경우
				isMove = true;
				boolean realMove = false;
				for (int i = 0; i < 7; i++) {
					dir = dir - 1 < 0 ? 7 : dir - 1;
					// fdy 는 0~7까지
					// 물고기 방향은 1~8까지
					ny = f.y + fdy[dir];
					nx = f.x + fdx[dir];

					if (!check(ny, nx) || ((ny == shark.y) && (nx == shark.x))
							|| !(isMove = check2(ny, nx, notMove, round))) {
						continue;
					} else {
						realMove = true;
						break;
					}
				}

				if (realMove) {
					dir = dir + 1;
					remainQ.add(new Fish(ny, nx, dir, f.num));
					map[ny][nx] += f.num;
				} else {
					// 이동하지 않는다.
					remainQ.add(new Fish(f.y, f.x, f.d, f.num));
					map[f.y][f.x] += f.num;
				}
			} else {
				remainQ.add(new Fish(ny, nx, f.d, f.num));
				map[ny][nx] += f.num;
			}
		}

		return map;
	}

	private static boolean check2(int ny, int nx, boolean[][][] notMove, int round) {
		boolean isMove = true;
		for (int r = round - 2; r < round; r++) {
			if (r < 0)
				continue;
			if (notMove[r][ny][nx]) {
				isMove = false;
				break;
			}
		}

		return isMove;
	}

	private static boolean check(int ny, int nx) {
		if (ny > 0 && ny < N && nx > 0 && nx < N)
			return true;
		return false;
	}

}
