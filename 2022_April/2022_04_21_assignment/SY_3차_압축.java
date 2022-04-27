package study0428;

import java.util.*;

public class SY_3차_압축 {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        int idx = 1;
        for (char c = 'A'; c <= 'Z'; c++) {
            map.put("" + c, idx++);
        } 
        
        here: for (int i = 0; i < msg.length(); i++) {
            String temp = "" + msg.charAt(i);
            
            while (map.containsKey(temp)) {
                if (i == msg.length() - 1) {
                    list.add(map.get(temp));
                    break here;
                }
                
                i++;
                temp += msg.charAt(i);
            }
            
            map.put(temp, idx++);
            list.add(map.get(temp.substring(0, temp.length() - 1)));
            i--;
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
