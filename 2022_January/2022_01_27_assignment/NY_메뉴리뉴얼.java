import java.util.*;
class Solution {
    static HashMap<String, Integer> hm;
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String>list = new ArrayList<>();
        hm = new HashMap<String ,Integer>();       
        
        int max[] = new int[100];
        
        
        //문자열 정렬
        for(int i=0;i<orders.length;i++){
            String cur = orders[i];
            char[] temp = new char[cur.length()];
            for(int j=0;j<cur.length();j++){
                temp[j] = cur.charAt(j);
            }
            Arrays.sort(temp);
            String str = "";
            for( char c : temp)
                str+=c;
            orders[i] = str;
        }
        for(String str : orders){
            for(int c : course){
                char[]  selected = new char[c];
                combination(0, 0, c ,str, selected);
            }
        }
        
        for(String key : hm.keySet()){
            if(hm.get(key)>1){
                int len = key.length();
                max[len] = Math.max(hm.get(key), max[len]); 
            }
        }
        for(String key : hm.keySet()){
            if(hm.get(key)>1){
                int len = key.length();
                if(hm.get(key) == max[len])
                    list.add(key);
            }
        }
        
            
        
        String [] answer = new String[list.size()];
        for(int i=0;i<list.size();i++){
            answer[i] = list.get(i);
         }
        Arrays.sort(answer);
        return answer;
    }
    public static void combination(int start, int count, int end, String str,char[] selected){
    
        if(count==end){
            String temp = "";
            Arrays.sort(selected);
            for(char c : selected)
                temp+= c;
            if(hm.containsKey(temp)){
                hm.put(temp, hm.get(temp)+1);
            }
            else{
                hm.put(temp,1);
            }
            
            return;
            
        }
        for(int i=start;i<str.length();i++){
            selected[count] = str.charAt(i);
            combination(i+1, count+1, end ,str, selected);
        }
    }
}