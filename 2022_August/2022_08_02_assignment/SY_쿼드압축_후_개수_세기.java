package study0809;

public class SY_쿼드압축_후_개수_세기 {
    
    int[][] array;
    
    public int[] solution(int[][] arr) {
        array = arr;
        return recursion(0, 0, arr.length);
    }
    
    private int[] recursion(int x, int y, int size) {
        int cntZero = 0;
        int cntOne = 0;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (array[i][j] == 0) cntZero++;
                else cntOne++;
            }
        }
        
        if (cntZero == 0) {
            return new int[]{cntZero, 1};
        }
        
        if (cntOne == 0) {
            return new int[]{1, cntOne};
        }
        
        int[] a = recursion(x, y, size / 2);
        int[] b = recursion(x + size / 2, y, size / 2);
        int[] c = recursion(x, y + size / 2, size / 2);
        int[] d = recursion(x + size / 2, y + size / 2, size / 2);
        
        cntZero = a[0] + b[0] + c[0] + d[0];
        cntOne = a[1] + b[1] + c[1] + d[1];
        
        return new int[]{cntZero, cntOne};
    }
}
