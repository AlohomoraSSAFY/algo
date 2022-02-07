class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        int c =1;
        int[][] map = new int[rows][columns];
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                map[i][j] = c++;
            }
        }
    
        int dx[] = {1,0,-1,0}; //하우좌상
        int dy[] = {0,1,0,-1};
        int tc = queries.length;
        for(int t =0; t<tc;t++) { //한 쿼리에 대해서            
            int x1 = queries[t][0]-1;
            int y1 = queries[t][1]-1;
            int x2 = queries[t][2]-1;
            int y2 = queries[t][3]-1;
            
            int min = map[x1][y1];
            int last =map[x1][y1];
            //회전하면서 min값 갱신
            int d =0;
            int x = x1;
            int y = y1;
            while(true){
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx > x2 || nx < x1 || ny > y2 || ny < y1){
                    d = (d+1)%4;
                    continue;
                }
                if( nx  == x1 && ny == y1){
                    map[x1][y1+1] = last;
                    break;
                }
                map[x][y] = map[nx][ny];
                min = Math.min(map[x][y],min);                
                x = nx;
                y = ny;
                
                
            }

                
                
                
            answer[t] = min;
        }
        
        
        
        
        return answer;
    }
}