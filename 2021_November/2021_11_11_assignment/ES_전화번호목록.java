import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);
        boolean answer = true;
        LOOP:
        for(int i = 0; i < phone_book.length-1; i++){
            String str = phone_book[i];
            if(phone_book[i+1].startsWith(str)){
                answer = false;
                break LOOP;
            }
            // for(int j = i+1; j < phone_book.length; j++){
            //     if(phone_book[j].startsWith(str)){
            //         answer = false;
            //         break LOOP;
            //     }
            // }
        }

        return answer;
    }
}