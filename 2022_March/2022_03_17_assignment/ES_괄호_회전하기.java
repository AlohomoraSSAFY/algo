package net.acmicpc.march.week4;


import java.util.*;

public class ES_괄호_회전하기 {
    public int solution(String s) {
        int answer = 0; int unvalid = 0;
        ArrayList<String> list = new ArrayList<>();
        
        for(int k = 0; k < s.length(); k++){
            list.add(String.valueOf(s.charAt(k)));
        }
        
        if(s.length()==0) return 1;
        
        for(int k = 0; k < s.length(); k++){
            Stack<String> stack = new Stack();
            boolean flag = false;
            for(int i = 0; i < s.length(); i++){
                String c = list.get(i);
                if(c.equals("(") || c.equals("{") || c.equals("[")){
                    stack.push(c);
                }else{
                    if(stack.isEmpty()){
                        flag = true;
                        break;
                    }
                    String top = stack.pop();
                    if(c.equals(")")){
                        if(top.equals("(")){

                        }else{
                            flag = true;
                            break;
                        }
                    }else if(c.equals("}")){
                        if(top.equals("{")){

                        }else{
                            flag = true;
                            break;
                        }
                    }else{
                        if(top.equals("[")){

                        }else{
                            flag = true;
                            break;
                        }
                    }
                }
            }
            
            if( flag || stack.size() > 0){
                unvalid++;
            }
            
            String first = list.get(0);
            list.remove(0);
            list.add(first);
        }
        
        return s.length() - unvalid;
    }
}