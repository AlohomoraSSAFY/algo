package date1219SUN;

import java.util.*;

public class 프로그래머스_위장 {

	public int solution(String[][] clothes) {
		int answer = 1;
		HashMap<String, Integer> hm = new HashMap<>();
		for (int i = 0; i < clothes.length; i++) {
			String str = clothes[i][1];
			if (!hm.containsKey(str)) {
				hm.put(str, 1);
			} else {
				int t = hm.get(str);
				hm.put(str, t + 1);

			}
		}
		for (String key : hm.keySet()) {
			answer *= (hm.get(key) + 1);
		}

		return answer - 1;
	}
}
