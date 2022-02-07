package date0203THU;

import java.io.*;
import java.util.*;
import java.io.InputStreamReader;

class Lesson implements Comparable<Lesson>{
	int s;
	int t;
	public Lesson(int s, int t) {
		this.s = s;
		this.t = t;
	}
	
	public int compareTo(Lesson lesson) {
		if(lesson.t == this.t)
			return this.s - lesson.s;
		return this.t - lesson.t;
	}
}
public class BOJ11000 {
	static int n;
	static int[][] lessons;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		lessons = new int[n][2];
		PriorityQueue<Lesson> pq = new PriorityQueue<>();
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			lessons[i][0] =s;
			lessons[i][1] =t;
		}
		
		Arrays.sort(lessons, new Comparator<int[]>(){
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if( o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});
		
		pq.add(new Lesson(lessons[0][0], lessons[0][1]));

		int answer =1;
		for(int i=1;i<n;i++) {
			if(pq.peek().t <= lessons[i][0]) {
				pq.poll();
			}else
				answer++;
			pq.add(new Lesson(lessons[i][0], lessons[i][1]));
		}
		
		System.out.println(answer);
	
	}

}
