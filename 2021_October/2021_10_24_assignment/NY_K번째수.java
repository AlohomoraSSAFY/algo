package date1028THU;

import java.util.*;

public class 프로그래머스_K번째수 {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer;
		int num = commands.length;

		answer = new int[num];

		for (int t = 0; t < num; t++) {
			int len = commands[t][1] - commands[t][0] + 1;
			int[] temp = new int[len];
			int idx = commands[t][0] - 1;
			for (int i = 0; i < len; i++) {
				temp[i] = array[idx++];
			}
			Arrays.sort(temp);
			answer[t] = temp[commands[t][2] - 1];
		}
		return answer;
	}
}
