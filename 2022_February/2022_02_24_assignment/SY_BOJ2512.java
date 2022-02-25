package study0303;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2512 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N];
		
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, array[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int left = 0;
		int right = max;
		int result = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (array[i] >= mid) {
					sum += mid;
				} else {
					sum += array[i];
				}
			}
			
			if (sum > M) {
				right = mid - 1;
			} else {
				result = Math.max(result, mid);
				left = mid + 1;
			}
		}
		
		System.out.println(result);
	}

}
