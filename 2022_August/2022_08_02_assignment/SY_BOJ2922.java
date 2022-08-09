package study0809;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ2922 {
	
	static char[] array;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		array = br.readLine().toCharArray();
		System.out.println(recursion(0, 0, 0, 0));
	}
	
	private static long recursion(int depth, int jCnt, int mCnt, int lCnt) {
		if (mCnt >= 3 || jCnt >= 3) return 0;
		if (depth == array.length) return lCnt;
		
		long cnt = 0;
		if (array[depth] == '_') {
			cnt += recursion(depth + 1, jCnt + 1, 0, lCnt) * 20;
			cnt += recursion(depth + 1, jCnt + 1, 0, 1);
			cnt += recursion(depth + 1, 0, mCnt + 1, lCnt) * 5;
		} else {
			if (array[depth] == 'A' || array[depth] == 'I' || array[depth] == 'E' || array[depth] == 'O' || array[depth] == 'U') {
				cnt += recursion(depth + 1, 0, mCnt + 1, lCnt);
			} else {
				if (array[depth] == 'L') {
					cnt += recursion(depth + 1, jCnt + 1, 0, 1);
				} else {
					cnt += recursion(depth + 1, jCnt + 1, 0, lCnt);
				}
			}
		}
		
		return cnt;
	}

}
