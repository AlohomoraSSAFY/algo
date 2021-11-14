package com.baekjoon.problem32;

import java.util.*;

public class HN_전화번호목록 {

	static class Tree {
		HashMap<Character, Tree> hmap;
		boolean end;

		Tree() {}
	}

	static Tree root;
	static int size, len;

	public boolean solution(String[] phone_book) {
		boolean answer = true;

		root = new Tree();
		size = phone_book.length;

		for (int s = 0; s < size; s++) {
			char[] word = phone_book[s].toCharArray();

			answer = add(word);

			if (!answer)
				break;
		}

		return answer;
	}

	public boolean add(char[] word) {
		Tree now = root;
		len = word.length;

		for (int l = 0; l < len; l++) {
			if (now.hmap == null)
				now.hmap = new HashMap<>();

			if (now.hmap.get(word[l]) == null)
				now.hmap.put(word[l], new Tree());

			if (now.end)
				return false;

			now = now.hmap.get(word[l]);
		}

		if (now.hmap != null)
			return false;

		now.end = true;
		return true;
	}

}
