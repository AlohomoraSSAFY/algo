package study0726;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ15662 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[][] array = new int[T+1][8];
		for (int i = 1; i <= T; i++) {
			char[] cArray = br.readLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				array[i][j] = cArray[j] - '0';
			}
		}
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int no = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int[] rotate = new int[T+1];
			rotate[no] = dir;
			for (int j = no-1; j > 0; j--) {
				if (array[j][2] != array[j+1][6]) {
					rotate[j] = rotate[j+1] * (-1);
				} else {
					break;
				}
			}
			for (int j = no+1; j <= T; j++) {
				if (array[j][6] != array[j-1][2]) {
					rotate[j] = rotate[j-1] * (-1);
				} else {
					break;
				}
			}
			
			for (int j = 1; j <= T; j++) {
				if (rotate[j] == 1) {
					int temp = array[j][7];
					for (int k = 7; k > 0; k--) {
						array[j][k] = array[j][k-1];
					}
					array[j][0] = temp;
				} else if (rotate[j] == -1) {
					int temp = array[j][0];
					for (int k = 0; k < 7; k++) {
						array[j][k] = array[j][k+1];
					}
					array[j][7] = temp;
				}
			}
		}
		
		int cnt = 0;
		for (int i = 1; i <= T; i++) {
			if (array[i][0] == 1) cnt++;
		}
		System.out.println(cnt);
	}

}
