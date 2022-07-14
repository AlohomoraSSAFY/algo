package study0714;

public class SY_풍선_터트리기 {
    public int solution(int[] a) {
        if (a.length == 1) return 1;
        
        int[] left = new int[a.length];
        int[] right = new int[a.length];
        int answer = 2;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
            left[i] = min;
        }
        
        min = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            if (min > a[i]) {
                min = a[i];
            }
            right[i] = min;
        }
        
        for (int i = 1; i < a.length - 1; i++) {
            if (!(a[i] > left[i-1] && a[i] > right[i+1])) answer++;
        }
        return answer;
    }
}
