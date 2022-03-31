package date0324;
import java.util.*;
public class NY_영어_끝말잇기 {
	   public int[] solution(int n, String[] words) {
	        int[] answer = new int[2];

	        int round = words.length/n+1;
	        int last = words[0].charAt(words[0].length()-1);
	        Set<String> set = new HashSet<String>();
	        set.add(words[0]);
	        for(int i=1;i<words.length;i++){
	            
	            if(last != words[i].charAt(0)){
	                answer[0] = (i%n)+1;
	                answer[1] = i/n+1;
	                break;
	            }
	            else if(set.contains(words[i])){
	                answer[0] = (i%n)+1;
	                answer[1] = i/n+1;
	                break;
	            }else{
	                set.add(words[i]);            
	                last = words[i].charAt(words[i].length()-1);
	            }
	                }
	        return answer;
	    }
}
