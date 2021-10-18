package com.ssafy.algostudy;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main16947 {

    static int N;
    static HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
    static int[] parent;
    static ArrayList<ArrayList<Integer>> cycles = new ArrayList<>();
    static int[] len;

    public static void dfs(int cur, int from){
        ArrayList<Integer> data = adj.get(cur); // cur의 이웃 탐색
        for(Integer e : data){
            if(e == from) continue;
            if( parent[e] == 0){ // parent가 없는 경우
                parent[e] = cur;
                dfs(e, cur);
            }else { // 사이클 형성
                parent[e] = cur;
                ArrayList<Integer> c = new ArrayList<>();
                int next= e;
                while(true){
                    if(c.contains(parent[next])){
                        break;
                    }
                    c.add(parent[next]);
                    next = parent[next];

                }
                cycles.add(c);
            }
        }
    }

    public static void dfs2(int cur, int from, ArrayList<Integer> cycle, int depth){
//        System.out.println(cur+" "+from);
        len[cur] = Math.min(len[cur], depth );
        ArrayList<Integer> data = adj.get(cur); // cur의 이웃 탐색
        for(Integer e : data){
            if(e == from) continue; // 부모면 pass
            if(cycle.contains(e)) continue; // 사이클이면 pass
            dfs2(e, cur, cycle, depth+1);
        }
    }

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/16947.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        while( true){
            String input = br.readLine();
            if( input==null || input.isEmpty()) break;

            StringTokenizer st = new StringTokenizer(input);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(adj.containsKey(a)){ // 포함하는 경우
                ArrayList<Integer> tmp = adj.get(a);
                tmp.add(b);
                adj.put(a, tmp );
            }else{
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(b);
                adj.put(a, tmp );
            }

            if(adj.containsKey(b)){
                ArrayList<Integer> tmp = adj.get(b);
                tmp.add(a);
                adj.put(b, tmp );
            }else{
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(a);
                adj.put(b, tmp );
            }

        }

        dfs(1, 0);
        // 형성된 사이클은 모두 cycles에

        StringBuilder sb = new StringBuilder();
        len = new int[N+1];
        Arrays.fill(len, Integer.MAX_VALUE);

//        for(int j = 0; j < cycles.size(); j++){
//            System.out.println("------------");
//            ArrayList<Integer> cycle = cycles.get(j);
//            for(Integer k : cycle){
//                System.out.print(k+" ");
//            }
//            System.out.println("---------");
//        }

        for(int i = 1; i < N+1; i++){
            for(int j = 0; j < cycles.size(); j++){
                ArrayList<Integer> cycle = cycles.get(j);
                if(cycle.contains(i)){ // 사이클 내에 있으면
                    len[i] = 0;
                }
            }
        }

        for(int j = 0; j < cycles.size(); j++){
            ArrayList<Integer> cycle = cycles.get(j);
            for(Integer e : cycle){
                if(adj.get(e).size() > 1){
                    dfs2(e, 0, cycle, 0);
                }
            }
        }

        for(int i = 1; i < N+1; i++){
            bw.write(len[i]+" ");
        }

        br.close();
        bw.close();
    }
}
