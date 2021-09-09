package date0909;

import java.io.*;
import java.util.*;

public class BOJ3079 {
static int n,m;
static int time[];
static int result;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		time = new int[n];
		for(int i=0;i<n;i++) {
			time[i] =Integer.parseInt(br.readLine());
		}
		result =0;
		
		//
		Arrays.sort(time);
		while(m<=0) {
			
		}
		
		
	}

}
