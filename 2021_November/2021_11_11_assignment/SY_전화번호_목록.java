package study1114;

import java.util.*;

public class SY_전화번호_목록 {
	public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for (int i = 1; i < phone_book.length; i++) {
            if (phone_book[i].startsWith(phone_book[i-1])) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
