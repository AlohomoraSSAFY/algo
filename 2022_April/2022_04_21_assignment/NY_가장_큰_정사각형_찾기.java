class Solution
{
    static int max;
    public int solution(int [][]board)
    {
        max =0;
        int n = board.length;
        int m = board[0].length;
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(board[i][j]==0)
                    continue;
                board[i][j] = Math.min(board[i-1][j],board[i][j-1]);
                board[i][j] = Math.min(board[i][j], board[i-1][j-1])+1;
                
                max = Math.max(max, board[i][j]);
                
            }
        }
      
        if(n==1 && m==1){
            max = board[0][0];
        }
        return max*max;
    }

}