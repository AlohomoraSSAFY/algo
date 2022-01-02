package study0102;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2116 {
	
	static int N;
	static int[][] array;
	static boolean[][] isFixed;
	static int result = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][6];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 6; i++) {
			isFixed = new boolean[N][6];
			isFixed[0][i] = true;
			isFixed[0][calOpIndex(i)] = true;
			
			int num = array[0][i];
			for (int j = 1; j < N; j++) {
				int index = 0;
				for (int k = 0; k < 6; k++) {
					if (array[j][k] == num) {
						index = k;
						break;
					}
				}
				
				isFixed[j][index] = true;
				isFixed[j][calOpIndex(index)] = true;
				num = array[j][calOpIndex(index)];
			}
			
			int sum = 0;
			for (int j = 0; j < N; j++) {
				int max = 0;
				for (int k = 0; k < 6; k++) {
					if (isFixed[j][k]) continue;
					max = Math.max(max, array[j][k]);
				}
				
				sum += max;
			}
			
			result = Math.max(result, sum);
		}
		
		System.out.println(result);
	}
	
	public static int calOpIndex(int index) {
		int opIndex = 0;
		switch(index) {
			case 0:
				opIndex = 5;
				break;
			case 1:
				opIndex = 3;
				break;
			case 2:
				opIndex = 4;
				break;
			case 3:
				opIndex = 1;
				break;
			case 4:
				opIndex = 2;
				break;
			case 5:
				opIndex = 0;
				break;
		}
		
		return opIndex;
	}

}
