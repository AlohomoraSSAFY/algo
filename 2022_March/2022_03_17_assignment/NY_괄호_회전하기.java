package date0317;

import java.util.*;
public class NY_괄호_회전하기 {
	    public int solution(String s) {
	        int answer = 0;
	        int idx =0;
	        while(idx< s.length()){ //왼쪽으로 한 칸씩 회전시키면서 확인 총 길이번
	            boolean isright = true;
	            Queue<Character> list= new  LinkedList<>();
	            int index =idx;
	            for(int i=0;i<s.length();i++){
	                
	                list.add(s.charAt(index));
	                index = (index+1)%s.length();
	            }
	            
	            Stack<Character> st = new Stack<>();
	            while(!list.isEmpty()){              
	                Character c = list.poll();
	                if( c == '[' || c== '{' || c=='('){
	                    st.push(c);
	                }else {//닫는 괄호일 경우
	                    if(st.size()==0){
	                        isright = false;
	                        break;
	                    }
	                    if(st.size()>0){
	                        Character temp = st.peek();
	                        if(c == '}' && temp == '{'){
	                            st.pop();
	                        }else if(c== ')' && temp == '('){
	                              st.pop();
	                        }else if ( c == ']' && temp=='['){
	                            st.pop();
	                        }
	                        else{
	                            isright =false;
	                            break;
	                        }
	                    }
	            }
	            
	            }
	            if(st.size() > 0)
	                isright = false;
	                
	            if(isright){
	                answer++;
	            }
	                
	            idx++;
	        }
	        return answer;
	    }
}
