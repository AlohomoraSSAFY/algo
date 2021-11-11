package date1111THU;

import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int node;
	int dist;
	
	public Edge(int node, int dist) {
		super();
		this.node = node;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.dist- o.dist;
	}
	
}
public class BOJ1922 {

static int n,m;
static boolean visited[];
static int answer;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		
		ArrayList<Edge> list[] = new ArrayList[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<m;i++) {			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Edge(b,c));
			list[b].add(new Edge(a,c));
		}
		
		answer =0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		pq.offer(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			Edge temp = pq.poll();
			if(!visited[temp.node]) {
				visited[temp.node] = true;
				answer+=temp.dist;
				
				for(Edge e : list[temp.node]) {
					if( !visited[e.node] ){
						pq.offer(e);
					}
				}
			}
		}
		
		System.out.println(answer);
		
		
	}

}
 