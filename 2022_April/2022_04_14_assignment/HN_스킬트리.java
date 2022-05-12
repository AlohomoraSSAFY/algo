package com.baekjoon.problem000;

import java.util.*;

public class HN_스킬트리 {
	class Solution {
		public int solution(String skill, String[] skill_trees) {
			int answer = skill_trees.length;

			char[] temp = skill.toCharArray();
			Map<Character, Integer> smap = new HashMap<>();
			for (int l = 0; l < temp.length; l++) {
				smap.put(temp[l], l);
			}

			for (int l = 0; l < skill_trees.length; l++) {
				int myLevel = 0;
				char[] tree = skill_trees[l].toCharArray();
				for (int t = 0; t < tree.length; t++) {
					if (smap.containsKey(tree[t])) {
						if (myLevel == smap.get(tree[t])) {
							myLevel++;
						} else {
							answer--;
							break;
						}
					}
				}
			}

			return answer;
		}
	}
}
