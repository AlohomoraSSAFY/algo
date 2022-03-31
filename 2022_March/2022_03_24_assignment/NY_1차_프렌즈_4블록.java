package date0324;
import java.util.*;
public class NY_프렌즈_4블록 {

    static int[][] map;
    static int n,m;
    static Queue<int[]> q;
    static int answer;
    public int solution(int a, int b, String[] board) {
        answer = 0;
        n = a;
        m = b;
        map = new int[n][m];
        
        for(int i=0;i<n;i++){
            String str = board[i];
            for(int j=0;j<m;j++){
               map[i][j] = str.charAt(j);
            }
        }
        //
        q = new LinkedList<>();
        while(true){
            for(int i=0;i<n-1;i++){
                for(int j=0;j<m-1;j++){
                     if(map[i][j]!=-1 && popcheck(i,j)){
                         addpop(i,j);
                    }
                }
            }
            if(q.size()==0)
                break;
            pop();
            //0으로 빈 부분 내려주기
           down();
        }
        
        return answer;
    }
    public static void down(){
        for(int j=0;j<m;j++){ //각 줄 체크
        boolean check=true;
            while(check){
                check = false;
                for(int i=0;i<n;i++){
                    if(map[i][j] ==0){//빈 공간 있으면 하나씩 내림
                        check=  true;
                        for(int k=i;k>0;k--){
                            map[k][j] = map[k-1][j];
                        }
                        map[0][j] = -1;
                    }
                }
            }
        }
    }
    public static boolean popcheck(int x, int y){
        int a = map[x][y];
        for(int i=x;i<=x+1;i++){
            for(int j=y;j<=y+1;j++){
                if(map[i][j] !=a)
                    return false;
            }
        }   
        return true;
    }
    
    public static void addpop(int x, int y){
         for(int i=x;i<=x+1;i++){
            for(int j=y;j<=y+1;j++){
                q.add(new int[] {i,j});
            }
        }   
    }
    public static void pop(){
        while(!q.isEmpty()){
            int[] temp = q.poll();
            if(map[temp[0]][temp[1]] != 0){//이미 지워진 블록이 아니면 
                answer++; //지워지는 블록 수 증가
            }
            map[temp[0]][temp[1]] = 0;
        }
    }

    public static void printmap(){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
               System.out.print(map[i][j]+"   ");
            }
            System.out.println();
        }
        System.out.println();   
    }
}
