import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new LinkedList<>();
		int sum = 0;
		int time = 0; 

		for(int i = 0; i < truck_weights.length; i++) {
			int tmp = truck_weights[i];
			while(true) {
				if(queue.isEmpty()) {  // 비어 있는 경우
					queue.add(tmp);
					sum += tmp;
					time++; 
					break;
				} else if(queue.size() == bridge_length) { // 꽉 찬 경우
					sum -= queue.poll();
				} else  { 
					if(sum + tmp <= weight) {
						queue.add(tmp);
						sum += tmp;
						time++;
						break;
					} else { 
						queue.add(0);
						time++;
					}
				}
			}
		}
        
		return time + bridge_length; 
    }
}