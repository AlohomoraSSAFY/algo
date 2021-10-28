import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Other_BOJ16947 {
    static ArrayList<Integer>[] stations;
    static boolean[] visited;
    static int[] distance;
    static Queue<Integer> q;
    static boolean isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        stations = new ArrayList[N+1];
        visited = new boolean[N+1];
        distance = new int[N+1];
        q = new LinkedList<>();
        Arrays.fill(distance, -1);

        for(int i=1; i<=N; i++){
            stations[i] = new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            stations[start].add(end);
            stations[end].add(start);
        }

        isCycle = false;
        //cycle 찾기
        dfs(1,1);
        //거기구하기
        bfs();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            sb.append(distance[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void bfs(){
        int cnt = 1;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int cur = q.poll();

                for(int next : stations[cur]){
                    if(distance[next] == -1){
                        distance[next] = cnt;
                        q.offer(next);
                    }
                }
            }
            cnt++;
        }
    }

    public static void dfs(int prev, int cur) {
        visited[cur] = true;

        for(int next : stations[cur]){
            if(!visited[next]){
                dfs(cur, next);

                if(isCycle){
                    if(distance[next] == 0){
                        isCycle = false;
                    }else{
                        distance[next] = 0;
                        q.offer(next);
                    }
                    return;
                }
            }
            else if(visited[next] && prev != next){
                distance[next] = 0;
                q.offer(next);
                isCycle = true;
                return;
            }
        }
    }
}
