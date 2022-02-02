package study0203;

public class SY_행렬_테두리_회전하기 {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] array = new int[rows+1][columns+1];
        
        int num = 0;
        for (int i = 1; i < rows+1; i++) {
            for (int j = 1; j < columns+1; j++) {
                array[i][j] = ++num;
            }
        }
        
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int min = Integer.MAX_VALUE;
            int sx = queries[i][0];
            int sy = queries[i][1];
            int ex = queries[i][2];
            int ey = queries[i][3];
            
            int temp = array[sx][sy];
            min = Math.min(min, temp);
            for (int j = sx; j < ex; j++) {
                min = Math.min(min, array[j+1][sy]);
                array[j][sy] = array[j+1][sy];
            }
            for (int j = sy; j < ey; j++) {
                min = Math.min(min, array[ex][j+1]);
                array[ex][j] = array[ex][j+1];
            }
            for (int j = ex; j > sx; j--) {
                min = Math.min(min, array[j-1][ey]);
                array[j][ey] = array[j-1][ey];
            }
            for (int j = ey; j > sy+1; j--) {
                min = Math.min(min, array[sx][j-1]);
                array[sx][j] = array[sx][j-1];
            }
            array[sx][sy+1] = temp;
            answer[i] = min;
        }
        
        return answer;
    }
}
