package com.day1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HN_BOJ5430 {
	static int TC, N;
	static char[] P;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringBuilder answer = new StringBuilder();
			P = br.readLine().toCharArray();
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), "[|,|]");
			List<Integer> nlist = new ArrayList<>();
			boolean LR = true;
			while(st.hasMoreTokens()) {
				nlist.add(Integer.parseInt(st.nextToken()));
			}
			int head = 0;
			int tail = nlist.size() - 1;
			boolean error = false;
			for (char c : P) {
				if (c == 'R') {
					LR = LR ? false : true;
				} else {
					if (head > tail) { // error발생
						error = true;
						break;
					}
					if (LR) {
						head++;
					} else {
						tail--;
					}
				}
			}

			if (error) {
				answer.append("error");
			} else {
				answer.append("[");
				if(LR) {
					for (int i = head; i <= tail; i++) {
						answer.append(nlist.get(i)).append(",");
					}
				}else {
					for (int i = tail; i >= head ; i--) {
						answer.append(nlist.get(i)).append(",");
					}
				}
				// Error없이 데이터가 0 일때 출력을 정상적으로 처리해주어야 함
				if(answer.length() != 1)
					answer.setLength(answer.length() - 1);
				answer.append("]");
			}
			System.out.println(answer);
		}// TC 종료
	}
}
