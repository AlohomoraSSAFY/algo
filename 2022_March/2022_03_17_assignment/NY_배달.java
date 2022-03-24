package date0317;

import java.util.*;
class Node implements Comparable<Node>{
	int to;
	int cost; 
		
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	public int compareTo(Node e) {
		return this.cost- e.cost;
	}
}
class NY_배달 {
    static int[] dist;
    static ArrayList<Node>[] list;
    public int solution(int N, int[][] road, int K) {
        int answer = 1;
        
        dist= new int[N+1];
        list= new ArrayList[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for(int i=0;i<=N;i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0;i<road.length;i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];
            list[a].add(new Node(b,c));
            list[b].add(new Node(a,c));        
        }
        //다익스트라
        PriorityQueue<Node> pq= new PriorityQueue<>();
        dist[1]=0;
        pq.add(new Node(1,0));
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(dist[cur.to] < cur.cost)
                continue;
            for(Node next : list[cur.to]){
                if(dist[next.to] > cur.cost+next.cost){
                    dist[next.to] = cur.cost+next.cost;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }
            
        }
        for(int i=2;i<=N;i++){
            if(dist[i]<=K)
                answer++;
        }
        return answer;
    }
}