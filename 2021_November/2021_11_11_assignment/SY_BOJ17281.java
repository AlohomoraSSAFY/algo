package study1114;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ17281 {
	
	static int N;
	static int[][] array;
	static boolean[] visited;
	static int[] selected;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][10];
		visited = new boolean[10];
		selected = new int[10];
		selected[4] = 1;
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < 10; j++) {
				array[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutation(1);
		System.out.println(result);
	}
	
	public static void permutation(int cnt) {
		if (cnt == 10) {
			getScore();
			return;
		}
		
		for (int i = 2; i <= 9; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			selected[cnt] = i;
			if (cnt == 3) permutation(cnt + 2);
			else permutation(cnt + 1);
			visited[i] = false;
		}
	}
	
	public static void getScore() {
		int[] loc;
		int order = 1;
		int score = 0;
		
		for (int i = 0; i < N; i++) {
			loc = new int[4];
			int out = 0;
			while (out < 3) {
				int index = selected[order];
				int res = array[i][index];
				
				if (res == 1) {
					if (loc[3] != 0) {
						score++;
						loc[3] = 0;
					}
					
					for (int j = 2; j >= 1; j--) {
						if (loc[j] != 0) {
							loc[j+1] = loc[j];
							loc[j] = 0;
						}
					}
					
					loc[1] = index;
				} else if (res == 2) {
					for (int j = 2; j < 4; j++) {
						if (loc[j] != 0) {
							score++;
							loc[j] = 0;
						}
					}
					
					if (loc[1] != 0) {
						loc[3] = loc[1];
						loc[1] = 0;
					}
					
					loc[2] = index;
				} else if (res == 3) {
					for (int j = 1; j < 4; j++) {
						if (loc[j] != 0) {
							score++;
							loc[j] = 0;
						}
					}
					
					loc[3] = index;
				} else if (res == 4) {
					for (int j = 1; j < 4; j++) {
						if (loc[j] != 0) {
							score++;
							loc[j] = 0;
						}
					}
					
					score++;
				} else {
					out++;
				}
				
				if (++order == 10) order = 1;
			}
		}
		
		result = Math.max(result, score);
	}

}
