package study1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ2467 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int min = Integer.MAX_VALUE;
		int rleft = 0;
		int rright = 0;
		int left = 0;
		int right = N-1;
		while (left < right) {
			int temp = array[left] + array[right];
			if (min > Math.abs(temp)) {
				min = Math.abs(temp);
				rleft = left;
				rright = right;
			}
			
			if (temp == 0) {
				break;
			} else if (temp > 0) {
				right -= 1;
			} else {
				left += 1;
			}
		}
		
		System.out.println(array[rleft] + " " + array[rright]);
	}

}
