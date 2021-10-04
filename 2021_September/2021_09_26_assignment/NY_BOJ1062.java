package date0930THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class BOJ1062 {
	static int n, k;
	static int answer;
	static int stb[];
	static int selected[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		stb = new int[n];
		
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < str.length(); j++) {
				char c = str.charAt(j);
				stb[i] |= (1 << c - 'a');
			}
		}
		
		//
		
		answer = 0;
		
		if (k == 26)
			answer = n;
		else if (k >= 5) {
			selected = new int[k - 5];
			combi(0, 0);
		}
		
		System.out.println(answer);

	}

	public static void combi(int count, int start) {
		if (count == k - 5) {
			int b = 532741;
			int cnt = 0;
			
			for (int c : selected) {
				b |= (1 << c);
			}
			
			for (int i = 0; i < stb.length; i++) {
				int a = stb[i];
				a &= ~(b);
				if (a == 0)
					cnt++;
			}

			answer = Math.max(answer, cnt);
			return;
		}

		for (int i = start; i <= 26; i++) {
			selected[count] = i;
			combi(count + 1, i + 1);
		}

	}

}
