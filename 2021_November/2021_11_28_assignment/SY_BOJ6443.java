package study1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SY_BOJ6443 {
	
	static char[] array;
	static int len;
	static char[] selected;
	static boolean[] visited;
	static char[] overlap;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			array = br.readLine().toCharArray();
			Arrays.sort(array);
			len = array.length;
			selected = new char[len];
			visited = new boolean[len];
			overlap = new char[len];
			permutation(0);
		}
		
		System.out.println(sb);
	}
	
	public static void permutation(int cnt) {
		if (cnt == len) {
			sb.append(String.valueOf(selected) + "\n");
			return;
		}
		
		overlap[cnt] = 'a' - 1;
		for (int i = 0; i < len; i++) {
			if (visited[i]) continue;
			if (overlap[cnt] >= array[i]) continue;
			
			overlap[cnt] = array[i];
			visited[i] = true;
			selected[cnt] = array[i];
			permutation(cnt + 1);
			visited[i] = false;
		}
	}

}
