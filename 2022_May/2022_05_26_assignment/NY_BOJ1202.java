package date0526;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Gem {

	int m;
	int v;

	public Gem(int m, int v) {
		super();
		this.m = m;
		this.v = v;
	}

}

public class NY_BOJ1202 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Gem> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.add(new Gem(m, v));
		}

		Collections.sort(list, new Comparator<Gem>() {

			@Override
			public int compare(Gem o1, Gem o2) {
				// TODO Auto-generated method stub
				if (o1.m == o2.m) {
					return o2.v - o1.v;
				}
				return o1.m - o2.m;
			}
		});

		ArrayList<Integer> bags = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			bags.add(w);
		}

		Collections.sort(bags);
		
		long answer = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		int idx =0;
		for (int i = 0; i < k; i++) {
			// 현재 가방의 무게보다 작거나 같은 보석을 모두 우선순위 큐에 넣음.
			while (idx < n && list.get(idx).m <= bags.get(i)) {
				pq.offer(list.get(idx++).v);
			}

			// 우선순위 큐에 있는 요소를 하나 빼서 가방에 넣음.
			// 이 때, 우선순위 큐는 내림차순 정렬이 되어있으므로
			// 첫 번째 요소는 가장 큰 가격을 의미함.
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		System.out.println(answer);

	}

}
