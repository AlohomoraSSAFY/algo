package study0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ1062 {
	
	static int N;
	static int K;
	static String[] wordList;
	static int num;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		wordList = new String[N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			wordList[i] = str.substring(4, str.length()-4);
		}
		
		if (K < 5) {
			System.out.println(0);
			return;
		}
		
		if (K == 26) {
			System.out.println(N);
			return;
		}
		
		num = K - 5;
		int bm = 0;
		bm = bm | (1 << ('a'-97)) | (1 << ('c'-97)) | (1 << ('i'-97)) | (1 << ('n'-97)) | (1 << ('t'-97));
		recursion(0, 0, bm);
		
		System.out.println(result);
	}
	
	public static void recursion(int cnt, int start, int bm) {
		if (cnt == num) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				boolean flag = true;
				String s = wordList[i];
				for (int j = 0; j < s.length(); j++) {
					if ((bm & (1 << (s.charAt(j)-97))) == 0) {
						flag = false;
						break;
					}
				}
				
				if (flag) count++;
			}
			
			result = Math.max(result, count);
			return;
		}
		
		for (int i = start; i < 26; i++) {
			if ((bm & (1 << i)) != 0) continue;
			recursion(cnt + 1, i, bm | (1 << i));
		}
	}

}
