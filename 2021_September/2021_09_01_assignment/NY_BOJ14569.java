import java.io.*;
import java.util.*;

public class BOJ14569 { 
	static int n, k, m, p;

	static boolean classes[][];
	static boolean students[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		classes = new boolean[n][51];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			k = Integer.parseInt(st.nextToken());

			for (int j = 0; j < k; j++) {
				int l = Integer.parseInt(st.nextToken());
				classes[i][l] =  true;
			}
		}
		
		m = Integer.parseInt(br.readLine());
		students = new boolean[m][51];
	
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());

			for (int j = 0; j < p; j++) {
				int l = Integer.parseInt(st.nextToken());
				students[i][l] = true; 
			}
		}
		
		//
		
		for (int i=0;i<m;i++) { 
		int count =0;
			for( int j=0;j<n;j++) { 
				boolean possible = true;
				
				for( int p=0;p<51;p++) {
					if(!students[i][p] && classes[j][p])
						possible = false;
				}
				
				if(possible) {
					count++;
					}
			}
		
		sb.append(count+"\n");
		}
		System.out.println(sb.toString());
	}

}
