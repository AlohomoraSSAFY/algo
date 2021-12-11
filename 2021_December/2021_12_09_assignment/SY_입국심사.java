package study1212;

public class SY_입국심사 {
	public long solution(int n, int[] times) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for (int i = 0; i < times.length; i++) {
            if (times[i] < min) min = times[i];
            if (times[i] > max) max = times[i];
        }
        
        long left = min;
        long right = max * n;
        long answer = 0;
        while (left <= right) {
            long mid = (left + right) / 2;
            
            long cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += (mid / times[i]);
            }
            
            if (cnt >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
}
