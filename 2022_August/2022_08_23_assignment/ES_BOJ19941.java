import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = br.readLine().toCharArray();
		
		int ans = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] == 'P') { // 사람
				for(int j = i - K; j <= i+K; j++) {
					if(j < 0 || j > N-1) continue;
					if(arr[j] == 'H') {
						ans++;
						arr[j] = 'X';
						break;
					}
				}
			}
			
		}
		
			
		bw.write(ans+"\n");
		bw.close();
		br.close();
	}

}