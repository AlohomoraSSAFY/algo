package study0324;

import java.util.*;

public class SY_후보키 {
    
    String[][] relation;
    HashSet<Integer> answer;
    int target;
    int[] selected;
    
    public int solution(String[][] relation) {
        this.relation = relation;
        answer = new HashSet<>();
        
        for (int i = 0; i < relation[0].length; i++) {
            target = i + 1;
            selected = new int[i+1];
            combination(0, 0);
        }
        
        return answer.size();
    }
    
    private void combination(int cnt, int start) {
        if (cnt == target) {
            if (isUnique()) {
                isMin();
            }
            
            return;
        }
        
        for (int i = start; i < relation[0].length; i++) {
            selected[cnt] = i;
            combination(cnt + 1, i + 1);
        }
    }
    
    private boolean isUnique() {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            String str = "";
            for (int s : selected) {
                str += relation[i][s];
            }
            
            if (set.contains(str)) {
                return false;
            }
            
            set.add(str);
        }
        
        return true;
    }
    
    private boolean isMin() {
        int bm = 0;
        for (int s : selected) {
            bm = bm | 1 << s;
        }
        
        for (Integer a : answer) {
            if ((bm & a) == a) {
                return false;
            }
        }
        
        answer.add(bm);
        return true;
    }
}
