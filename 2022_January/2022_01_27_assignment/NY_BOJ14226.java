package date0203THU;

import java.io.*;
import java.util.*;

public class BOJ14226 {

	static int s;
	static int[] arr;
	static boolean visited[][];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		s = Integer.parseInt(br.readLine());
		arr = new int[s + 1];
		visited = new boolean[s+1][s+1];
		arr[0] =1;
		for(int i=1; i<=s;i++) {
			arr[i] = i;
		}
		System.out.println(bfs());
	}

	public static int bfs() {

		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] { 1, 0, 0 });
		visited[1][0] = true;

		while (!q.isEmpty()) {
			int temp[] = q.poll();
			int num = temp[0];
			int time = temp[1];
			int clipboard = temp[2];
			if(num==s)
				return time;
			q.offer(new int[] {num, time+1, num}); //num을 복사
			visited[num][num] = true;
			
			if(clipboard != 0 && num+clipboard<=s && !visited[num+clipboard][clipboard]) { //붙여넣기
				q.offer(new int[] {num+clipboard, time+1, clipboard});
				visited[num+clipboard][clipboard] = true;
			}
			if(num>1 && !visited[num-1][clipboard]) { //하나 삭제
				visited[num-1][clipboard] = true;
				q.add(new int[] {num-1, time+1, clipboard});
			} 
			
		}
		return 0;
		
	}

}
