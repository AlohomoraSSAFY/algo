package com.baekjoon.problem55;

import java.util.Stack;

public class HN_괄호회전하기 {
	public int solution(String s) {
		int answer = 0;
		int len = s.length();
		boolean find = false;

		for (int l = 0; l < len; l++) {
			if (find)
				break;
			String str = s.substring(l, len) + s.substring(0, l);
			answer = 0;
			// 올바른 문자열 찾기
			char temp[] = str.toCharArray();
			Stack<Character> stack = new Stack<>();
			boolean find2 = true;
			check: for (int i = 0; i < len; i++) {
				switch (temp[i]) {
				case '(':
					stack.add(temp[i]);
					break;
				case '[':
					stack.add(temp[i]);
					break;
				case '{':
					stack.add(temp[i]);
					break;
				case ')':
					if (stack.size() != 0 && stack.peek() == '(') {
						stack.pop();
					} else {
						find2 = false;
						break check;
					}
					break;
				case ']':
					if (stack.size() != 0 && stack.peek() == '[') {
						stack.pop();
					} else {
						find2 = false;
						break check;
					}
					break;
				case '}':
					if (stack.size() != 0 && stack.peek() == '{') {
						stack.pop();
					} else {
						find2 = false;
						break check;
					}
					break;
				}
				if (stack.size() == 0)
					answer++;
			}
			if (find2)
				find = true;
		}
		return answer;
	}
}
