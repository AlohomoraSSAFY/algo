package date1230SUN;

import java.io.*;
import java.util.*;

public class BOJ2470 {
	static int aidx, bidx;
	static int n;
	static long[] arr;
	static long min;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new long[n];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		//
		Arrays.sort(arr);

		int left = 0;
		int right = n - 1;

		aidx = right;
		bidx = left;

		min = Long.MAX_VALUE;

		while (left < right) {
			long cur = Math.abs(arr[right] + arr[left]);
			if (cur < min) {
				min = cur;
				aidx = right;
				bidx = left;
				if (cur == 0)
					break;
			}
			long a = Math.abs(arr[right - 1] + arr[left]);
			long b = Math.abs(arr[right] + arr[left + 1]);

			if (a < b) {
				right--;
			} else {
				left++;
			}
		}

		System.out.println(arr[bidx] + " " + arr[aidx]);

	}

}
