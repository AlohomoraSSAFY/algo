package date1114SUN;
import java.util.*;

public class 프로그래머스_전화번호목록 {
	   public boolean solution(String[] phone_book) {
	        boolean answer = true;
	        int len = phone_book.length;
	        Arrays.sort(phone_book);
	        for(int i=0;i<len-1;i++){
	           if(phone_book[i+1].startsWith(phone_book[i]))
	                return false;
	            }
	        
	            return true;
	    }
}
