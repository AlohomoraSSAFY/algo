package date1010SUN;

public class 프로그래머스_로또의_최고_순위와_최저_순위 {

	public int[] solution(int[] lottos, int[] win_nums) {
		int match = 0;
		int blank = 0;
		int[] prize = { 6, 6, 5, 4, 3, 2, 1 };
		for (int i = 0; i < 6; i++) {
			if (lottos[i] == 0)
				blank++;
			for (int j = 0; j < 6; j++) {
				if (lottos[i] == win_nums[j])
					match++;
			}
		}

		int[] answer = new int[2];
		answer[1] = prize[match];
		answer[0] = prize[match + blank];

		return answer;
	}

}
