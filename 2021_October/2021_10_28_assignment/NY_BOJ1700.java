package date1031SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1700 {// 멀티탭 스케줄링
	static int n, k, arr[];
	static boolean plug[];
	static int min;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		plug = new boolean[k + 1];
		min = Integer.MAX_VALUE;
		arr = new int[k];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//

		{
			int cnt = 0;
			int plugnum = 0;
			for (int i = 0; i < k; i++) {
				int cur = arr[i];
				if (plug[cur]) // 현재 꽂혀있으면 그냥 넘김
					continue;

				if (plugnum < n) { // 멀티탭에 사용 가능한 구멍이 있으면
					plug[cur] = true;
					plugnum++;
				} else { // 멀티탭에 사용 가능한 구멍이 없는 경우

					boolean popcheck = false;
					// 현재 꽂혀있는 것 중에 앞으로 안 나오는거 뽑음
					for (int j = 1; j <= k; j++) { // 플러그에 꽂혀있는 j번 탐색
						boolean check = true;
						if (plug[j]) { // j가 현재 꽂혀있으면
							// 앞으로 나오지 않는지 체크
							for (int l = i + 1; l < k; l++) { //
								if (arr[l] == j) { // 앞으로 나오면
									check = false;
									break;
								}
							}
							if (check) { // 앞으로 나오지 않으면 j번 뽑고 cur번 꽂음
								plug[j] = false;
								plug[cur] = true;
								popcheck = true;
								cnt++;
								break;
							}
						}
					}
					if (!popcheck) { // 현재 꽂혀있는게 뒤에서 다 사용되는 경우 마지막에 사용되는거 뽑음
						boolean[] visited = new boolean[k + 1];
						int pop = -1;
						for (int j = i + 1; j < k; j++) { // 앞에서부터 탐색
							if (!visited[arr[j]] && plug[arr[j]]) { // arr[j] 가 꽂혀있으면 그거 뺌
								visited[arr[j]] = true;
								pop = arr[j];
							}
						}
						plug[pop] = false;
						plug[cur] = true;
						cnt++;

					}
				}
			}

			min = Math.min(cnt, min);
		}
		System.out.println(min);

	}

}
