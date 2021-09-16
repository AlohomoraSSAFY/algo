package com.ssafy.algostudy.implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main5430 {

    static int T;
    static int N;
    static boolean dir;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/5430.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        T = Integer.parseInt(br.readLine());
        for(int cs = 1; cs < T + 1; cs++){
            String func = br.readLine();
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");
            if(!arr[0].equals("")){
                for(int i = 0 ; i < arr.length; i++){
                    list.add(Integer.parseInt(arr[i]));
                }
            }
            dir = true; // 정방향
            boolean flag = true;
            int start = 0;
            int end = list.size()-1;
            for(int i = 0 ; i < func.length(); i++){
                if(func.charAt(i)=='R'){
                    dir = !dir;
                }else{
                    if(dir){ // 정방향
                        if(list.size() > 0) start++;
                        else {
                            flag = false;
                            break;
                        }
                    }else { // 역방향
                        if(list.size() > 0) end--;
                        else {
                            flag = false;
                            break;
                        }
                    }
                }
            }

            System.out.println(start+" "+end);

            if(end < start -1) flag = false;
            if(flag){
                bw.write("[");
                StringBuilder sb = new StringBuilder("");
                if(dir){
                    for(int i  = start; i < end+1; i++){
                        sb.append(list.get(i)+",");
                    }
                }else{
                    for(int i = end; i > start -1; i-- ){
                        sb.append(list.get(i)+",");
                    }
                }
                if(sb.length() > 1){
                    sb.deleteCharAt(sb.length()-1);
                }
                bw.write(sb.toString()+"]\n");
            }
            else
                bw.write("error\n");
        }



        bw.close();
        br.close();
    }
}
