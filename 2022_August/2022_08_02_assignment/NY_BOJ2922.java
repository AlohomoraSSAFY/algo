package date0802;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2922 {
	static String str;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		
		System.out.println(dfs(0,0,0,0));
	}
	public static long dfs(int mo, int ja, int l, int idx) {
		//자음, 모음이 연속해서 3번 이상
		if(mo >=3 || ja >=3)
			return 0;
		
		//글자 완성
		if(idx == str.length()) {
			if(l==0)
				return 0;
			else 
				return 1;
		}
		
		long answer =0;
		if(str.charAt(idx)== '_') { //_일때
			// _에 모음을 넣는 경우
			answer += 5* (dfs(mo+1, 0,l,idx+1));
			//_에 자음을 넣는 경우
			answer += 20* (dfs(0, ja+1,l,idx+1));
			//_에 L을 넣는 경우
			answer+=dfs(0,ja+1,l+1,idx+1);
		}else { //현재 글자가 _이 아닐 때
			char ch= str.charAt(idx);
			if(ch == 'A' || ch== 'E'|| ch== 'I'|| ch== 'O'|| ch== 'U')
				answer = dfs(mo+1,0,l,idx+1);
			else {
				if(ch == 'L')
					answer = dfs(0,ja+1,l+1,idx+1);
				else
					answer = dfs(0,ja+1,l,idx+1);
			}
		}
		
		
		return answer;
	}

}
