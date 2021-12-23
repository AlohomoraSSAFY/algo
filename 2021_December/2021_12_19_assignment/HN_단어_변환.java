package com.baekjoon.problem41;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;

public class HN_단어_변환 {
	static Map<String, Integer> dp;
	static int visited[];

	static class Word implements Comparable<Word> {
		char[] word;
		int count;

		public Word(char[] word, int count) {
			this.word = word;
			this.count = count;
		}

		public int compareTo(Word w) {
			return Integer.compare(this.count, w.count);
		}
	}

	public int solution(String begin, String target, String[] words) {
		int answer = 0;
		int len = words.length;
		int word_len = words[0].toCharArray().length;
		char wordArray[][] = new char[len][word_len];
		visited = new int[len];
		for (int i = 0; i < len; i++) {
			wordArray[i] = words[i].toCharArray();
		}
		answer = change(begin, target, wordArray, len, word_len);
		return answer;
	}

	public int change(String begin, String target, char[][] words, int len, int word_len) {
		PriorityQueue<Word> pq = new PriorityQueue<>();
		pq.add(new Word(begin.toCharArray(), 0));
		Arrays.fill(visited, Integer.MAX_VALUE);
		int idx = 0, cnt = 0;
		while (!pq.isEmpty()) {
			Word w = pq.poll();
			if (new String(w.word).equals(target)) {
				return w.count;
			}

			for (int i = 0; i < len; i++) {
				if (visited[i] <= w.count)
					continue;
				idx = 0;
				cnt = 0;
				for (int l = 0; l < word_len; l++) {
					if (w.word[l] != words[i][l]) {
						cnt++;
						idx = l;
						if (cnt >= 2)
							break;
					}
				}
				if (cnt == 1) {
					char new_word[] = words[i].clone();
					visited[i] = w.count + 1;
					pq.add(new Word(new_word, w.count + 1));
				}
			}
		}
		return 0;
	}
}
