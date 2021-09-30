package com.ssafy.algostudy.implementation;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main20055 {

    static int N, K;
    static ArrayList<Integer> belt = new ArrayList<>();
    static ArrayList<Integer> robot = new ArrayList<>();
    static int total = 0;

    private static void moveBelt(){
        int blast = belt.remove(2*N-1);
        belt.add(0, blast);
        int rlast = robot.remove(2*N-1);
        robot.add(0, rlast);

        if(robot.get(N-1) == 1){ // N위치에 도달한 경우 로봇이 존재할때
            robot.set(N-1, 0); // 즉시 내린다
        }

    }

    private static void moveRobot(){

        int prev = robot.get(N-2); // N-1 탐색
        if(prev==1){ // N-1칸에 로봇이 있다면
            int durability = belt.get(N-1); // 움직일 칸의 내구도 파악
            if(durability > 1){ // 2이상 내구도라 움직임 가능
                belt.set(N-1, durability-1); // 내구도 1 감소
                robot.set(N-2, 0); // 이동 후
                robot.set(N-1, 0); // N 즉시 내리기
            }else if(durability == 1){ // 1인 경우 내구도 0으로 되고 개수 카운팅
                belt.set(N-1, durability-1);
                total++;
                robot.set(N-2, 0); // 이동 후
                robot.set(N-1, 0); // N 즉시 내리기
            }else{
                // N번째 칸이 0이면 이동불가
            }
        }

        int next = robot.get(N-2); // N-1 로봇 여부 가져오기기
        for(int i = N - 3; i > -1; i--){ // N-2부터 0번째
            int exit = robot.get(i); // i번째에 로봇이 있다면
            if(exit==1){
                if(next==0){ // 그 다음 칸에 로봇이 있는지 확인 -> 없으면 이동
                    int durability = belt.get(i+1); // 움직일 칸의 내구도 파악
                    if(durability > 1){ // 2이상 내구도라 움직임 가능
                        belt.set(i+1, durability-1); // 내구도 1 감소
                    }else if(durability==1){ // 1인 경우 내구도 0으로 되고 개수 카운팅
                        belt.set(i+1, durability-1);
                        total++;
                    }else{ // 못 움직이는 경우
                        next = 1;
                        continue;
                    }
                    robot.set(i+1, 1); // 로봇 옮기기
                    robot.set(i, 0);
                    next = 0;
                }else {
                    next = 1;
                }
            }else {
                next = 0;
            }
        }

        // N+1에서 2N-1까지 탐색
        next = robot.get(2*N-1); // 2*N 로봇 여부 가져오기기
        for(int i = 2*N-2; i > N-1; i--){
            int exit = robot.get(i); // i번째에 로봇이 있다면
            if(exit==1){
                if(next==0){ // 그 다음 칸에 로봇이 있는지 확인 -> 없으면 이동
                    int durability = belt.get(i+1); // 움직일 칸의 내구도 파악
                    if(durability > 1){ // 2이상 내구도라 움직임 가능
                        belt.set(i+1, durability-1); // 내구도 1 감소
                    }else if(durability==1){ // 1인 경우 내구도 0으로 되고 개수 카운팅
                        belt.set(i+1, durability-1);
                        total++;
                    }else{ // 못 움직이는 경우
                        next = 1;
                        continue;
                    }
                    robot.set(i+1, 1); // 로봇 옮기기
                    robot.set(i, 0);
                    next = 0;
                }else { // 다음칸에 로봇 있으면 이동 불가
                    next = 1;
                }
            }else { // 로봇이 존재 하지 않는 경우
                next = 0;
            }

        }

        // 2N과 1
        int exit = robot.get(2*N-1); // 첫번째에 로봇이 있다면
        if(exit == 1){
            if(robot.get(0)==0){ // 그 다음 칸에 로봇이 있는지 확인 -> 없으면 이동
                int durability = belt.get(0); // 움직일 칸의 내구도 파악
                if(durability > 1){ // 2이상 내구도라 움직임 가능
                    belt.set(0, durability-1); // 내구도 1 감소
                }else if(durability==1){ // 1인 경우 내구도 0으로 되고 개수 카운팅
                    belt.set(0, durability-1);
                    total++;
                }
                robot.set(0, 1); // 로봇 옮기기
                robot.set(2*N-1, 0);
            }
        }

    }

    private static void addRobot(){
        if(robot.get(0)==0){
            int durability = belt.get(0);
            if(durability > 0){
                robot.set(0, 1); // 로봇 추가
                belt.set(0, durability-1); // 내구도 감소
                if(durability-1==0) total++;
            }
        }
    }

    private static int check(){
        int cnt = 0;
        for(Integer e : belt){
            if(e==0) cnt++;
        }
        return cnt;

    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 내구도
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < 2*N; i++){
            belt.add(Integer.parseInt(st.nextToken()));
            robot.add(0);
        }

        int level = 0;
        while (true){
            level++;
            moveBelt(); // 순서 1
            moveRobot(); // 순서 2
            addRobot(); // 순서 3
//            for(int i = 0 ; i < belt.size(); i++){
//                System.out.printf("%3d", belt.get(i));
//            }
//            System.out.println();
//            for(int i = 0 ; i < belt.size(); i++){
//                System.out.printf("%3d", robot.get(i));
//            }
//            System.out.println();

            if(check() >= K) break; // 순서 4
        }

        bw.write(level+"\n");
        bw.close();
        br.close();
    }
}
