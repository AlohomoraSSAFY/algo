package date0106THU;

import java.io.*;
import java.util.*;

public class BOJ20437 {
	static int t, k;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			
			String w = br.readLine();
			k = Integer.parseInt(br.readLine());

			int answer1 = Integer.MAX_VALUE;
			int answer2 = 0;
			
			ArrayList<Integer> list[] = new ArrayList[123]; // 알파벳 위치 저장할 리스트
			for (int q = 97; q < 123; q++) {
				list[q] = new ArrayList<>();
			}
			
			
			for (int j = 0; j < w.length(); j++) {
				char c = w.charAt(j);
				list[c].add(j); // 위치 리스트에 추가
			}

			boolean flag = false;

			for (int j = 97; j < 123; j++) {

				int size = list[j].size();
				if (size >= k) { // j번째 문자가 k개 이상 나옴
					flag = true;
					for (int m = 0; m < size - k + 1; m++) {
						int len = list[j].get(m + k - 1) - list[j].get(m) + 1;
						answer1 = Math.min(len, answer1);
						answer2 = Math.max(len, answer2);
					}
				}
			}

			if (flag)
				sb.append(answer1 + " " + answer2);
			else
				sb.append(-1);
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}

}
