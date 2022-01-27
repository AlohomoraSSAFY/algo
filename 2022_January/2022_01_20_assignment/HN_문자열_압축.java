package com.baekjoon.problem48;

public class HN_문자열_압축 {
	public int solution(String s) {
		char[] word = s.toCharArray();
		int len = word.length;
		int answer = len;
		int cnt = len / 2;
		for (int gab = cnt; gab > 0; gab--) {
			boolean pass = true;
			int result = 0;
			int pcnt = 1;
			find: for (int start = 0; start < len;) {
				for (int recnt = 0; recnt < gab; recnt++) {
					if (start + gab - 1 >= len || start + gab + recnt >= len) {
						if (pcnt == 1) {
							result += len - start;
						} else {
							result += Math.log10(pcnt) + 1 + gab + recnt;
							pcnt = 1;
						}
						break find;
					}

					if (word[start + recnt] != word[start + recnt + gab]) {
						pass = false;
						break;
					}
				}

				if (!pass) {
					if (pcnt == 1) {
						result += gab;
					} else {
						result += Math.log10(pcnt) + 1 + gab;
						pcnt = 1;
					}
					pass = true;
				} else {
					pcnt++;
				}
				start += gab;
			}

			answer = Math.min(answer, result);
		}
		return answer;
	}
}
