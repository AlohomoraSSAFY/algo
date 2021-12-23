package date1223THU;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1068 {
static int n,m;
static int[] parent;
static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		parent = new int[n];
		answer =0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			parent[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		
		boolean isparent[] = new boolean[n];
		
		delete(m);
		
		for(int i=0;i<n;i++) {
			if(parent[i] >= 0 )
				isparent[parent[i]] = true;
			else if( parent[i] == -2)
				isparent[i] = true;
		}
		for(int i=0;i<n;i++) {
			if(!isparent[i])
				answer++;
		}
		System.out.println(answer);
		
	}
	public static void delete(int m) {
		parent[m] = -2;
		for(int i=0;i<n;i++) {
			if(parent[i] == m) {
				parent[i]= -2;
				delete(i);
			}
		}
	}

}
