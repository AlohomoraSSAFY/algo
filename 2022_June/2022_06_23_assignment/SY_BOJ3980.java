package study0630;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ3980 {
	
	static int[][] array;
	static boolean[] visited;
	static int[] selected;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int C = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < C; tc++) {
			array = new int[11][11];
			visited = new boolean[11];
			selected = new int[11];
			result = 0;
			
			for (int i = 0; i < 11; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 11; j++) {
					array[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			recursion(0);
			System.out.println(result);
		}
	}
	
	private static void recursion(int cnt) {
		if (cnt == 11) {
			int sum = 0;
			for (int i = 0; i < 11; i++) {
				sum += selected[i];
			}
			
			result = Math.max(result, sum);
			return;
		}
		
		for (int i = 0; i < 11; i++) {
			if (visited[i] || array[cnt][i] == 0) continue;
			
			visited[i] = true;
			selected[cnt] = array[cnt][i];
			recursion(cnt + 1);
			visited[i] = false;
		}
	}

}
