package study0303;

import java.util.*;

public class SY_단체사진_찍기 {
    
    String[] dataArray;
    char[] array = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    Map<Character, Integer> map;
    boolean[] visited;
    int answer;
    
    public int solution(int n, String[] data) {
        dataArray = data;
        map = new HashMap<>();
        visited = new boolean[8];
        
        permutation(0);
        return answer;
    }
    
    private void permutation(int cnt) {
        if (cnt == 8) {
            boolean check = true;
            for (int i = 0; i < dataArray.length; i++) {
                String str = dataArray[i];
                char a = str.charAt(0);
                char b = str.charAt(2);
                char op = str.charAt(3);
                char c = str.charAt(4);
                int aIndex = map.get(a);
                int bIndex = map.get(b);
                
                if (op == '=') {
                    if ((Math.abs(aIndex - bIndex) - 1) != (c - '0')) check = false;
                } else if (op == '>') {
                    if ((Math.abs(aIndex - bIndex) - 1) <= (c - '0')) check = false;
                } else {
                    if ((Math.abs(aIndex - bIndex) - 1) >= (c - '0')) check = false;
                }
            }
            
            if (check) answer++;
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            map.put(array[i], cnt);
            permutation(cnt + 1);
            visited[i] = false;
        }
    }
}
