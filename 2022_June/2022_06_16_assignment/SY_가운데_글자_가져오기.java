package study0623;

public class SY_가운데_글자_가져오기 {
    public String solution(String s) {
        String answer = "";
        int len = s.length();
        if (len % 2 == 0) {
            answer = s.substring(len / 2 - 1, len / 2 + 1);
        } else {
            answer = s.substring(len / 2, len / 2 + 1);
        }

        return answer;
    }
}
