package date0310;

import java.util.*;

class  NY_뉴스_클러스터링  {
    public int solution(String str1, String str2) {
        
        HashMap<String, Integer> hm1 = new HashMap<String, Integer>();
        
        HashMap<String, Integer> hm2 = new HashMap<String, Integer>();
       for(int i=0; i<str1.length()-1;i++){
       char ch1 = Character.toUpperCase(str1.charAt(i));
            

       char ch2 = Character.toUpperCase(str1.charAt(i+1));
            
            if(ch1 >= 'A' && ch1 <='Z' && ch2>= 'A'  && ch2 <= 'Z') {
            String s = "";
                s+=ch1;
                s+=ch2;
                if(hm1.containsKey(s)){
                    hm1.put(s, hm1.get(s)+1);
                }else{
                    hm1.put(s,1);
                }                
            }          
        }
        
        for(int i=0; i<str2.length()-1;i++){

       char ch1 = Character.toUpperCase(str2.charAt(i));
       char ch2 = Character.toUpperCase(str2.charAt(i+1));

            if(ch1 >= 'A' && ch1 <='Z' && ch2>= 'A'  && ch2 <= 'Z') {

            String s = "";

                s+=ch1;
                s+=ch2;

                if(hm2.containsKey(s)){

                    hm2.put(s, hm2.get(s)+1);

                }else{

                    hm2.put(s,1);

                }                
            }
        }
        
        //
        
        int min =0; 
        int max =0;
        
        // min -> 교집합 찾기 
        // max -> 전체 원소 개수 합 for문 키셋으로 돌려서 구해서 - min
        
        for(String key : hm1.keySet()){
            if(hm2.containsKey(key)){
                min += Math.min(hm1.get(key),hm2.get(key));
            
            }
            
            max+=hm1.get(key);
        }
        
        //max
        for(String key : hm2.keySet()){
            

            max+=hm2.get(key);
        }
        max-=min;
        if( min == 0 && max ==0)

         return 65536;
         double jaccard = (double) min / max;
        
        int answer =(int) (jaccard*65536);
        
        return answer;
        
    }
}