package study0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2565 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] array = new int[501];
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			max = Math.max(max, A);
			array[A] = B;
		}
		
		int[] d = new int[max+1];
		int cnt = Integer.MIN_VALUE;
		for (int i = 1; i <= max; i++) {
			if (array[i] != 0) d[i] = 1;
			for (int j = 0; j < i; j++) {
				if (array[j] < array[i] && d[j] + 1 > d[i]) {
					d[i] = d[j] + 1;
				}
			}
			cnt = Math.max(cnt, d[i]);
		}
		
		System.out.println(N - cnt);
	}

}
