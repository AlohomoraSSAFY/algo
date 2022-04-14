package study0414;

public class SY_이진_변환_반복하기 {
    public int[] solution(String s) {
        int[] answer = new int[2];
        while (!s.equals("1")) {
            answer[0]++;
            int cnt = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '1') {
                    cnt++;
                } else {
                    answer[1]++;
                }
            }
            
            String str = "";
            while (cnt > 0) {
                str = String.valueOf(cnt % 2) + str;
                cnt /= 2;
            }
            s = str;
        }
        
        return answer;
    }
}
