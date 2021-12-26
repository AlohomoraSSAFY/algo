import java.io.*;
import java.util.*;

public class Main {
	static int[] dice = new int[10];
	static ArrayList<Integer> horse = new ArrayList<>(4);
	static int maxTotal = 0;
	
	static int[] map = {0, 2, 4, 6, 8, 10,
						12, 14, 16, 18, 20,
						22, 24, 26, 28, 30,
						32, 34, 36, 38,
						13, 16, 19, 22, 24, 28, 27, 26, 25,
						30, 35, 40, 0};
	static int count = 0;
	private static void select(int idx, int sum) {
		if(idx == 10) {
			maxTotal = Math.max(maxTotal, sum);
			return;
		}
		
		for(int i = 0 ; i < 4; i++) {
			int cur = horse.get(i); // 선택할 말
			int origin = cur;
			if(cur == 32) continue;
			// 말의 index 번호 조정
			if(cur==5) {
				if(dice[idx] < 4) {
					cur = 19 + dice[idx];
				}else{
					cur = 24 + dice[idx] ;
				}
			
			}else if(cur==10) {
				if(dice[idx] < 3) {
					cur = 22 + dice[idx];
				}else {
					cur = 25 + dice[idx]; 
				}
			}else if(cur==15) {
				cur = 24 + dice[idx];
			}else if(cur >= 20 && cur<=22) {
				if(cur+dice[idx] > 22) {
					cur = cur + dice[idx] + 5;
				}else {
					cur = cur + dice[idx];
				}
			}else if(cur == 23 || cur==24){
				if(cur+dice[idx] > 24) {
					cur = cur + dice[idx] + 3;
				}else {
					cur = cur+dice[idx];
				}
				
			}else if(cur>=16 && cur <=20){
				if(cur+dice[idx] > 19) {
					cur = cur + dice[idx]+11;
				}else {
					cur = cur + dice[idx];
				}
			}else {
				cur = cur + dice[idx];
			}
			
			if(cur > 31) {
				horse.set(i, 32);
				select(idx+1, sum);
				horse.set(i, origin);
			}else {
				if(!horse.contains(cur)) {
					// 포함되지 않을 경우 선택
					horse.set(i, cur);
					select(idx+1, sum + map[cur]);
					horse.set(i, origin);
				}				
			}
			
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 주사위 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		// 말 위치 추가
		for(int i = 0 ; i < 4; i++) {
			horse.add(0);
		}
		
		select(0, 0);
		
		bw.write(maxTotal+"\n");
		bw.close();
		br.close();	
		
	}

}