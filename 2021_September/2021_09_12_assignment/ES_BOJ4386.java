package com.ssafy.algostudy.tree;

import java.io.*;
import java.util.*;

// 별자리 만들기
// MST?
public class Main4386 {

    static int N;
    static double[][] pos;
    private static class Star implements Comparable<Star>{
        int node;
        double pos;

        public Star(int node, double pos){
            this.node = node;
            this.pos = pos;
        }

        @Override
        public int compareTo(Star s){
            return Double.compare(this.pos, s.pos);
        }
    }
    static List<List<Star>> edge = new ArrayList<>();
    static boolean[] visited;
    static int cnt = 0;
    static double total = 0.0;

    private static void prim(int start){
        PriorityQueue<Star> pq = new PriorityQueue<>();
        pq.add(new Star(start, start));
        while(!pq.isEmpty()){
            Star tmp = pq.poll();
            if(visited[tmp.node]) continue;
            visited[tmp.node] = true;
            total += tmp.pos;
            for(Star next : edge.get(tmp.node)){
                if(!visited[next.node]){
                    pq.add(next);
                }
            }

            if(++cnt==N) break;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//        N = Integer.parseInt(br.readLine());

//        N = 3;
//        String str =
//                "1.0 1.0\n" +
//                "2.0 2.0\n" +
//                "2.0 4.0";
//        N = 2;
//        String str =
//                "1 1\n" +
//                "18.1 1";

        N = 4;
        String str = "0 0\n" +
                "0 1\n" +
                "2 0\n" +
                "2 1";

        pos = new double[N][N];
        StringTokenizer st;
        for(int i = 0 ; i < N; i++){
//            st = new StringTokenizer(br.readLine());
            st = new StringTokenizer(str.split("\n")[i]);
            pos[i][0] = Double.parseDouble(st.nextToken());
            pos[i][1] = Double.parseDouble(st.nextToken());
        }


        for(int i = 0 ; i < N ; i++){
            List<Star> tmp = new ArrayList<>();
            for(int j = 0 ; j < N ; j++){
                double len = Math.sqrt( Math.pow(pos[i][0]-pos[j][0],2) + Math.pow(pos[i][1]-pos[j][1], 2) );
                tmp.add(new Star(j, len));
            }
            edge.add(tmp);
        }

        visited = new boolean[N];
        prim(0);

        bw.write(String.format("%.2f", total)+"\n");
        bw.close();
        br.close();
    }
}
