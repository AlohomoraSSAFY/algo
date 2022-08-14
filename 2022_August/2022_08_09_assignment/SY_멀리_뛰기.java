package study0816;

public class SY_멀리_뛰기 {
    public long solution(int n) {
        long[] array = new long[n+1];
        array[0] = 1;
        for (int i = 0; i < n; i++) {
            array[i+1] = (array[i+1] + array[i]) % 1234567;
            if (i+2 < n+1) {
                array[i+2] = (array[i+2] + array[i]) % 1234567;
            }
        }
        
        return array[n];
    }
}
