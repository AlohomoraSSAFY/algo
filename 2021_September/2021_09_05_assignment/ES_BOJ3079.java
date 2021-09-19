package com.ssafy.algostudy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main3079 {

    static long N, M;
    static long[] time;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/3079_1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Long.parseLong(st.nextToken());
        M = Long.parseLong(st.nextToken());

        time = new long[(int)N];
        for(int i = 0 ; i < N; i++){
            time[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(time);
        long left = 0, right = M * time[ (int)N - 1];
        long cnt = 0;
        long ans = Long.MAX_VALUE;
        while(left <= right){ // == 일때 mid는 left
            long mid = (left + right) / 2;
            cnt = 0; // 심사대 통과한 사람 수 카운트
            for(int i = 0 ; i < N ; i++){
                cnt += ( mid / time[i]); // mid를 기준으로 부족하면 오른쪽 넘치면 왼쪽으로 넘어가기 위해
            }
            if(cnt < M){
                left = mid + 1;
            }else {
                ans = mid;
                right = mid -1;
            }
        }

        bw.write(ans+"\n");
        bw.close();
        br.close();
    }
}
