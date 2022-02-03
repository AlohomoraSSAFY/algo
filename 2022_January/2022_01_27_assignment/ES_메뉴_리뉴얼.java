package net.acmicpc.feb.week1;

import java.util.*;

public class ES_메뉴_리뉴얼 {
    
    static HashMap<String, Integer> comb = new HashMap<>();
    static char[] selected;
    static ArrayList<Integer> co = new ArrayList<>();
    static int max;
    
    private void dfs(String str, int start, int cnt) {
        if(co.contains(cnt)) {
            String tmp = String.valueOf(selected).substring(0, cnt);
            comb.put(tmp, comb.getOrDefault(tmp, 0)+1);
            if(max == cnt || cnt == str.length()){
                return;
            }
        }
        
        for(int i = start; i < str.length(); i++){
            char c = str.charAt(i);
            selected[cnt] = c;
            dfs(str, i+1, cnt+1);
        }
        
    }
    
    public String[] solution(String[] orders, int[] course) {
        
        for(int e : course){
            co.add(e);
            max = e;
        }
        
        
        for(String str : orders){
            char[] ch = str.toCharArray();
            Arrays.sort(ch);
            selected = new char[max];
            dfs(String.valueOf(ch), 0, 0);
        }
        
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        
        for(String str : comb.keySet()){
            int val = comb.get(str);
            if(val > 1){
                if(map.containsKey(str.length())){
                    ArrayList<String> tmp = map.get(str.length());
                    int comp = comb.get(tmp.get(0));
                    if(comp > val) continue;
                    else if(comp == val){
                        tmp.add(str);
                    }else{
                        tmp = new ArrayList<>();
                        tmp.add(str);
                        map.put(str.length(), tmp);
                    }
                }else{
                    ArrayList<String> tmp = new ArrayList<>();
                    tmp.add(str);
                    map.put(str.length(), tmp);
                }
            }
        }
        ArrayList<String> answers = new ArrayList<>();
        for(int e : map.keySet()){
            answers.addAll(map.get(e));
        }
        Collections.sort(answers);
        
        return answers.toArray(String[]::new);
    }
}