package date0331;
import java.util.*;
public class NY_캐시 {
	  public int solution(int cacheSize, String[] cities) {
	        int answer = 0;
	        
	        ArrayList<String> cache = new ArrayList<>();
	        for(int i=0;i<cities.length;i++){
	     
	            String temp = cities[i].toLowerCase();
	            boolean hit = false;
	            int len = cache.size();
	            for(int j=0; j<len;j++){
	             
	                String str= cache.get(j).toLowerCase();
	                if(str.equals(temp)){ //hit
	                    cache.remove(j);
	                    cache.add(temp);
	                    answer++;
	                    hit=true;
	                        break;
	                }
	            }
	            if(!hit){//miss
	                    answer+=5;
	                    if(cache.size()>=cacheSize && cache.size()>0){
	                        //캐시 사이즈가 cacheSize이하일 경우 그냥 캐시에 넣어줌
	                     cache.remove(0);
	                    }
	                    if(cacheSize>0){
	                        cache.add(temp);
	                    }
	                
	                   
	            }
	            
	          
	        }
	        return answer;
	    }
}
