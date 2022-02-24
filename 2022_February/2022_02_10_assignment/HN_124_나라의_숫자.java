package Kakao;

import java.util.Stack;

public class HN_124_나라의_숫자 {

	class Solution {
		public String solution(int n) {
			String answer = dfs(n);
			;
			return answer;
		}

		public String dfs(int n) {
			Stack<Integer> stack = new Stack<>();
			int r = 3;

			while (n > 0) {
				int gab = r / 3;
				int m = n % r;
				if (m != 0 && m <= gab) {
					stack.add(1);
				} else if (m != 0 && m <= gab * 2) {
					stack.add(2);
				} else {
					stack.add(4);
				}
				n -= r;
				r *= 3;
			}

			StringBuilder sb = new StringBuilder();
			while (!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			return String.valueOf(sb);
		}
	}

}
