package date0317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Student implements Comparable<Student> {
	int order;
	int num;
	int score;

	public Student(int order, int num, int score) {
		this.order = order;
		this.num = num;
		this.score = score;
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		if (this.score == o.score)
			return this.order - o.order;
		return this.score - o.score;
	}

}

public class BOJ1713 {

	static int n, m;
	static int arr[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		arr = new int[191];
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Student> pq = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			int a = Integer.parseInt(st.nextToken());

			if (arr[a] == 0) { // 추천받은 적이 없는 경우
				if (pq.size() == n) { // 빈 사진첩이 없는 경우
					Student rm = pq.poll();
					arr[rm.num] = 0;
				}
				arr[a] = 1;
				pq.add(new Student(i, a, 1));
			} else { // 추천받은 적이 있는 경우
				//
				arr[a]++;
				Queue<Student> temp = new LinkedList<Student>();
				while (!pq.isEmpty()) {
					Student s = pq.poll();
					if (s.num != a)
						temp.add(s);
					else {
						pq.add(new Student(s.order, a, arr[a]));
						break;
					}
				}
				while (!temp.isEmpty()) {
					pq.add(temp.poll());
				}
			}

		}

		ArrayList<Integer> list = new ArrayList<>();
		while (!pq.isEmpty()) {
			list.add(pq.poll().num);
		}
		Collections.sort(list);

		for (Integer i : list) {
			System.out.print(i + " ");
		}

	}

}
