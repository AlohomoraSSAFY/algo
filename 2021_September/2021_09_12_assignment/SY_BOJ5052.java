package study0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SY_BOJ5052 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			int n = Integer.parseInt(br.readLine());
			String[] sArray = new String[n];
			for (int i = 0; i < n; i++) {
				sArray[i] = br.readLine();
			}
			Arrays.sort(sArray);
			
			boolean flag = true;
			for (int i = 0; i < n-1; i++) {
				if (sArray[i+1].startsWith(sArray[i])) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb);
	}

}
