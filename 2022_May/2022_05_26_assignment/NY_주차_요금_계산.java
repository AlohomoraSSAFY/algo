import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int count =0;
        HashMap<Integer,String> hm = new HashMap<Integer,String>();
        int[] min = new int[10000];
        for(int i=0;i<records.length;i++){
        
            StringTokenizer st = new StringTokenizer(records[i]);
            String curtime = st.nextToken();
            int carnum = Integer.parseInt(st.nextToken());
            String io = st.nextToken();
            
            if(io.equals("IN")){ //입차
                hm.put(carnum, curtime);
            }else{ //출차
                String intime = hm.get(carnum);
                            
                int minute = getmin(curtime,intime);
                min[carnum] += minute;
                
                hm.remove(carnum);
            }
        }
        
        
        if(!hm.isEmpty()){
            for(Integer i : hm.keySet()){
                min[i]+=getmin("23:59",hm.get(i));
            }
        }
        
        
        //
        ArrayList<Integer> costs = new ArrayList<>();
        
        for(int i=0;i<10000;i++){
            if(min[i]!=0){
                int cost= 0;
                //주차요금 계산
                int minute= min[i];
                
                minute-=fees[0];
                cost += fees[1];
                
                while(minute>0){
                    minute-=fees[2];
                    cost+=fees[3];
                }
                
             
                
                
                costs.add(cost);
                
            }
        }
        
        int[] answer = new int[costs.size()];
        for(int i=0;i<costs.size();i++){
            answer[i] = costs.get(i);
        }
        return answer;
    }
    public static int getmin(String curtime, String intime){
        int minute = 0;
        String t1[] = intime.split(":");
        String t2[] = curtime.split(":");
        minute += (Integer.parseInt(t2[0])-Integer.parseInt(t1[0])) *60;
        minute += Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
        return minute;
    }
    
}