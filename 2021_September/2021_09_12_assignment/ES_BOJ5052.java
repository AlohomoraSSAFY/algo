package com.ssafy.algostudy;

import java.io.*;
import java.util.*;

public class Main5052 {

    static int T;
    static int N;

    static class Phone {
        Map<Character, Phone> children = new HashMap<>();
        boolean terminal = false;
        int num = 0;

        public Phone(){}
    }

    static Phone root;
// 0 1  2 3 4 5 6 7 8 9
    // 9라는 phone 다음
    // 9라는 phone 이 가진 map에서
    // 0123
    // 1 phone
    static void insert(String key){
        int length = key.length();
        Phone cur = root; // 루트부터 시작
        for(int i = 0; i < length; i++){ // Tree에 넣어줄 값 처음부터 탐색
            char c = key.charAt(i);// 1
            if(!cur.children.containsKey(c)){ // 해당 char 값이 없으면
                cur.children.put(c, new Phone()); // 다음 노드 추가
            }
            cur = cur.children.get(c);
        }
        cur.terminal = true;
    }

    public static boolean find(String key){
        int length = key.length();
        Phone cur = root;
        for(int i = 0 ; i < length ; i++){
            if(cur.terminal){
                return true;
            } // 911 <- 91130
            char c = key.charAt(i);
            if(!cur.children.containsKey(c)){
                return false; // 911 <- 9132
            }
            cur = cur.children.get(c);
        }
        return cur!= null; // 91130 <- 911
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./res/5052.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        for(int cs = 1; cs < T + 1; cs++){
            N = Integer.parseInt(br.readLine());
            root = new Phone();
            boolean flag = false;
            String str = br.readLine();
            insert(str);
            for(int i = 1 ; i < N ;i++){
                str = br.readLine();
                flag = find(str);
                if(!flag){
                    insert(str);
                }else {
                    for(int j = i+1; j < N; j++)
                        br.readLine();
                    break;
                }
            }
            if(!flag){
                bw.write("YES\n");
            }else {
                bw.write("NO\n");
            }

        }




        br.close();
        bw.close();
    }
}
