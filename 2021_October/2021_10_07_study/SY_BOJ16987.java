package study1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ16987 {
	
	static int N;
	static int[][] array;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N][2];
		result = Integer.MIN_VALUE;
		StringTokenizer st;
		for (int i = 0; i < array.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			array[i][0] = Integer.parseInt(st.nextToken());
			array[i][1] = Integer.parseInt(st.nextToken());
		}
		
		recursion(0);
		System.out.println(result);
	}
	
	public static void recursion(int cnt) {
		if (cnt == N) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				if (array[i][0] <= 0) count++;
			}
			result = Math.max(result, count);
			return;
		}
		
		if (array[cnt][0] <= 0) {
			recursion(cnt + 1);
			return;
		}
		
		//깨지지 않은 다른 계란 중 칠 계란 선택
		boolean flag = false;
		for (int i = 0; i < N; i++) {
			if (i == cnt || array[i][0] <= 0) continue;

			flag = true;
			array[cnt][0] -= array[i][1];
			array[i][0] -= array[cnt][1];
			recursion(cnt + 1);
			array[cnt][0] += array[i][1];
			array[i][0] += array[cnt][1];
		}
		
		if (!flag) recursion(cnt + 1);
	}

}
