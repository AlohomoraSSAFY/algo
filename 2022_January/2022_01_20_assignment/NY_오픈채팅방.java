package date0127THU;
import java.util.*;

public class 프로그래머스_오픈채팅방 {

	class Solution {
	    public String[] solution(String[] record) {
	        ArrayList<String> list = new ArrayList<String>();
	        HashMap<String, String> hm = new HashMap<String, String>();
	        Queue<String> q = new LinkedList<>();
	        for(int i=0;i<record.length;i++){
	            StringTokenizer st = new StringTokenizer(record[i]);
	            String word = st.nextToken();
	            String user = st.nextToken();
	            if(word.equals("Enter") || word.equals("Change")){
	                String name = st.nextToken();
	                hm.put(user,name);
	            }
	            if(word.equals("Enter")){
	                q.add("E"+user);
	            }
	            if(word.equals("Leave")){
	                q.add("L"+user);
	            }
	        }
	        
	        while(!q.isEmpty()){
	            String str = q.poll();
	            String name = hm.get(str.substring(1,str.length()));
	             
	            if(str.charAt(0) =='E')
	                list.add(name+"님이 들어왔습니다.");
	            else 
	                list.add(name+"님이 나갔습니다.");    
	        }
	        String[] answer = new String[list.size()];
	        list.toArray(answer);
	        
	        
	        
	        return answer;
	    }
	}
}
