package com.baekjoon.problem47;

public class HN_카펫 {
	public int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int maxr = brown / 2;
		int maxc = brown / 2;
		find: for (int r = 3; r < maxr; r++) {
			for (int c = 3; c < maxc; c++) {
				if (r + c > maxr + 2) {
					break;
				}
				if ((r - 2) * (c - 2) == yellow) {
					answer[0] = c;
					answer[1] = r;
					break find;
				}
			}
		}
		return answer;
	}
}
