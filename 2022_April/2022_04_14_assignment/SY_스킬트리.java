package study0421;

public class SY_스킬트리 {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        for (int i = 0; i < skill_trees.length; i++) {
            String str = skill_trees[i];
            for (int j = 0; j < skill_trees[i].length(); j++) {
                char c = skill_trees[i].charAt(j);
                if (!skill.contains(Character.toString(c))) {
                    str = str.replace(Character.toString(c), "");
                }
            }
            
            if (skill.indexOf(str) == 0) answer++;
        }
        
        return answer;
    }
}
