package study0127;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ20061 {
	
	static boolean[][] green;
	static boolean[][] blue;
	static int score;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		green = new boolean[6][4];
		blue = new boolean[4][6];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			// 블록 이동
			moveBlock(t, x, y);
			
			// 점수 구하기
			getScore();
			
			// 연한 초록색 구역 체크
			int cntCheckGreen = checkGreen();
			
			// 연한 파란색 구역 체크
			int cntCheckBlue = checkBlue();
			
			// 개수에 맞춰 초록색 보드 블록 이동
			moveCheckGreen(cntCheckGreen);

			// 개수에 맞춰 파란색 보드 블록 이동
			moveCheckBlue(cntCheckBlue);
		}
		
		// 타일이 들어있는 칸의 개수 구하기
		int cnt = getTileCnt();
		
		System.out.println(score);
		System.out.println(cnt);
	}
	
	private static void moveBlock(int t, int x, int y) {
		if (t == 1) {
			// 초록색 보드
			green[0][y] = true;
			int idx = 1; 
			while (idx < 6 && green[idx][y] == false) {
				green[idx-1][y] = false;
				green[idx][y] = true;
				idx++;
			}
			
			// 파란색 보드
			blue[x][0] = true;
			idx = 1; 
			while (idx < 6 && blue[x][idx] == false) {
				blue[x][idx-1] = false;
				blue[x][idx] = true;
				idx++;
			}
		} else if (t == 2) {
			// 초록색 보드
			green[0][y] = true;
			green[0][y+1] = true;
			int idx = 1; 
			while (idx < 6 && green[idx][y] == false && green[idx][y+1] == false) {
				green[idx-1][y] = false;
				green[idx-1][y+1] = false;
				green[idx][y] = true;
				green[idx][y+1] = true;
				idx++;
			}
			
			// 파란색 보드
			blue[x][0] = true;
			blue[x][1] = true;
			idx = 2; 
			while (idx < 6 && blue[x][idx] == false) {
				blue[x][idx-2] = false;
				blue[x][idx] = true;
				idx++;
			}
		} else {
			// 초록색 보드
			green[0][y] = true;
			green[1][y] = true;
			int idx = 2; 
			while (idx < 6 && green[idx][y] == false) {
				green[idx-2][y] = false;
				green[idx][y] = true;
				idx++;
			}
			
			// 파란색 보드
			blue[x][0] = true;
			blue[x+1][0] = true;
			idx = 1; 
			while (idx < 6 && blue[x][idx] == false && blue[x+1][idx] == false) {
				blue[x][idx-1] = false;
				blue[x+1][idx-1] = false;
				blue[x][idx] = true;
				blue[x+1][idx] = true;
				idx++;
			}
		}
	}
	
	private static void getScore() {
		// 초록색 보드
		for (int i = 5; i >= 2; i--) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (green[i][j] == true) {
					cnt++;
				} else {
					break;
				}
			}
			
			if (cnt == 4) {
				score++;
				// 위에 블록 아래로 이동
				moveGreen(i);
				i++;
			}
		}
		
		// 파란색 보드
		for (int i = 5; i >= 2; i--) {
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				if (blue[j][i] == true) {
					cnt++;
				} else {
					break;
				}
			}
			
			if (cnt == 4) {
				score++;
				// 왼쪽에 블록 오른쪽으로 이동
				moveBlue(i);
				i++;
			}
		}
	}
	
	private static void moveGreen(int r) {
		for (int i = r; i > 0; i--) {
			for (int j = 0; j < 4; j++) {
				green[i][j] = green[i-1][j];
			}
		}
	}
	
	private static void moveBlue(int c) {
		for (int i = c; i > 0; i--) {
			for (int j = 0; j < 4; j++) {
				blue[j][i] = blue[j][i-1];
			}
		}
	}
	
	private static int checkGreen() {
		int cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j] == true) {
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}
	
	private static int checkBlue() {
		int cnt = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (blue[j][i] == true) {
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}
	
	private static void moveCheckGreen(int cnt) {
		for (int i = 5; i >= 2; i--) {
			for (int j = 0; j < 4; j++) {
				green[i][j] = green[i-cnt][j];
			}
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				green[i][j] = false;
			}
		}
	}
	
	private static void moveCheckBlue(int cnt) {
		for (int i = 5; i >= 2; i--) {
			for (int j = 0; j < 4; j++) {
				blue[j][i] = blue[j][i-cnt];
			}
		}
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				blue[j][i] = false;
			}
		}
	}
	
	private static int getTileCnt() {
		int cnt = 0;
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (green[i][j]) cnt++;
				if (blue[j][i]) cnt++;
			}
		}
		return cnt;
	}

}
