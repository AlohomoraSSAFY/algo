package study1205;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SY_BOJ2661 {
	
	static int N;
	static int[] selected;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		selected = new int[N];
		
		recursion(0, "");
	}
	
	public static void recursion(int cnt, String str) {
		if (cnt == N) {
			flag = true;
			System.out.println(str);
			
			return;
		}
		
		for (int i = 1; i <= 3; i++) {
			selected[cnt] = i;
			
			int c = (cnt+1)/2;
			boolean check = false;
			for (int j = 1; j <= c; j++) {
				int k = j;
				int count = 0;
				while (k-- > 0) {
					if (selected[cnt-k] == selected[cnt-k-j]) count++;
				}
				if (count == j) {
					check = true;
					break;
				}
			}
			
			if (check) continue;
			
			recursion(cnt + 1, str + Integer.toString(selected[cnt]));
			if (flag) break;
		}
	}

}
