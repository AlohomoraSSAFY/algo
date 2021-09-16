package date0912;

import java.io.*;
import java.util.*;

public class BOJ1038_2 {
	static long n;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Long.parseLong(st.nextToken());

		long count = 10;
		long num = 10;
		long answer = 0;

		boolean cannotfind = false;
		if (n < 11) {
			answer = n;
		} else {
			while (count <= n) { // while문 한번 돌 동안 하나의 수에 대해서 체크
				// System.out.println("num : " + num);
				if (num > 9876543210L) { // 이후로는 감소하는 수가 없음
					answer = -1;
					break;
				}

				// num증가시키며 count번째 감소하는 수 찾음
				boolean flag = true;


				int length = (int) Math.log10(num);
				long cur = num; 
				
				while (length > 0) { // 현재 체크할 수의 모든 자리 감소하는지 확인

					long t1 = (long) Math.pow(10, length);
					long t2 = (long) Math.pow(10, length - 1);

					int c1 = (int) (cur / t1);
					int c2 = (int) ((cur - c1 * t1) / t2);
					//System.out.println(c1 + " " + c2);
					if (c1 <= c2) {// 감소하는 수 아니면
						num += t1;
						num -= t2 * c2;
						flag = false;
						break;
					}

					cur -= t1 * c1;
					length--;

				}

				if (flag) { // 현재 num이 감소하는 수
					//System.out.println("count : " + count + "  num : " + num);
					answer = num;
					count++;
					num++;
				}

			}
		}

		System.out.println(answer);
	}

}
