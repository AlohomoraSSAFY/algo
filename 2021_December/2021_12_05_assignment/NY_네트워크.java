package date1209THU;
import java.util.*;
public class 프로그래머스_네트워크 {

	static boolean visited[];
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        
        for(int i=0;i<n;i++){
            if(!visited[i]){
                bfs(n,i,computers);
                answer++;
            }
        }
        
        return answer;
    }
    public static void bfs(int n,int num, int[][] computers){
        Queue<Integer> q = new LinkedList<>();
        q.offer(num);
        visited[num] = true;
        
        while(!q.isEmpty()){
            int t = q.poll();
            for(int i=0;i<n;i++){
                if(!visited[i] && computers[t][i] == 1){
                    q.offer(i);
                    visited[i] = true;
                }
                    
            }
        }
    }

}
