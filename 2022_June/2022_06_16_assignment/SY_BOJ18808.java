package study0623;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ18808 {
	
	static int N, M, R, C;
	static int[][] array;
	static int[][] sticker;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		array = new int[N][M];
		for (int t = 0; t < K; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			sticker = new int[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			here: for (int d = 0; d < 4; d++) {
				for (int i = 0; i <= N-R; i++) {
					for (int j = 0; j <= M-C; j++) {
						if (isAttachable(i, j)) {
							break here;
						}
					}
				}
				
				rotate();
			}
		}
		
		System.out.println(result);
	}
	
	private static boolean isAttachable(int x, int y) {
		for (int i = x; i < x+R; i++) {
			for (int j = y; j < y+C; j++) {
				if (array[i][j] == 1 && sticker[i-x][j-y] == 1) {
					return false;
				}
			}
		}
		
		for (int i = x; i < x+R; i++) {
			for (int j = y; j < y+C; j++) {
				if (sticker[i-x][j-y] == 1) {
					array[i][j] = 1;
					result++;
				}
			}
		}
		return true;
	}
	
	private static void rotate() {
		int t = R;
		R = C;
		C = t;
		
		int[][] temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				temp[i][j] = sticker[C-1-j][i];
			}
		}
		
		sticker = new int[R][C];
		sticker = temp;
	}

}
