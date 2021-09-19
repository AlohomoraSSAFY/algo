package com.ssafy.algostudy;

import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Main3190 {

    static int N, K, L;
    static int[][] map;
    static Queue< HashMap<Integer, Character> > direction = new LinkedList<>();
    static int[] dy = { 0, 1, 0, -1};
    static int[] dx= { 1, 0, -1, 0}; // 시계 방향 // 오른쪽이면 +1 왼쪽이면 -1
    static List<int[]> snake = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("./res/3190_4.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        K = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < K ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a-1][b-1] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < L ; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            HashMap<Integer, Character> tmp = new HashMap<>();
            tmp.put(Integer.valueOf(x), Character.valueOf(c));
            direction.offer(tmp);
        }

        map[0][0] = 2;
        snake.add(new int[]{0, 0}); // 0행 0열 머리

        boolean flag = true;
        int d = 0 , time = 0;

        while (true){

            time++;
            // 뱀의 움직임
            // 머리 움직이기
            int[] head = snake.get(0);
            if(d>3) d = 0;
            if(d<0) d= 3;
            int ny = head[0] + dy[d];
            int nx = head[1] + dx[d];
//            System.out.println(ny+" "+nx);

            // 벗어나는지 확인
            if(ny < 0 || nx < 0 || ny > N-1 || nx > N-1){
                flag = false;
                break;
            }

            // 안 벗어나면
            if(map[ny][nx] == 1){ // 사과가 있으면 머리만 증가
//                System.out.println("apple");
                snake.add(0, new int[]{ny, nx});
                map[ny][nx] = 2;
            }else if(map[ny][nx] == 0){ // 빈칸이면  꼬리 빼기 + 헤드 추가
//                System.out.println("move");
                int ssize = snake.size();
                int[] tail = snake.get(ssize-1);
//                System.out.println("tail "+tail[0]+" "+ tail[1]);
                map[tail[0]][tail[1]] = 0;
                snake.remove(ssize-1);
                snake.add(0, new int[]{ny, nx});
                map[ny][nx] = 2;
//                System.out.println("NEW "+snake.get(snake.size()-1)[0]+" "+snake.get(snake.size()-1)[0]);
            }else{ // 뱀 몸이랑 겹침
                flag = false;
                break;
            }

            // 방향 바꾸기
            if(direction.size()>0){
                if(direction.peek().containsKey(time)){ // 방향을 바꾸는 경우
                    if(direction.poll().get(time)=='L'){
                        d--;
                    }else{ // 'D'일때
                        d++;
                    }
                }
            }
        }

        bw.write(time+"\n");

        br.close();
        bw.close();
    }
}
