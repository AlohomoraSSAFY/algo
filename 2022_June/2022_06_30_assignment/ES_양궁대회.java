package algo;

import java.util.*;

public class ES_¾ç±Ã´ëÈ¸ {
    
    int N;
    int[] apeach;
    int max = 0; int[] tmp;
    
    private void dfs(int cnt, int total, int[] check){
        if(cnt == N+1){
            int a = 0, b = 0;
            for(int i = 0; i < 11; i++){
                if(apeach[i] == 0 && check[i] ==0) continue;
                if(apeach[i] >= check[i]) {
                    a += (10-i);
                } else {
                    b += (10-i);
                }
            }
           
            if(a < b ) {
                if(b - a > max) {
                    max = b -a;
                    tmp = new int[11];
                    for(int i = 0; i < 11; i++){
                        tmp[i] = check[i];
                    }
                } else if(b - a >= max) {
                    for(int i = 10; i > -1; i--){
                        if(tmp[i] == 0 && check[i] ==0) continue;
                        if(tmp[i] < check[i]) {
                            tmp = new int[11];
                            for(int j = 0; j < 11; j++){
                                tmp[j] = check[j];
                            }
                            break;
                        }else {
                            break;
                        }
                    }                    
                }
            }
            return;
        }
        
        for(int i = 0; i < 11; i++) {
            if(total + i <= N) {
                check[cnt] = i;
                dfs(cnt+1, total + i, check);
            }

        }
        
        
    }
    
    public int[] solution(int n, int[] info) {
        
        N = n; apeach = info;
        
        int[] check = new int[12];
        dfs(0, 0, check);
        if(max==0) {
            return new int[]{-1};
        }
        else {
            return tmp;
        }
    }
}