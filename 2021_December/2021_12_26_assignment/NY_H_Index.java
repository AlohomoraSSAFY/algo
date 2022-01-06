package date1230SUN;

import java.util.*;

public class 프로그래머스_H_Index {
	public int solution(int[] citations) {
		int answer = 0;
		int len = citations.length;
		Arrays.sort(citations);
		int h = citations[len - 1];
		while (h > 0) {
			int a = 0;
			int b = 0;
			for (int i = 0; i < len; i++) {
				if (citations[i] <= h)
					a++; // 이하
				else if (citations[i] >= h)
					b++; // 이상
			}
			if (a <= h && b >= h) {
				answer = h;
				break;
			}
			h--;
		}

		return answer;
	}
}
