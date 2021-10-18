package com.ssafy.algostudy.implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main14719 {

    static int H,W;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[W];
        for(int i = 0; i < W; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int mh = arr[0]; int idx = 0;
        int[] rain = new int[W];
        for(int i = 1 ; i < W-1; i++){
            if( mh > arr[i] ){ // 물 채워짐
                rain[i] = (mh-arr[i]);
            }else if(mh == arr[i]){ // 물 채워지지 않음
                idx = i;
            }else{ // mh < arr[i] stop
                mh = arr[i];
                idx = i;
            }
        }
//        for(int i = 0 ; i < W; i++){
//            System.out.print(rain[i]+" " );
//        }
//        System.out.println();

        //  reverse
//        int ridx = W-1; mh = arr[ridx];
//        System.out.println(mh+" "+idx);
//        for(int i = W-2 ; i > idx-1; i--){
//            if( mh > arr[i] ){ // 물 채워짐
//                rain[i] = (mh - arr[i]);
//            }else if(mh == arr[i]){ // 물 채워지지 않음
//                ridx = i;
//            }else{ // mh < arr[i] stop
//                mh = arr[i];
//                ridx = i;
//            }
//        }
        int ridx = W-1; mh = arr[ridx];
        for(int i = W-2 ; i > 0; i--){
            if( mh > arr[i] ){ // 물 채워짐
                if(rain[i] > mh - arr[i])
                    rain[i] = (mh - arr[i]);
            }else if(mh == arr[i]){ // 물 채워지지 않음
                ridx = i;
                rain[i] = 0;
            }else{ // mh < arr[i]
                mh = arr[i];
                ridx = i;
                rain[i] = 0;
            }
        }

        int sum = 0;
//        for(int i = 0 ; i < W; i++){
//            System.out.print(rain[i]+" " );
//        }
//        System.out.println();
        for(int i = 0 ; i < W; i++){
            sum += rain[i];
        }

        bw.write(sum+"\n");

        br.close();
        bw.close();
    }
}
