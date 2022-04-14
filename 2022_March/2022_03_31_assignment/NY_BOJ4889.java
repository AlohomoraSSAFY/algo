package date0331;

import java.io.*;
import java.util.*;
import java.io.InputStreamReader;

public class BOJ4889 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int count = 1;
		while (true) {

			int answer = 0;
			String str = br.readLine();
			if (str.startsWith("-"))
				break;
			Stack<Character> stack = new Stack<>();
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == '{') {
					stack.add('{');
				} else {
					if (!stack.isEmpty() && stack.peek() == '{') {
						stack.pop();
					} else
						stack.add('}');
				}
			}
			
			while(!stack.isEmpty()) {
				Character c = stack.pop();
				if(!stack.isEmpty()&&stack.peek() == c) {
					answer++;
					stack.pop();
				}else {
					answer+=2;
					stack.pop();
				}
			}
			sb.append(count++ + "." + " " + answer+"\n");
		}
		System.out.println(sb.toString());
	}

}
