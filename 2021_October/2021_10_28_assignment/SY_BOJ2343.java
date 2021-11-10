package study1031;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2343 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] array = new int[N];
		int max = 0;
		long sum = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
			if (array[i] > max) max = array[i];
			sum += array[i];
		}
		
		long result = sum;
		long left = Math.max(max, sum / M);
		long right = sum;
		
		while (left <= right) {
			long mid = (left + right) / 2;
			int cnt = 1;
			long size = 0;
			for (int i = 0; i < N; i++) {
				long temp = size + array[i];
				if (temp > mid) {
					cnt++;
					size = array[i];
				} else {
					size = temp;
				}
			}
			
			if (cnt > M) {
				left = mid + 1;
			} else {
				result = mid;
				right = mid - 1;
			}
		}
		
		System.out.println(result);
	}

}
