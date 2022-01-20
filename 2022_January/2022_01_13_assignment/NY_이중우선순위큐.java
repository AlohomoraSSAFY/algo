package date0120THU;

import java.util.*;

public class 프로그래머스_이중우선순위큐 {
	public int[] solution(String[] operations) {
		int[] answer = new int[2];

		PriorityQueue<Integer> aaa = new PriorityQueue<>();
		PriorityQueue<Integer> bbb = new PriorityQueue<>(Collections.reverseOrder());

		int len = 0;

		for (int i = 0; i < operations.length; i++) {

			StringTokenizer st = new StringTokenizer(operations[i]);
			char operator = st.nextToken().charAt(0);
			int temp = Integer.parseInt(st.nextToken());

			if (operator == 'I') {
				len++;
				aaa.offer(temp);
				bbb.offer(temp);
			} else { // 삭제
				len--;
				if (temp == -1 && !aaa.isEmpty()) { // 최솟값 삭제
					int c = aaa.poll();
					bbb.remove(c);
				} else if (!bbb.isEmpty()) {// 최댓값 삭제
					int c = bbb.poll();
					aaa.remove(c);
				}
			}
		}
		if (len <= 0) {
			return answer;
		}
		answer[0] = bbb.poll();
		answer[1] = aaa.poll();
		return answer;
	}
}
