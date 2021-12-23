package date1223THU;

import java.util.*;

public class 프로그래머스_단어변환 {
	public int solution(String begin, String target, String[] words) {

		int targetidx = 0;
		boolean possible = false;
		for (int i = 0; i < words.length; i++) {
			if (words[i].equals(target)) {
				targetidx = i;
				possible = true;
				break;
			}
		}
		if (!possible)
			return 0;
		int answer = bfs(begin, target, words, targetidx);
		return answer;
	}

	public static int bfs(String begin, String target, String[] words, int targetidx) {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[] = new boolean[words.length];
		// 큐 초기화 작업
		for (int i = 0; i < words.length; i++) {
			int count = 0;
			for (int j = 0; j < target.length(); j++) {
				if (begin.charAt(j) != words[i].charAt(j))
					count++;
			}
			if (count == 1)
				q.offer(new int[] { i, 1 });
		}

		while (!q.isEmpty()) {
			int[] temp = q.poll();
			int idx = temp[0];
			int cnt = temp[1];
			if (idx == targetidx)
				return cnt;

			for (int i = 0; i < words.length; i++) {
				if (!visited[i]) {
					int count = 0;
					for (int j = 0; j < target.length(); j++) {
						if (words[idx].charAt(j) != words[i].charAt(j))
							count++;
					}
					if (count == 1)
						q.offer(new int[] { i, cnt + 1 });
				}
			}

		}
		return 0;
	}
}

