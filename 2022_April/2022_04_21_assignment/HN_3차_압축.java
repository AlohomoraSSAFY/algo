package com.baekjoon.problem000;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HN_3차_압축 {

	public int[] solution(String msg) {
		List<Integer> list = new LinkedList<>();
		Map<String, Integer> map = new HashMap<>();

		char c = 'A';
		for (int i = 1; i <= 26; i++) {
			String str = String.valueOf(c);
			map.put(str, i);
			c++;
		}

		int value = 27;
		int start = 0;
		int end = start + 1;
		// substring은 끝문자를 포함하지 않으므로 <= 부등호 처리
		while (end <= msg.length()) {
			String str = msg.substring(start, end);
			// 사전에 추가되지 않은 단어를 찾는다.
			if (map.containsKey(str) && end <= msg.length()) {
				end++;
				continue;
			}
			// 사전에 추가하고, 출력 리스트에 출력값을 추가한다.
			list.add(map.get(msg.substring(start, end - 1)));
			map.put(str, value++);
			start = end - 1;
		}
		// 마지막 포함 값 넣어주기
		list.add(map.get(msg.substring(start, end - 1)));

		int[] answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}

}
