package study1011;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ2138 {
	
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		char[] array1 = new char[N];
		char[] array2 = new char[N];
		String str = br.readLine();
		for (int i = 0; i < N; i++) {
			array1[i] = array2[i] = str.charAt(i);
		}
		char[] target = br.readLine().toCharArray();
		
		switchClick(array2, 0);
		
		int cnt1 = 0;
		int cnt2 = 1;
		for (int i = 1; i < N; i++) {
			if (array1[i-1] != target[i-1]) {
				switchClick(array1, i);
				cnt1++;
			}
			if (array2[i-1] != target[i-1]) {
				switchClick(array2, i);
				cnt2++;
			}
		}
		
		boolean flag1 = true;
		boolean flag2 = true;
		for (int i = 0; i < N; i++) {
			if (array1[i] != target[i]) flag1 = false;
			if (array2[i] != target[i]) flag2 = false;
			if (!flag1 && !flag2) break;
		}
		
		if (flag1 && flag2) {
			System.out.println(Math.min(cnt1, cnt2));
		} else if (flag1) {
			System.out.println(cnt1);
		} else if (flag2) {
			System.out.println(cnt2);
		} else {
			System.out.println(-1);
		}
	}
	
	private static void switchClick(char[] array, int idx) {
		for (int i = idx - 1; i <= idx + 1; i++) {
			if (i >= 0 && i < N) {
				if (array[i] == '0') array[i] = '1';
				else array[i] = '0';
			}
		}
	}

}
