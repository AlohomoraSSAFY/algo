package study0414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class SY_BOJ4889 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 1;
		
		while (true) {
			String str = br.readLine();
			if (str.length() == 0) {
				sb.append(tc++ + ". " + 0 + "\n");
			} else {
				char[] array = str.toCharArray();
				if (array[0] == '-') break;
				
				Stack<Character> stack = new Stack<>();
				int cnt = 0;
				for (int i = 0; i < array.length; i++) {
					if (array[i] == '{') {
						stack.push('{');
					} else {
						if (stack.isEmpty()) {
							stack.push('{');
							cnt++;
						} else if (stack.peek() == '{') {
							stack.pop();
						} else {
							stack.pop();
							cnt++;
						}
					}
				}
				
				cnt += stack.size() / 2;
				sb.append(tc++ + ". " + cnt + "\n");
			}
		}
		
		System.out.println(sb);
	}

}
