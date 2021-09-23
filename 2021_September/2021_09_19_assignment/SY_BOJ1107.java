package study0923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1107 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[] array = new boolean[10];
		StringTokenizer st = null;
		if (M != 0) st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			array[Integer.parseInt(st.nextToken())] = true;
		}
		
		//100번에서 +,- 버튼을 통해 이동하는 경우
		int result = Math.abs(N-100);
		
		for (int i = 0; i < 1000000; i++) {
			String str = String.valueOf(i);
			int len = str.length();
			
			boolean flag = true;
			for (int j = 0; j < len; j++) {
				int num = str.charAt(j) - '0';
				if (array[num]) {
					flag = false;
					break;
				}
			}
			
			if (flag && result > len + Math.abs(N-i)) {
				result = len + Math.abs(N-i);
			}
		}
		
		System.out.println(result);
	}

}
