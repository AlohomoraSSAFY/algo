package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main15662 {
	
	static int T, K;
	static String[] gears; // 2, 6

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		gears = new String[T+1];
		for(int i = 1; i < T+1; i++) {
//			gears[i] = Integer.parseInt(br.readLine(), 2);
			gears[i] = br.readLine();
		}
		
//		for(int i = 0; i < T +1; i++) {
//			System.out.println(gears[i]);
//		}
		
		K = Integer.parseInt(br.readLine());
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			// 1 시계 -1 반시계
			int d = dir;
			int[] rotate = new int[T+1];
			rotate[no] = d;
			for(int r = no; r < T; r++) {
				if(gears[r].charAt(2) != gears[r+1].charAt(6)) {
					d *= (-1);
					rotate[r+1] = d;
				} else {
					break;
				}
			}
			
			d = dir;
			
			for(int l = no; l > 1; l--) {
				if(gears[l].charAt(6)!= gears[l-1].charAt(2)) {
					d *= (-1);
					rotate[l-1] = d;
				} else {
					break;
				}
			}
			
			for(int j = 1; j < T+1; j++) {
				
//					System.out.println(gears[j]+" "+rotate[j]);
				if(rotate[j] == 1) {
					String tmp = gears[j].substring(7, 8) + gears[j].substring(0, 7);
					gears[j] = tmp;
				} else if(rotate[j] == -1) {
					String tmp = gears[j].substring(1 , 8) + gears[j].substring(0, 1);
					gears[j] = tmp;
				}
//				System.out.println("이후에 "+gears[j]);
			}
			
			
		}
		
		int ans = 0;
		for(int i = 1; i < T+1; i++) {
			if(gears[i].charAt(0) == '1') {
				ans++;
			}
		}
		
		bw.write(ans+"\n");
		bw.close();
		br.close();
	}

}
