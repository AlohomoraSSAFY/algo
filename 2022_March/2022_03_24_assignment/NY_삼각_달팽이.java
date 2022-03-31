package date0324;

public class NY_삼각_달팽이 {
	  public int[] solution(int n) {
	        int len = ((n+1)*n)/2; 
	        int[] answer = new int[len];
	        int map[][] = new int[n][n];
	        int idx = 1;
	        int num =0;
	        int dx[] = {1,0,-1}; //하 우 좌상
	        int dy[] = {0,1,-1};
	        int d =0;
	        int x =-1;
	        int y =0;
	        
	        while(idx <= len){
	                        
	            int nx = dx[d]+x;
	            int ny = dy[d]+y;
	            if(nx < n && ny < n && nx >=0 && ny >=0 && map[nx][ny]==0){
	                map[nx][ny]= idx;
	                x = nx;
	                y=ny;
	                idx++;
	            }else
	                d = (d+1)%3;
	        }
	        int cnt = 0;
	        for(int i=0;i<n;i++){
	            for(int j=0;j<n;j++){
	                if(map[i][j]!=0)
	                answer[cnt++] = map[i][j];
	            }
	        }
	        return answer;
	    }
}
