package com.baekjoon.problem41;

import java.util.*;

public class HN_다리를_지나는_트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		int idx = 0, cur_time = 0, sum = 0;
		int len = truck_weights.length;
		Queue<int[]> q = new LinkedList<>();
		while (idx < len || !q.isEmpty()) {
			cur_time++;
			if (!q.isEmpty() && cur_time >= q.peek()[1]) {
				int temp[] = q.poll();
				sum -= temp[0];
				answer = temp[1];
			}
			if (idx < len && sum + truck_weights[idx] <= weight) {
				q.add(new int[] { truck_weights[idx], cur_time + bridge_length });
				sum += truck_weights[idx];
				idx++;
			}
		}
		return answer;
	}
}
