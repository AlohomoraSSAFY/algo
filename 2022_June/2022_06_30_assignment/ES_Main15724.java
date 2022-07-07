

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15724 {

	static int N, M, K;
	static long[][] a;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		a = new long[N+1][M+1];
		for(int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j < M+1; j++) {
				long val = Long.parseLong(st.nextToken());
				a[i][j] = a[i-1][j] + a[i][j-1] + val - a[i-1][j-1];
			}
		}
		
		K = Integer.parseInt(br.readLine());
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			long sum = a[x2][y2] -a[x2][y1-1] - a[x1-1][y2] + a[x1-1][y1-1];
			bw.write(sum+"\n");
		}
		
		
		bw.close();
		br.close();
	}

}
