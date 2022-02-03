package com.baekjoon.problem49;

import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class HN_추석_트래픽 {
	static class Time implements Comparable<Time> {
		int start, end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public int compareTo(Time t) {
			if (this.end == t.end) {
				return Integer.compare(t.start, this.start);
			}
			return Integer.compare(t.end, this.end);
		}
	}

	public int solution(String[] lines) {
		int answer = 0;
		PriorityQueue<Time> pq = new PriorityQueue<>();
		int len = lines.length;
		StringTokenizer st;
		int start, end;
		for (int l = 0; l < len; l++) {
			st = new StringTokenizer(lines[l], "s|:| ");
			st.nextToken();
			int si = Integer.parseInt(st.nextToken()) * 3600000;
			int bun = Integer.parseInt(st.nextToken()) * 60000;
			int cho = (int) (Double.parseDouble(st.nextToken()) * 1000);
			end = si + bun + cho;
			int ptime = (int) (Double.parseDouble(st.nextToken()) * 1000);
			start = end - ptime + 1;
			pq.add(new Time(start, end));
		}

		end = pq.peek().end;
		PriorityQueue<Time> output = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.start, t1.start));
		while (!pq.isEmpty()) {
			Time t = pq.poll();
			if (end > t.end) {
				end = t.end;
				while (!output.isEmpty()) {
					if (output.peek().start >= end + 1000) {
						output.poll();
					} else {
						break;
					}
				}
				output.add(t);
				answer = Math.max(output.size(), answer);
			} else {
				output.add(t);
				answer = Math.max(output.size(), answer);
			}
		}

		return answer;
	}
}
