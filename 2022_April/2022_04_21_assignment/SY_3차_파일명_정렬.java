package study0428;

import java.util.*;

public class SY_3차_파일명_정렬 {
    public String[] solution(String[] files) {
        String[][] array = new String[files.length][3];
        
        for (int i = 0; i < files.length; i++) {
            String str = files[i];
            String HEAD = "";
            String NUMBER = "";
            String TAIL = "";
            
            int idx = 0;
            while (idx < str.length()) {
                char c = str.charAt(idx);
                if (c >= '0' && c <= '9') break;
                HEAD += c;
                idx++;
            }
            
            while (idx < str.length()) {
                char c = str.charAt(idx);
                if (!(c >= '0' && c <= '9')) break;
                NUMBER += c;
                idx++;
            }
            
            while (idx < str.length()) {
                TAIL += str.charAt(idx);
                idx++;
            }
            
            array[i][0] = HEAD;
            array[i][1] = NUMBER;
            array[i][2] = TAIL;
        }
        
        Arrays.sort(array, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[0].toLowerCase().compareTo(o2[0].toLowerCase()) == 0) {
                    return Integer.compare(Integer.parseInt(o1[1]), Integer.parseInt(o2[1]));
                }                
                
                return o1[0].toLowerCase().compareTo(o2[0].toLowerCase());
            }
        });
        
        String[] answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = "";
            for (int j = 0; j < 3; j++) {
                answer[i] += array[i][j];
            }
        }
        return answer;
    }
}
