package date0421;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NY_BOJ_1339 {
	static int n;
	static int[] nums;
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = new int[100];
		answer = 0;

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			int len = str.length();
			for (int j = 0; j < len; j++) {
				char c = str.charAt(j);
				nums[c] += Math.pow(10, len - j - 1);
			}
		}
		Arrays.sort(nums);
		int cnt = 9;
		for (int i = 99; i > 0; i--) {
			if (nums[i] == 0)
				break;
			answer += nums[i] * (cnt--);
		}
		System.out.println(answer);
	}

}
