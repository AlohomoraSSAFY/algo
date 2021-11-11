package com.ssafy.algostudy.ds;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2304 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new int[10001];
        int min = N; int max = 1;
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a] = b;
            min = Math.min(a, min);
            max = Math.max(a, max);
        }
//        for(int i = 0; i < 4; i++){
//            if(i==1){
//                i = 2;
//            }
//            System.out.println(i);
//        }
        int answer = 0;
        if(min==max){
            answer = arr[min];
        }else{
            int[] forward = new int[1001];
            int top = arr[min];
            forward[min] = top;
            for(int i = min; i < max+1; i++){
                for (int j = i+1; j < max+1; j++){
                    if(top < arr[j]){
                        top = arr[j];
                        forward[j] = top;
                        i = j-1;
                        break;
                    }else{
                        forward[j] = top;
                    }
                }
            }
            top = arr[max];
            for(int i = max; i >= min ; i--) {
                for (int j = i; j >= min; j--) {
                    if (top < arr[j]) {
                        top = arr[j];
                        forward[j] = top;
                        i = j + 1;
                        break;
                    } else {
                        if ( top > forward[j]) {
                            continue;
                        }
                        forward[j] = top;
                    }
                }
            }

            for(int i = min ; i < max+1; i++){
                //System.out.println(forward[i]);
                answer += forward[i];
            }
        }

        bw.write(answer+"\n");
        br.close();
        bw.close();
    }
}
