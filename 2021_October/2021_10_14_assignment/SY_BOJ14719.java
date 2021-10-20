package study1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ14719 {
	
	static int H;
	static int W;
	static int[][] array;
	static int[] block;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		array = new int[H][W];
		block = new int[W];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if(r < block[c]) array[r][c] = 1;
			}
		}
		
		for (int c = 0; c < W; c++) {
			for (int r = 0; r < H; r++) {
				if (array[r][c] == 1) {
					int count = 0;
					for (int k = c+1; k < W; k++) {
						if (array[r][k] == 0) {
							count++;
						} else {
							result += count;
							break;
						}
					}
				}
			}
		}
		
		System.out.println(result);
	}

}
