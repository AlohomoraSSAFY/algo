package date0224;

import java.util.*;

public class NY_셔틀버스 {

	public String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		PriorityQueue<Integer> q = new PriorityQueue<>();
		int starttime = 9 * 60;

		for (int i = 0; i < timetable.length; i++) {
			StringTokenizer st = new StringTokenizer(timetable[i], ":");
			int cur = Integer.parseInt(st.nextToken()) * 60;
			cur += Integer.parseInt(st.nextToken());
			q.offer(cur);
		}

		while (n > 1) {
			for (int i = 0; i < m; i++) {
				if (!q.isEmpty() && q.peek() <= starttime) {
					q.poll();
				}
			}
			starttime += t;
			n--;
		}

		int corn = starttime;
		int min = Integer.MAX_VALUE;

		if (q.size() >= m) {
			for (int i = 0; i < m; i++) {
				if (!q.isEmpty() && q.peek() <= starttime) {
					min = q.poll();
				}
			}

			if (min != Integer.MAX_VALUE) {
				corn = Math.min(min - 1, corn);
			}

		}

		int minute = corn % 60;
		int hour = corn / 60;

		String mm = "";
		String hh = "";
		if (minute < 10)
			mm = 0 + Integer.toString(minute);
		else
			mm = Integer.toString(minute);
		if (hour < 10)
			hh = 0 + Integer.toString(hour);
		else
			hh = Integer.toString(hour);

		answer += hh + ":" + mm;
		return answer;

	}
}
