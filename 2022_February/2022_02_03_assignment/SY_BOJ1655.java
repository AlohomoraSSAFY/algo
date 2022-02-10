package study0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SY_BOJ1655 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pqMin = new PriorityQueue<>();
		PriorityQueue<Integer> pqMax = new PriorityQueue<>((o1, o2) -> o2 - o1);
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (pqMin.size() == pqMax.size()) {
				pqMax.offer(num);
			} else {
				pqMin.offer(num);
			}
			
			if (!pqMax.isEmpty() && !pqMin.isEmpty() && pqMax.peek() > pqMin.peek()) {
				int temp = pqMax.poll();
				pqMax.offer(pqMin.poll());
				pqMin.offer(temp);
			}
			
			sb.append(pqMax.peek() + "\n");
		}
		
		System.out.println(sb);
	}

}
