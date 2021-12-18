package study1219;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SY_BOJ10800 {
	
	static class Ball {
		int num;
		int color;
		int size;
		int sum;
		
		public Ball(int num, int color, int size, int sum) {
			this.num = num;
			this.color = color;
			this.size = size;
			this.sum = sum;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Ball> pq = new PriorityQueue<>(new Comparator<Ball>() {
			@Override
			public int compare(Ball b1, Ball b2) {
				if (b1.size == b2.size) {
					return b1.color - b2.color;
				}
				return b1.size - b2.size;
			}
		});
		
		int maxByColor = 1;
		int maxBySize = 1;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			maxByColor = Math.max(maxByColor, C);
			maxBySize = Math.max(maxBySize, S);
			pq.offer(new Ball(i, C, S, 0));
		}
		
		PriorityQueue<Ball> result = new PriorityQueue<>(new Comparator<Ball>() {
			@Override
			public int compare(Ball b1, Ball b2) {
				return b1.num - b2.num;
			}
		});
		
		int tempByColor = 0;
		int[] cntByColor = new int[maxByColor + 1];
		int[] cntBySize = new int[maxBySize + 1];
		int curSize = 0;
		int curColor = 0;
		while (!pq.isEmpty()) {
			Ball b = pq.poll();
			
			if (curSize < b.size) {
				cntByColor[curColor] += tempByColor;
				curColor = b.color;
				tempByColor = 0;
				
				if (curSize > 0) cntBySize[b.size-1] = cntBySize[curSize] + cntBySize[curSize-1];
				curSize = b.size;
			}
			
			if (curColor < b.color) {
				cntByColor[curColor] += tempByColor;
				curColor = b.color;
				tempByColor = 0;
			}
			
			result.offer(new Ball(b.num, b.color, b.size, cntBySize[b.size-1] - cntByColor[b.color]));
			tempByColor += b.size;
			cntBySize[b.size] += b.size;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(result.poll().sum + "\n");
		}
		System.out.println(sb);
	}

}
