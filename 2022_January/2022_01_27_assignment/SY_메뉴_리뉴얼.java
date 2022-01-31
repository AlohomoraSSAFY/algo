package study0203;

import java.util.*;

public class SY_메뉴_리뉴얼 {
    
    TreeMap<String, Integer> treeMap;
    int[] max;
    String order;
    
    public String[] solution(String[] orders, int[] course) {
        treeMap = new TreeMap<>();
        max = new int[course[course.length - 1] + 1];
        
        for (int i = 0; i < orders.length; i++) {
            char[] cArray = orders[i].toCharArray();
            Arrays.sort(cArray);
            String temp = new String(cArray);
            order = temp;
            
            for (int j = 0; j < course.length; j++) {
                combination(0, 0, "", course[j]);
            }
        }
        
        List<String> list = new ArrayList<>();
        for (String key : treeMap.keySet()) {
            if (treeMap.get(key) >= 2 && max[key.length()] == treeMap.get(key)) {
                list.add(key);
            }
        }
        
        String[] answer = new String[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    private void combination(int cnt, int start, String str, int len) {
        if (cnt == len) {
            treeMap.put(str, treeMap.getOrDefault(str, 0) + 1);
            if (max[len] < treeMap.get(str)) {
                max[len] = treeMap.get(str);
            }
            return;
        }
        
        for (int i = start; i < order.length(); i++) {
            combination(cnt + 1, i + 1, str + order.charAt(i), len);
        }
    }
}
