package study1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SY_BOJ13460 {
	
	static int N;
	static int M;
	static char[][] array;
	static int[] rPos;
	static int[] bPos;
	static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		array = new char[N][M];
		rPos = new int[2];
		bPos = new int[2];
		
		for (int i = 0; i < N; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				array[i][j] = cArray[j];
				if (array[i][j] == 'R') {
					rPos[0] = i;
					rPos[1] = j;
					array[i][j] = '.';
				} else if (array[i][j] == 'B') {
					bPos[0] = i;
					bPos[1] = j;
					array[i][j] = '.';
				}
			}
		}
		
		bfs();
		System.out.println(result);
	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {rPos[0], rPos[1], bPos[0], bPos[1], 0});
		boolean[][][][] visited = new boolean[N][M][N][M];
		visited[rPos[0]][rPos[1]][bPos[0]][bPos[1]] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int rx = cur[0];
			int ry = cur[1];
			int bx = cur[2];
			int by = cur[3];
			int cnt = cur[4];
			
			for (int i = 0; i < 4; i++) {
				boolean rHole = false;
				boolean bHole = false;
				
				int nrx = rx;
				int nry = ry;
				int nbx = bx;
				int nby = by;
				
				//빨간 구슬 이동(중간에 구멍 있는지 확인)
				while (array[nrx+d[i][0]][nry+d[i][1]] != '#') {
					nrx += d[i][0];
					nry += d[i][1];
					
					if (array[nrx][nry] == 'O') {
						rHole = true;
						break;
					}
				}
				
				//파란 구슬 이동(중간에 구멍 있는지 확인)
				while (array[nbx+d[i][0]][nby+d[i][1]] != '#') {
					nbx += d[i][0];
					nby += d[i][1];
					
					if (array[nbx][nby] == 'O') {
						bHole = true;
						break;
					}
				}
				
				//구멍에 빠졌는지 확인(빨간 구슬만 구멍에 빠져야 함)
				if (bHole) continue;
				if (rHole) {
					result = cnt + 1;
					return;
				}
				
				//빨간 구슬과 파란 구슬이 같은 위치에 있을 경우
				if (nrx == nbx && nry == nby) {
					if (i == 0) {
						if (rx < bx) nbx += 1;
						else nrx += 1;
					} else if (i == 1) {
						if (ry < by) nry -= 1;
						else nby -= 1;
					} else if (i == 2) {
						if (rx < bx) nrx -= 1;
						else nbx -= 1;
					} else {
						if (ry < by) nby += 1;
						else nry += 1;
					}
				}
				
				//처음 방문하는 곳이고, 현재 9번 이하로 움직였을 경우만 큐에 넣기
				if (!visited[nrx][nry][nbx][nby] && cnt < 9) {
					visited[nrx][nry][nbx][nby] = true;
					q.offer(new int[] {nrx, nry, nbx, nby, cnt + 1});
				}
			}
		}
		
		result = -1;
	}
}
