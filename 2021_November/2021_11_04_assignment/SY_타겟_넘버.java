package study1107;

public class SY_타겟_넘버 {

    static int[] array;
    static int t;
    static int answer;
    
    public int solution(int[] numbers, int target) {
        array = numbers;
        t = target;
        
        recursion(0, 0);
        return answer;
    }
    
    public static void recursion(int cnt, int sum) {
        if (cnt == array.length) {
            if (sum == t) answer++;
            return;
        }
        
        recursion(cnt + 1, sum + array[cnt]);
        recursion(cnt + 1, sum - array[cnt]);
    }
}
