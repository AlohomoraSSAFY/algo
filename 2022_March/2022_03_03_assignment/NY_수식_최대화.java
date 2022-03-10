package date0310;
import java.util.*;
public class 프로그래머스_수식최대화 {

    public long solution(String expression) {
		long answer = 0;
		int[][] operator = { { -1, -2, -3 }, { -1, -3, -2 }, { -2, -1, -3 }, { -3, -1, -2 }, { -2, -3, -1 },
				{ -3, -2, -1 } };
		char[] operatortochar = { ' ', '+', '-', '*' };

		StringTokenizer st = new StringTokenizer(expression, "+*-");

		ArrayList<Long> list = new ArrayList<>();
		while (st.hasMoreTokens()) {
			String str = st.nextToken();

			list.add(Long.parseLong(str));

		}
		// 연산자 넣어주기
		int idx = 1;
		int t = 1;
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '+') {
				list.add(idx, (long) -1);
				idx += 2;

			} else if (c == '-') {
				list.add(idx, (long) -2);
				idx += 2;
			} else if (c == '*') {
				list.add(idx, (long) -3);
				idx += 2;
			}
		}
		for (int o = 0; o < 6; o++) {
			ArrayList<Long> list2 = new ArrayList<>();
			for(long l : list) {
				list2.add(l);
			}
			int[] op = operator[o];
			long temp = 0;
			for (int i = 0; i < 3; i++) {
				int co = op[i];			
				for (int j = 0; j < list2.size(); j++) {
					if (co == list2.get(j)) {
						long a = list2.get(j - 1);
						long b = list2.get(j + 1);
						list2.remove(j - 1);
						list2.remove(j - 1);
						list2.remove(j - 1);
						long c = 0;
						if (co == -1)
							c = a + b;
						else if (co == -2)
							c = a - b;
						else if (co == -3)
							c = a * b;
						list2.add(j - 1, c);
						j-=1;
					}
				}

			}
			temp = list2.get(0);
			answer = Math.max(answer, Math.abs(temp));
		}

		return answer;
    }
}
