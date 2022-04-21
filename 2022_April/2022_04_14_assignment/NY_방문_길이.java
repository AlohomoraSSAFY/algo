class Solution {
    public int solution(String dirs) {
        int answer = 0;
        int n =11;
        boolean visited[][][][] =new boolean[n][n][n][n];
        int x = 5;
        int y = 5;
        
        for(int i=0;i<dirs.length();i++){
            char c = dirs.charAt(i);
            int dx =0;
            int dy =0;
            if(c == 'U')
                dy = 1;
            else if (c == 'D')
                dy = -1;
            else if(c =='R')
                dx = 1;
            else
                dx = -1;
            
            //
            
            int nx = x + dx;
            int ny = y + dy;
            
            if(nx < n && ny >=0  && ny < n && ny >=0){
                if(!visited[x][y][nx][ny] && !visited[nx][ny][x][y]){
                    answer++;
                    visited[x][y][nx][ny]=true;
                    visited[nx][ny][x][y] = true;
                }
                x = nx;
                y = ny;
            }
        }
        return answer;
        
    }
}