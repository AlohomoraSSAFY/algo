package study0317;

import java.util.*;

public class SY_순위_검색 {
    
    Map<String, List<Integer>> map;
    
    public int[] solution(String[] info, String[] query) {
        map = new HashMap<>();
        
        for (int i = 0; i < info.length; i++) {
            String[] sArray = info[i].split(" ");
            subset(sArray, "", 0);
        }
        
        for (String str : map.keySet()) {
            Collections.sort(map.get(str));
        }
        
        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String temp = query[i].replaceAll(" and ", " ");
            String[] sArray = temp.split(" ");
            String str = "";
            for (int j = 0; j < 4; j++) {
                str += sArray[j];
            }
            answer[i] = binarySearch(str, Integer.parseInt(sArray[4]));
        }
        return answer;
    }
    
    private void subset(String[] array, String str, int cnt) {
        if (cnt == 4) {
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>());
            }
            map.get(str).add(Integer.parseInt(array[4]));
            return;
        }
        
        subset(array, str + array[cnt], cnt + 1);
        subset(array, str + "-", cnt + 1);
    }
                             
    private int binarySearch(String str, int score) {
        if (!map.containsKey(str)) return 0;
        
        List<Integer> list = map.get(str);
        int left = 0;
        int right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < score) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return list.size() - left;
    }
}
