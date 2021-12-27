package study1230;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SY_BOJ2448 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		List<String> list = new ArrayList<>();
		list.add("*");
		list.add("* *");
		list.add("*****");
		
		int p = 0;
		while (Math.pow(2, p) != N / 3) {
			p++;
		}
		
		StringBuilder sb;
		for (int i = 0; i < p; i++) {
			int size = list.size();
			for (int j = 0; j < size; j++) {
				sb = new StringBuilder();
				sb.append(list.get(j));
				for (int k = 0; k < 2 * (size - j) - 1; k++) {
					sb.append(" ");
				}
				sb.append(list.get(j));
				list.add(sb.toString());
			}
		}
		
		sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < N - 1 - i; j++) {
				sb.append(" ");
			}
			sb.append(list.get(i));
			for (int j = 0; j < N - 1 - i; j++) {
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
