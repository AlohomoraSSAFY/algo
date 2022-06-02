import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<Integer, Integer> inTime = new HashMap<>();
        HashMap<Integer, Integer> outTime = new HashMap<>();
        HashMap<Integer, Integer> totalTime = new HashMap<>();
        ArrayList<int[]> list = new ArrayList<>();
        
        for(String line : records){
            String[] record = line.split(" ");
            String[] time = record[0].split(":");
            int min = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
            int num = Integer.parseInt(record[1]);
            if(record[2].equals("IN")){
                inTime.put(num, min);
            } else { // out
                int start = inTime.get(num);
                int end = min;                
                totalTime.put(num, totalTime.getOrDefault(num, 0) + (end-start));
                inTime.remove(num);
            }
        }
        
        
        for(Integer num : inTime.keySet()){
            int start = inTime.get(num);
            int end = 23*60+59; 
            totalTime.put(num, totalTime.getOrDefault(num, 0) + (end-start));
            //System.out.printf("%d 차량 %d %d\n", num, end - start, total);
        }
        
        for(Integer num : totalTime.keySet()){
            int total = fees[1];
            int time = totalTime.get(num);
            if( time > fees[0]){
                int diff = (time - fees[0]);
                int tmp = (diff/fees[2]) * fees[3];
                if(diff % fees[2] > 0) {
                    tmp += fees[3];
                }
                total += tmp;
            }
            list.add(new int[]{num, total});
        }
        
        Collections.sort(list, new Comparator<int[]>() {
           @Override
            public int compare(int[] a, int[] b){
                return a[0]-b[0];
            }
        });
        
       
        int[] answer = list.stream().mapToInt( o -> o[1]).toArray();
        return answer;
    }
}