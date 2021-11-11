package date1104THU;

import java.io.*;
import java.util.*;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class BOJ23290_2 { // 마법사 상어와 복제

	static int m;
	static int s;

	static int fdx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static int fdy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	static int sdx[] = { -1, 0, 1, 0 };
	static int sdy[] = { 0, -1, 0, 1 };

	static int selected[][];

	static int map[][];

	static int sharkdir[][];

	static Queue<int[]> curfish;
	static Queue<int[]> dupfish;

	static int sx, sy;
	static int answer;

	static int maxcnt;

	static boolean maxfound;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken()); // s번 반복

		curfish = new LinkedList<>();

		map = new int[5][5];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int fx = Integer.parseInt(st.nextToken());
			int fy = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			curfish.add(new int[] { fx, fy, d });
		}
		st = new StringTokenizer(br.readLine());

		sx = Integer.parseInt(st.nextToken());
		sy = Integer.parseInt(st.nextToken());

		selected = new int[3][2];

		for (int i = 0; i < s; i++) { // 한 사이클
			// 1. 물고기 위치 저장(나중에 복제하기 위해) -> dupfish에 현재 물고기 위치 리스트 저장
			copyfish();

			// 2. 모든 물고기 이동 (빈칸으로만, 반시계45도씩 회전)
			movefish();

			// 3. 상어 3칸 이동 (combination 돌려서 이동하면서 잡아먹을 수 있는 물고기 확인해서 수 제일 많은걸로)
			maxcnt = 0;
			sharkdir = new int[3][2]; // 상어가 최종적으로 움직일 방향 세 개
			maxfound = false;

			combination(0, 0); // 상어의 방향 결정

			moveshark(); //상어 이동

			// 4. 2턴 지난 물고기 냄새가 사라진다
			smell();

			// 5. 1에서 저장한 물고기 위치에 물고기 만들음
			pastefish();
		}
		
		System.out.println(curfish.size());

	}

	public static void combination(int start, int count) {
		if (count == 3) {
			if (possible(selected)) {
				if (!maxfound) {
					for (int i = 0; i < 3; i++) {
						sharkdir[i][0] = selected[i][0];
						sharkdir[i][1] = selected[i][1];
						maxfound = true;
					}
				}
				int fishcnt = fishcount(selected);
				if (maxcnt < fishcnt) {
					maxfound = true;
					maxcnt = fishcnt;
					for (int i = 0; i < 3; i++) {
						sharkdir[i][0] = selected[i][0];
						sharkdir[i][1] = selected[i][1];
					}
				}
			}
			return;
		}

		for (int i = 0; i < 4; i++) {
			selected[count][0] = sdx[i];
			selected[count][1] = sdy[i];
			combination(i + 1, count + 1);
		}
	}

	public static boolean possible(int[][] selected) { // 상어가 이동할 방향이 가능한지 확인
		int nx = sx;
		int ny = sy;

		for (int i = 0; i < 3; i++) { // selected의 선택된 세 방향에 대해서
			nx += selected[i][0];
			ny += selected[i][1];
			if (nx < 1 || ny < 1 || nx > 4 || ny > 4)
				return false;
		}
		return true;
	}

	public static void moveshark() { // 상어 실제로 이동

		int nx[] = new int[4];
		int ny[] = new int[4];

		nx[0] = sx;
		ny[0] = sy;

		for (int i = 1; i < 4; i++) {
			nx[i] = nx[i - 1] + sharkdir[i - 1][0];
			ny[i] = ny[i - 1] + sharkdir[i - 1][1];
		}
		int size = curfish.size();
		for (int i = 0; i < size; i++) {
			int[] tempfish = curfish.poll();
			boolean eaten = false;

			for (int j = 1; j < 4; j++) { // nx, ny 체크
				if (tempfish[0] == nx[j] && tempfish[1] == ny[j]) { // 상어가 이동할 위치와 같으면 잡아먹고 냄새 남김
					eaten = true; 
					map[nx[j]][ny[j]] = 3; // 물고기 냄새 맵에 표시
					break;
				}
			}
			if(!eaten) //잡아먹히지 않았다면 다시 넣어줌
				curfish.offer(tempfish);
		}
		sx = nx[3];
		sy = ny[3];
	}

	public static int fishcount(int[][] selected) {// 상어가 selected에 저장된 방향대로 움직일 경우 잡아먹을 수 있는 물고기의 수
		int cnt = 0;

		int nx[] = new int[4];
		int ny[] = new int[4];

		nx[0] = sx;
		ny[0] = sy;

		for (int i = 1; i < 4; i++) {
			nx[i] = nx[i - 1] + selected[i - 1][0];
			ny[i] = ny[i - 1] + selected[i - 1][1];
		}

		int size = curfish.size();
		for (int i = 0; i < size; i++) {
			int[] temp = curfish.poll();
			for (int j = 1; j < 4; j++) { // nx, ny 체크
				if (temp[0] == nx[j] && temp[1] == ny[j]) {
					cnt++;
					break;
				}
			}
			curfish.offer(temp);
		}

		return cnt;
	}

	public static void copyfish() {
		dupfish = new LinkedList<>();
		int size = curfish.size();
		for (int i = 0; i < size; i++) {
			int temp[] = curfish.poll();
			dupfish.add(temp);
			curfish.add(temp);
		}
	}

	public static void pastefish() {
		int size = dupfish.size();
		for (int i = 0; i < size; i++) {
			int temp[] = dupfish.poll();
			curfish.offer(temp);
		}
	}

	public static void movefish() {
		int size = curfish.size();
		for (int i = 0; i < size; i++) {

			int temp[] = curfish.poll();
			int x = temp[0];
			int y = temp[1];
			int d = temp[2];

			boolean moved = false;
			for (int j = 0; j < 8; j++) {
				int nx = x + fdx[d];
				int ny = y + fdy[d];
				if (nx >= 1 && ny >= 1 && nx < 5 && ny < 5 && (ny != sy || nx != sx) && map[nx][ny] == 0) { // 이동할 수 있는
																											// 위치면
					curfish.offer(new int[] { nx, ny, d }); // 이동하고
					moved = true;
					break;
				} else // 이동 못 하면
					d = (d - 1 > 0 ? d - 1 : 8); // 반시계 방향으로 45도 회전
			}

			// for문 다돌았는데 못움직였으면 그자리에 다시 넣어줌
			if (!moved)
				curfish.offer(new int[] { temp[0], temp[1], temp[2] });
		}
	}

	public static void smell() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				if (map[i][j] > 0)
					map[i][j]--;
			}
		}
	}

	public static void printmap() {
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

}
