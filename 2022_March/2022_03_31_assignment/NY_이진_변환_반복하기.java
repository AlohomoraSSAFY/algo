package date0331;

public class NY_이진_변환_반복하기 {
	 public int[] solution(String s) {
	        
	       	int count = 0;
			int num = 0;
			String str = s;
			while (true) {
				if (str.equals("1"))
					break;
				String temp = "";

				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == '1')
						temp += "1";
					else
						num++;
				}
				str = Integer.toBinaryString(temp.length());
				count++;

			}
			int[] answer = new int[2];
			answer[0] = count;
			answer[1] = num;
	        return answer;
	    }
}
