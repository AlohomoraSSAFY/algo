package study1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SY_BOJ16925 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[][] sArray = new char[2*N-2][];
		char[][] rArray = new char[2][];
		boolean[] check = new boolean[N];
		int temp = 0;
		for (int i = 0; i < sArray.length; i++) {
			String str = br.readLine();
			sArray[i] = str.toCharArray();
			if (str.length() == N-1) {
				rArray[temp++] = str.toCharArray();
			}
		}
		
		char[][] tArray = new char[2][N];
		tArray[0][0] = rArray[0][0];
		tArray[1][0] = rArray[1][0];
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j < N; j++) {
				tArray[i][j] = rArray[1-i][j-1];
			}
		}
		
		for (int i = 0; i < 2; i++) {
			StringBuilder sb = new StringBuilder();
			String str = String.valueOf(tArray[i]);
			sb.append(str + "\n");
			
			boolean flag = true;
			boolean flag1 = true;
			boolean flag2 = true;
			for (int j = 0; j < sArray.length; j++) {
				if (!str.startsWith(String.valueOf(sArray[j]))) {
					flag1 = false;
				}
				
				if (!str.endsWith(String.valueOf(sArray[j]))) {
					flag2 = false;
				}
				
				if (!flag1 && !flag2) {
					flag = false;
					break;
				} else if (flag1 && flag2) {
					if (!check[sArray[j].length]) {
						sb.append("P");
						check[sArray[j].length] = true;
					} else {
						sb.append("S");
					}
				} else if (flag1) {
					sb.append("P");
					check[sArray[j].length] = true;
					flag2 = true;
				} else {
					sb.append("S");
					flag1 = true;
				}
			}
			
			if (flag) {
				System.out.println(sb);
				return;
			}
		}
	}

}
