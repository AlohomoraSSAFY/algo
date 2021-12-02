package date1114SUN;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17281 {
	static int n;
	static int maxscore;
	static int max;
	static int inning[][];
	static int order[];
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		maxscore = 0;
		inning = new int[n][10];
		order = new int[10];
		visited = new boolean[10];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=1;j<=9;j++){
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//
		
		order[4] = 1;
		select(1);
		System.out.println(maxscore);
	}
	
	public static void select(int count) {
		if(count ==10) {
			maxscore = Math.max(go(order),maxscore);
			return;
		}
		
		for(int i=2;i<10;i++) {
			if(!visited[i]) {
			visited[i] = true;
			order[count] = i;
			if(count ==3)
				select(count+2);
			else
				select(count+1);
			visited[i] =false;
			}
		}
		
	}
	
	public static int go(int[] order) {
		int score= 0;
		int curnum =1; //타자 번호
		for(int i=0;i<n;i++) { //회별로 점수 계산
			boolean base[] = new boolean[4]; //1루 2루 3루 //잔루 제거 
			//int base = 1;
			int outcount=0;
			while(outcount<3) { //한 선수
				int cur = inning[i][order[curnum]];
				if (cur == 0)
					outcount++;
				else {
					for(int k=0;k<cur;k++) { //1루씩 보내고 점수 증가
						if(base[3]) {
							score++;
							base[3] =false;
						}
						if(base[2]) {
							base[3] =true;
							base[2] = false;
						}
						if(base[1]) {
							base[2] = true;
							base[1] =false;
						}
						if(k==0)
							base[1] =true;
					}
				}
				
				curnum++;//다음 선수
				if(curnum ==10)
					curnum=1;
			}
		}
		return score;
	}
	
}
