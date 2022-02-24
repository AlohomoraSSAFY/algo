package date0224;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9251 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		int answer =0;
		int lcs[][] = new int[str1.length()+1][str2.length()+1];
		for(int i=0;i<str1.length();i++) {
			for(int j=0;j<str2.length();j++) {
				if(str1.charAt(i)== str2.charAt(j)) {
					lcs[i+1][j+1] = lcs[i][j]+1;
				}else
					lcs[i+1][j+1] = Math.max(lcs[i][j+1], lcs[i+1][j]);
				answer = Math.max(answer, lcs[i+1][j+1]);
			}
		}
		System.out.println(answer);
	}
}
