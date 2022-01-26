package study0127;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SY_BOJ22867 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		double[][] array = new double[N][2];
		for (int i = 0; i < N; i++) {
			String[] sArray = br.readLine().split(" ");
			for (int j = 0; j < 2; j++) {
				double sum = 0;
				String[] temp = sArray[j].split(":");
				for (int k = 0; k < 3; k++) {
					switch(k) {
						case 0:
							sum += (Double.parseDouble(temp[k]) * 3600);
							break;
						case 1:
							sum += (Double.parseDouble(temp[k]) * 60);
							break;
						case 2:
							sum += Double.parseDouble(temp[k]);
							break;
					}	
				}
				array[i][j] = sum;
			}
		}
		
		Arrays.sort(array, new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				return Double.compare(o1[0], o2[0]);
			}
		});
		
		PriorityQueue<double[]> pq = new PriorityQueue<>(new Comparator<double[]>() {
			@Override
			public int compare(double[] o1, double[] o2) {
				return Double.compare(o1[1], o2[1]);
			}
		});
		pq.offer(new double[] {array[0][0], array[0][1]});
		
		int result = 1;
		for (int i = 1; i < N; i++) {
			double[] temp = pq.poll();
			if (temp[1] > array[i][0]) {
				result++;
				pq.offer(temp);
			}
			pq.offer(new double[] {array[i][0], array[i][1]});
		}
		System.out.println(result);
	}

}
