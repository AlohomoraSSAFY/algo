package date1216THU;

import java.util.*;
import java.io.*;

public class BOJ17937 {

	static int n, k;
	static int map[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static ArrayList<int[]> pieces;
	static Queue<Integer>[][] board;
	static int answer, count;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		pieces = new ArrayList<>();
		board = new LinkedList[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				board[i][j] = new LinkedList<>();
			}
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			pieces.add(new int[] { x, y, d });
			board[x][y].offer(i);
		}

		answer = -1;
		count = 1;

		while (count <= 1000) { // 1회
			if (checkfour()) {
				answer = count;
				break;
			}
			for (int i = 0; i < k; i++) { // i번 말 하나 이동
				int cur[] = pieces.get(i);
				int x = cur[0];
				int y = cur[1];
				int d = cur[2];

				move(i, x, y, d);
//				System.out.println("움직인 말 번호 " + i);
//				printboard();

				if (checkfour()) {
					answer = count;
					count = 1001;
					break;
				}
			}

			count++;

		}

		System.out.println(answer);
	}

	public static boolean checkfour() { // 쌓인 말 4개 이상인지 체크

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j].size() >= 4)
					return true;
			}
		}

		return false;
	}

	public static void move(int num, int x, int y, int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		// 일단 움직일 칸 정해두고
		// 큐에서 뽑으면서 그 위에것들 다같이 이동
		// 색깔 구분

		// 흰색, 빨간색 => 그냥 이동
		if (nx >= 0 && ny >= 0 && nx < n && ny < n && map[nx][ny] != 2) {
			boolean flag = false;
			Stack<Integer> red = new Stack<>();
			int len = board[x][y].size(); // 현재 위치에 쌓여있는 말 체크
			for (int i = 0; i < len; i++) { // 현재 있는거 뽑아서
				int t = board[x][y].poll();
				if (t == num) {
					pieces.set(t, new int[] { nx, ny, d });
					if (map[nx][ny] == 0)
						board[nx][ny].offer(t);
					else if (map[nx][ny] == 1) {
						red.push(t);

					}
					flag = true;
				} else { // 다른 숫자인 경우
					if (flag) { // 현재 숫자가 나온 경우 위에 쌓인거

						int td = pieces.get(t)[2];
						pieces.set(t, new int[] { nx, ny, td });
						if (map[nx][ny] == 0)
							board[nx][ny].offer(t);
						else if (map[nx][ny] == 1) {
							red.push(t);
						}
					} else { // 현재 숫자가 아직 안 나왔으면 밑에 있는거라 이동할 필요 x
						board[x][y].offer(t); // 다시 원래 자리에 넣어줌
					}
				}

				if (checkfour()) {
					answer = count;
					break;
				}
			}
			if (map[nx][ny] == 1) {// 스택에 쌓은거 옮겨줌
				while (!red.isEmpty()) {
					int te = red.pop();
					board[nx][ny].offer(te);
				}
			}

		} else { // 파란색이거나 체스판을 벗어나는 경우
			// 말 방향 반대로 바꾸고 01 23
			// 한 칸 이동하는데 파란색이면 그대로 둠
			// 위에 쌓인거 같이 움직여야 
			if (d == 0)
				d = 1;
			else if (d == 1)
				d = 0;
			else if (d == 2)
				d = 3;
			else
				d = 2;
			//
			int nnx = x + dx[d];
			int nny = y + dy[d];
			if (nnx >= 0 && nny >= 0 && nnx < n && nny < n && map[nnx][nny] != 2) { // 새로 이동하려는 방향이 이동 가능하면
				boolean flag = false;
				Stack<Integer> red = new Stack<>();
				int len = board[x][y].size(); // 현재 위치에 쌓여있는 말 체크
				for (int i = 0; i < len; i++) { // 현재 있는거 뽑아서
					int t = board[x][y].poll();
					if (t == num) {
						pieces.set(t, new int[] { nnx, nny, d });
						if (map[nnx][nny] == 0)
							board[nnx][nny].offer(t);
						else if (map[nnx][nny] == 1) {
							red.push(t);

						}
						flag = true;
					} else { // 다른 숫자인 경우
						if (flag) { // 현재 숫자가 나온 경우 위에 쌓인거

							int td = pieces.get(t)[2];
							pieces.set(t, new int[] { nnx, nny, td });
							if (map[nnx][nny] == 0)
								board[nnx][nny].offer(t);
							else if (map[nnx][nny] == 1) {
								red.push(t);
							}
						} else { // 현재 숫자가 아직 안 나왔으면 밑에 있는거라 이동할 필요 x
							board[x][y].offer(t); // 다시 원래 자리에 넣어줌
						}
					}

					if (checkfour()) {
						answer = count;
						break;
					}
				}
				if (map[nnx][nny] == 1) {// 스택에 쌓은거 옮겨줌
					while (!red.isEmpty()) {
						int te = red.pop();
						board[nnx][nny].offer(te);
					}
				}

			} else { // 이동 못 하는 경후 방향만 바꿔줌
				pieces.set(num, new int[] { x, y, d });
			}
		}

	}
}