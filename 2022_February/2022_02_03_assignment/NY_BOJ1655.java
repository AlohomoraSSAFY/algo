package date0203;

import java.io.*;
import java.util.*;

public class BOJ1655 {
	static int n;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> min = new PriorityQueue<>();
		PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			int cur = Integer.parseInt(br.readLine());
			if(max.size() <= min.size())
				max.offer(cur);
			else 
				min.offer(cur);
			
			if(!max.isEmpty() && !min.isEmpty() && max.peek()> min.peek()) {
				
				int temp = min.poll();
				min.offer(max.poll());
				max.offer(temp);
			}
	
			sb.append(max.peek()+"\n");
		}
		System.out.println(sb.toString());
	}

}
