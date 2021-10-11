package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main2002 {

    static int N;
    static HashMap<String ,Integer> set = new HashMap<>();
    static int[] after;
    static int[] index;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        after = new int[N];
        for(int i = 0 ; i < N; i++){
            set.put(br.readLine(), i);
        }
        index = new int[N];
        for(int i = 0 ; i < N; i++){
            String tmp = br.readLine();
            int value = set.get(tmp);
            after[i] = value;
            index[value] = i;
        }
        int cnt = 0;
        for(int i = N-1; i > -1 ; i--){ // 뒤에서 부터 탐색
            int cur = index[i];  // 해당 번호의 나왔을 때 위치
            boolean[] checked = new boolean[i]; // 앞에 만큼
            for(int k = 0 ; k < cur; k++){
                if(after[k] < i){
                    checked[after[k]] = true;
                }
            }
            boolean flag = true;
            for(int k = 0 ; k < i; k++){
                if(checked[k]==false){
                    flag = false;
                    break;
                }
            }

            if(!flag){
                cnt++;
            }

        }

        bw.write(cnt+"\n");
        br.close();
        bw.close();
    }
}
