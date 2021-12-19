package date1219SUN;

import java.util.*;

public class 프로그래머스_디스크컨트롤러 {

	public int solution(int[][] jobs) {
		int answer = 0;
		boolean checked[] = new boolean[jobs.length];

		Arrays.sort(jobs, new Comparator<int[]>() {

			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0])
					return o1[0] - o2[0];
				else
					return o1[1] - o2[1];
			}
		});
		int count = 0;
		int time = 0;
		while (count < jobs.length) {
			int min = 1001;
			int minidx = -1;
			for (int i = 0; i < jobs.length; i++) {
				// 전체에서 현재 시간에 가능한 것 중 소요시간 가장 짧은거 탐색
				if (jobs[i][0] <= time && !checked[i]) { // 현재 시간에 이미 요청되어있고
					if (jobs[i][1] < min) {
						minidx = i;
						min = jobs[i][1];
					}
				}
			}
			if (minidx != -1) { // 할 수 있는 일 찾았으면
				answer += time - jobs[minidx][0] + jobs[minidx][1];
				checked[minidx] = true;
				count++;
				time += jobs[minidx][1];
			} else
				time++;
		}
		return answer / jobs.length;
	}
}
