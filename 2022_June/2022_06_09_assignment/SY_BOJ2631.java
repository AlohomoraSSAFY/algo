package study0616;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SY_BOJ2631 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		int[] LIS = new int[N];
		int size = 0;
		for (int i = 0; i < N; i++) {
			int temp = Math.abs(Arrays.binarySearch(LIS, 0, size, array[i])) - 1;
			LIS[temp] = array[i];
			
			if (temp == size) size++;
		}
		
		System.out.println(N - size);
	}

}
