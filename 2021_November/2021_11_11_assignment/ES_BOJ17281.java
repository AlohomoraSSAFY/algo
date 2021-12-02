package com.ssafy.algostudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main17281 {

    static int N;
    static int[] selected = new int[10];
    static int score = 0;
    static int[][] predict;

    public static void perm(int cnt, boolean[] checked){ // 뽑기
        if(cnt==4){
            perm(cnt+1, checked);
            return;
        }

        if(cnt == 10){
//            for(int i = 1 ; i< 10; i++){
//                System.out.print(selected[i]+" ");
//            }
//            System.out.println();
            ining();
            return;
        }

        for(int i = 1; i < 10; i++){
            if(!checked[i]){
                checked[i] = true;
                selected[cnt] = i;
                perm(cnt+1, checked);
                checked[i] = false;
            }
        }
    }

    public static void ining(){
        int out = 0; int one =0, two = 0, three = 0;
        int idx = 1, sum = 0;
        for(int i = 0 ; i < N; i++){
            out = 0; one =0; two = 0; three = 0;
            while(out < 3){
                int cur = selected[idx];
                int s = predict[i][cur];
                switch (s){
                    case 0: // 아웃
                        out++;
                        break;
                    case 1: // 안타
                        sum += three;
                        three = two; two = one; one = 1;
                        break;
                    case 2: // 2도루
                        sum += (three + two);
                        three = one; two = 1; one = 0;
                        break;
                    case 3: // 3도루
                        sum += (three + two + one);
                        three = 1; two = 0; one = 0;
                        break;
                    case 4: // 홈런
                        sum += (three + two + one + 1);
                        three = 0; two = 0; one = 0;
                        break;
                }
                idx++;
                if(idx > 9) idx =1;
            }
        }


        score = Math.max(score, sum);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        boolean[] checked = new boolean[10];
        checked[1] = true;
        selected[4] = 1; // 4번 타자는 무조건 1번

        N = Integer.parseInt(br.readLine());
        predict = new int[N][10];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < 10; j++){
                predict[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        perm(1, checked);
        bw.write(score+"\n");
        br.close();
        bw.close();
    }
}
