package com.ssafy.algostudy.implementation;

import java.io.*;

public class Main17609 {

    static int T;

    private static int check(int front, int end, String line, int cnt){
        while(true){
            if(front >= end){
                break;
            }
            if(line.charAt(front) == line.charAt(end)){
                front++;
                end--;
                continue;
            }

            if(cnt == 1){
                cnt++;
                break;
            }

            if( end - front > 2 ){
                int value = -1;
                if(line.charAt(front) == line.charAt(end-1)){
                    cnt++;
                    value = check(front+1, end-2, line, cnt);
                    if(value == 0 || value == 1) break;
                    cnt--;
                }

                if( line.charAt(front+1)==line.charAt(end)){
                    cnt++;
                    value = check(front+2, end-1, line, cnt);
                    if(value == 0 || value == 1) break;
                    cnt--;
                }
                cnt = 2;
                break;
            }

            if(end-front == 2){
                if(line.charAt(front)==line.charAt(front+1) || line.charAt(end)==line.charAt(front+1)){
                    cnt++;
                    front++;
                    end--;
                    continue;
                }
                cnt = 2;
                break;
            }

            if(end - front ==1){
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws Exception {
        String str = "10\n" +
                "abbab\n" +
                "summuus\n" +
                "xabba\n" +
                "xabbay\n" +
                "comcom\n" +
                "comwwmoc\n" +
                "comwwtmoc\n"+
                "XYXYAAYXY\n"+ // 1
                "ppbpppb\n"+ // 2
                "sumumuus"; // 1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(str.split("\n")[0]);
        for(int i = 0 ; i < T; i++){
            String line = str.split("\n")[i+1];
            int size = line.length();
            int cnt = 0;
            int front = 0, end = size-1;
            int value = check(front, end, line, cnt);
            bw.write(value+"\n");

        }

        bw.close();
        br.close();
    }
}
