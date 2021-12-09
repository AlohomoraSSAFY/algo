package com.baekjoon.problem37;

public class HN_네트워크 {

	static boolean visited[];
	static int[][] connect;

	public int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[computers.length];
		connect = computers;

		for (int i = 0; i < computers.length; i++) {
			if (!visited[i]) {
				dfs(i);
				answer++;
			}
		}

		return answer;
	}

	static public void dfs(int no) {
		visited[no] = true;

		for (int i = 0; i < connect[no].length; i++) {
			if (!visited[i] && connect[no][i] == 1) {
				dfs(i);
			}
		}
	}

}
