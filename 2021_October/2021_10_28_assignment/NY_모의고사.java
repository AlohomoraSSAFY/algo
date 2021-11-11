package date1031SUN;

public class 프로그래머스_모의고사 {

	public int[] solution(int[] answers) {

		int len = answers.length;

		int count[] = new int[3];

		int one[] = { 1, 2, 3, 4, 5 };
		int two[] = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int three[] = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

		for (int i = 0; i < len; i++) {
			if (one[i % 5] == answers[i])
				count[0]++;
			if (two[i % 8] == answers[i])
				count[1]++;
			if (three[i % 10] == answers[i])
				count[2]++;

		}

		int max = Math.max(count[0], count[1]);
		max = Math.max(count[2], max);

		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (count[i] == max)
				cnt++;
		}

		int[] answer = new int[cnt];

		int idx = 0;
		for (int i = 0; i < 3; i++) {
			if (count[i] == max) {
				answer[idx] = i + 1;
				idx++;
			}
		}
		return answer;
	}

}
