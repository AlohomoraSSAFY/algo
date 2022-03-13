package study0317;

import java.util.*;

public class SY_튜플 {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] sArray = s.split("\\},\\{");
        Arrays.sort(sArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });
        
        int[] answer = new int[sArray.length];
        int idx = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < sArray.length; i++) {
            String str = sArray[i];
            String[] array = str.split(",");
            for (int j = 0; j < array.length; j++) {
                int num = Integer.parseInt(array[j]);
                if (!set.contains(num)) {
                    set.add(num);
                    answer[idx++] = num;
                }
            }
        }
        return answer;
    }
}
