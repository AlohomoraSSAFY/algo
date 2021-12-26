package study1230;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SY_BOJ2470 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(array);
		
		long min = Long.MAX_VALUE;
		int r1 = 0;
		int r2 = 0;
		int left = 0;
		int right = N-1;
		while (left < right) {
			long temp = array[left] + array[right];
			if (min > Math.abs(temp)) {
				min = Math.abs(temp);
				r1 = array[left];
				r2 = array[right];
			}
			
			if (temp == 0) {
				break;
			} else if (temp > 0) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(r1 + " " + r2);
	}

}
