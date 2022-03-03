package date0303;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2512 {

	static int n;
	static long m;
	static long sum;
	static long[] budget;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		sum =0;
		budget= new long[n];
		
		long answer= 0;
		for(int i=0;i<n;i++) {
			long b =  Long.parseLong(st.nextToken());
			budget[i] = b;
			sum+=b;
		}
		Arrays.sort(budget);
		
		m = Long.parseLong(br.readLine());
		
		if(sum<m) { //예산 범위 안이면
			System.out.println(budget[n-1]);
		}
		else {
			long left = 0;
			long right = budget[n-1];
			while(left<=right) {
				long mid = (left+right)/2;
				long su = 0;
				for(long l : budget) {
					if(l > mid)
						su += mid;
					else
						su +=l;
				}
				if(su > m)
					right= mid-1;
				else {
					left = mid+1;
					answer = Math.max(answer, mid);
				}
				
			}
			System.out.println(answer);
		}
		
	}
	public static long find() {
		long limit = budget[n/2];
		int idx =  n/2;
		while(true) {
			
			break;
		}
		
		return limit;
	}

}
