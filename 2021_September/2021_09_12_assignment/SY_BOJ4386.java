package study0916;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ4386 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		double[][] pos = new double[n][2];
		double[] minEdge = new double[n];
		boolean[] visited = new boolean[n];
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pos[i][0] = Double.parseDouble(st.nextToken());
			pos[i][1] = Double.parseDouble(st.nextToken());
			minEdge[i] = Integer.MAX_VALUE;
		}
		
		double result = 0;
		minEdge[0] = 0;
		
		for (int i = 0; i < n; i++) {
			double min = Double.MAX_VALUE;
			int minVertex = -1;
			for (int j = 0; j < n; j++) {
				if (!visited[j] && minEdge[j] < min) {
					min = minEdge[j];
					minVertex = j;
				}
			}
			
			visited[minVertex] = true;
			result += min;
			
			for (int j = 0; j < n; j++) {
				if (!visited[j] && minEdge[j] > Math.sqrt(Math.pow(pos[j][0] - pos[minVertex][0], 2) + Math.pow(pos[j][1] - pos[minVertex][1], 2))) {
					minEdge[j] = Math.sqrt(Math.pow(pos[j][0] - pos[minVertex][0], 2) + Math.pow(pos[j][1] - pos[minVertex][1], 2));
				}
			}
		}
		
		System.out.printf("%.2f", result);
	}

}
