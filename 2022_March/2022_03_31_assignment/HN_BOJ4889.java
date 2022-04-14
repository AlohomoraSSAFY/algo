package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class HN_BOJ4889 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input;
		int idx = 1;
		StringBuilder sb = new StringBuilder();
		while (!(input = br.readLine()).startsWith("-")) {
			char temp[] = input.toCharArray();
			int answer = 0;
			Stack<Character> stack = new Stack<>();
			for (char t : temp) {
				if (t == '{') {
					stack.add(t);
				} else {
					if (stack.size() > 0) {
						stack.pop();
					} else {  
						answer++;
						stack.add('{'); 
					}
				}
			}
			sb.append((idx++) + ". " + (answer + stack.size() / 2)).append("\n");
		}
		System.out.println(sb);
	}

}
