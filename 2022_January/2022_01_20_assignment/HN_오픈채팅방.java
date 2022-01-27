package com.baekjoon.problem48;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class HN_오픈채팅방 {
	static Map<String, String> name;
	static Map<String, List<Integer>> position;

	public String[] solution(String[] record) {
		name = new HashMap<>();
		position = new HashMap<>();

		StringTokenizer st;
		int len = record.length;
		int cur = 0;
		for (int l = 0; l < len; l++) {
			st = new StringTokenizer(record[l]);
			String ope = st.nextToken();
			String id = st.nextToken();
			if (ope.equals("Enter")) {
				String nickname = st.nextToken();
				List<Integer> plist = position.getOrDefault(id, new LinkedList<>());
				plist.add(cur++);
				position.put(id, plist);
				name.put(id, nickname);
			} else if (ope.equals("Leave")) {
				List<Integer> plist = position.get(id);
				plist.add(cur++);
				position.put(id, plist);
			} else {
				String nickname = st.nextToken();
				name.put(id, nickname);
			}
		}

		String answer[] = new String[cur];
		for (String id : name.keySet()) {
			String nickname = name.get(id);
			List<Integer> plist = position.get(id);
			int odd = 0;
			for (Integer i : plist) {
				if (odd++ % 2 == 0) {
					answer[i] = nickname + "님이 들어왔습니다.";
				} else {
					answer[i] = nickname + "님이 나갔습니다.";
				}
			}
		}
		return answer;
	}
}
