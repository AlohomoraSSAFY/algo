package study0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class SY_BOJ5430 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			char[] cmd = br.readLine().toCharArray();
			int n = Integer.parseInt(br.readLine());
			String str = br.readLine();
			str = str.substring(1, str.length()-1);
			String[] sArray = str.split(",");
			Deque<Integer> deque = new LinkedList<>();
			boolean loc = true; //true면 앞쪽, false면 뒷쪽
			boolean flag = true;
			for (int i = 0; i < n; i++) {
				deque.offer(Integer.parseInt(sArray[i]));
			}
			
			for (int i = 0; i < cmd.length; i++) {
				if (cmd[i] == 'R') {
					loc = !loc;
				} else {
					if (deque.isEmpty()) {
						flag = false;
						break;
					} else {
						if (loc) {
							deque.removeFirst();
						} else {
							deque.removeLast();
						}
					}
				}
			}
			
			if (flag) {
				sb.append("[");
				int size = deque.size();
				if (loc) {
					for (int i = 0; i < size; i++) {
						sb.append(deque.removeFirst()).append(",");
					}
				} else {
					for (int i = 0; i < size; i++) {
						sb.append(deque.removeLast()).append(",");
					}
				}
				if (size > 0) sb.setLength(sb.length()-1);
				sb.append("]\n");
			} else {
				sb.append("error\n");
			}
		}
		System.out.println(sb);
	}

}
