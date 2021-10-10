package study1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ3019 {
	
	static int[][][] shape = {
			{{0}},
			{{0}, {0, 0, 0, 0}},
			{{0, 0}},
			{{0, 1}, {1, 1, 0}},
			{{1, 0}, {0, 1, 1}},
			{{0, 0, 0}, {1, 0}, {0, 1, 0}, {0, 1}},
			{{0, 0, 0}, {0, 0}, {1, 0, 0}, {0, 2}},
			{{0, 0, 0}, {2, 0}, {0, 0, 1}, {0, 0}}
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int C = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int[] array = new int[C];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			array[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		for (int i = 0; i < shape[P].length; i++) {
			int[] s = shape[P][i];
			for (int j = 0; j < C - s.length + 1; j++) {
				boolean flag = true;
				int sum = s[0] + array[j];
				for (int k = 1; k < s.length; k++) {
					if (s[k] + array[j+k] != sum) {
						flag = false;
						break;
					}
				}
				
				if (flag) cnt++;
			}
		}
		
		System.out.println(cnt);
	}

}
