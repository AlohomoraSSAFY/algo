import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static double[][] A;
	static int[][] dp;
	static int total = 0;
	static int[] dr = { 0, 1, 0, -1}; // 왼 아래 오른 위
	static int[] dc = {-1, 0, 1, 0};
	
	private static void moveSand(int dir, int y, int x) {
		int[] dy = null; int[] dx = null; int[] alpha = null;
		double[] value = new double[]{	0.05, 0.10, 0.10,  0.02, 0.02, 0.07, 0.07, 0.01, 0.01};
		switch (dir) {
			case 0: // 왼쪽 이동
				dy = new int[]{ 0, -1,	1, -2, 2, -1, 1, -1, 1};
				dx = new int[]{-2, -1, -1,  0, 0,  0, 0,  1, 1};
				alpha = new int[] {0, -1};
				break;
			case 1: // 아래쪽 이동
				dy = new int[]{ 2, 1,  1, 0,  0, 0,  0, -1, -1};
				dx = new int[]{ 0, 1, -1, 2, -2, 1, -1,  1, -1};
				alpha = new int[] {1, 0};	
				break;
			case 2: // 오른쪽 이동
				dy = new int[]{ 0, 1, -1, 2, -2, 1, -1,  1, -1};
				dx = new int[]{ 2, 1,  1, 0,  0, 0,  0, -1, -1};
				alpha = new int[] {0, 1};						
				break;
			case 3://위쪽 이동
				dy = new int[]{-2, -1, -1,  0, 0,  0, 0,  1, 1};
				dx = new int[]{ 0, -1,  1, -2, 2, -1, 1, -1, 1};
				alpha = new int[] {-1, 0};
				break;
			
		}
		
		double sum = 0;
		for(int d = 0; d < 9; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if(ny > -1 && nx > -1 && ny < N && nx < N) { // 범위 내에 있을 경우
				double tmp = Math.floor(value[d] * A[y][x]);
				sum += tmp;
				A[ny][nx] += tmp;
				continue;
			}
			// 벗어난 경우
			double tmp = Math.floor(value[d] * A[y][x]);
			sum += tmp;
			total += tmp;
		}
		
		// 알파자리 확인
		if(y + alpha[0] > -1 && x + alpha[1] > -1 && y + alpha[0] < N && x + alpha[1] < N) { // 범위 내에 있을 경우
			double tmp = A[y][x] - sum; // 나머지 값들
			A[y + alpha[0]][x + alpha[1]] += tmp;
		}else {
			total += (A[y][x] - sum);
		}
		
		A[y][x] = 0; // 기존 자리 모래는 0으로
		
		
		
		
		
	}
	
	//nr < N - tab +1 && nr > tab -1 && nc < N - tab+1 && nc > tab-2
	private static void tornado(int sr, int sc) {
		int tab = N/2; int idx = 1;
		int r = sr; int c = sc;
		LOOP:
		while(true) {
			for(int d = 0 ; d < 4; d++) {
				while(true) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(nr < tab - d/2 || nr > N- tab || nc < tab - 1 || nc > N - tab ) break;
					if(nr < 0 || nc < 0 || nr > N-1 || nc > N-1) break;
					++idx;
					if(idx==N*N+1) break LOOP;
					moveSand(d, nr, nc);
					r = nr;
					c = nc;
				}
			}
			tab--;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		A = new double[N][N];
		dp = new int[N][N];
		// 원래 모래 양
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N; j++) {
				A[i][j] = Double.parseDouble(st.nextToken());
			}
		}
		
		tornado(N/2, N/2);
		
	
		bw.write(total+"\n");
		bw.close();
		br.close();	
		
	}

}