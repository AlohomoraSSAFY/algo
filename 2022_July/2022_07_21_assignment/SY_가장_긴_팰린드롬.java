package study0726;

public class SY_가장_긴_팰린드롬 {
    public int solution(String s) {
        int answer = 1;
        char[] cArray = s.toCharArray();
        here: for (int i = cArray.length; i > 1; i--) {
            for (int j = 0; j + i <= cArray.length; j++) {
                boolean flag = true;
                for (int k = 0; k < i / 2; k++) {
                    if (cArray[j + k] != cArray[j + i - 1 - k]) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    answer = i;
                    break here;
                }
            }
        }

        return answer;
    }
}
