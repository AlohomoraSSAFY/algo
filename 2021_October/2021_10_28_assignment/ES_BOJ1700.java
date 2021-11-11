package com.ssafy.algostudy.solving;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main1700 {

    static int N, K;
    static int[] order;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        order = new int[K+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < K+1; i++){
            order[i] = Integer.parseInt(st.nextToken());
        }

        boolean[] using = new boolean[K+1];
        int cnt = 0; int ans = 0;
        for(int i  = 1; i < K+1; i++){
            int cur = order[i];
//            System.out.println(order[i]);
            if(using[cur]){
//                System.out.println("pass");
                continue;
            }else{
                if( cnt <  N){
                    using[cur] = true;
                    cnt++;
//                    System.out.println("wh");
                }else{
//                    System.out.println("test");
                    ans++;
                    Set<Integer> set = new HashSet<>();
                    for(int j = i+1; j < K+1; j++){
                        if(using[order[j]]){
                            set.add(order[j]);
                        }
                    }
                    // 2
                    // 2 3 2 3 2 2
                    if(set.size() == N){ // 모두 다 재사용하면
                        int last = K+1;
                        ArrayList<Integer> check = new ArrayList<>();
                        for(int j = i+1; j < K+1; j++){
                            if(using[order[j]]){
                                if(!check.contains(order[j])){
                                    check.add(order[j]);
                                    last = j;
                                }
                            }
                        }
                        using[order[last]] = false;
                        using[cur] = true;
                    }else{ // 다 재사용 안하면
                        for(int j = 1; j < K+1; j++){
                            if(using[order[j]] && !set.contains(order[j])){
                                using[order[j]] = false;
                                using[cur] = true;
                                break;
                            }
                        }
                    }           
                }
            }

        }

        bw.write(ans+"\n");
        br.close();
        bw.close();
    }
}
