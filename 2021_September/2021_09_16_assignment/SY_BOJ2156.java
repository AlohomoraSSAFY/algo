package study0919;

import java.util.Scanner;

public class SY_BOJ2156 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] array = new int[n];
		int[] d = new int[n];
		for (int i = 0; i < n; i++) {
			array[i] = sc.nextInt();
		}
		
		d[0] = array[0];
		if (n > 1) d[1] = d[0] + array[1];
		if (n > 2) d[2] = Math.max(Math.max(d[0] + array[2], array[1] + array[2]), d[1]);
		
		for (int i = 3; i < n; i++) {
			d[i] = Math.max(Math.max(d[i-2] + array[i], d[i-3] + array[i-1] + array[i]), d[i-1]);
		}

		System.out.println(d[n-1]);
	}

}
