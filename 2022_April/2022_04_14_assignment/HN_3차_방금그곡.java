package com.baekjoon.problem000;

import java.util.*;

public class HN_3차_방금그곡 {
	static class Music {
		int order; // 들어온 순서
		int time; // 재생시간
		String title; // 곡제목

		public Music(int order, int time, String title) {
			this.order = order;
			this.time = time;
			this.title = title;
		}
	}

	static PriorityQueue<Music> pq;

	public String solution(String m, String[] musicinfos) {
		String answer = "";
		pq = new PriorityQueue<Music>((m1, m2) -> {
			if (m1.time == m2.time) {
				return Integer.compare(m1.order, m2.order);
			}
			return Integer.compare(m2.time, m1.time);
		});
		m = m.replace("C#", "H").replace("D#", "I").replace("E#", "J").replace("F#", "K").replace("G#", "L")
				.replace("A#", "M");
		StringTokenizer st;
		for (int i = 0; i < musicinfos.length; i++) {
			st = new StringTokenizer(musicinfos[i], ":|,");
			int sSI = Integer.parseInt(st.nextToken());
			int sBUN = Integer.parseInt(st.nextToken());
			int eSI = Integer.parseInt(st.nextToken());
			int eBUN = Integer.parseInt(st.nextToken());
			int time = (eSI * 60 + eBUN) - (sSI * 60 + sBUN);
			String title = st.nextToken();
			String code = st.nextToken();
			code = code.replace("C#", "H").replace("D#", "I").replace("E#", "J").replace("F#", "K").replace("G#", "L")
					.replace("A#", "M");

			String music = code;

			while (music.length() < time) {
				music += code;
			}
			music = music.substring(0, time);

			if (music.contains(m)) {
				pq.add(new Music(i, time, title));
			}
		}

		if (pq.size() == 0) {
			answer = "(None)";
		} else {
			Music music = pq.poll();
			answer = music.title;
		}

		return answer;
	}
}
