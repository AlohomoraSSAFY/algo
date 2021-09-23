package date0923THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1107 {
	static int n, m;
	static boolean broken[];
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		broken = new boolean[10];

		if (m != 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				int a = Integer.parseInt(st.nextToken());
				broken[a] = true;
			}

		}

		//
		answer = Math.abs(n - 100);
		if (m == 0) {// ���峭 ��ư�� ������ üũ x
			if (n != 0)
				answer = Math.min(answer, (int) (Math.log10(n)) + 1);
			else
				answer = 1;
		} else {
			for (int i = 0; i <= 999999; i++) { // ��� ���� ���� üũ
				String str = Integer.toString(i);
				int length = str.length();
				boolean flag = true;
				for (int j = 0; j < length; j++) {
					int cur = str.charAt(j) - '0';
					if (broken[cur]) {
						flag = false;
						break;
					}
				}
				if (flag) {
					int count = Math.abs(n - i) + length;
					answer = Math.min(count, answer);
				}

			}
		}
		System.out.println(answer);
	}

}
