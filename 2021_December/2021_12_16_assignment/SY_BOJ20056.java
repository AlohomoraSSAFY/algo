package study1219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ20056 {
	
	static int N;
	static int M;
	static int K;
	static Queue<Fireball> fList;
	static int[][] dir = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

	static class Fireball {
		int r, c, m, s, d;
		
		public Fireball(int r, int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		fList = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			fList.offer(new Fireball(r-1, c-1, m, s, d));
		}
		
		for (int i = 0; i < K; i++) {
			move();
		}
		
		int result = 0;
		int size = fList.size();
		for (int i = 0; i < size; i++) {
			Fireball cur = fList.poll();
			result += cur.m;
		}
		System.out.println(result);
	}
	
	public static void move() {
		Queue<Fireball>[][] map = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<>();
			}
		}
		
		int size = fList.size();
		for (int i = 0; i < size; i++) {
			Fireball cur = fList.poll();
			int r = cur.r;
			int c = cur.c;
			int m = cur.m;
			int s = cur.s;
			int d = cur.d;
			
			int nr = ((r + dir[d][0] * s) + N * 250) % N;
			int nc = ((c + dir[d][1] * s) + N * 250) % N;			
			map[nr][nc].offer(new Fireball(nr, nc, m, s, d));
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!map[i][j].isEmpty()) {
					if (map[i][j].size() == 1) {
						Fireball cur = map[i][j].poll();
						fList.offer(new Fireball(cur.r, cur.c, cur.m, cur.s, cur.d));
					} else {
						int cnt = map[i][j].size();
						int rm = 0;
						int rs = 0;
						int oddCnt = 0;
						int evenCnt = 0;
						for (int k = 0; k < cnt; k++) {
							Fireball f = map[i][j].poll();
							rm += f.m;
							rs += f.s;
							if (f.d % 2 == 0) {
								evenCnt++;
							} else {
								oddCnt++;
							}
						}
						
						if (rm/5 > 0) {
							if (oddCnt == 0 || evenCnt == 0) {
								for (int k = 0; k < 8; k+=2) {
									fList.offer(new Fireball(i, j, rm/5, rs/cnt, k));
								}
							} else {
								for (int k = 1; k < 8; k+=2) {
									fList.offer(new Fireball(i, j, rm/5, rs/cnt, k));
								}
							}
						}
					}
				}
			}
		}
	}

}
