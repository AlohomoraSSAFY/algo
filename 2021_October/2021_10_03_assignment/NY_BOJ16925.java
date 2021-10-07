package date1007THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ16925 {
	static int n;
	static String s;
	static String str[];
	static String sp[];
	static String check[];
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		str = new String[2 * n - 2];
		sp = new String[2 * n - 2];
		check = new String[2];
		visited = new boolean[n];
		int idx = 0;

		for (int i = 0; i < 2 * n - 2; i++) {
			str[i] = br.readLine();
			if (idx < 2 && str[i].length() == n - 1) {
				check[idx] = str[i];
				idx++;
			}
		}

		//
		
		boolean pre = true;

		for (int i = 0; i < check[0].length() - 1; i++) { // check[0]이 prefix
			if (check[0].charAt(i + 1) != check[1].charAt(i))
				pre = false;
		}

		if (pre) {
			s = check[0];
			s += check[1].charAt(n - 2);
		} else {
			s = check[1];
			s += check[0].charAt(n - 2);
		}

		for (int i = 0; i < 2 * n - 2; i++) {
			if (!s.startsWith(str[i]) && !s.endsWith(str[i])) {
				// check[1]이 prefix
				s = check[1];
				s += check[0].charAt(n - 2);
				break;
			}
		}

		sb.append(s + "\n");

		for (int i = 0; i < 2 * n - 2; i++) {
			if (s.startsWith(str[i]) && !visited[str[i].length()]) {
				sb.append("P");
				visited[str[i].length()] = true;
				}
			else if (s.endsWith(str[i]))
				sb.append("S");
		}
		System.out.println(sb.toString());
	}

}
