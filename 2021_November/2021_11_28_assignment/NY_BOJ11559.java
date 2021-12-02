package date1202THU;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BOJ11559 {
	static char map[][];
	static int count;
	static int visited[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0 };
	static Queue<int[]> poplist;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		map = new char[12][6];
		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
//	
		poplist = new LinkedList<>();
		int answer = 0;
		while (true) {
			int num = 1;
			visited = new int[12][6];
			boolean popcheck = false;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') {

						if (bfs(i, j, num)) {
							pop(num);
							//printmap();
							popcheck = true;
						}
						num++;
					}
				}
			}
			// 다 확인하면 터뜨리고
			if (popcheck) {
				down();
				answer++;
			} else
				break;
		}
		System.out.println(answer);
	}

	public static boolean bfs(int x, int y, int num) {
		char c = map[x][y];
		visited[x][y] = num;
		int count = 1;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });

		while (!q.isEmpty()) {
			int temp[] = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				if (nx < 12 && ny < 6 && nx >= 0 && ny >= 0 && map[nx][ny] == c && visited[nx][ny] != num) {
					count++;
					visited[nx][ny] = num;
					q.offer(new int[] { nx, ny });
				}
			}
		}
		if (count >= 4)
			return true;
		else
			return false;

	}

	public static void pop(int num) { 
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (visited[i][j] == num) {
					map[i][j] = '.';
				}
			}
		}
	}

	public static void down() {
		boolean blank = false;
		for (int j = 0; j < 6; j++) { // 세로 한 줄씩 체크
			for (int i = 11; i > 0; i--) {
				if (map[i][j] == '.' && map[i - 1][j] != '.') { // 현재칸이 빈칸이고 위가 뿌요면
					map[i][j] = map[i - 1][j];
					map[i - 1][j] = '.';
					i = 12;
				}
			}
		}
	}

	public static void printmap() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
