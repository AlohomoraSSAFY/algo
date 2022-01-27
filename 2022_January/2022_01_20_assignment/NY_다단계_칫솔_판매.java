package date0127THU;

import java.util.*;

public class 프로그래머스_다단계_칫솔_판매 {

	class Solution {
		public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
			int[] answer = new int[enroll.length];

			HashMap<String, Integer> map = new HashMap<String, Integer>();

			for (int i = 0; i < seller.length; i++) {
				int idx = -1;
				if (map.containsKey(seller[i])) {
					idx = map.get(seller[i]);
				} else {
					for (int j = 0; j < enroll.length; j++) {
						if (enroll[j].equals(seller[i])) {
							idx = j;
							map.put(seller[i], j);
							break;
						}
					}
				}
				dfs(enroll, referral, seller, answer, idx, amount[i] * 100, map);
			}

			return answer;
		}

		public void dfs(String[] enroll, String[] referral, String[] seller, int[] answer, int curidx, int money,
				HashMap<String, Integer> map) {
			answer[curidx] += money - money / 10;

			if (money / 10 == 0)
				return;
			String ref = referral[curidx];
			if (ref.equals("-")) {
				return;
			}

			int nextidx = -1;
			if (map.containsKey(ref)) {
				nextidx = map.get(ref);
			} else {
				for (int i = 0; i < enroll.length; i++) {
					if (enroll[i].equals(ref)) {
						map.put(ref, i);
						nextidx = i;
						break;
					}
				}
			}
			int m = money / 10;
			dfs(enroll, referral, seller, answer, nextidx, m, map);

		}
	}
}