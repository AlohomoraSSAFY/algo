package study0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ3079 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] array = new int[N];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(br.readLine());
			min = Math.min(min, array[i]);
		}
		
		long left = 1;
		long right = (long)min * M;
		long result = Long.MAX_VALUE;
		
		while (left <= right) {
			long mid = (left + right)/2;
			
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid / array[i];
			}
			
			if (sum >= M) {
				right = mid - 1;
				result = Math.min(result, mid);
			} else {
				left = mid + 1;
			}
		}
		
		System.out.println(result);
	}

}
