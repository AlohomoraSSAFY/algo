package date0324;

import java.util.*;
import java.io.*;

class Lecture implements Comparable<Lecture> {
	int p;
	int d;

	public Lecture(int p, int d) {
		this.p = p;
		this.d = d;
	}

	@Override
	public int compareTo(Lecture o) {
		if (this.d == o.d)
			return o.p - this.p;
		else
			return o.d - this.d;
	}
}

public class BOJ2109 {
	static int n;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		PriorityQueue<Lecture> temp;
		PriorityQueue<Lecture> pq = new PriorityQueue<>(new Comparator<Lecture>() {

			@Override
			public int compare(Lecture o1, Lecture o2) {
				if (o1.d == o2.d)
					return o2.p - o1.p;
				return o2.d - o1.d;
			}
		});
		int max = 0;
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			max = Math.max(max, d);
			pq.add(new Lecture(p, d));
		}

		int answer = 0;
		int day = max;
		temp = new PriorityQueue<>(new Comparator<Lecture>() {
			@Override
			public int compare(Lecture o1, Lecture o2) {
				return o2.p - o1.p;
			}
		});
		while (day > 0) {


			while (!pq.isEmpty()) {

				if (pq.peek().d >= day) {
					temp.add(pq.poll());
				} else
					break;
			}
			if (!temp.isEmpty())
				answer += temp.poll().p;

			day--;
		}
		System.out.println(answer);
	}

}
