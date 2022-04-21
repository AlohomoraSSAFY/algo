import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int skills[] = new int[300];
        int idx =1;
        for(int i=0;i<skill.length();i++){
            char c = skill.charAt(i);
            skills[c] = idx++;
        }
        int n = skill_trees.length;
        for(int i=0;i<n;i++){
            String cur = skill_trees[i]; 
            if(check(skills, cur)){
                              
                answer++;
            }
        }
        return answer;
    }
    public static boolean check(int[] skill, String cur){
        int idx =1;
        for(int i=0;i<cur.length();i++){
            char c = cur.charAt(i); 
            if(skill[c] == 0)
                continue;
            if( skill[c] != idx)
                return false;
            else
                idx++;
        }
        
        
        return true;
    }        
}