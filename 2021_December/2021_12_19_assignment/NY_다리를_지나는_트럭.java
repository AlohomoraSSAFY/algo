package date1223THU;

import java.util.*;

public class 프로그래머스_다리를_지나는_트럭 {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		Queue<Integer> bridge = new LinkedList<>();
		int time = 0;
		for (int i = 0; i < bridge_length; i++) {
			bridge.offer(0); // 다리 0으로 초기화
		}
		int sumweight = 0;
		int cur = 0;
		while (true) {

			time++;
			int temp = bridge.poll();
			sumweight -= temp;
			if (cur == truck_weights.length) {
				time += bridge_length - 1;
				break;
			}

			if (cur < truck_weights.length && (sumweight + truck_weights[cur]) <= weight) { // 트럭 무게 견딜 수 있음
				bridge.offer(truck_weights[cur]);
				sumweight += truck_weights[cur];
				cur++;
			} else
				bridge.offer(0);

		}

		return time;
	}
}
