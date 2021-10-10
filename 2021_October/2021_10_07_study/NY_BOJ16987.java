package date1010SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
	static int n;
	static int[] s, w;
	static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		s = new int[n];
		w = new int[n];
		answer = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			s[i] = Integer.parseInt(st.nextToken());
			w[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {// 현재 개란
			if (s[i] > 0) { // 현재 손에 든 계란이 깨지지 않았으면
				for (int j = i; j < n; j++) { // 칠 계란
					if (s[j] > 0) { // 칠 계란이 깨지지 않았으면
						// 계란을 침
					}
				}
			}
		}
		broke(0);
		System.out.println(answer);
	}

	public static void broke(int cnt) {
		if (cnt == n) {
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (s[i] <= 0)
					count++;
			}
			answer = Math.max(answer, count);
			return;
		}

		if (s[cnt] > 0) { //현재 든 계란이 안 깨져있으면
			boolean broken = false;
			for (int i = 0; i < n; i++) { // 칠 계란
				if (i != cnt && s[i] > 0) { // 칠 계란이 현재 계란이 아니고 깨지지 않았으면
					// 계란을 침
					s[cnt]-=w[i];
					s[i]-=w[cnt];
					broke(cnt+1);
					s[cnt]+=w[i];
					s[i]+=w[cnt];
					broken = true;
				}
			}
			if(!broken) //칠 계란이 없으면 넘어감
				broke(cnt+1);
			
		} else //현재 든 계란이 안깨져있으면 그냥 넘어감
			broke(cnt + 1);

	}

}
