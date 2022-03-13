package study0317;

import java.util.*;

public class SY_1차_뉴스_클러스터링 {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        
        for (int i = 0; i < str1.length() - 1; i++) {
            char c1 = str1.charAt(i);
            char c2 = str1.charAt(i+1);
            
            if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                list1.add(Character.toString(c1) + Character.toString(c2));
            }
        }
        
        for (int i = 0; i < str2.length() - 1; i++) {
            char c1 = str2.charAt(i);
            char c2 = str2.charAt(i+1);
            
            if (c1 >= 'a' && c1 <= 'z' && c2 >= 'a' && c2 <= 'z') {
                list2.add(Character.toString(c1) + Character.toString(c2));
            }
        }
        
        List<String> union = new ArrayList<>();
        List<String> intersection = new ArrayList<>();
        
        for (int i = 0; i < list1.size(); i++) {
            String temp = list1.get(i);
            union.add(temp);
            for (int j = 0; j < list2.size(); j++) {
                if (temp.equals(list2.get(j))) {
                    intersection.add(temp);
                    list2.remove(temp);
                    break;
                }
            }
        }
        
        for (int i = 0; i < list2.size(); i++) {
            union.add(list2.get(i));
        }
        
        int answer = 0;
        if (union.size() == 0) {
            answer = 65536;
        } else {
            answer = (int)((double)intersection.size() / union.size() * 65536);
        }
        return answer;
    }
}
