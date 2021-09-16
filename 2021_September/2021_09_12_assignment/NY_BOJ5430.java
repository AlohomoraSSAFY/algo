package date0916THU;

import java.io.*;
import java.util.*;

public class BOJ5430_2 {
	static int tc, n;
	static String p;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		tc = Integer.parseInt(br.readLine());

		for (int t = 0; t < tc; t++) {
			p = br.readLine();
			n = Integer.parseInt(br.readLine());
			arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
			Deque<Integer> dq = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				dq.add(Integer.parseInt(st.nextToken()));
			}

			//

			boolean r = false;
			boolean error = false;

			for (int i = 0; i < p.length(); i++) {

				char c = p.charAt(i);
				if (c == 'R') {
					r = !r;
				} else if (c == 'D') {
					if (dq.size() == 0) {
						error = true;
						sb.append("error\n");
						break;
					} else if (r) {
						dq.pollLast();
					} else {
						dq.pollFirst();
					}
					n--;
				}
			}

			if (!error) {
				sb.append("[");
				if (dq.size() != 0) {
					if (r) {
						while (dq.size() > 1) {
							sb.append(dq.pollLast() + ",");
						}
						sb.append(dq.pollLast());
					} else {
						while (dq.size() > 1) {
							sb.append(dq.poll() + ",");
						}
						sb.append(dq.poll());
					}
				}
				sb.append("]\n");
			}
		} // eot

		System.out.println(sb.toString());
	}

	public static String printlist(ArrayList<Integer> list) {
		String s = "[";
		if (list.size() > 0) {
			for (int i = 0; i < list.size() - 1; i++) {
				s += Integer.toString(list.get(i)) + ",";
			}
			s += Integer.toString(list.get(list.size() - 1));
		}
		s += "]";

		return s;
	}

}
